package i200Dodger.GameUI.Views;

import i200Dodger.GameLogic.Game;
import i200Dodger.GameLogic.Settings;
import i200Dodger.GameUI.Buttons.MainMenuButton;
import i200Dodger.GameUI.Elements.GameLabel;
import i200Dodger.GameUI.Utils.DrawGame;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class SettingsMenu {

    public static void draw(Stage gameStage) {

        VBox settingsMenuVbox = new VBox();
        Scene settingsMenuScene = new Scene(settingsMenuVbox);
        AnchorPane gameFieldBackGround = new AnchorPane();
        GridPane gameField = new GridPane();
        MainMenuButton backToStartMenuButton = new MainMenuButton(gameStage);
        Slider boardWidthSlider = new Slider(2,10, Settings.getBoardWidth());
        Slider boardHeightSlider = new Slider(2,10,Settings.getBoardHeight());
        GameLabel boardWidthLabel = new GameLabel("columns");
        GameLabel boardHeightLabel = new GameLabel("rows");
        GameLabel sampleBoardLabel = new GameLabel("Sample board:");

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

        // Draw a sample board using current board size:
        Game game = new Game();
        for (int i = 0; i < Settings.getBoardHeight()-1; i++) {
            game.insertRow();
        }

        DrawGame.draw(gameField, game);

        gameFieldBackGround.setBottomAnchor(gameField, 0.0);

        if (game.getBoardHeight() > game.getBoardWidth()) {
            gameFieldBackGround.setLeftAnchor(gameField, (double) (game.getBoardHeight() - game.getBoardWidth()) * (int) 400 / game.getBoardHeight() / 2);

        }

        gameFieldBackGround.getChildren().addAll(gameField);
        settingsMenuVbox.getChildren().addAll(backToStartMenuButton, boardWidthLabel, boardWidthSlider, boardHeightLabel, boardHeightSlider, sampleBoardLabel,gameFieldBackGround);


        /* Slider listeners. When sliders are set to a new integer value then listeners generate a new game with the new
        * dimensions and draw a new sample game board.*/

        boardWidthSlider.valueProperty().addListener((observable, oldSliderValue, newSliderValue) -> {

            if (newSliderValue.intValue() != Settings.getBoardWidth()) {

                Settings.setBoardWidth(newSliderValue.intValue());

                Game sampleGame = new Game();
                GridPane sampleGameField = new GridPane();


                for (int i = 0; i < Settings.getBoardHeight()-1; i++) {
                    sampleGame.insertRow();
                }

                Rectangle blankField = new Rectangle(400, 400);
                blankField.setFill(Color.BLACK);

                DrawGame.draw(sampleGameField, sampleGame);

                gameFieldBackGround.setBottomAnchor(sampleGameField, 0.0);

                // If the game board is not a square, then the sample board needs to be centered on the 400x400 background.
                if (sampleGame.getBoardHeight() > sampleGame.getBoardWidth()) {
                    gameFieldBackGround.setLeftAnchor(sampleGameField, (double) (sampleGame.getBoardHeight()-sampleGame.getBoardWidth()) * (int) 400 / sampleGame.getBoardHeight() / 2);
                }

                gameFieldBackGround.getChildren().addAll(blankField, sampleGameField);

            }
        });

        boardHeightSlider.valueProperty().addListener((observable, oldSliderValue, newSliderValue) -> {

            if (newSliderValue.intValue() != Settings.getBoardHeight()) {

                Settings.setBoardHeight(newSliderValue.intValue());

                Game sampleGame = new Game();
                GridPane sampleGameField = new GridPane();

                for (int i = 0; i < Settings.getBoardHeight() - 1; i++) {
                    sampleGame.insertRow();
                }

                Rectangle blankField = new Rectangle(400, 400);
                blankField.setFill(Color.BLACK);

                DrawGame.draw(sampleGameField, sampleGame);

                gameFieldBackGround.setBottomAnchor(sampleGameField, 0.0);

                if (sampleGame.getBoardHeight() > sampleGame.getBoardWidth()) {
                    gameFieldBackGround.setLeftAnchor(sampleGameField, (double) (sampleGame.getBoardHeight() - sampleGame.getBoardWidth()) * (int) 400 / sampleGame.getBoardHeight() / 2);

                }
                gameFieldBackGround.getChildren().addAll(blankField, sampleGameField);

            }
        });


        gameStage.setScene(settingsMenuScene);
    }
}
