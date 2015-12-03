package i200Dodger;

/**
 * Created by Roland on 03/12/2015.
 */
public class Game {

    private int boardWidth;
    private int boardHeight;
    private Obstacle[][] obstacles;

    public Game (){

        boardHeight = 7;
        boardWidth = 5;

        obstacles = initiateObstacles(7, 5);
        Player player = new Player(boardWidth);
        printGame(player,obstacles);
        System.out.println("test");

    }

    /* Sets up the board with the first row of obstacles */
    private Obstacle [][] initiateObstacles (int boardHeight, int boardWidth){

        obstacles = new Obstacle[boardHeight][boardWidth];

        /* First set up the board with blank obstacles */
        for (int i = 0; i < boardHeight ; i++) {

            for (int j = 0; j < boardWidth; j++) {

                obstacles[i][j] = new Obstacle();

                obstacles[i][j].setColor("____");
            }

        }

        insertRow(obstacles);

        return obstacles;

    }

    private void insertRow(Obstacle[][] obstacles) {

        //first shift all existing objects one row down

        for (int i = obstacles.length - 1; i > 0  ; i--) {
            for (int j = 0; j < obstacles[0].length; j++) {
                obstacles[i][j].setColor(obstacles[i-1][j].getColor());
            }

        }

        //insert new row

        for (int i = 0; i < obstacles[0].length; i++) {
            int obstacleType = (int) (Math.random()*4);
            if (obstacleType == 0)
                obstacles[0][i].setColor("blue");
            if(obstacleType == 1)
                obstacles[0][i].setColor("red");
        }
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

}
