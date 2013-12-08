package game.model;

import game.model.attack.Attack;
import game.model.evolution.AbstractEvolution;
import game.model.evolution.Evolution;
import game.model.evolution.NullEvolution;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private Type myType;
    private double myCatchRate;
    private List<AttackWrapper> myAttacks;
    private Map<String, Integer> myStatistics;
    private AbstractEvolution myEvolution;

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
        myStatistics.put(Constants.JSON_LEVEL, level);
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
        return myStatistics.get(Constants.BASE_HP);
    }

    /**
     * Gets the base attack of the monster
     * 
     * @return base attack of the monster
     */

    public int getBaseAttack () {
        return myStatistics.get(Constants.BASE_ATTACK);
    }

    /**
     * Gets the base defense of the monster
     * 
     * @return base defense of the monster
     */
    public int getBaseDefense () {
        return myStatistics.get(Constants.BASE_DEFENSE);
    }

    /**
     * Gets the level of the monster
     * 
     * @return Monster's level
     */
    public int getLevel () {
        return myStatistics.get(Constants.JSON_LEVEL);
    }

    /**
     * Gets the experience of the monster
     * 
     * @return experience of the monster
     */
    public int getExp () {
        return myStatistics.get(Constants.EXP);
    }

    /**
     * Get the amount of experience that is needed to move onto the next level
     * 
     * @return amount to move onto the next level
     */

    public int getExpToNextLevel () {
        return myStatistics.get(Constants.EXP_TO_NEXT_LEVEL);
    }

    /**
     * Gets the max HP of the monster
     * 
     * @return Max hp of monster
     */
    public int getMaxHP () {
        return myStatistics.get(Constants.MAX_HP);
    }

    /**
     * Gets the current HP of the monster
     * 
     * @return the current HP
     */
    public int getCurHP () {
        return myStatistics.get(Constants.CUR_HP);
    }

    /**
     * Gets the attack value
     * 
     * @return attack value
     */
    public int getAttack () {
        return myStatistics.get(Constants.ATTACK_LOWERCASE);
    }

    /**
     * defense value of the monster
     * 
     * @return defense value
     */

    public int getDefense () {
        return myStatistics.get(Constants.DEFENSE);
    }

    public void heal () {
        changeHealth(getMaxHP());
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
        int maxHP = myStatistics.get(Constants.MAX_HP);
        int oldHP = myStatistics.get(Constants.CUR_HP);
        int newHP = Math.min(Math.max(0, oldHP + amount), maxHP);
        myStatistics.put(Constants.CUR_HP, newHP);
    }

    /**
     * Determines if a pokemon is dead
     * 
     * @return true if current HP is 0
     */
    public boolean isDead () {
        return getCurHP() == 0;
    }

    /**
     * Stats for the monster
     */
    
    private void generateStats(){

        double factor = 1 + Math.log(35);
        myStatistics.put(Constants.EXP, 0);
        myStatistics.put(Constants.EXP_TO_NEXT_LEVEL, Integer.MAX_VALUE);
        myStatistics.put(Constants.MAX_HP, (int) (getBaseHP() * getLevel() * factor));
        myStatistics.put(Constants.ATTACK_LOWERCASE, (int) (getBaseAttack() * getLevel() * factor));
        myStatistics.put(Constants.DEFENSE, (int) (getBaseDefense() * getLevel() * factor));
        myStatistics.put(Constants.CUR_HP, getMaxHP());
    }

    /**
     * Read in the stats for the monster
     * 
     * @param objInWorld object containing the monster's stats
     */
    private void readStats (SmartJsonObject objInWorld) {
        try {
            myStatistics.put(Constants.JSON_LEVEL, objInWorld.getInt(Constants.JSON_LEVEL));
            myStatistics.put(Constants.EXP, objInWorld.getInt(Constants.EXP));
            myStatistics.put(Constants.EXP_TO_NEXT_LEVEL,
                             objInWorld.getInt(Constants.EXP_TO_NEXT_LEVEL));
            myStatistics.put(Constants.MAX_HP, objInWorld.getInt(Constants.MAX_HP));
            myStatistics.put(Constants.CUR_HP, getMaxHP());
            myStatistics.put(Constants.ATTACK_LOWERCASE,
                             objInWorld.getInt(Constants.ATTACK_LOWERCASE));
            myStatistics.put(Constants.DEFENSE, objInWorld.getInt(Constants.DEFENSE));
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
        myStatistics = new HashMap<String, Integer>();

        String imageURL = definition.getString(Constants.JSON_IMAGE);
        myImage = new ImageIcon(imageURL).getImage();
        myCatchRate = definition.getDouble(Constants.JSON_MONSTER_CATCH_RATE);
        myType = new Type(definition.getString(Constants.TYPE));

        myStatistics.put(Constants.BASE_HP, definition.getInt(Constants.BASE_HP));
        myStatistics.put(Constants.BASE_ATTACK, definition.getInt(Constants.BASE_ATTACK));
        myStatistics.put(Constants.BASE_DEFENSE, definition.getInt(Constants.BASE_DEFENSE));
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
        }
        myEvolution = readEvolution(definition);
    }

    public List<Attack> getAllAvailableAttacks () {
        List<Attack> attacks = new ArrayList<Attack>();
        for (AttackWrapper aw : myAttacks) {
            if (aw.canUse(getLevel())) {
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
        return 50 * getLevel();
    }

    public LevelChange addExperience (int exp) {
        boolean didLevelUp = false, didEvolve = false;
        System.out.printf("Adding experience: %d exp to next: %d curr exp: %d \n", exp,
                          getExpToNextLevel(), getExp());
        changeStat(Constants.EXP, exp);
        while (getExp() >= getExpToNextLevel()) {
            levelUp();
            didLevelUp = true;
            changeStat(Constants.EXP, (-1)*getExpToNextLevel());
            if (myEvolution.exists() && myEvolution.shouldEvolve(getLevel())) {
                System.out.println("Evolving!!!");
                didEvolve = true;
                evolve();
            }
        }
        return LevelChange.getStateChange(didLevelUp, didEvolve);
    }

    private void levelUp () {
        changeStat(Constants.JSON_LEVEL, 1);
        changeStat(Constants.ATTACK_LOWERCASE, getFudgeAmount());
        changeStat(Constants.DEFENSE, getFudgeAmount());
        changeStat(Constants.MAX_HP, 2*getFudgeAmount());
        changeStat(Constants.EXP_TO_NEXT_LEVEL, 10*getFudgeAmount());
    }

    private int getFudgeAmount () {
        return (int) Math.round(.8 + Math.random() * .4) * getLevel();
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
    
    public void changeStat(String statName, int amount){
        if(statName.equals(Constants.CUR_HP)){
            changeHealth(amount);
        }
        else {
            int value = myStatistics.get(statName) + amount;     
            myStatistics.put(statName, Math.max(1, value));
        }
    }

    private void evolve () {
        setName(myEvolution.getName());
        setImage(myEvolution.getImage());
        setAttacks(myEvolution.getAttacks());
        setEvolution(myEvolution.getNextEvolution());
    }

    @Override
    public JSONObject getSavedJson () {
        JSONObject toSave = super.getSavedJson();
        toSave.put(Constants.JSON_LEVEL, "" + getLevel());
        toSave.put(Constants.EXP, "" + getExp());
        toSave.put(Constants.EXP_TO_NEXT_LEVEL, "" + getExpToNextLevel());
        toSave.put(Constants.MAX_HP, "" + getMaxHP());
        toSave.put(Constants.CUR_HP, "" + getCurHP());
        toSave.put(Constants.ATTACK_LOWERCASE, "" + getAttack());
        toSave.put(Constants.DEFENSE, "" + getDefense());
        return toSave;
    }

}
