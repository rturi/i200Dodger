import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import java.util.Random;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.Timer;
import java.util.TimerTask;

public class Dodger extends Application{

    Stage gameStage = new Stage();
    Label gameScore = new Label();
    Game game;
    Timer timer;


    @Override
    public void start(Stage primaryStage) throws Exception {

        setGameStage();


    }

    //opens the program window
    private void setGameStage() {

        gameStage.setOnCloseRequest(event1 -> {System.exit(0);}); // Just to make sure everything gets closed
        gameStage.setTitle("i200Dodger");


        drawStartMenu();
        gameStage.show();

    }

    private void drawStartMenu() {

        VBox startMenuVbox = new VBox();
        Scene startMenuScene = new Scene(startMenuVbox);
        Button startGameButton = new Button("Start");
        Label manual = new Label("Use A, S nad D to control");
        startMenuVbox.getChildren().addAll(manual, startGameButton);

        startGameButton.setOnAction(event -> {
            launchNewGame();
        });

        gameStage.setScene(startMenuScene);
    }

    private void launchNewGame() {

        game = new Game();
        game.initiateGame(7, 5);



        BorderPane gamePane = new BorderPane();
        GridPane gameField = new GridPane();
        Scene gameScene = new Scene(gamePane);

        gameScore = new Label("Lives: 3 Score: 0");

        Button endGameButton = new Button("End");

        gamePane.setRight(endGameButton);
        gamePane.setBottom(gameScore);
        gamePane.setTop(gameField);

        game.drawGame(gameField);

        gameStage.setScene(gameScene);
        gameStage.show();


        endGameButton.setOnAction(event2 -> {
            gameStage.close();
        });


        timer = new Timer();


        gameScene.setOnKeyPressed(keyEvent -> {  // Keyboard input for the game
            String input;
            input = keyEvent.getText();
            System.out.println(input);

            if (input.equals("a")){
                game.player.movePlayer("left");
                game.board.insertRow();
                evaluateGame();
                game.drawGame(gameField);
            }

            if (input.equals("d")){
                game.player.movePlayer("right");
                game.board.insertRow();
                evaluateGame();
                game.drawGame(gameField);
            }

            if (input.equals("s")) {
                game.board.insertRow();
                evaluateGame();
                game.drawGame(gameField);
            }

        });

    }

    private void evaluateGame() {

        if(game.board.obstacleSet[game.board.obstacleSet.length-1][game.player.position].color.equals("red")) {
            game.player.lives--;
        }

        if(game.board.obstacleSet[game.board.obstacleSet.length-1][game.player.position].color.equals("blue")) {
            game.player.score++;
        }
        if (game.player.lives == 0){
            drawGameOverMenu();
        }

    }

    private void drawGameOverMenu() {
        VBox gameOverVbox = new VBox();
        Scene gameOverScene = new Scene(gameOverVbox);
        Button startGameButton = new Button("Start");
        Label mocking = new Label("Game over. Try again.");
        gameOverVbox.getChildren().addAll(mocking, startGameButton);

        startGameButton.setOnAction(event -> {
            launchNewGame();
        });

        gameStage.setScene(gameOverScene);
    }

    public static class Obstacle {

        String color = "unset";

        public void setColor(String inputColor) {
            color = inputColor;
        }

        public String getColor() {

            return color;
        }

    }

    public static class ObstacleSet{

        Obstacle[][] obstacleSet;


        public void initiateObstacleSet(int boardHeight, int boardWidth){

            obstacleSet = new Obstacle[boardHeight][boardWidth];

            for (int i = 0; i < boardHeight ; i++) {

                for (int j = 0; j < boardWidth; j++) {

                    obstacleSet[i][j] = new Obstacle();

                    obstacleSet[i][j].setColor("____");
                }

            }


        }

        public void printBoard (){

            for (int i = 0; i < obstacleSet.length ; i++) {
                for (int j = 0; j < obstacleSet[0].length; j++) {
                    System.out.print(obstacleSet[i][j].getColor() + " ");
                }
                System.out.println();
            }

        }




        public void insertRow (){

            //first shift all existing objects one row down

            for (int i = obstacleSet.length - 1; i > 0  ; i--) {
                for (int j = 0; j < obstacleSet[0].length; j++) {
                    obstacleSet[i][j].setColor(obstacleSet[i-1][j].getColor());
                }

            }

            //insert new row

            for (int i = 0; i < obstacleSet[0].length; i++) {
                int obstacleType = (int) (Math.random()*4);
                if (obstacleType == 0)
                    obstacleSet[0][i].setColor("blue");
                if(obstacleType == 1)
                    obstacleSet[0][i].setColor("red");
            }

        }
    }

    public static class Player {

        int position;
        int range;
        int score;
        int lives;
        String color;
        String name;

        public void initiatePlayer (int boardWidth){

            range = boardWidth;
            position = (int) boardWidth / 2;
            color = "blue";
            score = 0;
            lives = 3;

        }

        public void movePlayer(String direction) {

            if(direction.equals("left") && position != 0) position--;
            if(direction.equals("right") && position < range - 1) position ++;

        }

    }

    public class Game {

        ObstacleSet board = new ObstacleSet();
        Player player = new Player();

        public void initiateGame(int boardHeight, int boardWidth) {

            player.initiatePlayer(boardWidth);
            board.initiateObstacleSet(boardHeight, boardWidth);
            board.insertRow();
            board.insertRow();
            board.insertRow();

        }

        public void printGame (){
            System.out.println(player.lives);
            for (int i = 0; i < board.obstacleSet.length ; i++) {
                for (int j = 0; j < board.obstacleSet[0].length; j++) {

                    if ((i == board.obstacleSet.length-1) && player.position == j) {
                        System.out.print("[P1] ");
                        if (player.color.equals(board.obstacleSet[i][j].color)) player.score++;
                        else if (board.obstacleSet[i][j].color.equals("____")) {}//do nothing
                        else player.lives--;
                    }
                    else
                        System.out.print(board.obstacleSet[i][j].getColor() + " ");
                }
                System.out.println();
            }

        }

        public void drawGame (GridPane gameBoard){

            for (int i = 0; i < board.obstacleSet.length ; i++) {
                for (int j = 0; j < board.obstacleSet[0].length; j++) {
                    Rectangle obstacleIcon = new Rectangle(50, 50);
                    gameBoard.add(obstacleIcon, j, i);
                    if (board.obstacleSet[i][j].color.equals("red")) obstacleIcon.setFill(Color.RED);
                    if (board.obstacleSet[i][j].color.equals("blue")) obstacleIcon.setFill(Color.BLUE);
                }
            }

            
            Rectangle playerIcon = new Rectangle(50,50);
            playerIcon.setFill(Color.GREEN);

                        
            gameBoard.add(playerIcon, player.position, board.obstacleSet.length - 1);

            gameScore.setText("Lives: " + game.player.lives + " Score: " + game.player.score);





        }

    }


}
