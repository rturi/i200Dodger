package i200Dodger;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameUIViewsGameOverMenu {

    public static void draw(Stage gameStage, Game game) {

        VBox gameOverVbox = new VBox();
        Scene gameOverScene = new Scene(gameOverVbox);
        Button startGameButton = new Button("Start a new game");
        Label gameOver = new Label("Game over. Your score was " + game.getScore() + ". Try again.");
        Button goToMainMenuButton = new Button("Go to main menu");
        Button saveHighScoreButton = new Button("Save high score");

        if (HighScoresDB.isHighScore(game.getScore())) {
            gameOverVbox.getChildren().addAll(gameOver, saveHighScoreButton, startGameButton, goToMainMenuButton);
        } else
            gameOverVbox.getChildren().addAll(gameOver, startGameButton, goToMainMenuButton);

        startGameButton.setOnAction(event -> {
            Game newGame = new Game();
            GameUIViewsGame.draw(gameStage, newGame);
        });

        goToMainMenuButton.setOnAction(event -> {
            GameUIViewsMainMenu.draw(gameStage);
        });

        saveHighScoreButton.setOnAction(event -> {
            GameUIViewsNameInput.draw(gameStage, game);
        });

        gameStage.setScene(gameOverScene);
    }

}
