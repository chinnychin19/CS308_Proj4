package author.mapCreation;

import location.Loc;
import constants.Constants;

public class CanvasTileManager {
	
	private WorldTiles myWorld;
	
	private int myGCD;

	private int widthRatio;
	private int heightRatio;

	private int horizontalOffset;
	private int verticalOffset;

	private int widthFactor;
	private int heightFactor;

	private int middleHorizontalTile;
	private int middleVerticalTile;

	private double tileWidth;
	private double tileHeight;

	public CanvasTileManager(){
		myWorld = new WorldTiles();
		horizontalOffset = Constants.MIN_X_COORD;
		verticalOffset = Constants.MIN_Y_COORD;

		// default width and height are 15 and 9 respectively
		configureTiles(Constants.NUM_TILES_HORIZONTAL, Constants.NUM_TILES_VERTICAL);
	}

	public CanvasTileManager(int horizontalTiles, int verticalTiles){
		horizontalOffset = 0;
		verticalOffset = 0;

		// custom number of tiles
		configureTiles(horizontalTiles, verticalTiles);
	}

	public void configureTiles (int horizontal, int vertical) {
		// Scales no matter what, but this could cause errors
		// TODO: Come up with way to check that the parameters will work
		myGCD = GCD(horizontal, vertical);
		widthRatio = horizontal/myGCD;
		heightRatio = vertical/myGCD;

		widthFactor = horizontal/widthRatio;
		heightFactor = vertical/heightRatio;

		calcNewTileValues();
	}

	private void calcNewTileValues() {
		middleHorizontalTile = (myGCD * widthRatio) / 2;
		middleVerticalTile = (myGCD * heightRatio) / 2;

		tileWidth = (double) Constants.WIDTH / (getTotalHorizontalTiles());
		tileHeight = (double) Constants.HEIGHT / (getTotalVerticalTiles());
	}

	private int GCD(int a, int b){
		while (b > 0){
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	public void getTileClickLoc(int x, int y){
		myWorld.addToMap(new Loc(x, y), new GenericTileWrapper("blah", null/*new Image()*/));
	}

	public void expandView(){
		if (	horizontalOffset + getTotalHorizontalTiles() + widthRatio > Constants.MAX_X_COORD
				|| verticalOffset + getTotalVerticalTiles() + heightRatio > Constants.MAX_Y_COORD){
			// do nothing because view will expand beyond boundaries
		}
		else{ // good to expand view
			widthFactor += 1;
			heightFactor += 1;
			calcNewTileValues();
		}
	}

	public void contractView(){
		if (widthFactor > 1 && heightFactor > 1){
			widthFactor -= 1;
			heightFactor -= 1;
			calcNewTileValues();
		}
		// do nothing otherwise
	}

	public int getTotalHorizontalTiles(){
		return widthRatio * widthFactor;
	}

	public int getTotalVerticalTiles(){
		return heightRatio * heightFactor;
	}

	public int getHorizontalTileNum(int xValue){
		return (int) ((xValue/tileWidth) + horizontalOffset);
	}

	public int getVerticalTileNum(int yValue){
		return (int) ((yValue/tileHeight) + verticalOffset);
	}
	
	public double getTileAnchorX(int x) {
	    return x * tileWidth;
	}
	
	public double getTileAnchorY(int y) {
	    return y * tileHeight;
	}

	public void increaseHorizontalOffset(){
		if (horizontalOffset + getTotalHorizontalTiles() < Constants.MAX_X_COORD) { horizontalOffset += 1; }
		// otherwise do nothing
	}

	public void decreaseHorizontalOffset(){
		if (horizontalOffset > Constants.MIN_X_COORD) { horizontalOffset -= 1; }
		// otherwise do nothing
	}

	public void increaseVerticalOffset(){
		if (verticalOffset + getTotalVerticalTiles() < Constants.MAX_Y_COORD) { verticalOffset += 1; }
		// otherwise do nothing
	}

	public void decreaseVerticalOffset(){
		if (verticalOffset > Constants.MIN_Y_COORD) { verticalOffset -= 1; }
		// otherwise do nothing
	}

	public double getTileWidth(){
		return tileWidth;
	}

	public double getTileHeight(){
		return tileHeight;
	}

	public int getMiddleVerticalTile(){
		return middleVerticalTile;
	}

	public int getMiddleHorizontalTile(){
		return middleHorizontalTile;
	}
	
	public WorldTiles getWorld(){
		return myWorld;
	}

}
