package i200Dodger.GameUI.Elements;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * Created by Roland on 08/01/2016.
 */
public class GameButton extends javafx.scene.control.Button {

    public GameButton() {

        this.setFont(Font.font(null, 14));

    }

    public GameButton(String buttonName) {

        this.setText(buttonName);
        this.setFont(Font.font(null, 14));

    }

}
