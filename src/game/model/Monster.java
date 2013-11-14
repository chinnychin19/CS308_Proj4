package game.model;

import game.model.attack.AbstractAttack;
import java.util.Collection;
import org.json.simple.JSONObject;


public abstract class Monster extends AbstractViewableObject {

    public Monster (JSONObject definition, JSONObject objInWord) {
        super(definition, objInWord);
        // TODO Auto-generated constructor stub
    }
}
