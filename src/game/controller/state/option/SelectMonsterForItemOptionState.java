package game.controller.state.option;

import java.util.ArrayList;
import java.util.List;
import constants.Constants;
import game.controller.AbstractBattleMode;
import game.model.Item;
import game.model.Monster;
import game.model.MonsterInfo;


public class SelectMonsterForItemOptionState extends
        PartyOptionState {

    private Item myItem;

    public SelectMonsterForItemOptionState (AbstractBattleMode mode, Item item) {
        super(mode);
        myItem = item;
    }

    @Override
    public void onInteract () {
        List<Monster> monsters = getMonsters();
        Monster selectedMonster = monsters.get(mySelected);
        myItem.applyEffect(selectedMonster);
        myMode.setOptionState(new StateTransitionTextOptionState(myMode, String
                .format(Constants.TEXT_YOU_USED_ON_BLANK, myItem.getName(),
                        selectedMonster.getName())));
    }

    @Override
    public void onBack () {
        myMode.setOptionState(new ItemOptionState(myMode));
    }

    @Override
    protected List<MonsterInfo> getMonsterInfoList () {
        List<MonsterInfo> monsterInfoList = new ArrayList<MonsterInfo>();
        for (Monster m : myItem.getApplicableMonsters(getMonsters())) {
            monsterInfoList.add(new MonsterInfo(m));
        }
        return monsterInfoList;
    }
}
