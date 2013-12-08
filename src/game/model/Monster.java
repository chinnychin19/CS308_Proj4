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
    
    private Map<String, Double> myStatistics;
    private List<AttackWrapper> myAttacks;
    private AbstractEvolution myEvolution;
    private final static int LEVEL_FACTOR = 1;
    private final static int EXP_FACTOR = 10;
    private final static int DEFAULT_EXP_LEVEL = 300;
    
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
        myStatistics = new HashMap<String, Double>();
        myStatistics.put(Constants.JSON_LEVEL, (double) level);
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
        myStatistics = new HashMap<String, Double>();
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
    public Double getCatchRate () {
        return myStatistics.get(Constants.JSON_MONSTER_CATCH_RATE);
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
    public Double getBaseHP () {
        return myStatistics.get(Constants.BASE_HP);
    }

    /**
     * Gets the base attack of the monster
     * 
     * @return base attack of the monster
     */
    public Double getBaseAttack () {
        return myStatistics.get(Constants.BASE_ATTACK);
    }

    /**
     * Gets the base defense of the monster
     * 
     * @return base defense of the monster
     */
    public Double getBaseDefense () {
        return myStatistics.get(Constants.BASE_DEFENSE);
    }

    /**
     * Gets the level of the monster
     * 
     * @return Monster's level
     */
    public Double getLevel () {
        return myStatistics.get(Constants.JSON_LEVEL);
    }

    /**
     * Gets the experience of the monster
     * 
     * @return experience of the monster
     */
    public Double getExp () {
        return myStatistics.get(Constants.EXP);
    }

    /**
     * Get the amount of experience that is needed to move onto the next level
     * 
     * @return amount to move onto the next level
     */
    public Double getExpToNextLevel () {
        return myStatistics.get(Constants.EXP_TO_NEXT_LEVEL);
    }

    /**
     * Gets the max HP of the monster
     * 
     * @return Max hp of monster
     */
    public Double getMaxHP () {
        return myStatistics.get(Constants.MAX_HP);
    }

    /**
     * Gets the current HP of the monster
     * 
     * @return the current HP
     */
    public Double getCurHP () {
        return myStatistics.get(Constants.CUR_HP);
    }

    /**
     * Gets the attack value
     * 
     * @return attack value
     */
    public Double getAttack () {
        return myStatistics.get(Constants.ATTACK_LOWERCASE);
    }

    /**
     * defense value of the monster
     * 
     * @return defense value
     */
    public Double getDefense () {
        return myStatistics.get(Constants.DEFENSE);
    }

    public void heal () {
        double MaxHP = myStatistics.get(Constants.MAX_HP);
        myStatistics.put(Constants.CUR_HP, MaxHP);
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
        double curHP = getCurHP();
        curHP += amount;
        if (curHP < 0) {
            myStatistics.put(Constants.CUR_HP, 0.0);
        }
        else if (curHP > getMaxHP()) {
            heal();
        }
    }

    /**
     * Determines if a pokemon is dead
     * 
     * @return true if current HP is 0
     */
    public boolean isDead () {
        return myStatistics.get(Constants.CUR_HP) == 0.0;
    }

    /**
     * Stats for the monster
     */
    
    private void generateStats(){

        double factor = 1 + Math.log(35);
        double myBaseHP = myStatistics.get(Constants.BASE_HP);
        double myLevel = myStatistics.get(Constants.JSON_LEVEL);
        double myBaseAttack = myStatistics.get(Constants.BASE_ATTACK);
        double myBaseDefense = myStatistics.get(Constants.BASE_DEFENSE);
        
        myStatistics.put(Constants.EXP, 0.0);          
        myStatistics.put(Constants.EXP_TO_NEXT_LEVEL, (double) DEFAULT_EXP_LEVEL);
        myStatistics.put(Constants.MAX_HP, myBaseHP * myLevel * factor);
        myStatistics.put(Constants.CUR_HP, myBaseHP * myLevel * factor);
        myStatistics.put(Constants.ATTACK_LOWERCASE, myBaseAttack * myLevel * factor);
        myStatistics.put(Constants.DEFENSE, myBaseDefense * myLevel * factor);
    } 

    /**
     * Read in the stats for the monster
     * 
     * @param objInWorld object containing the monster's stats
     */
    private void readStats (SmartJsonObject objInWorld) {
        try {
            myStatistics.put(Constants.JSON_LEVEL, (double)objInWorld.getInt(Constants.JSON_LEVEL));
            myStatistics.put(Constants.EXP, (double)objInWorld.getInt(Constants.EXP));          
            myStatistics.put(Constants.EXP_TO_NEXT_LEVEL, (double) objInWorld.getInt(Constants.EXP_TO_NEXT_LEVEL));
            myStatistics.put(Constants.MAX_HP, (double)objInWorld.getInt(Constants.MAX_HP));
            myStatistics.put(Constants.CUR_HP, (double)objInWorld.getInt(Constants.CUR_HP));
            myStatistics.put(Constants.ATTACK_LOWERCASE, (double)objInWorld.getInt(Constants.ATTACK_LOWERCASE));
            myStatistics.put(Constants.DEFENSE, (double)objInWorld.getInt(Constants.DEFENSE));
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
        myType = new Type(definition.getString(Constants.TYPE));
        
        myStatistics.put(Constants.JSON_MONSTER_CATCH_RATE, definition.getDouble(Constants.JSON_MONSTER_CATCH_RATE));
        myStatistics.put(Constants.BASE_HP, (double) definition.getInt(Constants.BASE_HP));
        myStatistics.put(Constants.BASE_ATTACK, (double) definition.getInt(Constants.BASE_ATTACK));
        myStatistics.put(Constants.BASE_DEFENSE, (double) definition.getInt(Constants.BASE_DEFENSE));

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
            if (aw.canUse(getLevel().intValue())) {
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
        double myExp = myStatistics.get(Constants.EXP);
        myExp+=exp;
        myStatistics.put(Constants.EXP, myExp);
        while (myExp >= myStatistics.get(Constants.EXP_TO_NEXT_LEVEL)) {
            System.out.printf("Adding experience: %d exp to next: %d curr exp: %d \n", exp,
                              myStatistics.get(Constants.EXP_TO_NEXT_LEVEL), 
                              myStatistics.get(Constants.EXP));
            double myExpToNextLevel = myStatistics.get(Constants.EXP_TO_NEXT_LEVEL);
            myStatistics.put(Constants.EXP, myExp-myExpToNextLevel);
            levelUp();
            didLevelUp = true;
            if (myEvolution.exists() && myEvolution.shouldEvolve(myStatistics.get(Constants.JSON_LEVEL).intValue())) {
                System.out.println("Evolving!!!");
                didEvolve = true;
                evolve();
            }
        }
        return LevelChange.getStateChange(didLevelUp, didEvolve);
    }

    private void levelUp () {
        double myLevel = myStatistics.get(Constants.JSON_LEVEL);
        myStatistics.put(Constants.JSON_LEVEL, myLevel++);
        updateBaseStatistics(LEVEL_FACTOR);
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
        setEvolution(myEvolution.getNextEvolution()); 
        generateStats();
    }
    
    private void updateBaseStatistics (int factor) {
        
        double myLevel = myStatistics.get(Constants.JSON_LEVEL);
        double myExpToNextLevel = myStatistics.get(Constants.EXP_TO_NEXT_LEVEL) + factor*EXP_FACTOR*myLevel;
        double myMaxHP = myStatistics.get(Constants.MAX_HP) + factor*myLevel;        
        double myAttack = myStatistics.get(Constants.ATTACK_LOWERCASE) + factor*myLevel;
        double myDefense = myStatistics.get(Constants.DEFENSE) + factor*myLevel;
        
        myStatistics.put(Constants.EXP_TO_NEXT_LEVEL, myExpToNextLevel);
        myStatistics.put(Constants.MAX_HP, myMaxHP);
        myStatistics.put(Constants.CUR_HP, myMaxHP);
        myStatistics.put(Constants.ATTACK_LOWERCASE, myAttack);
        myStatistics.put(Constants.DEFENSE, myDefense);
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
