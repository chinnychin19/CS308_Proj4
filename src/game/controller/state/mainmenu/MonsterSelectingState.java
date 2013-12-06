package game.controller.state.mainmenu;

import java.awt.Color;
import java.util.List;
import constants.Constants;
import game.controller.Input;
import game.controller.MainMenuMode;
import game.controller.state.AbstractState;
import game.controller.state.Listable;
import game.controller.state.NotListableException;
import game.model.Monster;

public class MonsterSelectingState extends AbstractListableState {
    private int myChosen;
    public MonsterSelectingState (MainMenuMode mode, int chosen) {
        super("MONSTER SELECTING", mode);
        myChosen = chosen;
        //myBuffer.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
    }

    @Override
    public void paint() {
        super.paint();
        int x = 15;
        int y = 30;
        int inc = 50;
        for (int i = 0; i < getMonsterList().size(); i++) {
            
            if(i==getSelected()){
                myBuffer.setColor(Color.white);
            }
            
            if (i == myChosen){
                myBuffer.setColor(Color.red);
            }
                       
            
            
            myBuffer.drawString(getMonsterList().get(i).getName(), x, y + i * inc);
            if (i == getSelected()) {
                myBuffer.setColor(Color.black);
            }
        }
    }
    
    @Override
    public void act(Input input) {
        if (input.isKeyUpPressed() && getSelected() > 0) {
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
        System.out.println(getSelected());
        System.out.println(myChosen);
        swapMonsters(getMonsterList(), getSelected(), myChosen);
        getMode().setState(new MonsterSelecterState(getMode()));
    }
    
    private List<Monster> getMonsterList(){
        return getMode().getModel().getPlayer().getParty();
    }
    
    private void swapMonsters(List<Monster> list, int i, int j){
        Monster tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

}
