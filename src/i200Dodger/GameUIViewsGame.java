package i200Dodger;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;


public class GameUIViewsGame {

    public static void draw(Stage gameStage, Game game) {

        BorderPane gamePane = new BorderPane();
        GridPane gameField = new GridPane();
        Scene gameScene = new Scene(gamePane);
        Label scoreBoard = new Label("Lives: " + game.getLives() + "Score: " + game.getScore());

        Button endGameButton = new Button("End");

        endGameButton.setOnAction(event2 -> {
            gameStage.close();
        });


        gamePane.setRight(endGameButton);
        gamePane.setBottom(scoreBoard);
        gamePane.setTop(gameField);

        GameUI.drawGame(gameField, game);

        gameStage.setScene(gameScene);
        gameStage.show();

        setTimer(gameStage, game, gameField, scoreBoard);

        gameScene.setOnKeyPressed(keyEvent -> {  // Keyboard input for the game
            String input;
            input = keyEvent.getText();

            if (input.equals("a")){
                game.movePlayerLeft();
                game.insertRow();
                game.evaluateGame();
                if(game.isGameOver()) GameUIViewsGameOverMenu.draw(gameStage, game);
                scoreBoard.setText("Lives: " + game.getLives() + "Score: " + game.getScore());
                GameUI.drawGame(gameField, game);
            }

            if (input.equals("d")){
                game.movePlayerRight();
                game.insertRow();
                game.evaluateGame();
                if(game.isGameOver()) GameUIViewsGameOverMenu.draw(gameStage, game);
                scoreBoard.setText("Lives: " + game.getLives() + "Score: " + game.getScore());
                GameUI.drawGame(gameField, game);
            }

            if (input.equals("s")) {
                game.insertRow();
                game.evaluateGame();
                if(game.isGameOver()) GameUIViewsGameOverMenu.draw(gameStage, game);
                scoreBoard.setText("Lives: " + game.getLives() + "Score: " + game.getScore());
                GameUI.drawGame(gameField, game);
            }

        });

    }

    private static void setTimer(Stage gameStage, Game game, GridPane gameField, Label scoreBoard) {

        /* Takes in:
        - Game (to insert a new row if the timer runs out and
                * */
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        game.insertRow();
                        game.evaluateGame();
                        if (game.isGameOver()) GameUIViewsGameOverMenu.draw(gameStage, game);
                        GameUI.drawGame(gameField, game);
                        updateScoreBoard(game, scoreBoard);
                        if (!game.isGameOver())
                            setTimer(gameStage, game, gameField, scoreBoard); // new timer starts only when the game is not over
                    }
                });
            }
        }, 500 - game.getScore() * 3);
    }

    private static void updateScoreBoard(Game game, Label scoreBoard) {
        scoreBoard.setText("Lives: " + game.getLives() + "Score: " + game.getScore());
    }

}
