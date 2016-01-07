package i200Dodger;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;


public class GameUIViewsGame {

    public static void draw(Stage gameStage, Game game) {

        BorderPane gamePane = new BorderPane();
        GridPane gameField = new GridPane();
        Scene gameScene = new Scene(gamePane);
        Label scoreBoard = new Label();


        // Change score boards font and set it up with initial values.
        scoreBoard.setFont(Font.font(null, FontWeight.BOLD, 20));
        updateScoreBoard(game,scoreBoard);

        GameUI.drawGame(gameField, game);

        // Position UI elements
        gamePane.setTop(scoreBoard);
        gamePane.setCenter(gameField);
        gameStage.setScene(gameScene);
        gameStage.show();

        // Begin the timer that automatically inserts a new row periodically. Interval shortens as score increases.
        setTimer(gameStage, game, gameField, scoreBoard);


        /* Keyboard listener. If WASD or space is pressed player position is changed accordingly, new row is inserted
         * game is evaluated (score++ or lives-- if needed) and if isGameOver check returns false the view changes to
         * the game over menu. */

        gameScene.setOnKeyPressed(keyEvent -> {  // Keyboard input for the game
            String input;
            input = keyEvent.getText();
            if (input.equals("a")){
                game.movePlayerLeft();
                game.insertRow();
                game.evaluateGame();
                if(game.isGameOver()) GameUIViewsGameOverMenu.draw(gameStage, game);
                updateScoreBoard(game,scoreBoard);
                GameUI.drawGame(gameField, game);
            }

            if (input.equals("d")){
                game.movePlayerRight();
                game.insertRow();
                game.evaluateGame();
                if(game.isGameOver()) GameUIViewsGameOverMenu.draw(gameStage, game);
                updateScoreBoard(game,scoreBoard);
                GameUI.drawGame(gameField, game);
            }

            if (input.equals("s") || input.equals("w") || input.equals(" ")) {
                game.insertRow();
                game.evaluateGame();
                if(game.isGameOver()) GameUIViewsGameOverMenu.draw(gameStage, game);
                updateScoreBoard(game,scoreBoard);
                GameUI.drawGame(gameField, game);
            }

        });

    }

    private static void setTimer(Stage gameStage, Game game, GridPane gameField, Label scoreBoard) {

        /* Starts a timer and when the timer runs out starts a new timer that starts another timer until game is over.
        * Takes in a punch of parameters so it can update the game score and update the game board when every timer
        * runs out.
        * "500 - game.getScore() * 3" in the end means that at first timer interval is 0.5 seconds. Each time game score
        * increases by one point, timer interval gets decreases by 3 milliseconds.
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
        scoreBoard.setText("Lives: " + game.getLives() + "    Score: " + game.getScore());
    }

}
