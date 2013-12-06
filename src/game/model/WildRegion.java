package game.model;

import game.controller.Input;
import game.controller.WildBattleMode;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


/**
 * Represents a wild region - a ground region that stores monsters
 * At random times, if a user is standing on a wild region, they will
 * go into "Wild Battle Mode"
 * 
 * @author tylernisonoff
 * 
 */
public class WildRegion extends AbstractGround {

    private Image myImage;
    private double myProbability;
    private List<MonsterWrapper> myMonsterWrappers;

    public WildRegion (GameModel model,
                       World world,
                       SmartJsonObject definition,
                       SmartJsonObject objInWord) {
        super(model, world, definition, objInWord);

        try {
            myProbability = definition.getDouble(Constants.JSON_PROB);
            myMonsterWrappers = new ArrayList<MonsterWrapper>();
            for (Object objMonster : definition.getJSONArray(Constants.JSON_MONSTERS)) {
                SmartJsonObject monsterInfo = new SmartJsonObject((JSONObject) objMonster);
                myMonsterWrappers.add(new MonsterWrapper(monsterInfo));
            }
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
        // TODO: Implement myMonsters
    }

    /**
     * Frame-by-frame method for the Wild Region in Wandering Mode
     */
    @Override
    public void doFrame (World w, Input input) {
        // if player on me, check prob
        if (w.getPlayer().getLoc().equals(getLoc())) {
            double rand = Math.random();
            if (rand <= myProbability) {
                Monster toFight = selectMonster();
                // TODO: Discuss with Chinmay about better way to do this
                getModel().getController().setMode(new WildBattleMode(getModel(), getModel()
                        .getController().getView(), toFight));
            }
        }
    }

    /**
     * Randomly selects on monster from its list of monsters
     * based on their probability of being selected
     * 
     * @return
     */
    private Monster selectMonster () {
        double rand = Math.random();
        double seen = 0;
        for (MonsterWrapper m : myMonsterWrappers) {
            if (m.shouldUseMonster(rand, seen))
                return m.getMonster();
            seen += m.getProbability();
        }
        return null;
    }

    /**
     * Stores a Monster and its probability of being selected
     * Useful to couple to these two structures, while also allowing
     * a new monster to be generated every time through the getMonster method
     * 
     * @author tylernisonoff
     * 
     */
    private class MonsterWrapper {
        private double myProbability;
        private String myName;
        private int myLevel;

        public MonsterWrapper (SmartJsonObject monsterInfo) {
            try {
                myProbability = monsterInfo.getDouble(Constants.JSON_PROB);
                myName = monsterInfo.getString(Constants.JSON_NAME);
                myLevel = monsterInfo.getInt(Constants.JSON_LEVEL);
            }
            catch (SmartJsonException e) {
                e.printStackTrace();
            }
        }

        /**
         * Returns an instance of the Monster it is storing internally
         * 
         * @return a fresh Monster
         */
        public Monster getMonster () {
            try {
                return new Monster(getModel(), getModel().getDefinitionCache()
                        .getInstance("Monster", myName), myLevel);
            }
            catch (SmartJsonException e) {
                e.printStackTrace();
            }
            return null;
        }

        public double getProbability () {
            return myProbability;
        }

        /**
         * Determines if a monster should be selected based off of a random number
         * 
         * @param rand - the random number chosen for the selector
         * @param sumOfAttemptedProbabilities - the sum of the previous probabilities of monsters
         *        seen before
         * @return - true if the monster should be used, else false
         */
        public boolean shouldUseMonster (double rand, double sumOfAttemptedProbabilities) {
            return (sumOfAttemptedProbabilities + myProbability >= rand);
        }
    }
}
