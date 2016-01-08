package i200Dodger.GameUI.Elements;

import javafx.scene.control.Label;
import javafx.scene.text.Font;


public class GameLabel extends Label {

    public GameLabel(String labelName){

        this.setText(labelName);
        this.setFont(Font.font(null, 20));

    }
}
