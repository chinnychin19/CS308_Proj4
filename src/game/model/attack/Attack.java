package game.model.attack;

import java.util.ArrayList;
import java.util.Collection;
import game.model.AbstractModelObject;
import game.model.GameModel;
import game.model.Monster;
import game.model.effect.AbstractStatusEffect;
import game.model.effect.NullStatusEffect;
import game.model.effect.StatisticEffect;
import game.model.effect.StatusEffect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import constants.Constants;
import util.Target;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;


public class Attack extends AbstractModelObject {

    private int myPower;
    private double myAccuracy;
    private Collection<StatisticEffectWrapper> myStatisticEffects;
    private AbstractStatusEffect myStatusEffect;
    
    public Attack (GameModel model, SmartJsonObject definition) {
        super(model, definition);
    }

    @Override
    protected void readDefinition (SmartJsonObject definition) throws SmartJsonException {
        super.readDefinition(definition);
        myPower = definition.getInt(Constants.JSON_POWER);
        myAccuracy = definition.getDouble(Constants.JSON_ACCURACY);
        myStatisticEffects = new ArrayList<StatisticEffectWrapper>();
        try {
            JSONArray statisticsArray = definition.getJSONArray(Constants.STATISTIC_EFFECTS);
            for (Object statObject : statisticsArray) {
                SmartJsonObject statisticJSON = new SmartJsonObject((JSONObject) statObject);
                String target = statisticJSON.getString(Constants.JSON_TARGET);
                StatisticEffect effect = new StatisticEffect(statisticJSON);
                myStatisticEffects.add(new StatisticEffectWrapper(target, effect));

            }
        }
        catch (SmartJsonException e) {
            // nothing. statistic effects remains an empty list
        }
        try {
            SmartJsonObject statusJSON = definition.getSmartJsonObject(Constants.JSON_STATUS_EFFECT);
            myStatusEffect = new StatusEffect(getModel(), statusJSON);
        }
        catch (NoJSONObjectJsonException e) {
            myStatusEffect = new NullStatusEffect(getModel());
        }
    }
    public AttackResult doAttack (Monster attacker, Monster defender) {
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
        
        System.out.println("My status effect: "+myStatusEffect);
        if(myStatusEffect.targetIsSelf()) {
            myStatusEffect.apply(attacker);
        } else{
            myStatusEffect.apply(defender);
        }
        
        return new AttackResult(attacker.getName(), getName(), damage, multiplier, true);
    }
    
    private double damageFunction(int attackLevel, int attack, int defense, int power, double multiplier) {
        return 5 * (2 + ( 2 + (attackLevel * 0.4)) * attack * power / 50 / defense) * multiplier;
    }

    @Override
    public String toString () {
        return "" + myPower + "\t" + myAccuracy + "\t" + myStatisticEffects.toString() +
               myStatusEffect.toString();
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
}
