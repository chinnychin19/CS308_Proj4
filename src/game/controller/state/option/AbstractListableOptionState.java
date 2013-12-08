package game.controller.state.option;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.state.Listable;
import game.controller.state.NotListableException;

public abstract class AbstractListableOptionState extends AbstractOptionState {
    
    protected int mySelected;
    
    public AbstractListableOptionState (AbstractBattleMode mode) {
        this(mode, Constants.MODE_DEFAULT, true);
    }
    
    public AbstractListableOptionState (AbstractBattleMode mode, String name) {
        this(mode, name, true);
    }
    
    public AbstractListableOptionState (AbstractBattleMode mode, String name, boolean canGoBack) {
        super(mode, name, canGoBack);
        mySelected = 0;
    }
   
    protected int getSelected(){
        return mySelected;
    }
    
    protected <S> void paintList(List<S> list) throws NotListableException {
        
        myBuffer.setColor(Color.cyan);
        myBuffer.fillRect(0, 0, myBuffer.getClipBounds().width,
                                 myBuffer.getClipBounds().height);
        myBuffer.setColor(Color.black);
        myBuffer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        
        int x = 15;
        int y = 30;
        int inc = 50;
        for (int i = 0; i < list.size(); i++) {
            
            if (i % 3 == 0 && i != 0) {
                x = x + 3 * inc;
                y = 30;
            }
            if (i == mySelected) {
                myBuffer.setColor(Color.white);
            }
            S item = list.get(i);
            if(!(item instanceof Listable)){
                throw new NotListableException();
            }
            Listable listable = (Listable)item;
            myBuffer.drawString(listable.getName(), x, y + i % 3 * inc);
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
            if(mySelected == 0) mySelected = list.size() - 1;
            else decrementSelected();
         }

         if (input.isKeyDownPressed()) {
             if (mySelected == list.size() - 1) mySelected = 0; 
             else incrementSelected();
         }

         if (input.isKeyLeftPressed()) {
             if (mySelected - 3 >= 0) mySelected = mySelected - 3;
         }

         if (input.isKeyRightPressed()) {
             if (mySelected + 3 < list.size()) mySelected = mySelected + 3;
         }
    }
}
