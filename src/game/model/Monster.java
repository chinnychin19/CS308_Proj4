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

    private int myMaxHP;
    private double myCatchRate;
    private List<AttackWrapper> myAttacks;
    private Image myImage;
    private int myCurrentLevel;
//  private List<Monster> myEvolution; //this should not be a list...

    //To be called for an NPC's monsters or wild monsters
    //It will generate stats based on the level and base stats
    public Monster (GameModel model, SmartJsonObject definition) {
        super(model, definition);
        readDefinition(definition);
        //TODO: generate stats for maxHP, curHP, attack, defense
    }
    
    //To be called for the Player's monsters because they already have stats
    public Monster(GameModel model, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, definition);
        readDefinition(definition);
        readStats(objInWorld);
    }
    
    private void readStats(SmartJsonObject objInWorld) {
        //TODO: read in cur stats for maxHP, curHP, attack, defense
    }
    
    private void readDefinition(SmartJsonObject definition) {
        try {
            String imageURL = definition.getString(Constants.JSON_IMAGE);
            myImage = new ImageIcon(imageURL).getImage();
            myMaxHP = definition.getInt(Constants.JSON_MONSTER_MAX_HP);
            myCatchRate = definition.getDouble(Constants.JSON_MONSTER_CATCH_RATE);
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
    
    public Image getImage(){
        return myImage;
    }
        
    public List<Attack> getAllAvailableAttacks(){
        List<Attack> attacks = new ArrayList<Attack>();
        for(AttackWrapper aw : myAttacks){
            if(aw.canUse(myCurrentLevel)){
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
