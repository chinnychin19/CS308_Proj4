package game.model;

import game.controller.Input;

import java.awt.Image;
import org.json.simple.JSONObject;
import location.Loc;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoIntValueJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

/**
 * Abstract class for viewable.  This class specifies if objects can step on it, the image associated with it, and the 
 * interaction that takes place during each frame (if any).
 * @author tylernisonoff
 *
 */

public abstract class AbstractViewable extends AbstractModelObject {
    private Loc myLoc;
    private World myWorld;

    public AbstractViewable (GameModel model, World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, definition);
        myWorld = world;
        try {
            readWorld(objInWorld);
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Determines if an object can step on this viewable
     * @return boolean if stepping on is allowed
     */
    public abstract boolean canStepOn();

    /**
     * Gets the world associated with this viewable
     * @return the world for this viewable
     */
    public World getWorld () {
        return myWorld;
    }

    /**
     * Gets the location of this viewable 
     * @return location of the viewable
     */
    public Loc getLoc () {
        return myLoc;
    }

    /**
     * Gets the tile location of this viewable based on the location of the player
     * @param p Player that dictates where the viewable is positioned based on the Player's location
     * @return the location of the viewable
     */
    public Loc getTileLocationOnScreen (Player p) {
        int x = Constants.MIDDLE_TILE_HORIZONTAL + (myLoc.getX() - p.getLoc().getX());
        int y = Constants.MIDDLE_TILE_VERTICAL + (myLoc.getY() - p.getLoc().getY());
        return new Loc(x, y);
    }

    /**
     * Gets the image associated with this viewable
     * @return image for the viewable
     */
    public abstract Image getImage ();
    
    public void doFrame(World w, Input inputs) {
        // null op by default.
        //TODO: should this be abstract?
//        System.out.println(getName()+": "+myLoc);
    }
    
    /**
     * Destroys the viewable from the world that contains it
     */
    protected void destroy() {
        myWorld.removeObject(myLoc);
    }
    
    protected void readWorld(SmartJsonObject objInWorld) throws SmartJsonException{
        int x = objInWorld.getInt(Constants.JSON_X);
        int y = objInWorld.getInt(Constants.JSON_Y);
        myLoc = new Loc(x, y);
    }
    
    @Override
    public JSONObject getSavedJson() {
        JSONObject toSave = super.getSavedJson();
        toSave.put(Constants.JSON_X, ""+getLoc().getX());
        toSave.put(Constants.JSON_Y, ""+getLoc().getY());
        return toSave;
   }
}
