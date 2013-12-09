package game.model.evolution;

import java.awt.Image;
import java.util.List;
import game.model.AttackWrapper;
import game.model.GameModel;

public class NullEvolution extends AbstractEvolution {

    public NullEvolution (GameModel model) {
        super(model);
    }

    @Override
    public boolean exists () {
        return false;
    }

    @Override
    public String getName () {
        return "";
    }

    @Override
    public int getLevel () {
        return 0;
    }

    @Override
    public Image getImage () {
        return null;
    }

    @Override
    public AbstractEvolution getNextEvolution () {
        // TODO Auto-generated method stub
        return new NullEvolution(getModel());
    }

    @Override
    public boolean shouldEvolve (int currentLevel) {
        return false;
    }

    @Override
    public List<AttackWrapper> getAttacks () {
        // TODO Auto-generated method stub
        return null;
    }

}
