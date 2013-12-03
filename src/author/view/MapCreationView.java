package author.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import constants.Constants;

@SuppressWarnings("serial")
public class MapCreationView extends JPanel{

	private Image myBackground;
	private BufferedImage myImage;
	private Graphics myGraphics;
	
    private int horizontalTilesShowing;
    private int verticalTilesShowing;

    private int middleHorizontalTile;
    private int middleVerticalTile;

    private double tileWidth;
    private double tileHeight;
	
	public MapCreationView () {
		setFocusable(true);
		myImage = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
		myGraphics = myImage.getGraphics();
		myBackground = new ImageIcon("images/background/shortGrass.png").getImage();
		configureTiles(Constants.NUM_TILES_HORIZONTAL, Constants.NUM_TILES_VERTICAL); // 15, 9
		//this.setBackground(Color.BLUE);
	}
	
	private void configureTiles(int horizontal, int vertical){
		horizontalTilesShowing = horizontal;
		verticalTilesShowing = vertical;
		middleHorizontalTile = horizontalTilesShowing / 2;
		middleVerticalTile = verticalTilesShowing / 2;
		tileWidth = (double) Constants.WIDTH / horizontalTilesShowing;
	    tileHeight = (double) Constants.HEIGHT / verticalTilesShowing;
	}
	
    public void paintComponent (Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }
    
    public Image getBackgroundImage() {
        return myBackground;
    }

	public Graphics getGraphics() {
		return myGraphics;
	}
	
}
