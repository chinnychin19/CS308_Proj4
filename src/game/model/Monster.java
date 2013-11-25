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
//  private List<Monster> myEvolution; //this should not be a list...

    //To be called for an NPC's monsters or wild monsters
    //It will generate stats based on the level and base stats
    public Monster (GameModel model, SmartJsonObject definition, int level) {
        super(model, definition);
        readDefinition(definition);
        myLevel = level;
        generateStats();
    }
    
    //To be called for the Player's monsters because they already have stats
    public Monster(GameModel model, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, definition);
        readDefinition(definition);
        readStats(objInWorld);
    }
    
    public Image getImage () {
        return myImage;
    }

    public double getCatchRate () {
        return myCatchRate;
    }

    public Type getType () {
        return myType;
    }

    public int getBaseHP () {
        return myBaseHP;
    }

    public int getBaseAttack () {
        return myBaseAttack;
    }

    public int getBaseDefense () {
        return myBaseDefense;
    }

    public int getLevel () {
        return myLevel;
    }

    public int getExp () {
        return myExp;
    }

    public int getExpToNextLevel () {
        return myExpToNextLevel;
    }

    public int getMaxHP () {
        return myMaxHP;
    }

    public int getCurHP () {
        return myCurHP;
    }

    public int getAttack () {
        return myAttack;
    }

    public int getDefense () {
        return myDefense;
    }

    public List<AttackWrapper> getMyAttacks () {
        return myAttacks;
    }

    public void changeHealth(int amount) {
        myCurHP += amount;
        if (myCurHP < 0) {
            myCurHP = 0;
        } else if (myCurHP > myMaxHP) {
            myCurHP = myMaxHP;
        }
    }
    
    private void generateStats() {
        double factor = 1 + Math.log(35);
        myExp = 0;
        myExpToNextLevel = Integer.MAX_VALUE; //TODO: wild pokemon
        myMaxHP = (int) (myBaseHP * myLevel * factor);
        myCurHP = myMaxHP;
        myAttack = (int) (myBaseAttack * myLevel * factor);
        myDefense = (int) (myBaseDefense * myLevel * factor);
    }
        
    //TODO: make constants
    private void readStats(SmartJsonObject objInWorld) {
        try {
            myLevel = objInWorld.getInt(Constants.JSON_LEVEL);
            myExp = objInWorld.getInt("exp");
            myExpToNextLevel = objInWorld.getInt("expToNextLevel");
            myMaxHP = objInWorld.getInt("maxHP");
            myCurHP = objInWorld.getInt("curHP");
            myAttack = objInWorld.getInt("attack");
            myDefense = objInWorld.getInt("defense");
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
    
    //TODO: make constants
    private void readDefinition(SmartJsonObject definition) {
        try {
            String imageURL = definition.getString(Constants.JSON_IMAGE);
            myImage = new ImageIcon(imageURL).getImage();
            myCatchRate = definition.getDouble(Constants.JSON_MONSTER_CATCH_RATE);
            myType = new Type(definition.getString("type"));
            myBaseHP = definition.getInt("baseHP");
            myBaseAttack = definition.getInt("baseAttack");
            myBaseDefense = definition.getInt("baseDefense");
            myAttacks = new ArrayList<AttackWrapper>();
            for (Object obj : definition.getJSONArray(Constants.JSON_MONSTER_ALL_ATTACKS)) {
                SmartJsonObject attackJson = new SmartJsonObject((JSONObject) obj);
                String name = attackJson.getString("name");
                SmartJsonObject a = getModel().getDefinitionCache().getInstance("Attack", name);
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
}
