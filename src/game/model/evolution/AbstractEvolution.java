package game.model.evolution;

import java.awt.Image;
import java.util.List;
import util.jsonwrapper.SmartJsonObject;
import game.model.AbstractModelObject;
import game.model.GameModel;
import game.model.AttackWrapper;

public abstract class AbstractEvolution extends AbstractModelObject {
    
    public AbstractEvolution (GameModel model, SmartJsonObject definition) {
        super(model, definition);
    }
    
    public AbstractEvolution (GameModel model) {
        super(model);
    }
    public abstract boolean shouldEvolve(int currentLevel);
    
    public abstract int getLevel();
    
    public abstract Image getImage();
    
    public abstract List<AttackWrapper> getAttacks();
    
    public abstract AbstractEvolution getNextEvolution();

    
    public abstract boolean exists();
}
