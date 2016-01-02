package i200Dodger;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Roland on 03/12/2015.
 */

public class GameUI {

    private Stage gameStage;
    private Label scoreBoard; // globalish so that it could be updated from both launchGame() and updateTimer()

    public GameUI (){

        gameStage = new Stage();
        setGameStage();
        drawStartMenu();

    }

    private void setGameStage() {

        gameStage.setOnCloseRequest(event1 -> {System.exit(0);}); // Just to make sure everything gets closed
        gameStage.setTitle("i200Dodger");

        drawStartMenu();
        gameStage.show();

    }

    private void drawStartMenu() {

        VBox startMenuVbox = new VBox();
        Scene startMenuScene = new Scene(startMenuVbox);
        Button startGameButton = new Button("Start game");
        Button settingsButton = new Button("Settings");
        Label manual = new Label("Controls: A, S, D");

        startMenuVbox.getChildren().addAll(manual, startGameButton, settingsButton);

        startGameButton.setOnAction(event -> {
            launchNewGame();
        });

        settingsButton.setOnAction(event -> {
            drawSettingsMenu();
        });

        gameStage.setScene(startMenuScene);

    }

    private void drawSettingsMenu() {

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
            drawStartMenu();
        });


        // Draw a sample board using current board size:
        Game game = new Game();
        for (int i = 0; i < Settings.getBoardHeight(); i++) {
            game.insertRow();
        }
        drawGame(gameField, game);
        gameStage.show();


        boardWidthSlider.valueProperty().addListener((observable, oldSliderValue, newSliderValue) -> {

            if (newSliderValue.intValue() != Settings.getBoardWidth()) {

                Settings.setBoardWidth(newSliderValue.intValue());
                System.out.println("set width to " + newSliderValue.intValue());

                Game sampleGame = new Game();
                GridPane sampleGameField = new GridPane();


                for (int i = 0; i < Settings.getBoardHeight(); i++) {
                   sampleGame.insertRow();
                }

                Rectangle blankField = new Rectangle(400, 400);
                blankField.setFill(Color.BLACK);

                gameFieldBackGround.getChildren().addAll(blankField);

                drawGame(sampleGameField, sampleGame);
                gameFieldBackGround.getChildren().addAll(sampleGameField);

                gameStage.show();
            }
        });


        boardHeightSlider.valueProperty().addListener((observable, oldSliderValue, newSliderValue) -> {

            if (newSliderValue.intValue() != Settings.getBoardHeight()) {

                Game sampleGame = new Game();
                GridPane sampleGameField = new GridPane();

                System.out.println("set width to " + newSliderValue.intValue());

                Settings.setBoardHeight(newSliderValue.intValue());


                for (int i = 0; i < Settings.getBoardHeight(); i++) {
                    sampleGame.insertRow();
                }

                Rectangle blankField = new Rectangle(400, 400);
                blankField.setFill(Color.BLACK);

                drawGame(sampleGameField, sampleGame);

                gameFieldBackGround.getChildren().addAll(blankField);
                gameFieldBackGround.getChildren().addAll(sampleGameField);

                gameStage.show();
            }
        });

        gameFieldBackGround.getChildren().addAll(gameField);
        settingsMenuVbox.getChildren().addAll(backToStartMenuButton, boardWidthLabel, boardWidthSlider, boardHeithtLabel, boardHeightSlider, sampleBoardLabel,gameFieldBackGround);

        gameStage.setScene(settingsMenuScene);
    }

    private void drawSampleGameField(GridPane gameField, Game sampleGame) {
    }

    private void launchNewGame() {

        Game game = new Game();


        BorderPane gamePane = new BorderPane();
        GridPane gameField = new GridPane();
        Scene gameScene = new Scene(gamePane);

        scoreBoard = new Label("Lives: " + game.getLives() + "Score: " + game.getScore());

        Button endGameButton = new Button("End");

        endGameButton.setOnAction(event2 -> {
            gameStage.close();
        });


        gamePane.setRight(endGameButton);
        gamePane.setBottom(scoreBoard);
        gamePane.setTop(gameField);

        drawGame(gameField, game);

        gameStage.setScene(gameScene);
        gameStage.show();

        setTimer(game, gameField);

        gameScene.setOnKeyPressed(keyEvent -> {  // Keyboard input for the game
            String input;
            input = keyEvent.getText();

            if (input.equals("a")){
                game.movePlayerLeft();
                game.insertRow();
                game.evaluateGame();
                if(game.isGameOver()) drawGameOverMenu(game);
                updateScoreBoard(game);
                drawGame(gameField, game);
            }

            if (input.equals("d")){
                game.movePlayerRight();
                game.insertRow();
                game.evaluateGame();
                if(game.isGameOver()) drawGameOverMenu(game);
                updateScoreBoard(game);
                drawGame(gameField, game);
            }

            if (input.equals("s")) {
                game.insertRow();
                game.evaluateGame();
                if(game.isGameOver()) drawGameOverMenu(game);
                updateScoreBoard(game);
                drawGame(gameField, game);
            }


        });

    }

    private void updateScoreBoard(Game game) {
        scoreBoard.setText("Lives: " + game.getLives() + "Score: " + game.getScore());
    }

    private void drawGame(GridPane gameField, Game game) {

        // Loop goes throug the board grid cell by cell and if the obstacle in the corresponding game board is red or blue
        // inserts a rectangle of an appropriate color

        int obstacleSideLength = (int) 400/Math.max(game.getBoardHeight(), game.getBoardWidth());

        for (int i = 0; i < game.getBoardHeight(); i++) {
            for (int j = 0; j < game.getBoardWidth(); j++) {
                Rectangle obstacleIcon = new Rectangle(obstacleSideLength, obstacleSideLength);
                gameField.add(obstacleIcon, j, i);
                if (game.getObstacle(i,j).getColor().equals("red")) obstacleIcon.setFill(Color.RED);
                if (game.getObstacle(i,j).getColor().equals("blue")) obstacleIcon.setFill(Color.BLUE);
            }
        }

        // Finally add the player icon. If there was an obstacle in the same position, the player icon covers it.
        Rectangle playerIcon = new Rectangle(obstacleSideLength,obstacleSideLength);
        playerIcon.setFill(Color.GREEN);
        gameField.add(playerIcon, game.getPlayerPosition(), game.getBoardHeight() - 1);



    }

    private void setTimer(Game game, GridPane gameField){

        /* Takes in:
            - Game (to insert a new row if the timer runs out and
        * */

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        game.insertRow();
                        game.evaluateGame();
                        if(game.isGameOver()) drawGameOverMenu(game);
                        drawGame(gameField,game);
                        updateScoreBoard(game);
                        if (!game.isGameOver()) setTimer(game, gameField); // new timer starts only when the game is not over
                    }
                });
            }
        }, 500 - game.getScore()*3);

    }

    private void drawGameOverMenu(Game game) {
        VBox gameOverVbox = new VBox();
        Scene gameOverScene = new Scene(gameOverVbox);
        Button startGameButton = new Button("Start a new game");
        Label gameOver = new Label("Game over. Your score was " + game.getScore() + ". Try again.");
        Button goToStartMenuButton = new Button("Go to start menu");
        Label playerNameLabel = new Label("Your name");
        TextField playerNameField = new TextField(game.getPlayerName());
        Button saveHighScoreButton = new Button("save");

        if(HighScores.isHighScore(game.getScore()))
            gameOverVbox.getChildren().addAll(gameOver, playerNameLabel, playerNameField, saveHighScoreButton, startGameButton, goToStartMenuButton);
        else
            gameOverVbox.getChildren().addAll(gameOver, startGameButton, goToStartMenuButton);

        startGameButton.setOnAction(event -> {
            launchNewGame();
        });

        goToStartMenuButton.setOnAction(event -> {
            drawStartMenu();
        });

        saveHighScoreButton.setOnAction(event -> {
            game.setPlayerName(playerNameField.getText());
        });

        gameStage.setScene(gameOverScene);
    }

}
