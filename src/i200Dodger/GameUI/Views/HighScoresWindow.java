package i200Dodger.GameUI.Views;

import i200Dodger.GameUI.Buttons.MainMenuButton;
import i200Dodger.GameLogic.HighScoresDB;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class HighScoresWindow {

    public static void draw(Stage gameStage){

        VBox highScoresVBox = new VBox();
        Scene gameOverScene = new Scene(highScoresVBox);
        GridPane scoresPane = new GridPane();

        MainMenuButton goToMainMenuButton = new MainMenuButton(gameStage);


        Label tableHeaderPlace = new Label("#  ");
        tableHeaderPlace.setFont(Font.font(null, FontWeight.BOLD, 13));
        Label tableHeaderPlayerName = new Label("Name      ");
        tableHeaderPlayerName.setFont(Font.font(null, FontWeight.BOLD, 13));
        Label tableHeaderScore = new Label("Score  ");
        tableHeaderScore.setFont(Font.font(null, FontWeight.BOLD, 13));
        Label tableHeaderBoardSize = new Label("Board size  ");
        tableHeaderBoardSize.setFont(Font.font(null, FontWeight.BOLD, 13));
        Label tableHeaderGameDate = new Label("Date  ");
        tableHeaderGameDate.setFont(Font.font(null, FontWeight.BOLD, 13));


        scoresPane.add(tableHeaderPlace, 0, 0);
        scoresPane.add(tableHeaderPlayerName, 1, 0);
        scoresPane.add(tableHeaderScore, 2, 0);
        scoresPane.add(tableHeaderBoardSize, 3, 0);
        scoresPane.add(tableHeaderGameDate, 4, 0);

        String[][] highScores = HighScoresDB.getHighScores();

        for (int i = 0; i < 10; i++) {

            Label placeLabel = new Label(i+1 + "   ");
            scoresPane.add(placeLabel, 0, i+1);
            for (int j = 0; j < 4; j++) {
                Label label = new Label(highScores[i][j] + " ");
                scoresPane.add(label, j+1, i+1);
            }
        }

        highScoresVBox.getChildren().addAll(scoresPane, goToMainMenuButton);
        gameStage.setScene(gameOverScene);

    }

}