package game.model;

import game.controller.Input;

import java.awt.Image;
import location.Loc;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

public abstract class AbstractViewable extends AbstractModelObject {
    private Loc myLoc;
    private World myWorld;

    public AbstractViewable (GameModel model, World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, definition);
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
    
    public abstract boolean canStepOn();

    public World getWorld () {
        return myWorld;
    }

    public Loc getLoc () {
        return myLoc;
    }

    public Loc getTileLocationOnScreen (Player p) {
        int x = Constants.MIDDLE_TILE_HORIZONTAL + (myLoc.getX() - p.getLoc().getX());
        int y = Constants.MIDDLE_TILE_VERTICAL + (myLoc.getY() - p.getLoc().getY());
        return new Loc(x, y);
    }

    public abstract Image getImage ();
    
    public void doFrame(World w, Input inputs) {
        // null op by default.
        //TODO: should this be abstract?
//        System.out.println(getName()+": "+myLoc);
    }
    
    protected void destroy() {
        myWorld.removeObject(myLoc);
    }
}
