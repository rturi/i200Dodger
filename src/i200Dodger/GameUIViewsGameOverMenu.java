package i200Dodger;

import i200Dodger.HighScores;
import i200Dodger.Settings;
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
        Button goToStartMenuButton = new Button("Go to start menu");
        Label playerNameLabel = new Label("Your name");
        TextField playerNameField = new TextField(Settings.getPlayerName());
        Button saveHighScoreButton = new Button("save");

        if (HighScores.isHighScore(game.getScore()))
            gameOverVbox.getChildren().addAll(gameOver, playerNameLabel, playerNameField, saveHighScoreButton, startGameButton, goToStartMenuButton);
        else
            gameOverVbox.getChildren().addAll(gameOver, startGameButton, goToStartMenuButton);

        startGameButton.setOnAction(event -> {
            Game newGame = new Game();
            GameUIViewsGame.draw(gameStage, newGame);
        });

        goToStartMenuButton.setOnAction(event -> {
            GameUIViewsMainMenu.draw(gameStage);
        });

        saveHighScoreButton.setOnAction(event -> {
            Settings.setPlayerName(playerNameField.getText());
        });

        gameStage.setScene(gameOverScene);
    }

}
