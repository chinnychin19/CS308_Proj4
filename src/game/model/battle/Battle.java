package game.model.battle;

import game.model.Monster;

public class Battle {
    AbstractBattleParty myPlayerParty;
    AbstractBattleParty myWildParty;
    public Battle(AbstractBattleParty playerParty, AbstractBattleParty wildParty) {
        myPlayerParty = playerParty;
        myWildParty = wildParty;
    }
    
    public AbstractBattleParty getOtherParty(AbstractBattleParty self) {
        return (self == myPlayerParty) ? myWildParty : myPlayerParty;
    }
    
    public AbstractBattleParty getPlayerParty() {
        return myPlayerParty;
    }
    
    public AbstractBattleParty getWildParty() {
        return myWildParty;
    }
    
    public void conduct() { //TODO: implement properly
        while (!isOver()) {
            myPlayerParty.doTurn(); //TODO: supposed to wait for player to choose something
            myWildParty.doTurn();
            System.out.println("health: "+myPlayerParty.getCurrentMonster().getCurHP());
        }
    }
    
    public boolean isOver() {
        boolean aLost = true, bLost = false;
        for (Monster m : myPlayerParty.getMonsters()) {
            if (m.getCurHP() > 0) {
                aLost = false;
                break;
            }
        }
        for (Monster m : myWildParty.getMonsters()) {
            if (m.getCurHP() > 0) {
                bLost = false;
                break;
            }
        }
        return aLost || bLost;
    }
}
