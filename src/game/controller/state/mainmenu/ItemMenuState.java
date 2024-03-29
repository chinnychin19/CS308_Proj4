package game.controller.state.mainmenu;

import java.util.ArrayList;
import java.util.List;
import constants.Constants;
import game.controller.Input;
import game.controller.MainMenuMode;
import game.controller.state.NotListableException;
import game.model.Item;
import game.model.KeyItem;

public class ItemMenuState extends AbstractListableState {

    public ItemMenuState (MainMenuMode mode) {
        super(Constants.MAIN_MENU_ITEM, mode);
    }
    
    @Override
    public void paint(){
        super.paint();
        
        try {
            paintList(getItemList());
        }
        catch (NotListableException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void act(Input i){
        actList(i, getItemList());
    }
    @Override
    protected void onInteract () {
        Item selectedItem = getItemList().get(getSelected());
        getMode().setState(new SelectMonsterForItemMenuState(getMode(), selectedItem));
    }
    
    private List<Item> getItemList () {
        return getMode().getModel().getPlayer().getItems();
    }

}
