package game.controller.optionState;

import java.util.ArrayList;
import java.util.List;
import game.controller.AbstractBattleMode;
import game.model.Monster;

public class LivingPartyOptionState extends PartyOptionState {

    public LivingPartyOptionState (AbstractBattleMode mode) {
        this(mode, true);
    }
    
    public LivingPartyOptionState (AbstractBattleMode mode, boolean canGoBack) {
        super(mode, canGoBack);
    }
    
    @Override
    protected List<Monster> getMonsters(){
        List<Monster> allMonsters = myMode.getBattle().getPlayerParty().getMonsters();
        List<Monster> aliveMonsters = new ArrayList<Monster>();
        for(Monster m : allMonsters){
            if (!m.isDead()){
                aliveMonsters.add(m);
            }
        }
        System.out.println("alive monsters "+aliveMonsters.size());
        return aliveMonsters;
    }
}
