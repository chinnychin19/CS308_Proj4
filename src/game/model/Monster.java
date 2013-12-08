package game.model;

import game.model.attack.Attack;
import game.model.evolution.AbstractEvolution;
import game.model.evolution.Evolution;
import game.model.evolution.NullEvolution;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


/**
 * Work in progress
 * 
 * @author rtoussaint
 * 
 */

public class Monster extends AbstractModelObject implements Saveable {

    private Image myImage;
    private double myCatchRate;
    private Type myType;
    private int myBaseHP;
    private int myBaseAttack;
    private int myBaseDefense;
    private int myLevel;
    private int myExp;
    private int myExpToNextLevel;
    private int myMaxHP;
    private int myCurHP;
    private int myAttack;
    private int myDefense;
    private List<AttackWrapper> myAttacks;
    private AbstractEvolution myEvolution;

    // TODO: EVOLUTION

    /**
     * To be called for an NPC's monsters or wild monster
     * It will generate stats based on the level and base stats
     * 
     * @param model
     * @param definition
     * @param level
     */
    public Monster (GameModel model, SmartJsonObject definition, int level) {
        super(model, definition);
        myLevel = level;
        generateStats();
    }

    /**
     * To be called for the Player's monsters because they already have stats
     * 
     * @param model
     * @param definition
     * @param objInWorld
     */
    public Monster (GameModel model, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, definition);
        readStats(objInWorld);
    }

    /**
     * Get the image of the monster
     * 
     * @return monster's image
     */
    public Image getImage () {
        return myImage;
    }

    /**
     * Get the catch rate of the monster. A value of how difficult it is to catch this monster
     * 
     * @return monster's catch rate
     */
    public double getCatchRate () {
        return myCatchRate;
    }

    /**
     * Gets the type of the monster
     * 
     * @return type of monster
     */
    public Type getType () {
        return myType;
    }

    /**
     * Gets the base HP of the monster
     * 
     * @return base HP of the monster
     */
    public int getBaseHP () {
        return myBaseHP;
    }

    /**
     * Gets the base attack of the monster
     * 
     * @return base attack of the monster
     */
    public int getBaseAttack () {
        return myBaseAttack;
    }

    /**
     * Gets the base defense of the monster
     * 
     * @return base defense of the monster
     */
    public int getBaseDefense () {
        return myBaseDefense;
    }

    /**
     * Gets the level of the monster
     * 
     * @return Monster's level
     */
    public int getLevel () {
        return myLevel;
    }

    /**
     * Gets the experience of the monster
     * 
     * @return experience of the monster
     */
    public int getExp () {
        return myExp;
    }

    /**
     * Get the amount of experience that is needed to move onto the next level
     * 
     * @return amount to move onto the next level
     */
    public int getExpToNextLevel () {
        return myExpToNextLevel;
    }

    /**
     * Gets the max HP of the monster
     * 
     * @return Max hp of monster
     */
    public int getMaxHP () {
        return myMaxHP;
    }

    /**
     * Gets the current HP of the monster
     * 
     * @return the current HP
     */
    public int getCurHP () {
        return myCurHP;
    }

    /**
     * Gets the attack value
     * 
     * @return attack value
     */
    public int getAttack () {
        return myAttack;
    }

    /**
     * defense value of the monster
     * 
     * @return defense value
     */
    public int getDefense () {
        return myDefense;
    }

    public void heal () {
        myCurHP = myMaxHP;
        // TODO: set status to okay
    }

    /**
     * Get the attacks associated with the monster
     * 
     * @return list of attacks
     */
    public List<AttackWrapper> getMyAttacks () {
        return myAttacks;
    }

    /**
     * Change the health of the monster and see if the monster is at 0 health or max health
     * 
     * @param amount the change in the monster's health
     */
    public void changeHealth (int amount) {
        myCurHP += amount;
        if (myCurHP < 0) {
            myCurHP = 0;
        }
        else if (myCurHP > myMaxHP) {
            myCurHP = myMaxHP;
        }
    }

    /**
     * Determines if a pokemon is dead
     * 
     * @return true if current HP is 0
     */
    public boolean isDead () {
        return myCurHP == 0;
    }

    /**
     * Stats for the monster
     */
    private void generateStats () {
        double factor = 1 + Math.log(35);
        myExp = 0;
        myExpToNextLevel = Integer.MAX_VALUE; // TODO: wild pokemon
        myMaxHP = (int) (myBaseHP * myLevel * factor);
        myCurHP = myMaxHP;
        myAttack = (int) (myBaseAttack * myLevel * factor);
        myDefense = (int) (myBaseDefense * myLevel * factor);
    }

    /**
     * Read in the stats for the monster
     * 
     * @param objInWorld object containing the monster's stats
     */
    private void readStats (SmartJsonObject objInWorld) {
        try {
            myLevel = objInWorld.getInt(Constants.JSON_LEVEL);
            myExp = objInWorld.getInt(Constants.EXP);
            myExpToNextLevel = objInWorld.getInt(Constants.EXP_TO_NEXT_LEVEL);
            myMaxHP = objInWorld.getInt(Constants.MAX_HP);
            myCurHP = objInWorld.getInt(Constants.CUR_HP);
            myAttack = objInWorld.getInt(Constants.ATTACK_LOWERCASE);
            myDefense = objInWorld.getInt(Constants.DEFENSE);
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read in the definition information for the monster
     * 
     * @param definition the file containing the monster's info
     */
    @Override
    protected void readDefinition (SmartJsonObject definition) throws SmartJsonException {
        super.readDefinition(definition);

        String imageURL = definition.getString(Constants.JSON_IMAGE);
        myImage = new ImageIcon(imageURL).getImage();
        
        //TODO : Put all these statistics in a map, ya dingus
        myCatchRate = definition.getDouble(Constants.JSON_MONSTER_CATCH_RATE);
        myType = new Type(definition.getString(Constants.TYPE));
        myBaseHP = definition.getInt(Constants.BASE_HP);
        myBaseAttack = definition.getInt(Constants.BASE_ATTACK);
        myBaseDefense = definition.getInt(Constants.BASE_DEFENSE);
        myAttacks = new ArrayList<AttackWrapper>();

        for (Object obj : definition.getJSONArray(Constants.JSON_MONSTER_ALL_ATTACKS)) {
            SmartJsonObject attackJson = new SmartJsonObject((JSONObject) obj);
            String name = attackJson.getString(Constants.NAME);
            SmartJsonObject a =
                    getModel().getDefinitionCache().getInstance(Constants.ATTACK_UPPERCASE,
                                                                name);
            Attack attack = new Attack(getModel(), a);
            int unlockLevel = attackJson.getInt(Constants.JSON_ATTACK_UNLOCK_LEVEL);
            myAttacks.add(new AttackWrapper(attack, unlockLevel));
            // TODO: Implement myEvolution
        }
        myEvolution = readEvolution(definition);
    }

    public List<Attack> getAllAvailableAttacks () {
        List<Attack> attacks = new ArrayList<Attack>();
        for (AttackWrapper aw : myAttacks) {
            if (aw.canUse(myLevel)) {
                attacks.add(aw.getAttack());
            }
        }
        return attacks;
    }

    public void setImage (String imagePath) {
        myImage = new ImageIcon(imagePath).getImage();
    }

    public void setImage (Image i) {
        myImage = i;
    }

    public void setEvolution (AbstractEvolution ev) {
        myEvolution = ev;
    }
    
    public void setAttacks (List<AttackWrapper> attacks) {
        myAttacks = attacks;
    }

    public int getRewardExperience () {
        return 300; // TODO: Actually implement
    }

    public LevelChange addExperience (int exp) {
        boolean didLevelUp = false, didEvolve = false;

        System.out.printf("Adding experience: %d exp to next: %d curr exp: %d \n", exp,
                          myExpToNextLevel, myExp);
        myExp += exp;
        while (myExp >= myExpToNextLevel) {
            levelUp();
            didLevelUp = true;
            myExp = myExp - myExpToNextLevel;
            if (myEvolution.exists() && myEvolution.shouldEvolve(myLevel)) {
                System.out.println("Evolving!!!");
                didEvolve = true;
                evolve();
            }
        }
        return LevelChange.getStateChange(didLevelUp, didEvolve);
    }

    private void levelUp () {
        myLevel++;
        // TODO: update stats
    }

    private AbstractEvolution readEvolution (SmartJsonObject definition) {
        try {
            SmartJsonObject evolutionJson = definition.getSmartJsonObject(Constants.JSON_EVOLUTION);
            return new Evolution(evolutionJson, getModel());
        }
        catch (SmartJsonException e) {
            return new NullEvolution(getModel());
        }
    }

    private void evolve () {
        setName(myEvolution.getName());
        setImage(myEvolution.getImage());
        setAttacks(myEvolution.getAttacks());
        setEvolution(myEvolution.getNextEvolution());
        
        // TODO: update stats
    }

    @Override
    public JSONObject getSavedJson () {
        JSONObject toSave = super.getSavedJson();
        toSave.put(Constants.JSON_LEVEL, ""+getLevel());
        toSave.put(Constants.EXP, ""+getExp());
        toSave.put(Constants.EXP_TO_NEXT_LEVEL, ""+getExpToNextLevel());
        toSave.put(Constants.MAX_HP, ""+getMaxHP());
        toSave.put(Constants.CUR_HP, ""+getCurHP());
        toSave.put(Constants.ATTACK_LOWERCASE, ""+getAttack());
        toSave.put(Constants.DEFENSE, ""+getDefense());
        return toSave;
    }

    public void changeStatistic (String myStatistic, int myChange) {
        // TODO Auto-generated method stub
        
    }
}
