package game.model.statisticeffect;

import game.model.Monster;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;

public abstract class AbstractStatisticEffect {

    private String myStatisticName;
    private int myChange;

    public AbstractStatisticEffect (SmartJsonObject object) {
        try {
            
            myStatisticName = object.getString("statName");
            myChange = object.getInt(Constants.CHANGE);
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
    
    public String getStatisticName(){
    	return myStatisticName;
    }
    public int getChange(){
    	return myChange;
    }
    
    public abstract void apply(Monster monster);
}
