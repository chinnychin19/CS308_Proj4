package game.controller.state;

import game.controller.AbstractMode;
import game.controller.TrainerBattleMode;


public class ModeTransitionTextState extends TextState {
    private AbstractMode myModeToEnter;

    public ModeTransitionTextState (AbstractMode currentMode,
                                    int x,
                                    int y,
                                    int w,
                                    int h,
                                    String text,
                                    AbstractMode modeToEnter) {
        super(currentMode, x, y, w, h, text);
        myModeToEnter = modeToEnter;
    }

    @Override
    protected void onBack() {
        getMode().getController().setMode(myModeToEnter);
    }
}
