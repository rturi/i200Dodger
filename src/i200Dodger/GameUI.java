package i200Dodger;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameUI {

    public GameUI (){

        Stage gameStage = new Stage();

        //setGameStage();

        gameStage.setOnCloseRequest(event1 -> {System.exit(0);}); // Just to make sure everything gets closed
        gameStage.setTitle("i200Dodger");

        GameUIViewsMainMenu.draw(gameStage);
        gameStage.show();

    }

    public static void drawGame(GridPane gameField, Game game) {
    /* Draws obstacles and the player icon on a GridPane. Used during tha game and in the settings menu to draw a s
    *  sample board. */

        int obstacleSideLength = (int) 400/Math.max(game.getBoardHeight(), game.getBoardWidth());

        /* Loop goes throug the board grid cell by cell and if the obstacle in the corresponding game board is red or
        blue inserts a rectangle of an appropriate color */
        for (int i = 0; i < game.getBoardHeight(); i++) {
            for (int j = 0; j < game.getBoardWidth(); j++) {
                Rectangle obstacleIcon = new Rectangle(obstacleSideLength, obstacleSideLength);
                gameField.add(obstacleIcon, j, i);
                if (game.getObstacle(i,j).getColor().equals("red")) obstacleIcon.setFill(Color.RED);
                if (game.getObstacle(i,j).getColor().equals("blue")) obstacleIcon.setFill(Color.BLUE);
            }
        }

        // Finally adds the player icon. If there was an obstacle in the same position, the player icon covers it.
        Rectangle playerIcon = new Rectangle(obstacleSideLength,obstacleSideLength);
        playerIcon.setFill(Color.GREEN);
        gameField.add(playerIcon, game.getPlayerPosition(), game.getBoardHeight() - 1);

    }
}
