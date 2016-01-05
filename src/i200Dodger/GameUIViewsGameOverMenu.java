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
        Label playerNameLabel = new Label("Your name");
        TextField playerNameField = new TextField(Settings.getPlayerName());
        Button saveHighScoreButton = new Button("save");

        if (HighScoresDB.isHighScore(game.getScore())) {
            gameOverVbox.getChildren().addAll(gameOver, playerNameLabel, playerNameField, saveHighScoreButton, startGameButton, goToMainMenuButton);
            HighScoresDB.insertHighScore(Settings.getPlayerName(), game.getScore(), Settings.getBoardHeight(), Settings.getBoardWidth(), game.getGameEndDate());
            HighScoresDB.getHighScores();
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
            Settings.setPlayerName(playerNameField.getText());
        });

        gameStage.setScene(gameOverScene);
    }

}
