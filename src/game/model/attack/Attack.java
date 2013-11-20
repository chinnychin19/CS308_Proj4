package game.model.attack;

import java.util.ArrayList;
import java.util.Collection;
import game.model.AbstractModelObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.Target;

public class Attack extends AbstractModelObject {
    private int myPower;
    private double myAccuracy;
    Collection<StatisticEffect> myStatisticEffects;
    Collection<StatusEffect> myStatusEffects;

    public Attack(JSONObject definition){
        super(definition);
        myStatisticEffects = new ArrayList<Attack.StatisticEffect>();
        myStatusEffects = new ArrayList<Attack.StatusEffect>();

        myPower = Integer.parseInt(definition.get("power").toString());
        myAccuracy = Double.parseDouble(definition.get("accuracy").toString());
        JSONArray statisticsArray = (JSONArray)definition.get("statisticEffect");
        for(Object statObject : statisticsArray){
            JSONObject json = (JSONObject)statObject;
            myStatisticEffects.add(new StatisticEffect(json));
            
        }
        JSONArray statusArray = (JSONArray)definition.get("statusEffects");
        for(Object statusObject : statisticsArray){
            JSONObject json = (JSONObject)statusObject;
            myStatusEffects.add(new StatusEffect(json));
        }
    }
    
    @Override
    public String toString(){
        return ""+myPower+"\t"+myAccuracy+"\t"+myStatisticEffects.toString()+myStatusEffects.toString();
    }
    
    private class StatisticEffect {
        private Target myTarget;
        private String myStatistic;
        private int myChange;
        public StatisticEffect(JSONObject definition){
            myTarget = Target.getTarget(definition.get("target").toString());
            myStatistic = definition.get("statistic").toString();
            myChange = Integer.parseInt(definition.get("change").toString());
        }
    }
    
    private class StatusEffect {
        private String myStatus;
        private Target myTarget;
        public StatusEffect(JSONObject definition){
            myTarget = Target.getTarget(definition.get("target").toString());
            myStatus = definition.get("status").toString();
        }
    }
}
