package i200Dodger;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;


public class GameUIViewsMainMenu {

    public static void draw (Stage gameStage){

        VBox startMenuVbox = new VBox();
        Scene startMenuScene = new Scene(startMenuVbox);
        Button startGameButton = new Button("Start game");
        Button settingsButton = new Button("Settings");
        Button highScoresButton = new Button("High scores");
        Label manual = new Label("Controls: A, S, D");

        startMenuVbox.getChildren().addAll(manual, startGameButton, highScoresButton, settingsButton);

        startGameButton.setOnAction(event -> {
            Game newGame = new Game();
            Timer timer = new Timer();
            GameUIViewsGame.draw(gameStage, newGame);
        });

        settingsButton.setOnAction(event -> {
            GameUIViewsSettingsMenu.draw(gameStage);
        });

        highScoresButton.setOnAction(event -> {
            GameUIViewsHighScores.draw(gameStage);
        });

        gameStage.setScene(startMenuScene);
    }
}
