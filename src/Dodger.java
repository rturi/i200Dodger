import java.util.Random;
import java.util.Scanner;

public class Dodger {

    public static void main(String[] args){

        Game game = new Game();

        game.initiateGame(6,3);

        System.out.println("Press 'a' to move left, 'd' to move right, 's' to stay put, 'q' to quit");
        System.out.println();

        for (int i = 0; i < 4; i++) {
            game.board.insertRow();
        }
        game.printGame();

        Scanner sc = new Scanner(System.in);

        while(true){

            String move = sc.next();

            if (move.equals("q"))break;

            if (move.equals("a")){
                game.board.insertRow();
                game.player.movePlayer("left");
                game.printGame();
            }
            else if (move.equals("d")) {
                game.board.insertRow();
                game.player.movePlayer("right");
                game.printGame();
            }
            else if (move.equals("s")) {
                game.board.insertRow();
                game.printGame();
            }
            System.out.println();
            System.out.println("Score: " + game.player.score + " Lives: " + game.player.lives);
            if (game.player.lives == 0){
                System.out.println("Game over");
                break;
            }
        }

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

                if (((int) (Math.random()*2) == 0))
                    obstacleSet[0][i].setColor("blue");
                else
                    obstacleSet[0][i].setColor("red ");
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

    public static class Game {

        ObstacleSet board = new ObstacleSet();
        Player player = new Player();

        public void initiateGame(int boardHeight, int boardWidth) {

            player.initiatePlayer(boardWidth);
            board.initiateObstacleSet(boardHeight, boardWidth);

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

    }


}
