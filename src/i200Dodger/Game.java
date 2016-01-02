package i200Dodger;

import java.security.Timestamp;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.SimpleTimeZone;

/**
 * Created by Roland on 03/12/2015.
 */
public class Game {

    private int boardWidth;
    private int boardHeight;
    private int level = 0;

    private Player player;

    private String playerName = "Anonymous";

    private Obstacle[][] obstacles;

    private int score = 0;

    private LocalDate gameEndDate;

    public Game (){

        boardHeight = Settings.getBoardHeight();
        boardWidth = Settings.getBoardWidth();

        player = new Player(boardWidth);

        obstacles = initilizeObstacles(boardHeight, boardWidth);

        this.insertRow();

    }

    private Obstacle [][] initilizeObstacles (int boardHeight, int boardWidth){

        obstacles = new Obstacle[boardHeight][boardWidth];

        /* Set up the board with blank obstacles */
        for (int i = 0; i < boardHeight ; i++) {
            for (int j = 0; j < boardWidth; j++) {
                obstacles[i][j] = new Obstacle();
                obstacles[i][j].setColor("____");
            }
        }
        return obstacles;
    }

    private void generateNewTopRow(){
        for (int i = 0; i < obstacles[0].length; i++) {
            int obstacleType = (int) (Math.random()*4);
            if (obstacleType == 0)
                obstacles[0][i].setColor("blue");
            if(obstacleType == 1)
                obstacles[0][i].setColor("red");
            if(obstacleType > 2)
                obstacles[0][i].setColor("____");
        }
            //System.out.println("generated a new row");
            //printGame(player,obstacles);
    }

    private void fillTopRowWithBlanks() {
        for (int i = 0; i < obstacles[0].length; i++) {
            obstacles[0][i].setColor("____");
        }
    }

    public void insertRow() {

        int test = 0;

        //first shift all existing obstacles one row down
        for (int i = obstacles.length - 1; i > 0  ; i--) {
            for (int j = 0; j < obstacles[0].length; j++) {
                obstacles[i][j].setColor(obstacles[i-1][j].getColor());
            }
        }

        //generate obstacles for the new top row
        this.generateNewTopRow();



        // Check if there is a nonnegative choice for the player with the new top row
        while(!hasNonNegativeChoice((boardHeight - 1), this.getPlayerPosition()) && test < 25){
            this.generateNewTopRow();
            test++;
            if (test == 25) this.fillTopRowWithBlanks();
        }
    }


    private boolean hasNonNegativeChoice(int row, int column){

        //printGame(player, obstacles);

        boolean OKtoGoLeft = false;
        boolean OKtoGoForward = false;
        boolean OKtoGoRight = false;

        if (row == 1) {
            if (column != 0) {
                //System.out.println("row " + row + ", column " + column + " leftOK? - " + isNonNegativeObstacle(0, (column - 1)));
                if(isNonNegativeObstacle(0, (column - 1))) return true;
            }
            if(isNonNegativeObstacle(0, column)){
                //System.out.println("row " + row + ", column " + column + " forwardOK? - " + isNonNegativeObstacle(0, column));
                return true;
            }
            if (column != (boardWidth - 1)) {
                //System.out.println("row " + row + ", column " + column + " rightOK? - " + isNonNegativeObstacle(0, (column + 1)));
                if(isNonNegativeObstacle(0, (column + 1))) return true;
            }
        } else {
            if (column != 0 && isNonNegativeObstacle((row - 1), (column - 1))) {
                //System.out.println("row " + row + ", column " + column + " leftOK? - " + hasNonNegativeChoice((row - 1), (column - 1)));
                if(hasNonNegativeChoice((row - 1), (column - 1))) return true;
            }
            if (isNonNegativeObstacle((row - 1), (column))) {
                //System.out.println("row " + row + ", column " + column + " forwardOK? - " + isNonNegativeObstacle((row - 1), (column)));
                if(hasNonNegativeChoice((row - 1), (column))) return true;
            }
            if (column != (boardWidth - 1) && isNonNegativeObstacle((row - 1), (column + 1))) {
                //System.out.println("row " + row + ", column " + column + " rightOK? - " + isNonNegativeObstacle((row - 1), (column + 1)));
                if(hasNonNegativeChoice((row - 1), (column + 1))) return true;
            }
        }

        //System.out.println("conclusion - row " + row + ", column " + column + " - " + (OKtoGoLeft || OKtoGoForward || OKtoGoRight));
        //System.out.println();

        return false;
    }


    /*Prints out the game board to the console*/
    public void printGame(Player player, Obstacle[][] obstacleSet){

        for (int i = 0; i < obstacleSet.length ; i++) {
            for (int j = 0; j < obstacleSet[0].length; j++) {

                if ((i == obstacleSet.length-1) && player.getPosition() == j) {
                    System.out.print("[P1] ");
                }
                else
                    System.out.print(obstacleSet[i][j].getColor() + " ");
            }
            System.out.println();
        }
            System.out.println();

    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return player.getLives();
    }

    public Obstacle getObstacle(int row, int column){
        return obstacles[row][column];
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getPlayerPosition() {
        return player.getPosition();
    }

    /* Looks at the obstacles and player and changes the score and/or lives if needed */
    public void evaluateGame() {

        if(getObstacle(boardHeight-1, this.player.getPosition()).getColor().equals("red")) {
            player.reduceLives();
        }

        if(this.getObstacle(boardHeight-1, this.player.getPosition()).getColor().equals("blue")) {
            score++;
        }
    }

    public void movePlayerLeft() {
        player.movePlayer("left");
    }

    public void movePlayerRight() {
        player.movePlayer("right");
    }

    public boolean isGameOver() {

        if(this.getLives() < 0) {
            gameEndDate= LocalDate.now();
            return true;
        }

        return false;
    }

    private boolean isNonNegativeObstacle (int row, int column){

        //System.out.println("viewing obstacle " + row + " " + column + " and it's " + obstacles[row][column].getColor());

        if (obstacles[row][column].getColor() != "red") {
            return true;
        }

        return false;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
