package author.mapCreation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import author.listeners.MapCreationKeyListener;
import author.listeners.MapCreationMouseListener;
import constants.Constants;


@SuppressWarnings("serial")
public class MapCreationView extends JPanel {

    private WorldCreationMap myWorld;
    private Image myBackground;
    private CanvasTileManager myTileManager;

    public MapCreationView () {
        setFocusable(true);
        this.setPreferredSize(Constants.MAP_CREATOR_SIZE);
        myBackground = new ImageIcon(Constants.SHORTGRASS_PNG_FILEPATH).getImage();
        myTileManager = new CanvasTileManager(); // 15, 9
        initListeners();
        
    }

    public WorldCreationMap getMyWorld () {
        return myWorld;
    }

    private void initListeners () {
        this.addKeyListener(new MapCreationKeyListener(this, myTileManager));
        this.addMouseListener(new MapCreationMouseListener(this, myTileManager));
    }

    public CanvasTileManager getTileManager () {
        return myTileManager;
    }

    public void paintComponent (Graphics g) {
        g.drawImage(myBackground, 0, 0, getWidth(), getHeight(), null);
    }

    public void paintTile (Graphics g, int x, int y, ImageObserver io) {
        double topLeftX = myTileManager.getTileAnchorX(x);
        double topLeftY = myTileManager.getTileAnchorY(y);
        super.paintComponent(g);
        g.drawRect((int) topLeftX, (int) topLeftY, (int) myTileManager.getTileWidth(), (int) myTileManager.getTileHeight());
        g.setColor(Color.RED);
        g.fillRect((int) topLeftX, (int) topLeftY, (int) myTileManager.getTileWidth(), (int) myTileManager.getTileHeight());
    }

    public Image getBackgroundImage () {
        return myBackground;
    }

}
