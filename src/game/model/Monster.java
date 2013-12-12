package game.model;

import game.model.attack.Attack;
import game.model.battle.TemporaryStatistics;
import game.model.evolution.AbstractEvolution;
import game.model.evolution.Evolution;
import game.model.evolution.NullEvolution;
import game.model.status.Status;
import game.model.status.StatusOkay;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


public class Monster extends AbstractModelObject implements Saveable {

    private Image myImage;
    private Type myType;
    private double myCatchRate;
    private List<AttackWrapper> myAttacks;
    private Map<String, Integer> myStatistics;
    private AbstractEvolution myEvolution;
    private Status myStatus;

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
        myStatus = new StatusOkay(getModel());
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
        readFields(objInWorld);
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
    
    public Status getStatus() {
        return myStatus;
    }

    public void heal () {
        int maxHP = myStatistics.get(Constants.STAT_MAX_HP);
        myStatistics.put(Constants.STAT_CUR_HP, maxHP);
        myStatus = new StatusOkay(getModel());
    }
    
    public void setStatus(Status status) {
        myStatus = status;
    }

    /**
     * Get the attacks associated with the monster
     * 
     * @return list of attacks
     */
    public List<AttackWrapper> getMyAttacks () {
        return myAttacks;
    }
    
    public int getStat(String statName) {
        return myStatistics.get(statName);
    }

    /**
     * Change the health of the monster and see if the monster is at 0 health or max health
     * 
     * @param amount the change in the monster's health
     */
    public void changeHealth (int amount) {
        String strCurHP = Constants.STAT_CUR_HP;
        int maxHP = myStatistics.get(Constants.STAT_MAX_HP);
        int oldHP = myStatistics.get(strCurHP);
        int newHP = Math.max(0, oldHP + amount);
        // guarantees newHP is at least 0
        newHP = Math.min(newHP, maxHP);
        // guarantees newHP is less than or equal to maxHP
        myStatistics.put(strCurHP, newHP);
    }

    /**
     * Determines if a pokemon is dead
     * 
     * @return true if current HP is 0
     */
    public boolean isDead () {
        return myStatistics.get(Constants.STAT_CUR_HP) == 0;
    }

    /**
     * Stats for the monster
     */
    
    private void generateStats(){

        double factor = 1 + Math.log(35);
        myStatistics.put(Constants.STAT_EXP, 0);
        int level = myStatistics.get(Constants.JSON_LEVEL);
        int baseHP = myStatistics.get(Constants.STAT_BASE_HP);
        int baseAttack = myStatistics.get(Constants.STAT_BASE_ATTACK);
        int baseDefense = myStatistics.get(Constants.STAT_BASE_DEFENSE);
        myStatistics.put(Constants.STAT_MAX_HP, (int) (baseHP * level * factor));
        myStatistics.put(Constants.STAT_CUR_HP, myStatistics.get(Constants.STAT_MAX_HP));
        myStatistics.put(Constants.STAT_ATTACK, (int) (baseAttack * level * factor));
        myStatistics.put(Constants.STAT_DEFENSE, (int) (baseDefense * level * factor));
        myStatistics.put(Constants.STAT_EXP_TO_NEXT_LEVEL, getRewardExperience()*5);
    }

    /**
     * Read in the stats for the monster
     * 
     * @param objInWorld object containing the monster's stats
     */
    private void readFields (SmartJsonObject objInWorld) {
        try {
            myStatistics.put(Constants.JSON_LEVEL, objInWorld.getInt(Constants.JSON_LEVEL));
            myStatistics.put(Constants.STAT_EXP, objInWorld.getInt(Constants.STAT_EXP));
            myStatistics.put(Constants.STAT_EXP_TO_NEXT_LEVEL, objInWorld.getInt(Constants.STAT_EXP_TO_NEXT_LEVEL));
            myStatistics.put(Constants.STAT_MAX_HP, objInWorld.getInt(Constants.STAT_MAX_HP));
            myStatistics.put(Constants.STAT_CUR_HP, objInWorld.getInt(Constants.STAT_CUR_HP));
            myStatistics.put(Constants.STAT_ATTACK, objInWorld.getInt(Constants.STAT_ATTACK));
            myStatistics.put(Constants.STAT_DEFENSE, objInWorld.getInt(Constants.STAT_DEFENSE));
            

        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
        
        // Read status
        try {
            String statusName = objInWorld.getString(Constants.JSON_STATUS_LOWERCASE);
            SmartJsonObject statusDefinition = getModel().getDefinitionCache().getInstance(Constants.JSON_STATUS, statusName);
            myStatus = new Status(getModel(), statusDefinition);
        } catch (Exception e) {
            myStatus = new StatusOkay(getModel());
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

        myType = new Type(definition.getString(Constants.STAT_TYPE));

        myStatistics.put(Constants.STAT_BASE_HP, definition.getInt(Constants.STAT_BASE_HP));
        myStatistics.put(Constants.STAT_BASE_ATTACK, definition.getInt(Constants.STAT_BASE_ATTACK));
        myStatistics.put(Constants.STAT_BASE_DEFENSE, definition.getInt(Constants.STAT_BASE_DEFENSE));

        myAttacks = new ArrayList<AttackWrapper>();

        for (Object obj : definition.getJSONArray(Constants.JSON_MONSTER_ALL_ATTACKS)) {
            SmartJsonObject attackJson = new SmartJsonObject((JSONObject) obj);
            String name = attackJson.getString(Constants.NAME);
            SmartJsonObject attackDefinition =
                    getModel().getDefinitionCache().getInstance(Constants.ATTACK_UPPERCASE,
                                                                name);
            Attack attack = new Attack(getModel(), attackDefinition);
            int unlockLevel = attackJson.getInt(Constants.JSON_ATTACK_UNLOCK_LEVEL);
            myAttacks.add(new AttackWrapper(attack, unlockLevel));
        }
        myEvolution = readEvolution(definition);
    }

    public List<Attack> getAllAvailableAttacks () {
        List<Attack> attacks = new ArrayList<Attack>();
        for (AttackWrapper aw : myAttacks) {
            if (aw.canUse(myStatistics.get(Constants.JSON_LEVEL))) {
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
        int ret = 0;
        ret += myStatistics.get(Constants.STAT_ATTACK);
        ret += myStatistics.get(Constants.STAT_DEFENSE);
        ret += myStatistics.get(Constants.STAT_MAX_HP);
        return ret;
    }

    public LevelChange addExperience (int exp, TemporaryStatistics temp) {
        boolean didLevelUp = false, didEvolve = false;

//        System.out.println("cur exp: "+myStatistics.get(Constants.STAT_EXP));
//        System.out.println("exp to next level: " + myStatistics.get(Constants.STAT_EXP_TO_NEXT_LEVEL));
//        System.out.println("Adding experience: " + exp);
                          
        int newExp = exp + myStatistics.get(Constants.STAT_EXP);
        myStatistics.put(Constants.STAT_EXP, newExp);
        while (myStatistics.get(Constants.STAT_EXP) >= myStatistics.get(Constants.STAT_EXP_TO_NEXT_LEVEL)) {
            levelUp(temp);
            didLevelUp = true;
            System.out.println("exp to next level: "+myStatistics.get(Constants.STAT_EXP_TO_NEXT_LEVEL));
            changeStat(Constants.STAT_EXP, -myStatistics.get(Constants.STAT_EXP_TO_NEXT_LEVEL));
            if (myEvolution.exists() && myEvolution.shouldEvolve(myStatistics.get(Constants.JSON_LEVEL))) {
                didEvolve = true;
                evolve();
            }
        }
        return LevelChange.getStateChange(didLevelUp, didEvolve);
    }

    private void levelUp (TemporaryStatistics temp) {
        int hpBoost = getAStatisticIncremement();
        int attackBoost = getAStatisticIncremement();
        int defenseBoost = getAStatisticIncremement();
        temp.boostHP(hpBoost);
        temp.boostAttack(attackBoost);
        temp.boostDefense(defenseBoost);
        System.out.println("leveling up");
        changeStat(Constants.JSON_LEVEL, 1);
        changeStat(Constants.STAT_ATTACK, attackBoost);
        changeStat(Constants.STAT_DEFENSE, defenseBoost);
        changeStat(Constants.STAT_MAX_HP, hpBoost);
        changeStat(Constants.STAT_EXP_TO_NEXT_LEVEL, 10 * getAStatisticIncremement());
    }
    
    private int getAStatisticIncremement(){
        double wigglePercentage = .25;
        int level = myStatistics.get(Constants.JSON_LEVEL);
        int signChange = Math.random() > 0.5 ? 1 : -1;
        double factor = 1 + (signChange * wigglePercentage * Math.random());
        return (int) Math.round(factor * level);
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
        if(statName.equals(Constants.STAT_CUR_HP)){
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

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject getSavedJson () {
        JSONObject toSave = super.getSavedJson();
        toSave.put(Constants.JSON_LEVEL, ""+myStatistics.get(Constants.JSON_LEVEL));
        toSave.put(Constants.STAT_EXP, ""+myStatistics.get(Constants.STAT_EXP));
        toSave.put(Constants.STAT_EXP_TO_NEXT_LEVEL, ""+myStatistics.get(Constants.STAT_EXP_TO_NEXT_LEVEL));
        toSave.put(Constants.STAT_MAX_HP, ""+myStatistics.get(Constants.STAT_MAX_HP));
        toSave.put(Constants.STAT_CUR_HP, ""+myStatistics.get(Constants.STAT_CUR_HP));
        toSave.put(Constants.STAT_ATTACK, ""+myStatistics.get(Constants.STAT_ATTACK));
        toSave.put(Constants.STAT_DEFENSE, ""+myStatistics.get(Constants.STAT_DEFENSE));
        toSave.put(Constants.JSON_STATUS_LOWERCASE, myStatus.getName());

        return toSave;
    }
}
