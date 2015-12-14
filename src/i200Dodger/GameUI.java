package i200Dodger;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Roland on 03/12/2015.
 */
public class GameUI {

    private Stage gameStage;
    private Label scoreBoard; // globalish so that

    public GameUI (){

        gameStage = new Stage();

        setGameStage();

        drawStartMenu();

    }

    private void setGameStage() {

        gameStage.setOnCloseRequest(event1 -> {System.exit(0);}); // Just to make sure everything gets closed
        gameStage.setTitle("i200Dodger");


        drawStartMenu();
        gameStage.show();

    }

    private void drawStartMenu() {

        VBox startMenuVbox = new VBox();
        Scene startMenuScene = new Scene(startMenuVbox);
        Button startGameButton = new Button("Start");
        Label manual = new Label("Use A, S nad D to control");
        startMenuVbox.getChildren().addAll(manual, startGameButton);

        startGameButton.setOnAction(event -> {
            launchNewGame();
        });

        gameStage.setScene(startMenuScene);
    }

    private void launchNewGame() {

        Game game = new Game();


        BorderPane gamePane = new BorderPane();
        GridPane gameField = new GridPane();
        Scene gameScene = new Scene(gamePane);

        scoreBoard = new Label("Lives: " + game.getLives() + "Score: " + game.getScore());

        Button endGameButton = new Button("End");

        endGameButton.setOnAction(event2 -> {
            gameStage.close();
        });


        gamePane.setRight(endGameButton);
        gamePane.setBottom(scoreBoard);
        gamePane.setTop(gameField);

        drawGame(gameField, game);

        gameStage.setScene(gameScene);
        gameStage.show();

        setTimer(game, gameField);

        gameScene.setOnKeyPressed(keyEvent -> {  // Keyboard input for the game
            String input;
            input = keyEvent.getText();
            System.out.println(input);

            if (input.equals("a")){
                game.movePlayerLeft();
                game.insertRow(game);
                game.evaluateGame();
                if(game.isGameOver(game)) drawGameOverMenu(game);
                updateScoreBoard(game);
                drawGame(gameField, game);
            }

            if (input.equals("d")){
                game.movePlayerRight();
                game.insertRow(game);
                game.evaluateGame();
                if(game.isGameOver(game)) drawGameOverMenu(game);
                updateScoreBoard(game);
                drawGame(gameField, game);
            }

            if (input.equals("s")) {
                game.insertRow(game);
                game.evaluateGame();
                if(game.isGameOver(game)) drawGameOverMenu(game);
                updateScoreBoard(game);
                drawGame(gameField, game);
            }


        });

    }

    private void updateScoreBoard(Game game) {
        scoreBoard.setText("Lives: " + game.getLives() + "Score: " + game.getScore());
    }

    private void drawGame(GridPane gameField, Game game) {

        // Loop goes throug the board grid cell by cell and if the obstacle in the corresponding game board is red or blue
        // inserts a rectangle of an appropriate color
        for (int i = 0; i < game.getBoardHeight(); i++) {
            for (int j = 0; j < game.getBoardWidth(); j++) {
                Rectangle obstacleIcon = new Rectangle(50, 50);
                gameField.add(obstacleIcon, j, i);
                if (game.getObstacle(i,j).getColor().equals("red")) obstacleIcon.setFill(Color.RED);
                if (game.getObstacle(i,j).getColor().equals("blue")) obstacleIcon.setFill(Color.BLUE);
            }
        }

        // Finally add the player icon. If there was an obstacle in the same position, the player icon covers it.
        Rectangle playerIcon = new Rectangle(50,50);
        playerIcon.setFill(Color.GREEN);
        gameField.add(playerIcon, game.getPlayerPosition(), game.getBoardHeight() - 1);

    }

    private void setTimer(Game game, GridPane gameField){

        /* Takes in:
            - Game (to insert a new row if the timer runs out and
        * */

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        game.insertRow(game);
                        game.evaluateGame();
                        if(game.isGameOver(game)) drawGameOverMenu(game);
                        drawGame(gameField,game);
                        updateScoreBoard(game);
                        if (game.getLives() > 0) setTimer(game, gameField); // new timer starts only when the game is not over
                    }
                });
            }
        }, 500 - game.getScore()*3);

    }

    private void drawGameOverMenu(Game game) {
        VBox gameOverVbox = new VBox();
        Scene gameOverScene = new Scene(gameOverVbox);
        Button startGameButton = new Button("Start");
        Label mocking = new Label("Game over. Your score was " + game.getScore() + ". Try again.");
        gameOverVbox.getChildren().addAll(mocking, startGameButton);

        startGameButton.setOnAction(event -> {
            launchNewGame();
        });

        gameStage.setScene(gameOverScene);
    }

}
