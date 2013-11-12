package game.model;

import java.awt.Image;
import org.json.simple.JSONObject;

public abstract class AbstractViewableObject extends AbstractModelObject {
    
    public AbstractViewableObject(int x, int y, JSONObject definition){
    }
    
    public abstract Image getImage();
}
