package author.mapCreation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import author.listeners.MapCreationKeyListener;
import author.listeners.MapCreationMouseListener;
import constants.Constants;


@SuppressWarnings("serial")
public class MapCreationView extends JPanel {

	private Image myBackground;
	private BufferedImage myImage;
	private Graphics myGraphics;
	private CanvasTileManager myTileManager;

	public MapCreationView () {
		setFocusable(true);
		myImage = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
		myGraphics = myImage.getGraphics();
		myBackground = new ImageIcon(Constants.SHORTGRASS_PNG_FILEPATH).getImage();
		myTileManager = new CanvasTileManager(); // 15, 9
		initListeners();
	}
	
	private void initListeners(){
		this.addKeyListener(new MapCreationKeyListener(myTileManager));
		this.addMouseListener(new MapCreationMouseListener(myTileManager));
	}

	public CanvasTileManager getTileManager(){
		return myTileManager;
	}

	public void paintComponent (Graphics g) {
		g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
	}

	public Image getBackgroundImage () {
		return myBackground;
	}

	public Graphics getGraphics () {
		return myGraphics;
	}

}
