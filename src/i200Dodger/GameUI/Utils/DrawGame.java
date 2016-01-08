package i200Dodger.GameUI.Utils;

import i200Dodger.GameLogic.Game;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Roland on 08/01/2016.
 */
public class DrawGame {

    public static void draw(GridPane gameField, Game game) {

    /* Draws obstacles and the player icon on a GridPane. Used during tha game and in the settings menu to draw a
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

        // Finally adds the player icon. If there is an obstacle in the same position, the player icon covers it.
        Rectangle playerIcon = new Rectangle(obstacleSideLength,obstacleSideLength);
        playerIcon.setFill(Color.GREEN);
        gameField.add(playerIcon, game.getPlayerPosition(), game.getBoardHeight() - 1);

    }

}
