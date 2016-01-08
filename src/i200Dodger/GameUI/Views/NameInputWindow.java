package i200Dodger.GameUI.Views;

import i200Dodger.GameLogic.Game;
import i200Dodger.GameLogic.HighScoresDB;
import i200Dodger.GameLogic.Settings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class NameInputWindow {

    /* Used when player saves a high score.
    * error message is an internal variable to display error messages when player inserts unsuitable names*/

    private static Label errorMessage;

    public static void draw(Stage gameStage, Game game) {

        VBox nameInputVBox = new VBox();
        Scene nameInputScene = new Scene(nameInputVBox);
        Label nameLabel = new Label("Enter your name");
        TextField playerNameField = new TextField(Settings.getPlayerName());
        Button scoreSaveButton = new Button("Save");

        playerNameField.setFont(Font.font(null, 16));
        nameLabel.setFont(Font.font(null, 16));

        errorMessage = new Label();
        errorMessage.setFont(Font.font(null, 16));
        errorMessage.setTextFill(Color.RED);

        nameInputVBox.getChildren().addAll(nameLabel, playerNameField, scoreSaveButton, errorMessage);

        gameStage.setScene(nameInputScene);

        scoreSaveButton.setOnAction(event -> {

            if (isLegalName(playerNameField.getText())){
                Settings.setPlayerName(playerNameField.getText());
                HighScoresDB.insertHighScore(Settings.getPlayerName(), game.getScore(), Settings.getBoardHeight(), Settings.getBoardWidth(), game.getGameEndDate());
                HighScoresWindow.draw(gameStage);
            }
        });

    }

    private static boolean isLegalName(String name) {

        if (name.length() == 0){
            errorMessage.setText("Please enter a name");
            return false;
        }

        if(name.length() > 30) {
            errorMessage.setText("Too long (max 30 chars)");
            return false;
        }

        String tempString = name.replaceAll("[^A-Za-z0-9\\sÕÄÖÜŠŽšžõäöü]+","");

        if(name.length() > tempString.length()) {

            Character guiltyChar = name.charAt(0);

            for (int i = 0; i < tempString.length(); i++) {
                System.out.println(name.charAt(i) + " " + tempString.charAt(i));

                if (name.charAt(i) != tempString.charAt(i)) {
                    guiltyChar = name.charAt(i);
                    break;
                } else guiltyChar = name.charAt(i + 1);
            }

            errorMessage.setText("Illegal character: " + guiltyChar);

            return false;
        }

        return true;
    }

}
