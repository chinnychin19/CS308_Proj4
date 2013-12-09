package game.model.effect;

import game.model.Monster;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

public class StatisticEffect {
    private String myStatisticName;
    private int myChange;

    public StatisticEffect (SmartJsonObject object) {
        try {
            
            myStatisticName = object.getString(Constants.JSON_NAME);
            myChange = object.getInt(Constants.CHANGE);
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
    
    public void apply(Monster m) {
        m.changeStat(myStatisticName, myChange);
    }
}
