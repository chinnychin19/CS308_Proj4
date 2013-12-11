package author.mapCreation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import location.Loc;
import author.listeners.MapCreationKeyListener;
import author.listeners.MapCreationMouseListener;
import constants.Constants;


@SuppressWarnings("serial")
public class MapCreationView extends JPanel {

    private MapCreationView mcv = this;
    //private WorldTiles myWorld;
    private WorldCreationMap myWorldCreationMap;
    private BufferedImage myBackground;
    private WorldCreationMap myWorld;
    private CanvasTileManager myTileManager;

    public MapCreationView () {
        setFocusable(true);
        this.setPreferredSize(Constants.MAP_CREATOR_SIZE);
        
        //Try to get the image of the specified background.
        try {
            myBackground = ImageIO.read(new File(Constants.TEST_SHORTGRASS_PNG_FILEPATH));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        myTileManager = new CanvasTileManager(); // 15, 9
        myWorldCreationMap = new WorldCreationMap();
        initListeners(); 
    }
    
    /*public WorldCreationMap getMyWorld () {
        return myWorld;
    }*/

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

    public void paintTile (Graphics2D g, int x, int y) {
        double topLeftX = myTileManager.getTileAnchorX(x);
        double topLeftY = myTileManager.getTileAnchorY(y);
        TexturePaint tp = new TexturePaint(myBackground, new Rectangle(0, 0, (int) myTileManager.getTileWidth(), (int) myTileManager.getTileHeight()));        
        super.paintComponent(g);
        g.setPaint(tp);
        g.fillRect((int) topLeftX, (int) topLeftY, (int) myTileManager.getTileWidth(), (int) myTileManager.getTileHeight());
    }
    
    public void paintTile (Graphics2D g, BufferedImage img, int x, int y) {
        double topLeftX = myTileManager.getTileAnchorX(x);
        double topLeftY = myTileManager.getTileAnchorY(y);
        
        TexturePaint tp = new TexturePaint(img, new Rectangle(0, 0, (int) myTileManager.getTileWidth(), (int) myTileManager.getTileHeight()));        
        super.paintComponent(g);
        g.setPaint(tp);
        g.fillRect((int) topLeftX, (int) topLeftY, (int) myTileManager.getTileWidth(), (int) myTileManager.getTileHeight());
        
        myWorldCreationMap.put(new Loc((int) topLeftX, (int) topLeftY), new GenericTileWrapper("tileName", img));
        repaint();
    }

    public BufferedImage getBackgroundImage () {
        return myBackground;
    }
    
    public void setBackgroundImage(String filePath) {
        BufferedImage newBG = null;
        try {
            newBG = ImageIO.read(new File(filePath));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (newBG != null) {
            myBackground = newBG; 
        }
        else {
            System.out.println("Background tile failed to reset. Did you properly choose a file?");
        }
    }
    
    @Override
    public void repaint() {
        if(myWorldCreationMap != null) {
            for (Map.Entry<Loc, GenericTileWrapper> tile : myWorldCreationMap.getWorldTileMap().entrySet()) {
                paintTile((Graphics2D) this.getGraphics(), tile.getValue().getImage(), tile.getKey().getX(), tile.getKey().getY());
            }
        }
    }
    
    public WorldCreationMap getWorldCreationMap() {
        return myWorldCreationMap;
    }
}
