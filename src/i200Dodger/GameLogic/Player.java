package i200Dodger.GameLogic;


public class Player {

    private int position;
    private int range;
    private int lives;

    public Player (int boardWidth){

        range = boardWidth;
        position = (int) boardWidth / 2;
        lives = 3;

    }

    public void movePlayer(String direction) {

        if(direction.equals("left") && position != 0) position--;
        if(direction.equals("right") && position < range - 1) position ++;

    }


    public int getLives(){
        return lives;
    }

    public int getPosition(){
        return position;
    }

    public void reduceLives() {
        lives--;
    }

}
