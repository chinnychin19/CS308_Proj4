package game.model.evolution;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import org.json.simple.JSONObject;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoStringValueJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import game.model.AttackWrapper;
import game.model.GameModel;
import game.model.Monster;
import game.model.attack.Attack;

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

    @Override
    public List<AttackWrapper> getAttacks () {
        try {
            SmartJsonObject jsonMonster = getJsonMonster();
            List<AttackWrapper> attacks = new ArrayList<AttackWrapper>();

            for (Object obj : jsonMonster.getJSONArray(Constants.JSON_MONSTER_ALL_ATTACKS)) {
                SmartJsonObject attackJson = new SmartJsonObject((JSONObject) obj);
                String name = attackJson.getString(Constants.NAME);
                SmartJsonObject a =
                        getModel().getDefinitionCache().getInstance(Constants.ATTACK_UPPERCASE,
                                                                    name);
                Attack attack = new Attack(getModel(), a);
                int unlockLevel = attackJson.getInt(Constants.JSON_ATTACK_UNLOCK_LEVEL);
                attacks.add(new AttackWrapper(attack, unlockLevel));
            }
            return attacks;
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
            return null;
        }
    }
}
