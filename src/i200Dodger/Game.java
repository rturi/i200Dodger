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

        obstacles = initiateObstacles(boardHeight, boardWidth);

        insertRow(this);

        printGame(player,obstacles);

    }

    /* Sets up the board with the first row of obstacles */
    private Obstacle [][] initiateObstacles (int boardHeight, int boardWidth){

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
        }
    }

    public void insertRow(Game game) {

        //first shift all existing obstacles one row down

        for (int i = obstacles.length - 1; i > 0  ; i--) {
            for (int j = 0; j < obstacles[0].length; j++) {
                obstacles[i][j].setColor(obstacles[i-1][j].getColor());
            }

        }

        //generate obstacles for the new top row
        generateNewTopRow();


        // Check if there is a nonnegative choice for the player with the new top row
        while(!hasNonNegativeChoice(game, (game.getBoardHeight() - 1), game.getPlayerPosition())){
            generateNewTopRow();

        }
    }


    private boolean hasNonNegativeChoice(Game game, int row, int column){

        System.out.println("row: " + row + " column: " + column);

        if (row == 1){

            if (column == 0) {
               return (isNonNegativeObstacle(0, column) || (isNonNegativeObstacle(0, (column + 1))) );
            }

            if (column == (game.getBoardWidth() - 1)){
                return (isNonNegativeObstacle(0, (column -1)) || (isNonNegativeObstacle(0, (column))) );
            }


            return (isNonNegativeObstacle(0, (column -1)) || (isNonNegativeObstacle(0, (column))) || (isNonNegativeObstacle(0, (column + 1))));

        }

        if(column == 0) {

            return (hasNonNegativeChoice(game, (row - 1), column) || hasNonNegativeChoice(game, (row - 1), (column + 1)));
        }

        if (column == (game.getBoardWidth() - 1)){

            return (hasNonNegativeChoice(game, (row - 1), (column - 1)) || hasNonNegativeChoice(game, (row - 1), column));
        }

        return (hasNonNegativeChoice(game, (row - 1), (column - 1)) || hasNonNegativeChoice(game, (row - 1), column) || hasNonNegativeChoice(game, (row - 1), (column + 1)));
    }

    /*Prints out the game board to the console*/
    public void printGame(Player player, Obstacle[][] obstacleSet){

        System.out.println(player.getColor());

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
        if (level < 18) level++;
    }

    public boolean isGameOver(Game game) {

        if(game.getLives() <= 0) return true;

        return false;
    }

    private boolean isNonNegativeObstacle (int row, int column){

        if (obstacles[row][column].getColor() != "red") return true;

        return false;
    }


}
