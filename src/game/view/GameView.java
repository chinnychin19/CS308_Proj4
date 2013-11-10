package game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import game.model.GameModel;
import game.model.Player;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import util.Direction;
import constants.Constants;


public class GameView extends JPanel {
    private Image myBackground;
    private BufferedImage myImage;
    private Graphics myBuffer;
    private CustomKeyListener myKeys;
    private Timer timer;
    private GameModel myModel;
    private Painter myPainter;

    public GameView (String nameOfGame) {
        setFocusable(true);
        myImage = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBackground = new ImageIcon("images/background/shortGrass.png").getImage();
        myKeys = new CustomKeyListener();
        addKeyListener(myKeys);
        myPainter = new Painter(myBuffer);
        timer = new Timer(Constants.REFRESH_RATE, new PaintDelegator());
        myModel = new GameModel(nameOfGame);
        timer.start();
    }

    private class PaintDelegator implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            myPainter.drawBackground(myBackground);
            myPainter.drawFrame();
            myPainter.drawPlayer(myModel.getPlayer());

            // world.drawObstacles(myBuffer);
            // world.drawPlayer(myBuffer);
            act();

            repaint();
        }
    }

    public void act () {
        if (myKeys.keyPressed) {
            myModel.getPlayer().setDirection(myKeys.myDirection);
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
