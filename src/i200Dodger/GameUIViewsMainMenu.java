package i200Dodger;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameUIViewsMainMenu {

    public static void draw (Stage gameStage){

    VBox startMenuVbox = new VBox();
    Scene startMenuScene = new Scene(startMenuVbox);
    Button startGameButton = new Button("Start game");
    Button settingsButton = new Button("Settings");
    Label manual = new Label("Controls: A, S, D");

    startMenuVbox.getChildren().addAll(manual, startGameButton, settingsButton);

    startGameButton.setOnAction(event -> {
        Game newGame = new Game();
        GameUIViewsGame.draw(gameStage, newGame);
    });

    settingsButton.setOnAction(event -> {
        GameUIViewsSettingsMenu.draw(gameStage);
    });

    gameStage.setScene(startMenuScene);
    }
}
