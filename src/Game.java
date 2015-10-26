import java.util.Random;

/**
 * Created by Roland on 10/10/2015.
 */

import java.util.Random;

public class Game {

    public static void main(String[] args){

        GameBoard gameboard = new GameBoard();

        gameboard.initiateBoard();

        for (int i = 0; i < 6; i++) {

            gameboard.insertRow();
            System.out.println("uus laua seis");
            gameboard.printBoard();
            System.out.println();
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

    public static class GameBoard{

        Obstacle[][] board = new Obstacle[6][3];


        public void initiateBoard(){

            for (int i = 0; i < board.length ; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    board[i][j] = new Obstacle();
                    board[i][j].setColor("____");
                }
            }


        }

        public void printBoard (){

            for (int i = 0; i < board.length ; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j].getColor() + " ");
                }
                System.out.println();
            }

        }

        public void insertRow (){

            //first shift all existing objects one row down

            for (int i = board.length - 1; i > 0  ; i--) {
                for (int j = 0; j < board[0].length; j++) {
                    board[i][j].setColor(board[i-1][j].getColor());
                }

            }

            //insert new row

            for (int i = 0; i < board[0].length; i++) {

                if (((int) (Math.random()*2) == 0))
                    board[0][i].setColor("blue");
                else
                    board[0][i].setColor("red ");
            }

        }
    }


}

