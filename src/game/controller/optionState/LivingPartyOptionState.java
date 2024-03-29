package game.controller.optionState;

import java.util.ArrayList;
import java.util.List;
import game.controller.AbstractBattleMode;
import game.controller.state.option.PartyOptionState;
import game.model.Monster;

public class LivingPartyOptionState extends PartyOptionState {

    public LivingPartyOptionState (AbstractBattleMode mode) {
        this(mode, true);
    }
    
    public LivingPartyOptionState (AbstractBattleMode mode, boolean canGoBack) {
        super(mode, canGoBack);
    }
    
    /**
     * @return Only returns the list of alive monsters
     */
    @Override
    protected List<Monster> getMonsters(){
        List<Monster> allMonsters = myMode.getBattle().getPlayerParty().getMonsters();
        List<Monster> aliveMonsters = new ArrayList<Monster>();
        for(Monster m : allMonsters){
            if (!m.isDead()){
                aliveMonsters.add(m);
            }
        }
        return aliveMonsters;
    }
}
