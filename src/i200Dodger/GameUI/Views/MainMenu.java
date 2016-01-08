package i200Dodger.GameUI.Views;

import i200Dodger.GameUI.Buttons.StartGameButton;
import i200Dodger.GameUI.Elements.GameButton;
import i200Dodger.GameUI.Elements.GameLabel;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    public static void draw (Stage gameStage){

        VBox startMenuVbox = new VBox();
        Scene startMenuScene = new Scene(startMenuVbox);

        StartGameButton startGameButton = new StartGameButton(gameStage);
        GameButton settingsButton = new GameButton("Settings");
        GameButton highScoresButton = new GameButton("High scores");
        GameLabel manual = new GameLabel("Avoid the reds, go for the blues.\nControls: A, S, D");


        startMenuVbox.getChildren().addAll(manual, startGameButton, highScoresButton, settingsButton);


        settingsButton.setOnAction(event -> {
            SettingsMenu.draw(gameStage);
        });

        highScoresButton.setOnAction(event -> {
            HighScoresWindow.draw(gameStage);
        });

        gameStage.setScene(startMenuScene);
    }
}
