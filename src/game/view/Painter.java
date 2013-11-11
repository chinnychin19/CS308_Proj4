package game.view;

import game.model.Player;
import java.awt.Graphics;
import java.awt.Image;
import util.Direction;
import constants.Constants;


public class Painter {
    private Graphics myBuffer;

    public Painter (Graphics g) {
        myBuffer = g;
    }

    public void drawPlayer (Player p) {
        myBuffer.drawImage(p.getImage(),
                           (int) (Constants.MIDDLE_TILE_HORIZONTAL * Constants.TILE_WIDTH),
                           (int) (Constants.MIDDLE_TILE_VERTICAL * Constants.TILE_HEIGHT),
                           (int) Constants.TILE_WIDTH,
                           (int) Constants.TILE_HEIGHT,
                           null);
    }

    public void drawFrame () {
        myBuffer.setColor(Constants.BORDER_COLOR);
        myBuffer.fillRect(0, 0, Constants.WIDTH, Constants.BORDER_THICKNESS);
        myBuffer.fillRect(0, Constants.HEIGHT - Constants.BORDER_THICKNESS,
                          Constants.WIDTH, Constants.HEIGHT);
        myBuffer.fillRect(0, Constants.BORDER_THICKNESS, Constants.BORDER_THICKNESS,
                          Constants.HEIGHT);
        myBuffer.fillRect(Constants.WIDTH - Constants.BORDER_THICKNESS, 0,
                          Constants.WIDTH, Constants.HEIGHT);
    }

    public void drawBackground (Image bg, int moveFrames, Direction direction) {
        // draws default background image

        double offX = 0;
        double offY = 0;
        double rawOffset =
                moveFrames == Constants.MOVE_FRAMES ? 0 : (double) moveFrames /
                                                          Constants.MOVE_FRAMES;
        switch (direction) {
            case LEFT:
                offX += rawOffset * Constants.TILE_WIDTH;
                break;
            case RIGHT:
                offX -= rawOffset * Constants.TILE_WIDTH;
                break;
            case UP:
                offY += rawOffset * Constants.TILE_HEIGHT;
                break;
            case DOWN:
                offY -= rawOffset * Constants.TILE_HEIGHT;
                break;
        }

        myBuffer.drawImage(bg, (int) offX, (int) offY,
                           Constants.WIDTH,
                           Constants.HEIGHT,
                           null);

    }
    /**
     * public void drawBackground() {
     * // draws normal grass (no wild pokemon)
     * 
     * int cellW = (int) world.getCellWidth();
     * int cellH = (int) world.getCellHeight();
     * int offX = 0;
     * int offY = 0;
     * if (moveCounter != Constants.MOVE_FRAMES) { // then we are in the middle
     * // of a move
     * moveCounter++;
     * if (player.getDir().equals(Constants.UP)) {
     * offY = (int) (moveCounter * world.getCellHeight() / Constants.MOVE_FRAMES);
     * } else if (player.getDir().equals(Constants.DOWN)) {
     * offY = -1
     * (int) (moveCounter * world.getCellHeight() / Constants.MOVE_FRAMES);
     * } else if (player.getDir().equals(Constants.LEFT)) {
     * offX = (int) (moveCounter * world.getCellWidth() / Constants.MOVE_FRAMES);
     * } else if (player.getDir().equals(Constants.RIGHT)) {
     * offX = -1
     * (int) (moveCounter * world.getCellWidth() / Constants.MOVE_FRAMES);
     * } else {
     * System.out.println("Failed in drawing background animation");
     * System.exit(0);
     * }
     * offX -= cellW;// prevents blurring on edge of frame
     * offY -= cellH;// prevents blurring on edge of frame
     * }
     * myBuffer.drawImage(normalBackground, offX, offY, Constants.WIDTH + 2
     * cellW, Constants.HEIGHT + 2 * cellH, null);
     * 
     * // draws white space
     * // myBuffer.setColor(Color.white);
     * // myBuffer.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
     * }
     */
}
