package game.controller.state.mainmenu;

import java.awt.Color;
import java.util.List;
import constants.Constants;
import game.controller.AbstractMode;
import game.controller.Input;
import game.controller.MainMenuMode;
import game.controller.state.Listable;
import game.controller.state.NotListableException;

public abstract class AbstractListableState extends AbstractMenuState {
    private int mySelected;
    public AbstractListableState (String name, MainMenuMode mode) {
        super(name, mode);
        mySelected = Constants.ZERO;
    }
   
    protected int getSelected(){
        return mySelected;
    }
    
    protected <S> void paintList(List<S> list) throws NotListableException{
        int x = Constants.TEXT_START_X;
        int y = Constants.TEXT_START_Y;
        int inc = Constants.TEXT_Y_INC;
        
        for (int i = 0; i < list.size(); i++) {
            if (i == mySelected) {
                myBuffer.setColor(Color.white);
            }
            S item = list.get(i);
            if(!(item instanceof Listable)){
                throw new NotListableException();
            }
            Listable listable = (Listable)item;
            
            myBuffer.drawString(listable.getName(), x, y + i * inc);
            if (i == mySelected) {
                myBuffer.setColor(Color.black);
            }
        }
    }
    
    protected void incrementSelected(){
        mySelected++;
    }
    
    protected void decrementSelected(){
        mySelected--;
    }
    
    protected <S> void actList (Input input, List<S> list) {
        if (input.isKeyUpPressed()) {
            if(mySelected == Constants.ZERO) mySelected = list.size()-1;
            else decrementSelected();
        }
        else if (input.isKeyDownPressed()) {
            if(mySelected == list.size()-1) mySelected = Constants.ZERO;
            else incrementSelected();
        }

        if (input.isKeyInteractPressed()) {
            onInteract();
        }

        if (input.isKeyBackPressed()) {
            onBack();
        }
    }
 

}
