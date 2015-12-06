package i200Dodger;

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

/**
 * Created by Roland on 03/12/2015.
 */
public class GameUI {

    Stage gameStage;

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
        Label gameScore = new Label("Lives: " + game.getLives() + "Score: " + game.getScore());

        Button endGameButton = new Button("End");

        endGameButton.setOnAction(event2 -> {
            gameStage.close();
        });


        gamePane.setRight(endGameButton);
        gamePane.setBottom(gameScore);
        gamePane.setTop(gameField);

        drawGame(gameField, game);

        gameStage.setScene(gameScene);
        gameStage.show();

        gameScene.setOnKeyPressed(keyEvent -> {  // Keyboard input for the game
            String input;
            input = keyEvent.getText();
            System.out.println(input);

            if (input.equals("a")){
                game.movePlayerLeft();
                game.insertRow();
                game.evaluateGame();
                drawGame(gameField, game);
            }

            if (input.equals("d")){
                game.movePlayerRight();
                game.insertRow();
                game.evaluateGame();
                drawGame(gameField, game);
            }

            if (input.equals("s")) {
                game.insertRow();
                game.evaluateGame();
                drawGame(gameField, game);
            }

        });




        // Initiate timer
        // Listen to keyboard

        // When timer runs out insert row
        // When control keys are pressed insert row

        // Reset timer
        
        // Draw board or game over
        // Game.evaluateGame(game);
        // Draw board or game over






    }

    private void drawGame(GridPane gameField, Game game) {



        for (int i = 0; i < game.getBoardHeight(); i++) {
            for (int j = 0; j < game.getBoardWidth(); j++) {
                Rectangle obstacleIcon = new Rectangle(50, 50);
                gameField.add(obstacleIcon, j, i);
                if (game.getObstacle(i,j).getColor().equals("red")) obstacleIcon.setFill(Color.RED);
                if (game.getObstacle(i,j).getColor().equals("blue")) obstacleIcon.setFill(Color.BLUE);
            }
        }


        Rectangle playerIcon = new Rectangle(50,50);
        playerIcon.setFill(Color.GREEN);


        gameField.add(playerIcon, game.getPlayerPosition(), game.getBoardHeight() - 1);



    }

}
