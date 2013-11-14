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
    private Graphics myBuffer;
    private CustomKeyListener myKeys;
    private Timer timer;
    private GameModel myModel;
    private Painter myPainter;
    private int myMoveFrames;

    public GameView (String nameOfGame) {
        setFocusable(true);
        myImage = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBackground = new ImageIcon("images/background/shortGrass.png").getImage();
        myKeys = new CustomKeyListener();
        addKeyListener(myKeys);
        myPainter = new Painter(myBuffer);
        timer = new Timer(Constants.REFRESH_RATE, new PaintDelegator());
        try {
            myModel = new GameModel(nameOfGame);
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading game data.");
            System.exit(1);
        }
        myMoveFrames = Constants.MOVE_FRAMES;
        timer.start();
    }

    private class PaintDelegator implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            myPainter.drawBackground(myBackground, myMoveFrames, myModel.getPlayer().getDirection());
            myPainter.drawPlayer(myModel.getPlayer());
            myPainter.drawViewableObjectsOnScreen(getViewableObjectsOnScreen(), myModel.getPlayer());
            myPainter.drawFrame();
            act();

            repaint();
        }
        
        private Collection<AbstractViewableObject> getViewableObjectsOnScreen() {
            Collection<AbstractViewableObject> list = new ArrayList<AbstractViewableObject>();
            int playerX = myModel.getPlayer().getLoc().getX();
            int playerY = myModel.getPlayer().getLoc().getY();
            for (int dx = -Constants.MIDDLE_TILE_HORIZONTAL; dx <= Constants.MIDDLE_TILE_HORIZONTAL; dx++) {
                for (int dy = -Constants.MIDDLE_TILE_VERTICAL; dy <= Constants.MIDDLE_TILE_VERTICAL; dy++) {
                    AbstractViewableObject obj = myModel.getViewableObjects().get(new Loc(playerX + dx, playerY + dy));
                    if (obj != null) {
                        list.add(obj);
                    }
                }
            }
            return list;
        }
    }

    public void act () {
        if (myMoveFrames == Constants.MOVE_FRAMES && myKeys.keyPressed) {
            myModel.movePlayer(myKeys.myDirection);
            myMoveFrames = 0;
            print(myModel.getPlayer().getLoc());
        }
        else if (myMoveFrames < Constants.MOVE_FRAMES) {
            myMoveFrames++;
        }
    }

    public void print (Object o) {
        System.out.println(o.toString());
    }

    public void paintComponent (Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private class CustomKeyListener extends KeyAdapter
    {
        private boolean keyPressed = false;
        private Direction myDirection;

        public void keyPressed (KeyEvent e)
        {
            keyPressed = true;
            int x = e.getKeyCode();
            if (x == KeyEvent.VK_LEFT) {
                myDirection = Direction.LEFT;
            }
            if (x == KeyEvent.VK_UP) {
                myDirection = Direction.UP;
            }
            if (x == KeyEvent.VK_RIGHT) {
                myDirection = Direction.RIGHT;
            }
            if (x == KeyEvent.VK_DOWN) {
                myDirection = Direction.DOWN;
            }
        }

        public void keyReleased (KeyEvent e)
        {
            keyPressed = false;
        }
    }
}
