package game.controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import location.Loc;
import constants.Constants;
import game.model.AbstractViewable;
import game.model.GameModel;
import game.view.GameView;

/**
 * Represents the Wandering Mode of the Game
 * @author tylernisonoff
 *
 */
public class WanderingMode extends AbstractMode {

    public WanderingMode (GameModel model, GameView view) {
        super(model, view);
    }

    /**
     * Paints the background, player, objects on screen, and screen boarder
     */
    @Override
    public void paint () {
        paintBackground();
        paintPlayer();
        paintViewablesOnScreen();
        paintBorder();
    }
    
    /**
     * Calls doFrame() on all ViewableObjects and GroundObjects
     */
    @Override
    public void act () {
        for (AbstractViewable obj : getGroundObjectsOnScreen()) {
            obj.doFrame(getModel().getWorld(), this.getInput());
        }
        for (AbstractViewable obj : getViewableObjectsOnScreen()) {
//            System.out.println("act object: "+obj.getLoc());
            obj.doFrame(getModel().getWorld(), this.getInput());
        }
    }
    
    /**
     * Turns off Wandering Mode - removes KeyListeners
     */
    @Override
    public void turnOff () {
        getInput().resetAllInputs();
        getView().removeKeyListener(this);
    }

    /**
     * Turns on Wandering Mode - adds KeyListeners
     */
    @Override
    public void turnOn () {
        getInput().resetAllInputs();
        getView().addKeyListener(this);
    }
    
    private void paintPlayer () {
        getGraphics().drawImage(getModel().getPlayer().getImage(),
                                (int) (Constants.MIDDLE_TILE_HORIZONTAL * Constants.TILE_WIDTH),
                                (int) (Constants.MIDDLE_TILE_VERTICAL * Constants.TILE_HEIGHT),
                                (int) Constants.TILE_WIDTH,
                                (int) Constants.TILE_HEIGHT,
                                null);
    }
    
    private void paintBackground () {
        // draws default background image
        getGraphics().
                drawImage(
                          getView().
                                  getBackgroundImage(), 0, 0,
                          Constants.WIDTH,
                          Constants.HEIGHT,
                          null);
    }
    
    private Collection<AbstractViewable> getViewableObjectsOnScreen() {
        ArrayList<AbstractViewable> list = new ArrayList<AbstractViewable>();
        int px = getModel().getPlayer().getLoc().getX();
        int py = getModel().getPlayer().getLoc().getY();
        int tilesRight = Constants.NUM_TILES_HORIZONTAL/2;
        int tilesDown = Constants.NUM_TILES_VERTICAL/2;
        for (int i = px-tilesRight; i <= px+tilesRight; i++) {
            for (int j = py-tilesDown; j <= py+tilesDown; j++) {
                Loc target = new Loc(i,j);
                AbstractViewable object = getModel().getViewableObject(target);
                if (null != object) {
                    list.add(object);
                }
            }
        }
        return list;
    }

    private Collection<AbstractViewable> getGroundObjectsOnScreen() {
        ArrayList<AbstractViewable> list = new ArrayList<AbstractViewable>();
        int px = getModel().getPlayer().getLoc().getX();
        int py = getModel().getPlayer().getLoc().getY();
        int tilesRight = Constants.NUM_TILES_HORIZONTAL/2;
        int tilesDown = Constants.NUM_TILES_VERTICAL/2;
        for (int i = -tilesRight; i <= tilesRight; i++) {
            for (int j = -tilesDown; j <= tilesDown; j++) {
                Loc target = new Loc(i,j);
                AbstractViewable ground = getModel().getGroundObject(target);
                if (null != ground) {
                    list.add(ground);
                }
            }
        }
        return list;
    }

    private void paintViewablesOnScreen () {
        for (AbstractViewable obj : getGroundObjectsOnScreen()){
            Loc tileLoc = obj.getTileLocationOnScreen(getModel().getPlayer());
            getGraphics().drawImage(obj.getImage(),
                               (int) (tileLoc.getX() * Constants.TILE_WIDTH),
                               (int) (tileLoc.getY() * Constants.TILE_HEIGHT),
                               (int) Constants.TILE_WIDTH,
                               (int) Constants.TILE_HEIGHT,
                               null);

        }
        for (AbstractViewable obj : getViewableObjectsOnScreen()){
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
