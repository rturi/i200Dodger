package i200Dodger.GameUI.Views;

import i200Dodger.GameUI.Buttons.MainMenuButton;
import i200Dodger.GameLogic.HighScoresDB;
import i200Dodger.GameUI.Elements.GameLabel;
import i200Dodger.GameUI.Elements.GameLabelBold;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HighScoresWindow {

    public static void draw(Stage gameStage){

        VBox highScoresVBox = new VBox();
        Scene gameOverScene = new Scene(highScoresVBox);
        GridPane scoresPane = new GridPane();

        MainMenuButton goToMainMenuButton = new MainMenuButton(gameStage);


        GameLabelBold tableHeaderPlace = new GameLabelBold("#  ");
        GameLabelBold tableHeaderPlayerName = new GameLabelBold("Name      ");
        GameLabelBold tableHeaderScore = new GameLabelBold("Score  ");
        GameLabelBold tableHeaderBoardSize = new GameLabelBold("Board size  ");
        GameLabelBold tableHeaderGameDate = new GameLabelBold("Date  ");


        scoresPane.add(tableHeaderPlace, 0, 0);
        scoresPane.add(tableHeaderPlayerName, 1, 0);
        scoresPane.add(tableHeaderScore, 2, 0);
        scoresPane.add(tableHeaderBoardSize, 3, 0);
        scoresPane.add(tableHeaderGameDate, 4, 0);

        String[][] highScores = HighScoresDB.getHighScores();

        for (int i = 0; i < 10; i++) {

            GameLabel placeLabel = new GameLabel(i+1 + "   ");
            scoresPane.add(placeLabel, 0, i+1);
            for (int j = 0; j < 4; j++) {
                GameLabel label = new GameLabel(highScores[i][j] + " ");
                scoresPane.add(label, j+1, i+1);
            }
        }

        highScoresVBox.getChildren().addAll(scoresPane, goToMainMenuButton);
        gameStage.setScene(gameOverScene);

    }

}
