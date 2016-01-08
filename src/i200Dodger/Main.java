package i200Dodger;

import i200Dodger.GameLogic.HighScoresDB;
import i200Dodger.GameLogic.Settings;
import i200Dodger.GameUI.Views.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage gameStage = new Stage();

        gameStage.setOnCloseRequest(event1 -> {
            System.exit(0);
        }); // Just to make sure everything gets closed
        gameStage.setTitle("i200Dodger");

        MainMenu.draw(gameStage);

        HighScoresDB.closeDBConnection(HighScoresDB.openDBconnection());

        gameStage.show();

    }
}
