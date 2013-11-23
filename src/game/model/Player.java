package game.model;

import game.controller.AbstractMode;
import game.controller.Input;

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

    public Player (GameModel model,
                   World world,
                   SmartJsonObject definition,
                   SmartJsonObject objInWorld) {
        super(model, world, definition, objInWorld);
        myKeyItems = new HashSet<KeyItem>();
        myKeyItems.add(new KeyItem(model, "razor"));// TODO: REMOVE
        myParty = new ArrayList<Monster>(); // TODO: populate
        loadFromWorld(objInWorld);
    }
    
    public void loadFromWorld(SmartJsonObject objInWorld){
        try {
            //ADDING MONSTERS
            myParty = new ArrayList<Monster>(); // TODO: populate
            JSONArray myMonstersJSON = objInWorld.getJSONArray("monsters");
            for (Object monsterObj : myMonstersJSON) {
                SmartJsonObject monsterInWorld = new SmartJsonObject((JSONObject) monsterObj);
                SmartJsonObject monsterDefinition =
                        getModel().getDefinitionCache()
                                .getInstance("Monster", monsterInWorld.getString(Constants.JSON_NAME));
                myParty.add(new Monster(getModel(), monsterDefinition, monsterInWorld));
            }
            
            int x = objInWorld.getInt(Constants.JSON_X);
            int y = objInWorld.getInt(Constants.JSON_Y);
            setLoc(new Loc(x, y), getWorld());

            String directionStr = objInWorld.getString(Constants.JSON_ORIENTATION);
            setDirection(Direction.constructFromString(directionStr));
            
            //ADDING KEY ITEMS
            myKeyItems = new HashSet<KeyItem>();
            JSONArray playerKeyItems = objInWorld.getJSONArray(Constants.JSON_KEYITEMS);
            Collection<KeyItem> keyItems = new ArrayList<KeyItem>();
            for (Object o : playerKeyItems) {
                keyItems.add(new KeyItem(getModel(), (String)o));
            }
            setKeyItems(keyItems);
        } catch(SmartJsonException e){
            
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
    public void doFrame (World w, Input input) { //TODO: inputs should be an object
      Direction dir = getMoveDirection(input);
      if (null != dir) {
          setDirection(dir);
          Loc target = getLoc().adjacentLoc(getDirection());
          if (!w.isLocOccupied(target)) {
              setLoc(target, w);
          }
      }
    }
    
    //TODO: this method should be in the inputs object
    private Direction getMoveDirection(Input input) {
        if (input.isKeyUpPressed()) {
            return Direction.UP;
        }
        if (input.isKeyLeftPressed()) {
            return Direction.LEFT;
        }
        if (input.isKeyDownPressed()) {
            return Direction.DOWN;
        }
        if (input.isKeyRightPressed()) {
            return Direction.RIGHT;
        }
        return null;
    }
}
