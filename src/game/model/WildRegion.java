package game.model;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


public class WildRegion extends AbstractGround { // TODO: extend AbstractGroundObject

    private Image myImage;
    private double myProbability;
    private List<MonsterWrapper> myMonsters;

    public WildRegion (GameModel model, World world, SmartJsonObject definition, SmartJsonObject objInWord) {
        super(model, world, definition, objInWord);

        try {
            myProbability = definition.getDouble(Constants.JSON_PROB);
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
            }
        }
    }
    
    private Monster selectMonster(double rand){
        double seen = 0;
        for(MonsterWrapper m : myMonsters){
            if(m.shouldUseMonster(rand, seen))
                return m.getMonster();
            seen += m.getProbability();
        }
        return null;
    }
    //
    // // frequency of tile
    // // frequnecy of monsters
    private class MonsterWrapper {
        private Monster myMonster;
        private double myProbability;

        public MonsterWrapper (Monster monster, double probability) {
            myMonster = monster;
            myProbability = probability;
        }

        public Monster getMonster () {
            return myMonster;
        }
        
        public double getProbability () {
            return myProbability;
        }
        
        public boolean shouldUseMonster (double rand, double sumOfAttemptedProbabilities) {
            return (sumOfAttemptedProbabilities + myProbability >= rand);
        }
    }
}
