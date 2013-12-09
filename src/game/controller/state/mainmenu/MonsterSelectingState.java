package game.controller.state.mainmenu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import constants.Constants;
import game.controller.Input;
import game.controller.MainMenuMode;
import game.controller.state.AbstractState;
import game.controller.state.Listable;
import game.controller.state.NotListableException;
import game.model.Monster;
import game.model.MonsterInfo;

public class MonsterSelectingState extends AbstractListableState {
    private int myChosen;
    public MonsterSelectingState (MainMenuMode mode, int chosen) {
        super("", mode);
        myChosen = chosen;
        //myBuffer.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        //TODO: Should this line above be taken out????
    }

    @Override
    public void paint() {
        super.paint();
        int x = Constants.TEXT_START_X;
        int y = Constants.TEXT_START_Y;
        int inc = Constants.TEXT_START_INC;
        for (int i = 0; i < getMonsterList().size(); i++) {
            
            if(i==getSelected()){
                myBuffer.setColor(Color.white);
            }
            
            if (i == myChosen){
                myBuffer.setColor(Color.red);
            }
            
            myBuffer.drawString(getMonsterInfoList().get(i).getName(), x, y + i * inc);
            if (i == getSelected()) {
                myBuffer.setColor(Color.black);
            }
            if (i == myChosen){
                myBuffer.setColor(Color.black);
            }
        }
    }
    
    @Override
    public void act(Input input) {
        if (input.isKeyUpPressed() && getSelected() > Constants.ZERO) {
            decrementSelected();
        }
        else if (input.isKeyDownPressed() && getSelected() < getMonsterList().size()) {
            incrementSelected();
        }

        if (input.isKeyInteractPressed()) {
            onInteract();
        }

        if (input.isKeyBackPressed()) {
            onBack();
        }
    }
    
    @Override
    protected void onBack () {
        // TODO Auto-generated method stub

    }
    
    @Override
    protected void onInteract () {
        System.out.println(getSelected()); //TODO: Take out print statement
        System.out.println(myChosen); //TODO: take out print statement
        swapMonsters(getMonsterList(), getSelected(), myChosen);
        getMode().setState(new MonsterSelecterState(getMode()));
    }
    
    private void swapMonsters(List<Monster> list, int i, int j){
        Monster tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
    
    protected List<Monster> getMonsterList(){
        return getMode().getModel().getPlayer().getParty();
    }
    
    protected List<MonsterInfo> getMonsterInfoList(){
        List<MonsterInfo> monsterList = new ArrayList<MonsterInfo>();
        for(Monster m : getMonsterList()){
            monsterList.add(new MonsterInfo(m));
        }
        return monsterList;
    }

}
