package i200Dodger.GameUI.Buttons;

import i200Dodger.GameUI.Elements.GameButton;
import i200Dodger.GameUI.Views.MainMenu;
import javafx.stage.Stage;


public class MainMenuButton extends GameButton {

    public MainMenuButton(Stage gameStage) {

        this.setText("Go to main menu");

        this.setOnAction(event -> {
            MainMenu.draw(gameStage);
        });
    }

}
