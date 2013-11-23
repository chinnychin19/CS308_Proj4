package game.model;

import game.controller.AbstractMode;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;
import location.Direction;
import location.Loc;


public class Player extends AbstractCharacter implements Fighter {
    private List<Monster> myParty;
    private List<Item> myItems;
    private Collection<KeyItem> myKeyItems;

    public Player(GameModel model, World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, world, definition, objInWorld);
        try {
            myKeyItems = new HashSet<KeyItem>();
            myKeyItems.add(new KeyItem(model,"razor"));//TODO: REMOVE
            myParty = new ArrayList<Monster>(); //TODO: populate
            JSONArray myMonstersJSON = objInWorld.getJSONArray("monsters");
            for (Object monsterObj : myMonstersJSON) {
                SmartJsonObject monsterInWorld = new SmartJsonObject((JSONObject) monsterObj);
                SmartJsonObject monsterDefinition =
                        getModel().getDefinitionCache()
                                .getInstance("Monster", monsterInWorld.getString(Constants.JSON_NAME));
                myParty.add(new Monster(getModel(), monsterDefinition, monsterInWorld));
            }
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
    
    public void setKeyItems(Collection<KeyItem> keyItems){
        myKeyItems = keyItems;
    }
    
    public Collection<KeyItem> getKeyItems() {
        return myKeyItems;
    }

    @Override
    public List<Monster> getParty () {
        return myParty;
    }

    @Override
    public void doFrame (World w, boolean[] inputs) { //TODO: inputs should be an object
      Direction dir = getMoveDirection(inputs);
      if (null != dir) {
          setDirection(dir);
          Loc target = getLoc().adjacentLoc(getDirection());
          if (!w.isLocOccupied(target)) {
              setLoc(target, w);
          }
      }
    }
    
    //TODO: this method should be in the inputs object
    private Direction getMoveDirection(boolean[] inputs) {
        if (inputs[AbstractMode.INDEX_UP]) {
            return Direction.UP;
        }
        if (inputs[AbstractMode.INDEX_LEFT]) {
            return Direction.LEFT;
        }
        if (inputs[AbstractMode.INDEX_DOWN]) {
            return Direction.DOWN;
        }
        if (inputs[AbstractMode.INDEX_RIGHT]) {
            return Direction.RIGHT;
        }
        return null;
    }
}
