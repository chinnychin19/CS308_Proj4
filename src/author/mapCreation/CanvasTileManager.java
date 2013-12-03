package author.mapCreation;

import constants.Constants;

public class CanvasTileManager {

	private int myGCD;
	
	private int widthRatio;
	private int heightRatio;
	
	private int widthFactor;
	private int heightFactor;

    private int middleHorizontalTile;
    private int middleVerticalTile;

    private double tileWidth;
    private double tileHeight;
    
    public CanvasTileManager(){
    	// default width and height are 15 and 9 respectively
    	configureTiles(Constants.NUM_TILES_HORIZONTAL, Constants.NUM_TILES_VERTICAL);
    }
    
    public CanvasTileManager(int horizontalTiles, int verticalTiles){
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
    
    public void expandMap(){
    	widthFactor += 1;
    	heightFactor += 1;
    	//widthRatio = widthRatio * widthFactor;
    	//heightRatio = heightRatio * heightFactor;
    	calcNewTileValues();
    }
    
    public void contractMap(){
    	if (widthFactor > 1 && heightFactor > 1){
    		widthFactor -= 1;
    		heightFactor -= 1;
    		//widthRatio = widthRatio * widthFactor;
        	//heightRatio = heightRatio * heightFactor;
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
    	return (int) (xValue/tileWidth);
    }
    
    public int getVerticalTileNum(int yValue){
    	return (int) (yValue/tileHeight);
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
	
}
