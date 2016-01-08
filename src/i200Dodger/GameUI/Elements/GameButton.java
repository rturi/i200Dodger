package i200Dodger.GameUI.Elements;

import javafx.scene.text.Font;

public class GameButton extends javafx.scene.control.Button {

    public GameButton() {

        this.setFont(Font.font(null, 14));
        this.setMaxWidth(Double.MAX_VALUE);

    }

    public GameButton(String buttonName) {

        this.setText(buttonName);
        this.setFont(Font.font(null, 14));
        this.setMaxWidth(Double.MAX_VALUE);

    }

}
