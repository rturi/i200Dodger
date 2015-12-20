package i200Dodger;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Roland on 03/12/2015.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Settings.setBoardWidth(5);
        Settings.setBoardHeight(9);
        GameUI gameUI = new GameUI();
        //System.exit(0);
    }
}
