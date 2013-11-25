package game.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
import location.Direction;
import location.Loc;
import constants.Constants;
import game.model.AbstractViewableObject;
import game.model.GameModel;
import game.model.Player;
import game.view.GameView;


public class WanderingMode extends AbstractMode {

    public WanderingMode (GameModel model, GameView view) {
        super(model, view);
    }

    @Override
    public void paint () {
        paintBackground();
        paintPlayer();
        paintViewableObjectsOnScreen();
        paintBorder();
    }
    
    private Collection<AbstractViewableObject> getViewableObjectsOnScreen() {
        return getModel().getViewableObjects().values();
        //TODO: implement this properly
    }

    @Override
    public void act () {
        for (AbstractViewableObject obj : getViewableObjectsOnScreen()) {
            obj.doFrame(getModel().getWorld(), getInputs());
        }
//        // Check for movement
//        Direction dir = getMoveDirection();
//        if (null != dir) {
//            getModel().doMove(dir);
//        }
//
//        // Check for interaction
//        if (getInputs()[INDEX_INTERACT]) {
//            getModel().doInteraction();
//        }
    }
    
//    private Direction getMoveDirection() {
//        boolean[] inputs = getInputs();
//        if (inputs[INDEX_UP]) {
//            return Direction.UP;
//        }
//        if (inputs[INDEX_LEFT]) {
//            return Direction.LEFT;
//        }
//        if (inputs[INDEX_DOWN]) {
//            return Direction.DOWN;
//        }
//        if (inputs[INDEX_RIGHT]) {
//            return Direction.RIGHT;
//        }
//        return null;
//    }

    public void paintPlayer () {
        getGraphics().drawImage(getModel().getPlayer().getImage(),
                                (int) (Constants.MIDDLE_TILE_HORIZONTAL * Constants.TILE_WIDTH),
                                (int) (Constants.MIDDLE_TILE_VERTICAL * Constants.TILE_HEIGHT),
                                (int) Constants.TILE_WIDTH,
                                (int) Constants.TILE_HEIGHT,
                                null);
    }
    
    public void paintBackground () {
        // draws default background image
        getGraphics().
                drawImage(
                          getView().
                                  getBackgroundImage(), 0, 0,
                          Constants.WIDTH,
                          Constants.HEIGHT,
                          null);

    }

    private void paintViewableObjectsOnScreen () {
        for (AbstractViewableObject obj : getViewableObjectsOnScreen()){
            Loc tileLoc = obj.getTileLocationOnScreen(getModel().getPlayer());
            getGraphics().drawImage(obj.getImage(),
                               (int) (tileLoc.getX() * Constants.TILE_WIDTH),
                               (int) (tileLoc.getY() * Constants.TILE_HEIGHT),
                               (int) Constants.TILE_WIDTH,
                               (int) Constants.TILE_HEIGHT,
                               null);

        }
    }
}
