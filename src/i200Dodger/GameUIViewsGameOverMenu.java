package i200Dodger;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GameUIViewsGameOverMenu {

    public static void draw(Stage gameStage, Game game) {

        VBox gameOverVbox = new VBox();
        Scene gameOverScene = new Scene(gameOverVbox);
        Button startGameButton = new Button("Start a new game");
        Label gameOver = new Label("Game over. Your score was " + game.getScore() + ". Try again.");
        Button goToMainMenuButton = new Button("Go to main menu");
        Button saveHighScoreButton = new Button("Save high score");

        gameOver.setFont(Font.font(null, 16));

        // If the game score was good enough to get to the top ten, the "Save high score" button is shown.
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
