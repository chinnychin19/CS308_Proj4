package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.WildBattleMode;
import game.controller.state.NotListableException;
import game.model.Monster;
import game.model.MonsterInfo;
import game.model.attack.Attack;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class PartyOptionState extends AbstractListableOptionState{
    public PartyOptionState (AbstractBattleMode mode) {
        this(mode, true);
    }
    
    public PartyOptionState (AbstractBattleMode mode, boolean canGoBack){
        super(mode,"PARTY",canGoBack);
    }
    
    @Override
    public void paint () {
        try { 
            paintList(getMonsterInfoList()); 
        }
        catch (NotListableException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void act(Input input) {
        super.act(input);
        actList(input, getMonsters());
    }
    
    @Override
    public void onInteract(){
        List<Monster> monsters = getMonsters();
        Monster selectedMonster = monsters.get(mySelected);
        myMode.getBattle().getPlayerParty().setCurrentMonster(selectedMonster);
        myMode.setOptionState(new StateTransitionTextOptionState(myMode, String.format("You switch to %s", selectedMonster.getName())));
        //TODO: String constant
    }
    
    @Override
    protected void onBack () {
        if(canGoBack()){
            myMode.setOptionState(myMode.getAMainOptionState());
        }
    }

    protected List<Monster> getMonsters(){
        return myMode.getBattle().getPlayerParty().getMonsters();
    }
    
    protected List<MonsterInfo> getMonsterInfoList(){
        List<MonsterInfo> monsterInfoList = new ArrayList<MonsterInfo>();
        for(Monster m : getMonsters()){
            monsterInfoList.add(new MonsterInfo(m));
        }
        return monsterInfoList;
    }
}
