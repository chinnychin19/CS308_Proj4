package game.model.battle;

import java.util.List;
import java.util.ArrayList;
import game.controller.GameController;
import game.model.AbstractCharacter;
import game.model.Fighter;
import game.model.GameModel;
import game.model.Monster;

/**
 * Abstract Class for Battle Parties
 * Contains a collection on monsters, a fighter, and a reference to the Battle
 * @author tylernisonoff
 *
 */
public abstract class AbstractBattleParty {
    private List<Monster> myMonsters;
    private Fighter myFighter;
    private GameController myController;
    private Monster myCurrentMonster;
    private Battle myBattle;
    
    public AbstractBattleParty(GameController controller, Fighter fighter) {
        myMonsters = new ArrayList<Monster>();
        myController = controller;
        myFighter = fighter;
        myMonsters = fighter.getParty();
        myCurrentMonster = getFirstAliveMonster();
    }
    
    public AbstractBattleParty(GameController controller, Monster monster) {
        myMonsters = new ArrayList<Monster>();
        myController = controller;
        myCurrentMonster = monster;
        myMonsters = new ArrayList<Monster>();
        myMonsters.add(monster);
        myFighter = null;
    }
    
    public void setBattle(Battle b) {
        myBattle = b;
    }
    
    public Battle getBattle() {
        return myBattle;
    }
    
    private Monster getFirstAliveMonster () {
        for (Monster m : myMonsters) {
            if (m.getCurHP() > 0) {
                return m;
            }
        }
        return null;
    }

    public abstract void doTurn();
    
    public Fighter getFighter() {
        return myFighter;
    }
    
    public List<Monster> getMonsters() {
        return myMonsters;
    }
    
    public GameController getController() {
        return myController;
    }
    
    public Monster getCurrentMonster() {
        return myCurrentMonster;
    }
}
