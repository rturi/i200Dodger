package i200Dodger;

/**
 * Created by Roland on 03/12/2015.
 */
public class Player {

    private int position;
    private int range;
    private int score;
    private int lives;
    private String color;
    private String name;

    public Player (int boardWidth){

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

    public int getScore(){
        return score;
    }

    public int lives(){
        return lives;
    }

    public int getPosition(){
        return position;
    }

    public String getColor(){
        return color;
    }

    public void reduceLives() {
        lives--;
    }

    public void increaseScore() {
        score++;
    }
}
