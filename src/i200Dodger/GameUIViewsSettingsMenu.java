package i200Dodger;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GameUIViewsSettingsMenu {
    public static void draw(Stage gameStage) {
        VBox settingsMenuVbox = new VBox();
        Scene settingsMenuScene = new Scene(settingsMenuVbox);
        StackPane gameFieldBackGround = new StackPane();
        GridPane gameField = new GridPane();
        Button backToStartMenuButton = new Button("Back to Start Menu");
        Slider boardWidthSlider = new Slider(2,10,Settings.getBoardWidth());
        Slider boardHeightSlider = new Slider(2,10,Settings.getBoardHeight());
        Label boardWidthLabel = new Label("columns");
        Label boardHeithtLabel = new Label("rows");
        Label sampleBoardLabel = new Label("Sample board:");

        Rectangle sampleFieldBackGround = new Rectangle(400, 400);
        sampleFieldBackGround.setFill(Color.BLACK);
        gameFieldBackGround.getChildren().addAll(sampleFieldBackGround);

        gameFieldBackGround.setPrefSize(400, 400);


        boardWidthSlider.setShowTickLabels(true);
        boardWidthSlider.setMinorTickCount(1);
        boardWidthSlider.setMajorTickUnit(2);
        boardWidthSlider.setSnapToTicks(true);

        boardHeightSlider.setShowTickLabels(true);
        boardHeightSlider.setMinorTickCount(1);
        boardHeightSlider.setMajorTickUnit(2);
        boardHeightSlider.setSnapToTicks(true);


        backToStartMenuButton.setOnAction(event -> {
            GameUIViewsMainMenu.draw(gameStage);
        });


        // Draw a sample board using current board size:
        Game game = new Game();
        for (int i = 0; i < Settings.getBoardHeight(); i++) {
            game.insertRow();
        }
        GameUI.drawGame(gameField, game);
        gameStage.show();


        boardWidthSlider.valueProperty().addListener((observable, oldSliderValue, newSliderValue) -> {

            if (newSliderValue.intValue() != Settings.getBoardWidth()) {

                Settings.setBoardWidth(newSliderValue.intValue());

                Game sampleGame = new Game();
                GridPane sampleGameField = new GridPane();


                for (int i = 0; i < Settings.getBoardHeight(); i++) {
                    sampleGame.insertRow();
                }

                Rectangle blankField = new Rectangle(400, 400);
                blankField.setFill(Color.BLACK);

                gameFieldBackGround.getChildren().addAll(blankField);

                GameUI.drawGame(sampleGameField, sampleGame);
                gameFieldBackGround.getChildren().addAll(sampleGameField);

                gameStage.show();
            }
        });


        boardHeightSlider.valueProperty().addListener((observable, oldSliderValue, newSliderValue) -> {

            if (newSliderValue.intValue() != Settings.getBoardHeight()) {

                Game sampleGame = new Game();
                GridPane sampleGameField = new GridPane();

                Settings.setBoardHeight(newSliderValue.intValue());


                for (int i = 0; i < Settings.getBoardHeight(); i++) {
                    sampleGame.insertRow();
                }

                Rectangle blankField = new Rectangle(400, 400);
                blankField.setFill(Color.BLACK);

                GameUI.drawGame(sampleGameField, sampleGame);

                gameFieldBackGround.getChildren().addAll(blankField);
                gameFieldBackGround.getChildren().addAll(sampleGameField);

                gameStage.show();
            }
        });

        gameFieldBackGround.getChildren().addAll(gameField);
        settingsMenuVbox.getChildren().addAll(backToStartMenuButton, boardWidthLabel, boardWidthSlider, boardHeithtLabel, boardHeightSlider, sampleBoardLabel,gameFieldBackGround);

        gameStage.setScene(settingsMenuScene);
    }
}
