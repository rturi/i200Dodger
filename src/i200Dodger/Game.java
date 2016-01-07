package i200Dodger;

import java.time.LocalDate;


public class Game {

    private int boardWidth;
    private int boardHeight;

    private Player player;

    private Obstacle[][] obstacles;

    private int score;

    private LocalDate gameEndDate;


    public Game (){

        /* Constructor. Called once in the beginning of the game. Sets up a new player, score and a board with blank
        obstacles. */

        // gets game board dimensions form settings file
        boardHeight = Settings.getBoardHeight();
        boardWidth = Settings.getBoardWidth();

        // boardWidth is included so player can calculate starting position (player keeps track of its position on the
        // board) and movement range
        player = new Player(boardWidth);

        obstacles = initializeObstacles(boardHeight, boardWidth);

        score = 0;

    }

    private Obstacle [][] initializeObstacles(int boardHeight, int boardWidth){

        /* Set up the board with blank obstacles */

        obstacles = new Obstacle[boardHeight][boardWidth];

        for (int i = 0; i < boardHeight ; i++) {
            for (int j = 0; j < boardWidth; j++) {
                obstacles[i][j] = new Obstacle();
                obstacles[i][j].setColor("____");
            }
        }
        return obstacles;
    }

    private void generateNewTopRow(){

        /* Every obstacle has 1/3 chance to be blue or red or blank */

        for (int i = 0; i < obstacles[0].length; i++) {
            int obstacleType = (int) (Math.random()*5);
            if (obstacleType == 0)
                obstacles[0][i].setColor("blue");
            if(obstacleType == 1)
                obstacles[0][i].setColor("red");
            if(obstacleType == 3)
                obstacles[0][i].setColor("____");
        }
    }

    private void fillTopRowWithBlanks() {

        /* Used when player has put herself in a position without positive choices - i.e. there is no way to avoid
        * hitting a red obstacle. */

        for (int i = 0; i < obstacles[0].length; i++) {
            obstacles[0][i].setColor("____");
        }
    }

    public void insertRow() {

        /* Shifts all obstacles down one row and inserts a new row. */

        int numberOfTries = 0;

        //first shift all existing obstacles one row down
        for (int i = obstacles.length - 1; i > 0  ; i--) {
            for (int j = 0; j < obstacles[0].length; j++) {
                obstacles[i][j].setColor(obstacles[i-1][j].getColor());
            }
        }

        //generate obstacles for the new top row
        this.generateNewTopRow();

        /* Checks if player has a path to a non-negative obstacle in the new top row and if not, generates a new top
        * row. If check fails 25 times, fills the top row with blanks */
        while(!hasNonNegativeChoice((boardHeight - 1), this.getPlayerPosition()) && numberOfTries < 25){
            this.generateNewTopRow();
            numberOfTries++;
            if (numberOfTries == 25) this.fillTopRowWithBlanks();
        }
    }


    private boolean hasNonNegativeChoice(int row, int column){

        /* Recursively checks if player in given position has non-negative choices (i.e. blues or blanks) and calls
        itself on these said non-negative positions. If the position is in the second row from the top, method returns
        true if there is a positive choice.
        If one non-negative choice is found in the second row the "true" should travel back to the original position and
        the board evaluation should stop and the method should return "true".
        False is returned if there are no non-negative choices on the whole board.
        */

        if (row == 1) {
            if (column != 0) {
                if(isNonNegativeObstacle(0, (column - 1))) return true;
            }
            if(isNonNegativeObstacle(0, column)){
                return true;
            }
            if (column != (boardWidth - 1)) {
                if(isNonNegativeObstacle(0, (column + 1))) return true;
            }
        } else {
            if (column != 0 && isNonNegativeObstacle((row - 1), (column - 1))) {
                if(hasNonNegativeChoice((row - 1), (column - 1))) return true;
            }
            if (isNonNegativeObstacle((row - 1), (column))) {
                if(hasNonNegativeChoice((row - 1), (column))) return true;
            }
            if (column != (boardWidth - 1) && isNonNegativeObstacle((row - 1), (column + 1))) {
                if(hasNonNegativeChoice((row - 1), (column + 1))) return true;
            }
        }

        return false;
    }


    private boolean isNonNegativeObstacle (int row, int column){

        if (obstacles[row][column].getColor() != "red") {
            return true;
        }

        return false;
    }

    public void printGame(Player player, Obstacle[][] obstacleSet){

        /* Prints out the game board to the console. Once upon a time was used for debugging. */

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

    public void evaluateGame() {

        /* Looks at the obstacles and player and changes the score and/or lives if needed */

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
            gameEndDate = LocalDate.now();
            return true;
        }

        return false;
    }

    public String getGameEndDate() {
        return gameEndDate.toString();
    }
}
