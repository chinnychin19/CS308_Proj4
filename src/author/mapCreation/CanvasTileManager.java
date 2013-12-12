package author.mapCreation;

import java.util.Map;
import location.Loc;
import constants.Constants;


/**
 * Handles the management, arrangement, and sizing
 * of the visual tile system in the MapCreationView.
 * Holds the methods and state necessary to calculate
 * the current dimension, in tiles, of the MapCreationView.
 * Converts point-and-click coordinates into gameplay tiles
 * for easy object placement.
 * 
 * @author Michael Marion and Wes Koorbusch
 * 
 */

public class CanvasTileManager {

    private WorldCreationMap myWorld;

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

    public CanvasTileManager () {
        myWorld = new WorldCreationMap();
        horizontalOffset = Constants.MIN_X_COORD;
        verticalOffset = Constants.MIN_Y_COORD;

        // default width and height are 15 and 9 respectively
        configureTiles(Constants.NUM_TILES_HORIZONTAL, Constants.NUM_TILES_VERTICAL);
    }

    public CanvasTileManager (int horizontalTiles, int verticalTiles) {
        horizontalOffset = 0;
        verticalOffset = 0;

        // custom number of tiles
        configureTiles(horizontalTiles, verticalTiles);
    }

    public void configureTiles (int horizontal, int vertical) {
        // Scales no matter what, but this could cause errors
        // TODO: Come up with way to check that the parameters will work
        myGCD = GCD(horizontal, vertical);
        widthRatio = horizontal / myGCD;
        heightRatio = vertical / myGCD;

        widthFactor = horizontal / widthRatio;
        heightFactor = vertical / heightRatio;

        calcNewTileValues();
    }

    private void calcNewTileValues () {
        middleHorizontalTile = (myGCD * widthRatio) / 2;
        middleVerticalTile = (myGCD * heightRatio) / 2;

        tileWidth = (double) Constants.WIDTH / (getTotalHorizontalTiles());
        tileHeight = (double) Constants.HEIGHT / (getTotalVerticalTiles());

    }

    private int GCD (int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /*
     * public void getTileClickLoc(int x, int y){
     * myWorld.put(new Loc(x, y), new GenericTileWrapper("blah", nullnew Image()));
     * }
     */

    public void expandView () {
        if (horizontalOffset + getTotalHorizontalTiles() + widthRatio > Constants.MAX_X_COORD
            || verticalOffset + getTotalVerticalTiles() + heightRatio > Constants.MAX_Y_COORD) {
            // do nothing because view will expand beyond boundaries
        }
        else { // good to expand view
            widthFactor += 1;
            heightFactor += 1;
            calcNewTileValues();
        }
        getBorderTiles();
    }

    public void contractView () {
        if (widthFactor > 1 && heightFactor > 1) {
            widthFactor -= 1;
            heightFactor -= 1;
            calcNewTileValues();
        }
        getBorderTiles();
        // do nothing otherwise
    }

    public int getTotalHorizontalTiles () {
        return widthRatio * widthFactor;
    }

    public int getTotalVerticalTiles () {
    	System.out.println("Vertical Tile numbers here: " + (heightRatio * heightFactor));
        return heightRatio * heightFactor;
    }
    
    public int getFurthestLeftTile(){
    	System.out.println("Left most tile is: " + horizontalOffset);
    	return horizontalOffset;
    }
    
    public int getTopTile(){
    	System.out.println("Top tile is: " + verticalOffset);
    	return horizontalOffset;
    }
    
    public int getBottomTile(){
    	System.out.println("Bottom tile is: " + (getTotalVerticalTiles() + verticalOffset));
    	return getTotalVerticalTiles() + verticalOffset;
    }
    
    public int getFurthestRightTile(){
    	System.out.println("Right most tile is: " + (horizontalOffset + getTotalHorizontalTiles()));
    	return horizontalOffset + getTotalHorizontalTiles();
    }

    public int getHorizontalTileNum (int xValue) {
        return (int) ((xValue / tileWidth) + horizontalOffset);
    }

    public int getVerticalTileNum (int yValue) {
        return (int) ((yValue / tileHeight) + verticalOffset);
    }

    public double getTileAnchorX (int x) {
        return (x - horizontalOffset) * tileWidth;
    }

    public double getTileAnchorY (int y) {
        return (y - verticalOffset) * tileHeight;
    }

    public void increaseHorizontalOffset () {
        if (horizontalOffset + getTotalHorizontalTiles() < Constants.MAX_X_COORD) {
            horizontalOffset += 1;
        }
        getBorderTiles();
        // otherwise do nothing
    }
    
    private void getBorderTiles(){
    	getFurthestLeftTile();
    	getFurthestRightTile();
    	getTopTile();
    	getBottomTile();
    }

    public void decreaseHorizontalOffset () {
        if (horizontalOffset > Constants.MIN_X_COORD) {
            horizontalOffset -= 1;
        }
        getBorderTiles();
        // otherwise do nothing
    }

    public void increaseVerticalOffset () {
        if (verticalOffset + getTotalVerticalTiles() < Constants.MAX_Y_COORD) {
            verticalOffset += 1;
        }
        getBorderTiles();
        // otherwise do nothing
    }

    public void decreaseVerticalOffset () {
        if (verticalOffset > Constants.MIN_Y_COORD) {
            verticalOffset -= 1;
        }
        getBorderTiles();
        // otherwise do nothing
    }

    public double getTileWidth () {
        return tileWidth;
    }

    public double getTileHeight () {
        return tileHeight;
    }

    public int getMiddleVerticalTile () {
        return middleVerticalTile;
    }

    public int getMiddleHorizontalTile () {
        return middleHorizontalTile;
    }

    public WorldCreationMap getWorld () {
        return myWorld;
    }

    public Map<Loc, GenericTileWrapper> getWorldTiles () {
        return myWorld.getWorldTileMap();
    }

}
