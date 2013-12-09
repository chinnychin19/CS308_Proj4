package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.state.AbstractState;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import constants.Constants;


public abstract class AbstractOptionState extends AbstractState{
    protected AbstractBattleMode myMode;
    protected int mySelected = 0;
    private boolean myCanGoBack;


    public AbstractOptionState (AbstractBattleMode mode) {
        this(mode, Constants.MODE_DEFAULT);
    }

    public AbstractOptionState (AbstractBattleMode mode, String name) {
        this(mode, name, true);
    }

    public AbstractOptionState (AbstractBattleMode mode,  String name, boolean canGoBack) {
        super(name, mode, 0, Constants.HEIGHT*2/3, Constants.WIDTH, Constants.HEIGHT/3);
        myMode = mode;
        mySelected = 0;
        myName = name;
        myCanGoBack = canGoBack;
    }

    public String getName () {
        return myName;
    }

    public void paint () {
        myBuffer.setColor(Color.cyan);
        myBuffer.fillRect(0, 0, myBuffer.getClipBounds().width,
                          myBuffer.getClipBounds().height);
        myBuffer.setColor(Color.black);
        myBuffer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    }

    protected boolean canGoBack () {
        return myCanGoBack;
    }
    
    protected void incrementSelected(){
        mySelected++;
    }
    
    protected void decrementSelected(){
        mySelected--;
    }

    protected abstract void onInteract ();

    protected abstract void onBack ();

    public void act (Input input) {
        if (input.isKeyInteractPressed()) {
            onInteract();
        }

        if (input.isKeyBackPressed()) {
            onBack();
        }
    }

}
