package game.model;

import java.awt.Image;
import org.json.simple.JSONObject;

public abstract class AbstractInteractableObject extends AbstractViewableObject {

    public AbstractInteractableObject (World world, JSONObject definition, JSONObject objInWord) {
        super(world, definition, objInWord);
    }
    
    @Override
    public boolean canStepOver() {
        return false;
    }

    public void doInteraction(Player p) {
        // null op by default
        
        // TODO: ask duvall if we ought to make this abstract and force the developer to explicitly
        // state that he/she wants to implement no operation for the interactions
    }
}
