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
    private List<KeyItem> myKeyItems;
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
            
            myParty = new ArrayList<Monster>();
            JSONArray myMonstersJSON = objInWorld.getJSONArray(Constants.MONSTERS_LOWERCASE);
            for (Object monsterObj : myMonstersJSON) {
                SmartJsonObject monsterInWorld = new SmartJsonObject((JSONObject) monsterObj);
                SmartJsonObject monsterDefinition =
                        getModel().getDefinitionCache()
                                .getInstance(Constants.MONSTER_UPPERCASE,
                                             monsterInWorld.getString(Constants.JSON_NAME));
               Monster monster = new Monster(getModel(), monsterDefinition, monsterInWorld);
               myParty.add(monster);
            }
            int x = objInWorld.getInt(Constants.JSON_X);
            int y = objInWorld.getInt(Constants.JSON_Y);
            myLastSavedX = x;
            myLastSavedY = y;
            setLoc(new Loc(x, y), getWorld());

            String directionStr = objInWorld.getString(Constants.JSON_ORIENTATION);
            setDirection(Direction.constructFromString(directionStr));

            // ADDING KEY ITEMS
            myKeyItems = new ArrayList<KeyItem>();
            JSONArray playerKeyItems = objInWorld.getJSONArray(Constants.JSON_KEYITEMS);
            List<KeyItem> keyItems = new ArrayList<KeyItem>();
            for (Object o : playerKeyItems) {
                keyItems.add(new KeyItem(getModel(), getModel().getDefinitionCache().getInstance(Constants.TEXT_KEY_ITEM, (String)o)));
            }
            setKeyItems(keyItems);
            
            myItems = new ArrayList<Item>();
            List<Item> items = new ArrayList<Item>();
            JSONArray playerItems = objInWorld.getJSONArray(Constants.TEXT_ITEMS_LOWERCASE); //TODO
            for (Object o : playerItems) {
//            	System.out.println(o.toString());
            	//TODO: print statement take out
                items.add(new Item(getModel(), getModel().getDefinitionCache().getInstance(Constants.TEXT_ITEM, (String)o)));
            }
            setItems(items);
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
    public void setKeyItems (List<KeyItem> keyItems) {
        myKeyItems = keyItems;
    }
    
    public void setItems(List<Item> items){
    	myItems = items;
    }

    /**
     * 
     * @return - Key Items of the Player
     */
    public List<KeyItem> getKeyItems () {
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
        toSave.put("items", getItemsToSave());
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
    
    private JSONArray getItemsToSave(){
        JSONArray array = new JSONArray();
        for(Item item : myItems){
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
