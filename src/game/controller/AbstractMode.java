package game.controller;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import constants.Constants;
import game.model.GameModel;
import game.view.GameView;


/**
 * This is an abstract class to represent the different modes of a game, i.e. Wandering, Battle,
 * etc. Each mode performs different with different user input and paints the screen differently.
 * This mode serves as a controller and has access to the view and the model.
 * 
 * @author Chinmay
 * 
 */

public abstract class AbstractMode extends KeyAdapter {
    private GameModel myModel;
    private GameView myView;
    private Graphics myGraphics;
    private boolean[] myInputs;
    private final int NUM_INPUTS = 6;
    /**
     * These final constants may be used to index into the boolean array of inputs
     */
    protected final int INDEX_UP = 0,
            INDEX_LEFT = 1,
            INDEX_DOWN = 2,
            INDEX_RIGHT = 3,
            INDEX_INTERACT = 4,
            INDEX_MENU = 5;

    public AbstractMode (GameModel model, GameView view, Graphics g) {
        myModel = model;
        myView = view;
        myGraphics = g;
        myInputs = new boolean[NUM_INPUTS];
        paint(); //initially paint the state before waiting for key input
    }

    public abstract void paint ();

    public abstract void act (); // TODO: take some sort of input object as parameter? or is this
                                 // the input object?

    protected GameModel getModel () {
        return myModel;
    }

    protected GameView getView () {
        return myView;
    }

    /**
     * Returns the state of the inputs as a boolean array. It may be index into by the final
     * constants defined above
     * 
     * @return
     */
    protected boolean[] getInputs () {
        return myInputs;
    }

    /**
     * Returns the Graphics object to be used by subclasses when painting
     * 
     * @return
     */
    protected Graphics getGraphics () {
        return myGraphics;
    }

    /**
     * Updates the state of the inputs and performs actions/repaints the screen.
     */
    @Override
    public void keyPressed (KeyEvent e) {
        updateInputs(e, true);
        actAndPaint();
    }

    /**
     * Updates the state of the inputs and performs actions/repaints the screen.
     */
    @Override
    public void keyReleased (KeyEvent e) {
        updateInputs(e, false);
        actAndPaint();
    }

    /**
     * Performs all actions resulting from input and repaints the screen.
     */
    private void actAndPaint () {
        act();
        paint(); // order matters. paint after action completed.
        myView.repaint();
    }

    /**
     * Updates the appropriate input boolean given a KeyEvent
     * 
     * @param e An event representing a key press or key release
     * @param flag Should be true if a key press, false if a key release
     */
    private void updateInputs (KeyEvent e, boolean flag) {
        int x = e.getKeyCode();
        if (x == KeyEvent.VK_UP) {
            myInputs[INDEX_UP] = flag;
        }
        if (x == KeyEvent.VK_LEFT) {
            myInputs[INDEX_LEFT] = flag;
        }
        if (x == KeyEvent.VK_DOWN) {
            myInputs[INDEX_DOWN] = flag;
        }
        if (x == KeyEvent.VK_RIGHT) {
            myInputs[INDEX_RIGHT] = flag;
        }
        if (x == KeyEvent.VK_Z) {
            myInputs[INDEX_INTERACT] = flag;
        }
        if (x == KeyEvent.VK_SPACE) {
            myInputs[INDEX_MENU] = flag;
        }
    }

    /**
     * Paints a border around the edge of the screen.
     */
    protected void paintBorder () {
        myGraphics.setColor(Constants.BORDER_COLOR);
        myGraphics.fillRect(0, 0, Constants.WIDTH, Constants.BORDER_THICKNESS);
        myGraphics.fillRect(0, Constants.HEIGHT - Constants.BORDER_THICKNESS,
                            Constants.WIDTH, Constants.HEIGHT);
        myGraphics.fillRect(0, Constants.BORDER_THICKNESS, Constants.BORDER_THICKNESS,
                            Constants.HEIGHT);
        myGraphics.fillRect(Constants.WIDTH - Constants.BORDER_THICKNESS, 0,
                            Constants.WIDTH, Constants.HEIGHT);
    }
}
