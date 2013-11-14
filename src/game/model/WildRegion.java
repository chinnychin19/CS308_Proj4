package game.model;

import java.awt.Image;

import location.Loc;

import org.json.simple.JSONObject;

public abstract class WildRegion extends AbstractViewableObject {

	private Loc myLoc;
    private Image myImage;
	
    public WildRegion (int x, int y, JSONObject definition) {
        super(x, y, definition);
        // TODO Auto-generated constructor stub
    }

    //frequency of tile
    //frequnecy of monsters
    
}
