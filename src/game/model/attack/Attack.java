package game.model.attack;

import java.util.ArrayList;
import java.util.Collection;
import game.model.AbstractModelObject;
import game.model.GameModel;
import game.model.Monster;
import game.model.statisticeffect.StatisticEffect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import constants.Constants;
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
    Collection<StatisticEffectWrapper> myStatisticEffects;
    Collection<StatusEffect> myStatusEffects;
    //TODO: move to readDefinition
    public Attack (GameModel model, SmartJsonObject definition) {
        super(model, definition);
    }

    @Override
    protected void readDefinition (SmartJsonObject definition) throws SmartJsonException {
        super.readDefinition(definition);
        myPower = definition.getInt(JSON_POWER);
        myAccuracy = definition.getDouble(JSON_ACCURACY);
        myStatisticEffects = new ArrayList<StatisticEffectWrapper>();
        myStatusEffects = new ArrayList<Attack.StatusEffect>();
        JSONArray statisticsArray = definition.getJSONArray(JSON_STATISTIC_EFFECT);
        for (Object statObject : statisticsArray) {
            SmartJsonObject statisticJSON = new SmartJsonObject((JSONObject) statObject);
            String target = statisticJSON.getString("target");
            StatisticEffect effect = new StatisticEffect(statisticJSON);
            myStatisticEffects.add(new StatisticEffectWrapper(target, effect));

        }
        JSONArray statusArray = definition.getJSONArray(JSON_STATUS_EFFECT);
        for (Object statusObject : statusArray) {
            SmartJsonObject json = new SmartJsonObject((JSONObject) statusObject);
            myStatusEffects.add(new StatusEffect(json));
        }
    }
    public AttackResult doAttack (Monster attacker, Monster defender) {
        //TODO: consider accuracy
        int attack = attacker.getStat(Constants.STAT_ATTACK);
        int defense = defender.getStat(Constants.STAT_DEFENSE);
        double multiplier = getModel().getTypeMatrix().getDamageMultiplier(attacker.getType(),
                                                                           defender.getType());
        if (Math.random() > myAccuracy) { //attack missed
            return new AttackResult(attacker.getName(), getName(), 0, multiplier, false);
        }
        int attackerLevel = attacker.getStat(Constants.JSON_LEVEL);
        int damage = (int) Math.round(damageFunction(attackerLevel, attack, defense, myPower, multiplier));
        damage = Math.min(damage, defender.getStat(Constants.STAT_CUR_HP));
        defender.changeHealth(-damage);
        
        for(StatisticEffectWrapper effectWrap : myStatisticEffects){
            if(effectWrap.targetIsSelf()){
                effectWrap.apply(attacker);
            } else{
                effectWrap.apply(defender);
            }
        }
        
        return new AttackResult(attacker.getName(), getName(), damage, multiplier, true);
    }
    
    private double damageFunction(int attackLevel, int attack, int defense, int power, double multiplier) {
        return 5 * (2 + ( 2 + (attackLevel * 0.4)) * attack * power / 50 / defense) * multiplier;
    }

    @Override
    public String toString () {
        return "" + myPower + "\t" + myAccuracy + "\t" + myStatisticEffects.toString() +
               myStatusEffects.toString();
    }

  
    private class StatisticEffectWrapper{
        private Target myTarget;
        private StatisticEffect myEffect;
        public StatisticEffectWrapper(String target, StatisticEffect effect){
            myTarget = Target.getTarget(target);
            myEffect = effect;
        }
        
        public boolean targetIsSelf(){
            return myTarget==Target.SELF;
        }
        
        public void apply(Monster m){
            myEffect.apply(m);
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
