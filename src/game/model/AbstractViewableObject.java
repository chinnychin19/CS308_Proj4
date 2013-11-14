package game.model;

import java.awt.Image;
import location.Loc;
import org.json.simple.JSONObject;
import constants.Constants;

public abstract class AbstractViewableObject extends AbstractModelObject {
    private Loc myLoc;
    public AbstractViewableObject(int x, int y, JSONObject definition){
        myLoc = new Loc(x, y);
    }
    
    public Loc getLoc(){
        return myLoc;
    }
    
    public Loc getTileLocationOnScreen(Player p){
        int x = Constants.MIDDLE_TILE_HORIZONTAL + (myLoc.getX() - p.getLoc().getX()); 
        int y = Constants.MIDDLE_TILE_VERTICAL + (myLoc.getY() - p.getLoc().getY()); 
        return new Loc(x,y);
    }
    
    public abstract Image getImage();
   
}
