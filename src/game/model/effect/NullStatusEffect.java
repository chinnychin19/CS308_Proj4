package game.model.effect;

import game.model.GameModel;
import game.model.Monster;

public class NullStatusEffect extends AbstractStatusEffect {

    public NullStatusEffect(GameModel m) {
        super(m);
    }
    
    @Override
    public void apply (Monster m) {
        // null op
    }

}
