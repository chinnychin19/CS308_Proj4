package game.model.attack;

import java.util.ArrayList;
import java.util.Collection;
import game.model.AbstractModelObject;
import game.model.GameModel;
import game.model.Monster;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.Target;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoIntValueJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;


public class Attack extends AbstractModelObject {
    private static final String JSON_POWER = "power";
    private static final String JSON_ACCURACY = "accuracy";
    private static final String JSON_STATISTIC_EFFECT = "statisticEffect";
    private static final String JSON_STATUS_EFFECT = "statusEffect";

    private int myPower;
    private double myAccuracy;
    Collection<StatisticEffect> myStatisticEffects;
    Collection<StatusEffect> myStatusEffects;

    public Attack (GameModel model, SmartJsonObject definition) {
        super(model, definition);
        myStatisticEffects = new ArrayList<Attack.StatisticEffect>();
        myStatusEffects = new ArrayList<Attack.StatusEffect>();

        try {
            myPower = definition.getInt(JSON_POWER);

            myAccuracy = definition.getDouble(JSON_ACCURACY);
            JSONArray statisticsArray = definition.getJSONArray(JSON_STATISTIC_EFFECT);
            for (Object statObject : statisticsArray) {
                SmartJsonObject json = new SmartJsonObject((JSONObject) statObject);
                myStatisticEffects.add(new StatisticEffect(json));

            }
            JSONArray statusArray = definition.getJSONArray(JSON_STATUS_EFFECT);
            for (Object statusObject : statusArray) {
                SmartJsonObject json = new SmartJsonObject((JSONObject) statusObject);
                myStatusEffects.add(new StatusEffect(json));
            }
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }

    public double doAttack (Monster attacker, Monster defender) {
        //TODO: consider accuracy
        int attack = attacker.getAttack().intValue();
        int defense = defender.getDefense().intValue();
        double multiplier = getModel().getTypeMatrix().getDamageMultiplier(attacker.getType(),
                                                                           defender.getType());
        double damage = damageFunction(attacker.getLevel().intValue(), attack, defense, myPower, multiplier);
        System.out.println("damage: "+damage);
        defender.changeHealth((int)(-damage));
//        for(StatisticEffect se : myStatisticEffects){
//            Monster receiver = (se.myTarget == Target.SELF) ? attacker : defender;
//            receiver.changeStatistic(se.myStatistic, se.myChange);            
//        }
        return damage;
    }
    
    private double damageFunction(int attackLevel, int attack, int defense, int power, double multiplier) {
        return 5 * (2 + ( 2 + (attackLevel * 0.4)) * attack * power / 50 / defense) * multiplier;
    }

    @Override
    public String toString () {
        return "" + myPower + "\t" + myAccuracy + "\t" + myStatisticEffects.toString() +
               myStatusEffects.toString();
    }

    private class StatisticEffect {
        private Target myTarget;
        private String myStatistic;
        private int myChange;

        public StatisticEffect (SmartJsonObject definition) {
            try {
                myTarget = Target.getTarget(definition.getString("target"));
                myStatistic = definition.getString("statistic");
                myChange = definition.getInt("change");
            }
            catch (SmartJsonException e) {
                e.printStackTrace();
            }
        }
    }

    private class StatusEffect {
        private String myStatus;
        private Target myTarget;

        public StatusEffect (SmartJsonObject definition) {
            try {
                myTarget = Target.getTarget(definition.getString("target"));
                myStatus = definition.getString("status");
            }
            catch (SmartJsonException e) {
                e.printStackTrace();
            }
        }
    }
}
