package i200Dodger.GameUI.Views;

import i200Dodger.GameLogic.Game;
import i200Dodger.GameUI.Buttons.StartGameButton;
import i200Dodger.GameUI.Buttons.MainMenuButton;
import i200Dodger.GameUI.Elements.GameButton;
import i200Dodger.GameLogic.HighScoresDB;
import i200Dodger.GameUI.Elements.GameLabel;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameOverMenu {

    public static void draw(Stage gameStage, Game game) {

        VBox gameOverVbox = new VBox();
        Scene gameOverScene = new Scene(gameOverVbox);

        GameLabel gameOver = new GameLabel("Game over. Your score was " + game.getScore() + ". Try again.");
        StartGameButton startGameButton = new StartGameButton(gameStage);
        MainMenuButton goToMainMenuButton = new MainMenuButton(gameStage);
        GameButton saveHighScoreButton = new GameButton("Save high score");


        // If the game score was good enough to get to the top ten, the "Save high score" button is shown.
        if (HighScoresDB.isHighScore(game.getScore())) {
            gameOverVbox.getChildren().addAll(gameOver, saveHighScoreButton, startGameButton, goToMainMenuButton);
        } else
            gameOverVbox.getChildren().addAll(gameOver, startGameButton, goToMainMenuButton);


        saveHighScoreButton.setOnAction(event -> {
            NameInputWindow.draw(gameStage, game);
        });

        gameStage.setScene(gameOverScene);
    }

}
