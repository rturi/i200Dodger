package i200Dodger.GameUI.Elements;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameLabelBold extends Label {

    public GameLabelBold(String labelName){

        this.setText(labelName);
        this.setFont(Font.font(null, FontWeight.BOLD, 16));

    }

}
