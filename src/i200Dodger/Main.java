package i200Dodger;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Roland on 03/12/2015.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //HighScoresDB.createTable();
        GameUI gameUI = new GameUI();
        //System.exit(0);
    }
}
