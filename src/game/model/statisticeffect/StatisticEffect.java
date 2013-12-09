package game.model.statisticeffect;

import game.model.Monster;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

public class StatisticEffect {
    private String myStatisticName;
    private int myChange;

    public StatisticEffect (SmartJsonObject object) {
        try {
            
            myStatisticName = object.getString(Constants.TEXT_STAT_NAME);
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
