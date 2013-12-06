package game.model;

import game.controller.Input;
import game.controller.TrainerBattleMode;
import java.util.ArrayList;
import java.util.List;
import location.Direction;
import location.Loc;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


/**
 * A class used to create FightingNPCs and to interact with the Player. The NPC will check to see
 * whether or not the Player is within its line of sight. If so,
 * the NPC will move towards the player and go into battle mode.
 * 
 * @author rtoussaint
 * 
 */
//TODO: Use readDefinition 
public class FightingNPC extends NPC implements Fighter {
    private List<Monster> myParty;
    private String myPostDialogue;
    private List<KeyItem> myKeyItems;
    private int myBet;
    private int myLineOfSightDistance;
    private boolean myIsDefeated;

    public FightingNPC (GameModel model,
                        World world,
                        SmartJsonObject definition,
                        SmartJsonObject objInWorld) {
        super(model, world, definition, objInWorld);
        try {
            myPostDialogue = definition.getString(Constants.JSON_POST_DIALOGUE);
            myKeyItems = new ArrayList<KeyItem>();
            for (Object obj : definition.getJSONArray(Constants.JSON_KEYITEMS)) {
                myKeyItems.add(new KeyItem(model, getModel().getDefinitionCache().getInstance(Constants.JSON_KEYITEM, obj.toString())));
            }
            myIsDefeated = false;
            myBet = definition.getInt(Constants.JSON_BET);
            myLineOfSightDistance = definition.getInt(Constants.JSON_LINE_OF_SIGHT_DISTANCE);
            myParty = new ArrayList<Monster>();
            JSONArray myMonstersJSON = definition.getJSONArray(Constants.MONSTERS_LOWWERCASE);
            for (Object monsterObj : myMonstersJSON) {
                SmartJsonObject monsterInWorld = new SmartJsonObject((JSONObject) monsterObj);
                SmartJsonObject monsterDefinition =
                        getModel().getDefinitionCache()
                                .getInstance(Constants.MONSTER_UPPERCASE,
                                             monsterInWorld.getString(Constants.JSON_NAME));
                myParty.add(new Monster(getModel(), monsterDefinition, monsterInWorld));
            }
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
    
    public void setDefeated(boolean defeated) {
        myIsDefeated = defeated;
    }

    /**
     * Returns the NPC's line of sight distance. Essentially, how far ahead the NPC can see -- used
     * for detecting when the main
     * player is within range to interact with
     * 
     * @return myLineOfSightDistance
     */
    public int getLineOfSightDistance () {
        return myLineOfSightDistance;
    }

    /**
     * Check to see if the FightingNPC should interact with the Player
     */
    @Override
    public void doFrame (World w, Input input) {
        super.doFrame(w, input);
        boolean playerWithinRange = checkLineOfSight();
        if (playerWithinRange) {
            moveTowardsPlayer();
            onInteract();
        }
    }

    /**
     * Check to see if the player is within the line of sight of the player
     * 
     * @return whether or not the NPC can 'see' the player
     */
    private boolean checkLineOfSight () {
        if (myIsDefeated) {
            return false;
        }
        int sight = 0;
        Loc tempLoc = getLoc();
        while (sight <= myLineOfSightDistance) {
            if (tempLoc.equals(getModel().getPlayer().getLoc())) { return true; }
            tempLoc = tempLoc.adjacentLoc(getDirection());
            sight++;
        }
        return false;
    }

    /**
     * move towards the player when it is within the NPC's line of sight
     */
    private void moveTowardsPlayer () {
        // /TODO: freeze player keyboard for movement/animation
        Direction oppositeDirection = Direction.opposite(getDirection());
        while (!getLoc().equals(getModel().getPlayer().getLoc().adjacentLoc(oppositeDirection))) {
            setLoc(getLoc().adjacentLoc(getDirection()), getWorld());
        }
        getModel().getPlayer().setDirection(oppositeDirection);
    }

    /**
     * Returns the NPC's key items. These are items that will help the main player get through
     * obstacles and advance through the game.
     * 
     * @return myKeyItems
     */
    public List<KeyItem> getKeyItem () {
        return myKeyItems;
    }

    /**
     * Returns the NPC's party, containing the monsters that it has
     * 
     * @return myParty
     */
    @Override
    public List<Monster> getParty () {
        return myParty;
    }

    @Override
    public void onInteract () {
        facePlayer();
        // TODO: Wrap Dialogue every 63 characters (the amount for one line)
        // TODO: state changing will be refactored:
        // AbstractMode mode = getModel().getController().getMode();
        // mode.addDynamicState(new TextState(mode,
        // Constants.BORDER_THICKNESS,
        // Constants.HEIGHT - Constants.BORDER_THICKNESS - Constants.DIALOGUE_HEIGHT,
        // Constants.WIDTH - 2*Constants.BORDER_THICKNESS,
        // Constants.DIALOGUE_HEIGHT,
        // getDialogue()));
        if (!myIsDefeated) {
            getModel().getController().setMode(new TrainerBattleMode(getModel(), getModel()
                                                                     .getController().getView(), this));
        } else {
            System.out.println(myPostDialogue);
        }
    }
}
