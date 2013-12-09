package game.model;

import java.util.ArrayList;
import java.util.List;
import game.controller.AbstractBattleMode;
import game.controller.AbstractMode;
import game.controller.MainMenuMode;
import game.controller.state.Listable;
import game.model.effect.StatisticEffect;
import org.json.simple.JSONObject;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;


/**
 * 
 * @author rtoussaint
 * 
 */

public class Item extends AbstractModelObject {

    private Consciousness myConsciousness;
    private StatisticEffect myEffect;

    public Item (GameModel model, SmartJsonObject definition) {
        super(model, definition);
    }

    @Override
    public void readDefinition (SmartJsonObject definition) throws SmartJsonException {
        super.readDefinition(definition);
        myConsciousness = Consciousness.fromString(definition.getString(Constants.CONSCIOUSNESS));
        myEffect = new StatisticEffect(getModel(), definition.getSmartJsonObject(Constants.TEXT_STAT_EFFECT));
    }

    public void applyEffect (Monster m) {
        myEffect.apply(m);
    }

    public List<Monster> getApplicableMonsters (List<Monster> monsters) {
        List<Monster> applicableMonsters = new ArrayList<Monster>();
        for (Monster m : monsters) {
            if (this.canApply(m)) {
                applicableMonsters.add(m);
            }
        }
        return applicableMonsters;
    }

    private boolean canApply (Monster m) {
        if (m.isDead()) { return myConsciousness == Consciousness.DEAD ||
                                 myConsciousness == Consciousness.BOTH; }
        return myConsciousness == Consciousness.ALIVE || myConsciousness == Consciousness.BOTH;
    }
}
