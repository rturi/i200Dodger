package i200Dodger;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameUIViewsMainMenu {

    public static void draw (Stage gameStage){

        VBox startMenuVbox = new VBox();
        Scene startMenuScene = new Scene(startMenuVbox);
        Button startGameButton = new Button("Start game");
        Button settingsButton = new Button("Settings");
        Button highScoresButton = new Button("High scores");
        Label manual = new Label("Avoid the reds, go for the blues.\nControls: A, S, D");

        manual.setFont(Font.font(null, 16));

        startMenuVbox.getChildren().addAll(manual, startGameButton, highScoresButton, settingsButton);

        startGameButton.setOnAction(event -> {
            Game newGame = new Game();
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
