package i200Dodger.GameUI.Buttons;

import i200Dodger.GameLogic.Game;
import i200Dodger.GameUI.Elements.GameButton;
import i200Dodger.GameUI.Views.GameWindow;
import javafx.stage.Stage;

public class StartGameButton extends GameButton{

    public StartGameButton(Stage gameStage){

        this.setText("Start a new game");

        this.setOnAction(event -> {
            Game newGame = new Game();
            GameWindow.draw(gameStage, newGame);
        });

    }

}
