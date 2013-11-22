package game.model;

import java.awt.Image;
import location.Loc;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


public abstract class AbstractViewableObject extends AbstractModelObject {
    private Loc myLoc;
    private World myWorld;

    public AbstractViewableObject (World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(definition);
        myWorld = world;
        try {
            int x = objInWorld.getInt(Constants.JSON_X);
            int y = objInWorld.getInt(Constants.JSON_Y);
            myLoc = new Loc(x, y);
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }

    public World getWorld () {
        return myWorld;
    }

    public Loc getLoc () {
        return myLoc;
    }

    public void setLoc (Loc loc) {
        myLoc = loc;
    }

    public Loc getTileLocationOnScreen (Player p) {
        int x = Constants.MIDDLE_TILE_HORIZONTAL + (myLoc.getX() - p.getLoc().getX());
        int y = Constants.MIDDLE_TILE_VERTICAL + (myLoc.getY() - p.getLoc().getY());
        return new Loc(x, y);
    }

    public abstract Image getImage ();
    
    public boolean canStepOver() {
        return true; //overridden in subclass for interactable objects
    }
    
    protected void destroy() {
        myWorld.removeObject(myLoc);
    }
}
