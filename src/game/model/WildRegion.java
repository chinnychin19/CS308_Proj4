package game.model;

import game.controller.AbstractMode;
import game.controller.GameController;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


public class WildRegion extends AbstractGround {

    private Image myImage;
    private double myProbability;
    private List<MonsterWrapper> myMonsterWrappers;
    public WildRegion (GameModel model, World world, SmartJsonObject definition, SmartJsonObject objInWord) {
        super(model, world, definition, objInWord);

        try {
            myProbability = definition.getDouble(Constants.JSON_PROB);
            myMonsterWrappers = new ArrayList<MonsterWrapper>();
            for(Object objMonster : definition.getJSONArray(Constants.JSON_MONSTERS)){
                SmartJsonObject monsterInfo = new SmartJsonObject((JSONObject)objMonster);
                myMonsterWrappers.add(new MonsterWrapper(monsterInfo));
            }
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
        // TODO: Implement myMonsters
    }
    
    @Override
    public void doFrame(World w, boolean[] inputs){
        //if player on me, check prob
        if(w.getPlayer().getLoc().equals(getLoc())){
            double rand = Math.random();
            if(rand <= myProbability){
                System.out.println("WILD BATTLE MODE");
                Monster toFight = selectMonster();
                getModel().getController().setWildBattleMode(toFight);
            }
        }
    }
    
    private Monster selectMonster(){
        double rand = Math.random();
        double seen = 0;
        for(MonsterWrapper m : myMonsterWrappers){
            if(m.shouldUseMonster(rand, seen))
                return m.getMonster();
            seen += m.getProbability();
        }
        return null;
    }

    private class MonsterWrapper {
        private double myProbability;
        private String myName;
        private int myLevel;

        public MonsterWrapper (SmartJsonObject monsterInfo) {
            try{
                myProbability = monsterInfo.getDouble(Constants.JSON_PROB);
                myName = monsterInfo.getString(Constants.JSON_NAME);
                myLevel = monsterInfo.getInt(Constants.JSON_LEVEL);
            } catch(SmartJsonException e){
                e.printStackTrace();
            }
        }

        public Monster getMonster () {
            try{
                return new Monster(getModel(), getModel().getDefinitionCache()
                        .getInstance("Monster", myName), myLevel);
            } catch(SmartJsonException e){
                e.printStackTrace();
            }
            return null;
        }
        
        public double getProbability () {
            return myProbability;
        }
        
        public boolean shouldUseMonster (double rand, double sumOfAttemptedProbabilities) {
            return (sumOfAttemptedProbabilities + myProbability >= rand);
        }
    }
}
