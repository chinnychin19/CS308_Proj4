package game.model;

import java.awt.Image;
import location.Loc;
import org.json.simple.JSONObject;
import constants.Constants;


public abstract class AbstractViewableObject extends AbstractModelObject {
    private Loc myLoc;

    public AbstractViewableObject (JSONObject definition, JSONObject objInWord) {
        super(definition);
        int x = Integer.parseInt(objInWord.get(Constants.JSON_X).toString());
        int y = Integer.parseInt(objInWord.get(Constants.JSON_Y).toString());
        myLoc = new Loc(x, y);
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

}
