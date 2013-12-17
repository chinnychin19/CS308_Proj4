package author.mapCreation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.JPanel;
import location.Loc;
import author.listeners.MapCreationKeyListener;
import author.listeners.MapCreationMouseListener;
import author.model.TileWrapper;
import constants.Constants;


/**
 * Class that handles the painting of tile-based world objects into
 * a given world. Renders graphics onto a JPanel.
 * 
 * @author Michael Marion
 * 
 */

@SuppressWarnings("serial")
public class MapCreationView extends JPanel {

	private MapCreationView singleton;

	private TileWrapper myCurrentTile;

	private CanvasTileManager myTileManager;
	private WorldCreationMap myWorldCreationMap;

	//private BufferedImage myBackgroundTile;

	public MapCreationView () {

		// Set JPanel attributes
		setFocusable(true);
		this.setPreferredSize(Constants.MAP_CREATOR_SIZE);
		this.setBackground(Constants.MAP_CREATOR_BACKGROUND_COLOR);

		myCurrentTile = null;
		//try {
		//	myBackgroundTile = ImageIO.read(new File(Constants.IMG_FOLDER_FILEPATH + File.separator + "shortGrass.png"));
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}

		// Instantiate the tile manager and world creation map.
		myTileManager = new CanvasTileManager(); // 15, 9
		myWorldCreationMap = myTileManager.getWorld();

		initListeners();

		singleton = this;
		this.setVisible(true);
	}

	/**
	 * Attaches a keyboard and mouse listener to the map creation view
	 * to allow user interactivity.
	 */
	private void initListeners () {
		this.addKeyListener(new MapCreationKeyListener(this, myTileManager));

		MapCreationMouseListener mouseListener = new MapCreationMouseListener(this,myTileManager);
		this.addMouseMotionListener(mouseListener);
		this.addMouseListener(mouseListener);
	}

	/**
	 * Paints the component with all tiles currently placed in the
	 * world tile map.
	 */
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		if (myWorldCreationMap.getWorldTileMap() != null) {
			for (Map.Entry<Loc, TileWrapper> tile : myWorldCreationMap.getWorldTileMap()
					.entrySet()) {
				paintTile((Graphics2D) g,
						tile.getKey().getX(),
						tile.getKey().getY(),
						tile.getValue());
			}
		}
	}


	/**
	 * Paints a tile at the given coordinate and records its
	 * location in the WorldTileMap.
	 * 
	 * @param g
	 * @param x
	 * @param y
	 */
	public void paintAndRecordTile (Graphics2D g, int x, int y) {
		paintTile(g, x, y);
		if (isValueSelected()) {
			myWorldCreationMap.put(new Loc(x, y), myCurrentTile);
		}
	}

	/**
	 * Paints a tile in the view at the given x-
	 * and y-coordinates. Called by the mouse listener
	 * which handles the point-and-click painting of
	 * tiles on the part of the user.
	 * 
	 * @param g
	 * @param x
	 * @param y
	 */
	public void paintTile (Graphics2D g, int x, int y) {
		if (isValueSelected()) {
			TexturePaint tp =
					new TexturePaint(myCurrentTile.getImage(),
							new Rectangle(0,
									0,
									(int) myTileManager.getTileWidth(),
									(int) myTileManager.getTileHeight()));

			g.setPaint(tp);
			g.fillRect((int) myTileManager.getTileAnchorX(x),
					(int) myTileManager.getTileAnchorY(y),
					(int) myTileManager.getTileWidth(),
					(int) myTileManager.getTileHeight());
		}

	}

	/**
	 * Paints a tile in the view at the given x-
	 * and y-coordinates. Called by the paintComponent
	 * method, which handles the painting of all tiles
	 * from the tile map.
	 * 
	 * @param g
	 * @param x
	 * @param y
	 */
	public void paintTile (Graphics2D g, int x, int y, TileWrapper tile) {
		TexturePaint tp =
				new TexturePaint(tile.getImage(),
						new Rectangle(0,
								0,
								(int) myTileManager.getTileWidth(),
								(int) myTileManager.getTileHeight()));

		g.setPaint(tp);
		g.fillRect((int) myTileManager.getTileAnchorX(x),
				(int) myTileManager.getTileAnchorY(y),
				(int) myTileManager.getTileWidth(),
				(int) myTileManager.getTileHeight());
	}

	public void removeTileFromMap(Graphics g, int xTile, int yTile) {
		Loc location = new Loc(xTile, yTile);
		myWorldCreationMap.remove(location);
		super.paintComponent(g);
	}

	/**
	 * A boolean check to ensure that the state of the
	 * MapCreationView class is not null before attempting
	 * to draw and place tiles in the MapCreationView
	 * 
	 * @return a boolean check to ensure that myCurrentTile is not null.
	 */
	public boolean isValueSelected () {
		return (myCurrentTile != null);
	}

	public BufferedImage getCurrentTileImage () {
		return myCurrentTile.getImage();
	}

	public void setCurrentTile (TileWrapper tile) {
	        myCurrentTile = tile;
	}

	public Map<Loc, TileWrapper> getMyWorldTiles () {
		return myWorldCreationMap.getWorldTileMap();
	}

	public CanvasTileManager getTileManager () {
		return myTileManager;
	}

	/**
	 * Returns a singleton instance of this map
	 * creation view.
	 * 
	 * @return singleton instance of map creation view
	 */
	public MapCreationView getMapCreationView () {
		if (singleton == null) {
			return new MapCreationView();
		}
		else {
			return singleton;
		}
	}



}
