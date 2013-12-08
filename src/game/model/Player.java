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


/**
 * The Main Player class
 * Represents the user the player controllers
 * 
 * @author tylernisonoff
 * 
 */
public class Player extends AbstractCharacter implements Fighter, Saveable {
    private List<Monster> myParty;
    private List<Item> myItems;
    private Collection<KeyItem> myKeyItems;
    private int myLastSavedX, myLastSavedY;

    public Player (GameModel model,
                   World world,
                   SmartJsonObject definition,
                   SmartJsonObject objInWorld) {
        super(model, world, definition, objInWorld);
    }

    /**
     * Creates the player from a JSON object
     * Can come from initial world definition or a savedState
     * 
     * @param objInWorld - JSON Object that describes the player
     */
    protected void readWorld (SmartJsonObject objInWorld) throws SmartJsonException{
            super.readWorld(objInWorld);
            // ADDING MONSTERS
            
            myParty = new ArrayList<Monster>(); // TODO: populate
            JSONArray myMonstersJSON = objInWorld.getJSONArray(Constants.MONSTERS_LOWERCASE);
            for (Object monsterObj : myMonstersJSON) {
                SmartJsonObject monsterInWorld = new SmartJsonObject((JSONObject) monsterObj);
                SmartJsonObject monsterDefinition =
                        getModel().getDefinitionCache()
                                .getInstance(Constants.MONSTER_UPPERCASE,
                                             monsterInWorld.getString(Constants.JSON_NAME));
               
               myParty.add(new Monster(getModel(), monsterDefinition, monsterInWorld));
            }
            int x = objInWorld.getInt(Constants.JSON_X);
            int y = objInWorld.getInt(Constants.JSON_Y);
            myLastSavedX = x;
            myLastSavedY = y;
            setLoc(new Loc(x, y), getWorld());

            String directionStr = objInWorld.getString(Constants.JSON_ORIENTATION);
            setDirection(Direction.constructFromString(directionStr));

            // ADDING KEY ITEMS
            myKeyItems = new HashSet<KeyItem>();
            JSONArray playerKeyItems = objInWorld.getJSONArray(Constants.JSON_KEYITEMS);
            Collection<KeyItem> keyItems = new ArrayList<KeyItem>();
            for (Object o : playerKeyItems) {
                keyItems.add(new KeyItem(getModel(), getModel().getDefinitionCache().getInstance("KeyItem", (String)o)));
            }
            setKeyItems(keyItems);
    }  
    
    public void goToLastSavedLoc() {
        setLoc(new Loc(myLastSavedX, myLastSavedY), getWorld());
    }
    
    public void saveThisLoc() {
        myLastSavedX = getLoc().getX();
        myLastSavedY = getLoc().getY();
    }

    public void healAllMonsters () {
        for (Monster m : myParty) {
            m.heal();
        }
    }

    /**
     * sets a Collection of keyItems for a user
     * 
     * @param keyItems
     */
    public void setKeyItems (Collection<KeyItem> keyItems) {
        myKeyItems = keyItems;
    }

    /**
     * 
     * @return - Key Items of the Player
     */
    public Collection<KeyItem> getKeyItems () {
        return myKeyItems;
    }

    /**
     * 
     * @return - Key Items of the Player
     */
    public List<Item> getItems () {
        return myItems;
    }

    /**
     * Returns the Party for battle
     */
    @Override
    public List<Monster> getParty () {
        return myParty;
    }

    /**
     * Method called on each movement in wandering mode
     * Makes sure that the player is allowed to walk in a given space
     */
    @Override
    public void doFrame (World w, Input input) {
        super.doFrame(w, input);
        Direction dir = getMoveDirection(input);
        if (null != dir) {
            setDirection(dir);
            Loc target = getLoc().adjacentLoc(getDirection());
            if (!w.isLocOccupied(target)) {
                setLoc(target, w);
            }
        }
    }

    // TODO: this method should be in the inputs object
    private Direction getMoveDirection (Input input) {
        if (input.isKeyUpPressed()) { return Direction.UP; }
        if (input.isKeyLeftPressed()) { return Direction.LEFT; }
        if (input.isKeyDownPressed()) { return Direction.DOWN; }
        if (input.isKeyRightPressed()) { return Direction.RIGHT; }
        return null;
    }

    @Override
    protected void onInteract () {

    }

    @Override
    protected void onBack () {

    }
    

    @Override
    public JSONObject getSavedJson () {
        JSONObject toSave = super.getSavedJson();       
        toSave.put(Constants.JSON_KEYITEMS, getKeyItemsToSave());
        toSave.put(Constants.JSON_MONSTERS, getMonstersToSave());
        return toSave;
    }
    
    private JSONArray getKeyItemsToSave(){
        JSONArray array = new JSONArray();
        for(KeyItem item : myKeyItems){
            array.add(item.getName());
        }
        return array;
    }
    
    private JSONArray getMonstersToSave(){
        JSONArray array = new JSONArray();
        for(Monster m : myParty){
            array.add(m.getSavedJson());
        }
        return array;
    }
}
