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
import game.controller.GameController;
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
    private GameController myController;

    public GameView (String nameOfGame) {
        setFocusable(true);
        myImage = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        myGraphics = myImage.getGraphics();
        myBackground = new ImageIcon("images/background/shortGrass.png").getImage();
        myController = new GameController(nameOfGame, this);
        myController.loadState(); // ought to be called by a user input
    }
    
    public Graphics getBuffer() { //getGraphics() is a Swing method, so I don't want to override it.
        return myGraphics;
    }
    
    public Image getBackgroundImage() {
        return myBackground;
    }
    
    public void print (Object o) {
        System.out.println(o.toString());
    }

    public void paintComponent (Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }
}
