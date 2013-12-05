package game.view;

import game.model.AbstractViewableObject;
import game.model.Player;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
import java.util.Map;
import location.Direction;
import location.Loc;
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

    public void drawViewableObjectsOnScreen (Collection<AbstractViewableObject> viewableObjects,
                                             Player p) {
        for (AbstractViewableObject obj : viewableObjects) {

            Loc tileLoc = obj.getTileLocationOnScreen(p);
            myBuffer.drawImage(obj.getImage(),
                               (int) (tileLoc.getX() * Constants.TILE_WIDTH),
                               (int) (tileLoc.getY() * Constants.TILE_HEIGHT),
                               (int) Constants.TILE_WIDTH,
                               (int) Constants.TILE_HEIGHT,
                               null);
        }
    }
}
