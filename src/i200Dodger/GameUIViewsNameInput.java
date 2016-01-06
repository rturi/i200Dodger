package i200Dodger;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Roland on 06/01/2016.
 */
public class GameUIViewsNameInput {

    private static Label errorMessage;

    public static void draw(Stage gameStage, Game game) {

        VBox nameInputVBox = new VBox();
        Scene nameInputScene = new Scene(nameInputVBox);
        Label nameLabel = new Label("Enter your name");
        TextField playerNameField = new TextField(Settings.getPlayerName());
        Button scoreSaveButton = new Button("Save");
        errorMessage = new Label();
        errorMessage.setTextFill(Color.RED);

        nameInputVBox.getChildren().addAll(nameLabel, playerNameField, scoreSaveButton, errorMessage);

        gameStage.setScene(nameInputScene);

        scoreSaveButton.setOnAction(event -> {

            if (isLegalName(playerNameField.getText())){
                Settings.setPlayerName(playerNameField.getText());
                HighScoresDB.insertHighScore(Settings.getPlayerName(), game.getScore(), Settings.getBoardHeight(), Settings.getBoardWidth(), game.getGameEndDate());
                GameUIViewsHighScores.draw(gameStage);
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

        if(name.length() > name.replaceAll("[^A-Za-z0-9\\sÕÄÖÜŠŽšžõäöü]+","").length()) {
            errorMessage.setText("Illegal character: ");
            return false;
        }

        return true;
    }

}
