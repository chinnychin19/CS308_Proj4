package game.model;

import game.model.attack.AbstractAttack;
import java.util.Collection;
import org.json.simple.JSONObject;


public abstract class Monster extends AbstractViewableObject {

    public Monster (World world, JSONObject definition, JSONObject objInWord) {
        super(world, definition, objInWord);
        // TODO Auto-generated constructor stub
    }
}
