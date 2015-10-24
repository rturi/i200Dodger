import java.util.Random;

/**
 * Created by Roland on 10/10/2015.
 */
//test change
import java.util.Random;

public class Game {

    public static void main(String[] args){

        Obstacle obstacle = new Obstacle();

        obstacle.setColor("blue");

        System.out.println(obstacle.getColor());


        GameBoard gameboard = new GameBoard();



        gameboard.printGameBoard(3, 4);

        gameboard.initiateBoard(gameboard.board);

        gameboard.printGameBoard(1,1);


    }

    public static class Obstacle {

        String color = "";

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor() {

            return color;
        }

    }




    public static class GameBoard {

        Obstacle[][] board = new Obstacle[3][5];

        public static void printGameBoard(int i, int j){

            //TODO: Prints obstacle colors as text in console

            System.out.println("" + i);
            System.out.println("" + j);

        }

        public static Obstacle[][] initiateBoard(Obstacle[][] board){

            //Sets input board's all obstacles to "n" as in none
            //TODO: should accept a two dimensional board of any size

            for(int i = 0; i < 3; i++) {

                for(int j = 0; j < 5; j++) {

                    board[i][j].setColor("n");


                }
            }

            return board;

        }


    }

}

