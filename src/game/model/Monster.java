package game.model;

import game.model.attack.AbstractAttack;
import java.util.Collection;
import org.json.simple.JSONObject;


public abstract class Monster extends AbstractViewableObject {
    public Monster (int x, int y, JSONObject definition) {
        super(x, y, definition);
        // TODO Auto-generated constructor stub
    }

    private Collection<AbstractAttack> myAttacks;
    private Stat myStat;

    public Collection<AbstractAttack> getAttacks () {
        return myAttacks;
    }

    public abstract Stat getStat ();

}
