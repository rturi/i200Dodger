package i200Dodger;

/**
 * Created by Roland on 03/12/2015.
 */
public class Game {

    private int boardWidth;
    private int boardHeight;
    private int level = 0;

    private Player player;

    private Obstacle[][] obstacles;

    private int score = 0;

    public Game (){

        boardHeight = 7;
        boardWidth = 5;

        player = new Player(boardWidth);

        obstacles = initilizeObstacles(boardHeight, boardWidth);

        insertRow(this);

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

    public void insertRow(Game game) {

        //first shift all existing obstacles one row down

        for (int i = obstacles.length - 1; i > 0  ; i--) {
            for (int j = 0; j < obstacles[0].length; j++) {
                obstacles[i][j].setColor(obstacles[i-1][j].getColor());
            }

        }

        //generate obstacles for the new top row

        game.generateNewTopRow();

        // Check if there is a nonnegative choice for the player with the new top row
        int test = 0;
        while(!hasNonNegativeChoice(game, (game.getBoardHeight() - 1), game.getPlayerPosition()) && test < 300){
            game.generateNewTopRow();

            test++;
            if (test > 295) System.out.println("test: " + test);
            for (int i = 0; i < game.getBoardWidth(); i++) {
                obstacles[0][i].setColor("____");
            }
        }
    }


    private boolean hasNonNegativeChoice(Game game, int row, int column){

        //System.out.println("start hasNonNegativeChoice row: " + row + " column: " + column);

        boolean leftOK = false;
        boolean forwardOK = false;
        boolean rightOK = false;

        if (row == 1) {
            if (column != 0) leftOK = isNonNegativeObstacle(0, (column - 1));
            forwardOK = isNonNegativeObstacle(0, column);
            if (column != (game.getBoardWidth() - 1)) rightOK = isNonNegativeObstacle(0, (column + 1));
        } else {
            if (column != 0 && isNonNegativeObstacle((row - 1), (column - 1))) {
                //System.out.println("goin' left " + row + " " + column);
                leftOK = hasNonNegativeChoice(game, (row - 1), (column - 1));
            }
            if (column != 0 && column != (game.getBoardWidth() - 1) && isNonNegativeObstacle((row - 1), (column))) {
                //System.out.println("goin' straight on " + row + " " + column);
                forwardOK = hasNonNegativeChoice(game, (row - 1), (column));
            }
            if (column != (game.getBoardWidth() - 1) && isNonNegativeObstacle((row - 1), (column + 1))) {
                //System.out.println("goin' right " + row + " " + column);
                rightOK = hasNonNegativeChoice(game, (row - 1), (column + 1));
            }
        }

        //System.out.println("end hasNonNegativeChoice row: " + row + " column: " + column + " leftOK: " + leftOK + " forwardOK " + forwardOK + " rightOK " + rightOK);

        return (leftOK || forwardOK || rightOK);
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

        if(getObstacle(this.getBoardHeight()-1, this.player.getPosition()).getColor().equals("red")) {
            player.reduceLives();
        }

        if(this.getObstacle(this.getBoardHeight()-1, this.player.getPosition()).getColor().equals("blue")) {
            score++;
        }
    }

    public void movePlayerLeft() {
        player.movePlayer("left");
    }

    public void movePlayerRight() {
        player.movePlayer("right");
    }

    public int getLevel() {
        return level;
    }

    public void increaseLevel() {
        level++;
        System.out.println("new level:" + level);
    }

    public boolean isGameOver(Game game) {

        if(game.getLives() <= 0) return true;

        return false;
    }

    private boolean isNonNegativeObstacle (int row, int column){

        //System.out.println("viewing obstacle " + row + " " + column + " and it's " + obstacles[row][column].getColor());

        if (obstacles[row][column].getColor() != "red") {
            return true;
        }

        return false;
    }


    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }
}
