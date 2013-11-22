package game.model.attack;

import java.util.ArrayList;
import java.util.Collection;
import game.model.AbstractModelObject;
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

    public Attack (JSONObject definition) {
        super(definition);
        SmartJsonObject smartJSON = new SmartJsonObject(definition);
        myStatisticEffects = new ArrayList<Attack.StatisticEffect>();
        myStatusEffects = new ArrayList<Attack.StatusEffect>();

        try {
            myPower = smartJSON.getInt(JSON_POWER);

            myAccuracy = smartJSON.getDouble(JSON_ACCURACY);
            JSONArray statisticsArray = smartJSON.getJSONArray(JSON_STATISTIC_EFFECT);
            for (Object statObject : statisticsArray) {
                JSONObject json = (JSONObject) statObject;
                myStatisticEffects.add(new StatisticEffect(json));

            }
            JSONArray statusArray = smartJSON.getJSONArray(JSON_STATUS_EFFECT);
            for (Object statusObject : statusArray) {
                JSONObject json = (JSONObject) statusObject;
                myStatusEffects.add(new StatusEffect(json));
            }
        }
        catch (SmartJsonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

        public StatisticEffect (JSONObject definition) {
            myTarget = Target.getTarget(definition.get("target").toString());
            myStatistic = definition.get("statistic").toString();
            myChange = Integer.parseInt(definition.get("change").toString());
        }
    }

    private class StatusEffect {
        private String myStatus;
        private Target myTarget;

        public StatusEffect (JSONObject definition) {
            myTarget = Target.getTarget(definition.get("target").toString());
            myStatus = definition.get("status").toString();
        }
    }
}
