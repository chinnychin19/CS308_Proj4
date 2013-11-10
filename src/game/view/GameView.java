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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import constants.Constants;


public class GameView extends JPanel {
    private Image myBackground;
    private BufferedImage myImage;
    private Graphics myBuffer;
    private CustomKeyListener myKeys;
    private Timer timer;
    private GameModel myModel;

    public GameView (String nameOfGame) {
        setFocusable(true);
        myImage = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBackground = new ImageIcon("images/background/shortGrass.png").getImage();
        myKeys = new CustomKeyListener();
        addKeyListener(myKeys);
        timer = new Timer(Constants.REFRESH_RATE, new Painter());
        myModel = new GameModel(nameOfGame);
        timer.start();
    }

    private class Painter implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            drawBackground();
            drawFrame();

            // world.drawObstacles(myBuffer);
            // world.drawPlayer(myBuffer);
            // act();

            repaint();
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
        private boolean LEFT = false, RIGHT = false, UP = false, DOWN = false, START = false;

        public void keyPressed (KeyEvent e)
        {
            int x = e.getKeyCode();
            if (x == KeyEvent.VK_LEFT) {
                LEFT = true;
            }
            if (x == KeyEvent.VK_UP) {
                UP = true;
            }
            if (x == KeyEvent.VK_RIGHT) {
                RIGHT = true;
            }
            if (x == KeyEvent.VK_DOWN) {
                DOWN = true;
            }

            if (x == KeyEvent.VK_Q) {
                START = true;
            }
        }

        public void keyReleased (KeyEvent e)
        {
            int x = e.getKeyCode();
            if (x == KeyEvent.VK_LEFT)
                LEFT = false;
            if (x == KeyEvent.VK_UP)
                UP = false;
            if (x == KeyEvent.VK_RIGHT)
                RIGHT = false;
            if (x == KeyEvent.VK_DOWN)
                DOWN = false;

            if (x == KeyEvent.VK_Q) {
                START = false;
            }
        }
    }

    public void drawFrame () {
        myBuffer.setColor(Constants.BORDER_COLOR);
        myBuffer.fillRect(0, 0, Constants.WIDTH, Constants.BORDER_THICKNESS);
        myBuffer.fillRect(0, Constants.HEIGHT - Constants.BORDER_THICKNESS,
                          Constants.WIDTH, Constants.HEIGHT);
        myBuffer.fillRect(0, Constants.BORDER_THICKNESS, Constants.BORDER_THICKNESS,
                          Constants.HEIGHT);
        myBuffer.fillRect(Constants.WIDTH - Constants.BORDER_THICKNESS, 0,
                          Constants.WIDTH, Constants.HEIGHT);
    }

    public void drawBackground () {
        // draws default background image
        myBuffer.drawImage(myBackground, 0, 0,
                           Constants.WIDTH,
                           Constants.HEIGHT,
                           null);

    }

}
