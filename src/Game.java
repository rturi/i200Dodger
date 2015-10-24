import java.util.Random;

/**
 * Created by Roland on 10/10/2015.
 */

import java.util.Random;

public class Game {

    public static void main(String[] args){

        Obstacle obstacle = new Obstacle();

        obstacle.setColor("vermilion");

        System.out.println(obstacle.getColor());

    }

    public static class Obstacle {

        String color = "unset";

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor() {

            return color;
        }

    }

    public static class GameBoard{


    }


}

