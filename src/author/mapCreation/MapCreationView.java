package author.mapCreation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
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
		myBackground = new ImageIcon("images/background/shortGrass.png").getImage();
		myTileManager = new CanvasTileManager(); // 15, 9
		// this.setBackground(Color.BLUE);
		createMouseListener();
		createZoomListener();
	}

	private void createZoomListener(){
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				int key = arg0.getKeyCode();

				// Zoom out
				if (key == KeyEvent.VK_Z){ myTileManager.expandMap(); }

				// Zoom in
				if (key == KeyEvent.VK_X){ myTileManager.contractMap(); }
			}
			@Override
			public void keyReleased(KeyEvent arg0) { 
				int key = arg0.getKeyCode();

				// Finished Zooming out
				if (key == KeyEvent.VK_Z){ 
					System.out.println(
							"Map expanded to " + 
							myTileManager.getTotalHorizontalTiles() + " x " +
							myTileManager.getTotalVerticalTiles() + " tiles");
				}

				// Finished Zooming in
				if (key == KeyEvent.VK_X){ 
					System.out.println(
							"Map contracted to " + 
							myTileManager.getTotalHorizontalTiles() + " x " +
							myTileManager.getTotalVerticalTiles() + " tiles");
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) { /* do nothing... */ }

		});
	}

	private void createMouseListener() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked (MouseEvent arg0) {
				int x = arg0.getX();
				int y = arg0.getY();

				System.out.println("Mouse clicked at: " + x + ", " + y);
				System.out.println("Click translates to tile " +
						"column: " + myTileManager.getHorizontalTileNum(x) + 
						", row: " + myTileManager.getVerticalTileNum(y));
			}

			@Override
			public void mouseEntered (MouseEvent arg0) { /* do nothing... */ }
			@Override
			public void mouseExited (MouseEvent arg0) { /* do nothing... */ }
			@Override
			public void mousePressed (MouseEvent arg0) { /* do nothing... */ }
			@Override
			public void mouseReleased (MouseEvent arg0) { /* do nothing... */ }

		});
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
