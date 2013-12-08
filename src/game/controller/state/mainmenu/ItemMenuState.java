package game.controller.state.mainmenu;

import java.util.ArrayList;
import java.util.List;
import constants.Constants;
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
    protected void onBack () {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onInteract () {
        // TODO Auto-generated method stub

    }
    
    private List<Item> getItemList () {
    	//TOO: Implement
        return new ArrayList<Item>();
    }

}
