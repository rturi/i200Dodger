package i200Dodger.settings;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Created by Roland on 08/01/2016.
 */
public class GameUIElementsLabel extends Label {


    public GameUIElementsLabel(String labelName){

        this.setFont(Font.font(null, 16));
        this.setText(labelName);

    }

}
