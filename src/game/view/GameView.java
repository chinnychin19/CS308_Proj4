package game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import game.controller.AbstractMode;
import game.controller.WanderingMode;
import game.model.AbstractViewableObject;
import game.model.GameModel;
import game.model.Player;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import location.Direction;
import location.Loc;
import constants.Constants;


public class GameView extends JPanel {
    private Image myBackground;
    private BufferedImage myImage;
    private Graphics myGraphics;
//    private CustomKeyListener myKeys;
    private GameModel myModel;
//    private Painter myPainter;
    private int myModeIndex;
    private AbstractMode[] myModeArray;
    private static final int INDEX_WANDERING = 0;

    public GameView (String nameOfGame) {
        setFocusable(true);
        myImage = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        myGraphics = myImage.getGraphics();
        myBackground = new ImageIcon("images/background/shortGrass.png").getImage();
//        myPainter = new Painter(myBuffer);
//        timer = new Timer(Constants.REFRESH_RATE, new PaintDelegator());
        try {
            myModel = new GameModel(nameOfGame); //TODO: view should ask controller to do model things
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading game data.");
            System.exit(1);
        }
        myModel.loadState();
        
        initModes();
//        myKeys = new CustomKeyListener();
        addKeyListener(myModeArray[myModeIndex]);        
    }
    
    private void initModes() {
        myModeArray = new AbstractMode[] {
                                          new WanderingMode(myModel, this, myGraphics),
        };
        myModeIndex = INDEX_WANDERING;
    }
    
    public Image getBackgroundImage() {
        return myBackground;
    }
    
//    private class PaintDelegator implements ActionListener
//    {
//        @Override
//        public void actionPerformed (ActionEvent e)
//        {
//            myPainter.drawBackground(myBackground, myMoveFrames, myModel.getPlayer().getDirection());
//            myPainter.drawPlayer(myModel.getPlayer());
//            myPainter.drawViewableObjectsOnScreen(getViewableObjectsOnScreen(), myModel.getPlayer());
//            myPainter.drawFrame();
//            act();
//
//            repaint();
//        }
//        
//        private Collection<AbstractViewableObject> getViewableObjectsOnScreen() {
//            Collection<AbstractViewableObject> list = new ArrayList<AbstractViewableObject>();
//            int playerX = myModel.getPlayer().getLoc().getX();
//            int playerY = myModel.getPlayer().getLoc().getY();
//            for (int dx = -Constants.MIDDLE_TILE_HORIZONTAL; dx <= Constants.MIDDLE_TILE_HORIZONTAL; dx++) {
//                for (int dy = -Constants.MIDDLE_TILE_VERTICAL; dy <= Constants.MIDDLE_TILE_VERTICAL; dy++) {
//                    AbstractViewableObject obj = myModel.getViewableObjects().get(new Loc(playerX + dx, playerY + dy));
//                    if (obj != null) {
//                        list.add(obj);
//                    }
//                }
//            }
//            return list;
//        }
//    }

//    public void act () {
//        // Check for movement
//        if (myMoveFrames == Constants.MOVE_FRAMES && myKeys.isArrowKeyPressed) {
//            myModel.doMove(myKeys.myDirection);
//            myMoveFrames = 0;
//        }
//        else if (myMoveFrames < Constants.MOVE_FRAMES) {
//            myMoveFrames++;
//        }
//        
//        // Check for interaction
//        if (myKeys.isInteractionKeyPressed) {
//            myModel.doInteraction();
//        }
//    }

    public void print (Object o) {
        System.out.println(o.toString());
    }

    public void paintComponent (Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }
    
//    private class CustomKeyListener extends KeyAdapter
//    {
//        private boolean isArrowKeyPressed = false;
//        private boolean isInteractionKeyPressed = false;
//        private Direction myDirection;
//
//        public void keyPressed (KeyEvent e)
//        {
//            int x = e.getKeyCode();
//            if (x == KeyEvent.VK_LEFT) {
//                isArrowKeyPressed = true;
//                myDirection = Direction.LEFT;
//            }
//            if (x == KeyEvent.VK_UP) {
//                isArrowKeyPressed = true;
//                myDirection = Direction.UP;
//            }
//            if (x == KeyEvent.VK_RIGHT) {
//                isArrowKeyPressed = true;
//                myDirection = Direction.RIGHT;
//            }
//            if (x == KeyEvent.VK_DOWN) {
//                isArrowKeyPressed = true;
//                myDirection = Direction.DOWN;
//            }
//            if (x == KeyEvent.VK_Z) {
//                isInteractionKeyPressed = true;
//            }
//        }
//
//        public void keyReleased (KeyEvent e)
//        {
//            isArrowKeyPressed = false;
//            isInteractionKeyPressed = false;
//        }
//    }
}
