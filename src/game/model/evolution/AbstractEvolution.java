package game.model.evolution;

import java.awt.Image;
import game.model.AbstractModelObject;
import game.model.GameModel;

public abstract class AbstractEvolution extends AbstractModelObject {
    
    public AbstractEvolution (GameModel model) {
        super(model);
    }
    public abstract boolean shouldEvolve(int currentLevel);

    public abstract String getName();
    
    public abstract int getLevel();
    
    public abstract Image getImage();
    
    public abstract AbstractEvolution getNextEvolution();

    
    public abstract boolean exists();
}
