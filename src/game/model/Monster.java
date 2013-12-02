package game.model;

import game.model.attack.Attack;
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

public class Monster extends AbstractModelObject {

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
    private Monster myEvolution;
    //TODOL: EVOLUTION
    
    /**
     * To be called for an NPC's monsters or wild monster
     * It will generate stats based on the level and base stats 
     * @param model
     * @param definition
     * @param level
     */
    public Monster (GameModel model, SmartJsonObject definition, int level) {
        super(model, definition);
        readDefinition(definition);
        myLevel = level;
        generateStats();
    }
    
    /**
     * To be called for the Player's monsters because they already have stats
     * @param model
     * @param definition
     * @param objInWorld
     */
    public Monster(GameModel model, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, definition);
        readDefinition(definition);
        readStats(objInWorld);
    }
    
    /**
     * Get the image of the monster
     * @return monster's image
     */
    public Image getImage () {
        return myImage;
    }
    /**
     * Get the catch rate of the monster.  A value of how difficult it is to catch this monster
     * @return monster's catch rate
     */
    public double getCatchRate () {
        return myCatchRate;
    }
    
    /**
     * Gets the type of the monster
     * @return type of monster
     */
    public Type getType () {
        return myType;
    }
    /**
     * Gets the base HP of the monster
     * @return base HP of the monster
     */
    public int getBaseHP () {
        return myBaseHP;
    }
    /**
     * Gets the base attack of the monster
     * @return base attack of the monster 
     */
    public int getBaseAttack () {
        return myBaseAttack;
    }
    /**
     * Gets the base defense of the monster
     * @return base defense of the monster
     */
    public int getBaseDefense () {
        return myBaseDefense;
    }
    /**
     * Gets the level of the monster
     * @return Monster's level
     */
    public int getLevel () {
        return myLevel;
    }
    /**
     * Gets the experience of the monster
     * @return experience of the monster
     */
    public int getExp () {
        return myExp;
    }
    /**
     * Get the amount of experience that is needed to move onto the next level
     * @return amount to move onto the next level
     */
    public int getExpToNextLevel () {
        return myExpToNextLevel;
    }
    /**
     * Gets the max HP of the monster
     * @return Max hp of monster
     */
    public int getMaxHP () {
        return myMaxHP;
    }
    /**
     * Gets the current HP of the monster
     * @return the current HP
     */
    public int getCurHP () {
        return myCurHP;
    }
    /**
     * Gets the attack value
     * @return attack value
     */
    public int getAttack () {
        return myAttack;
    }
    /**
     * defense value of the monster
     * @return defense value
     */
    public int getDefense () {
        return myDefense;
    }
    
    public void heal() {
        myCurHP = myMaxHP;
    }
    /**
     * Get the attacks associated with the monster
     * @return list of attacks
     */
    public List<AttackWrapper> getMyAttacks () {
        return myAttacks;
    }
    /**
     * Change the health of the monster and see if the monster is at 0 health or max health
     * @param amount the change in the monster's health
     */
    public void changeHealth(int amount) {
        myCurHP += amount;
        if (myCurHP < 0) {
            myCurHP = 0;
        } else if (myCurHP > myMaxHP) {
            myCurHP = myMaxHP;
        }
    }
    
    /**
     * Determines if a pokemon is dead
     * @return true if current HP is 0
     */
    public boolean isDead(){
        return myCurHP == 0;
    }
    /**
     * Stats for the monster
     */
    private void generateStats() {
        double factor = 1 + Math.log(35);
        myExp = 0;
        myExpToNextLevel = Integer.MAX_VALUE; //TODO: wild pokemon
        myMaxHP = (int) (myBaseHP * myLevel * factor);
        myCurHP = myMaxHP;
        myAttack = (int) (myBaseAttack * myLevel * factor);
        myDefense = (int) (myBaseDefense * myLevel * factor);
    }
    /**
     * Read in the stats for the monster    
     * @param objInWorld object containing the monster's stats
     */
    private void readStats(SmartJsonObject objInWorld) {
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
     * @param definition the file containing the monster's info
     */
    private void readDefinition(SmartJsonObject definition) {
        try {
            String imageURL = definition.getString(Constants.JSON_IMAGE);
            myImage = new ImageIcon(imageURL).getImage();
            myCatchRate = definition.getDouble(Constants.JSON_MONSTER_CATCH_RATE);
            myType = new Type(definition.getString(Constants.TYPE));
            myBaseHP = definition.getInt(Constants.BASE_HP);
            myBaseAttack = definition.getInt(Constants.BASE_ATTACK);
            myBaseDefense = definition.getInt(Constants.BASE_DEFENSE);
            myAttacks = new ArrayList<AttackWrapper>();
            for (Object obj : definition.getJSONArray(Constants.JSON_MONSTER_ALL_ATTACKS)) {
                SmartJsonObject attackJson = new SmartJsonObject((JSONObject) obj);
                String name = attackJson.getString(Constants.NAME);
                SmartJsonObject a = getModel().getDefinitionCache().getInstance(Constants.ATTACK_UPPERCASE, name);
                Attack attack = new Attack(getModel(), a);
                int unlockLevel = attackJson.getInt(Constants.JSON_ATTACK_UNLOCK_LEVEL);
                myAttacks.add(new AttackWrapper(attack, unlockLevel));
                // TODO: Implement myEvolution
            }
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
        
    public List<Attack> getAllAvailableAttacks(){
        List<Attack> attacks = new ArrayList<Attack>();
        for(AttackWrapper aw : myAttacks){
            if(aw.canUse(myLevel)){
                attacks.add(aw.getAttack());
            }
        }
        return attacks;
    }
    
    private class AttackWrapper{
        private Attack myAttack;
        private int myUnlockLevel;
        public AttackWrapper(Attack a, int unlockLevel){
            myAttack = a;
            myUnlockLevel = unlockLevel;
        }
     
        public Attack getAttack(){
            return myAttack;
        }
        
        public boolean canUse(int level){
            return myUnlockLevel <= level;
        }
    }

    /**
     *  Returns the probability of catching this monster.  The probability decreases as a monster
     *  increases their level, and increases as the monster loses more HP. TODO - Incorporating
     *  the current status of the monster into this equation
     *  HealthFactor returns the minimum between .01 and 1-CurrentHP/MaxHP - so if they have 100% health,
     *  the HealthFactor will be .01 instead of 0 so that there is still a chance to catch the pokemon.
     *  Otherwise, it would be impossible.
     * @return
     */
    public double getCatchProbability () {
      double levelFactor = 1/this.getLevel();
      double healthFactor = Math.min(1 - this.getCurHP()/this.getMaxHP(), 0.01);
      return this.getCatchRate()*levelFactor*healthFactor;
              //TODO - Statuses
    }

    public int getRewardExperience () {
        return 100; //TODO: Actually implement
    }

    public void addExperience (int exp) {
        myExp+= exp;
        while(myExp >= myExpToNextLevel){
            myLevel++;
            myExp = myExp - myExpToNextLevel;         
        }
        //TODO: EVOLUTION
    }
}
