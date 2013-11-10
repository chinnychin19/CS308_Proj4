package game.view;

import game.model.Player;
import java.awt.Graphics;
import java.awt.Image;
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

    public void drawBackground (Image bg) {
        // draws default background image
        myBuffer.drawImage(bg, 0, 0,
                           Constants.WIDTH,
                           Constants.HEIGHT,
                           null);

    }

}
