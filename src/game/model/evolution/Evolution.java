package game.model.evolution;

import java.awt.Image;
import javax.swing.ImageIcon;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoStringValueJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import game.model.GameModel;
import game.model.Monster;

public class Evolution extends AbstractEvolution {

    private int myLevel;
    public Evolution (SmartJsonObject definition, GameModel model) {
        super(model, definition);
        try {
            myLevel = definition.getInt(Constants.JSON_LEVEL);
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists () {
        return true;
    }
    
    @Override
    public boolean shouldEvolve(int currentLevel){
        return currentLevel >= myLevel;
    }

    @Override
    public int getLevel () {
        return myLevel;
    }

    @Override
    public Image getImage () {
        try {
            SmartJsonObject jsonMonster = getJsonMonster();
            String imagePath = jsonMonster.getString(Constants.JSON_IMAGE);
            Image image = new ImageIcon(imagePath).getImage();
            return image;
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AbstractEvolution getNextEvolution () {
        try{
            SmartJsonObject jsonMonster = getJsonMonster();
            SmartJsonObject evolution = jsonMonster.getSmartJsonObject(Constants.JSON_EVOLUTION);
            return new Evolution(evolution, getModel());
        } catch(SmartJsonException e){
            return new NullEvolution(getModel());
        }
    }
    
    private SmartJsonObject getJsonMonster() throws SmartJsonException{
        return getModel().getDefinitionCache().getInstance(Constants.MONSTER_UPPERCASE, getName());
    }
}
