package author.mapCreation;

import java.util.HashMap;
import java.util.Map;

import location.Loc;

public class WorldTiles {

	private Map<Loc, GenericTileWrapper> myTileLocations;
	//private CanvasTileManager myTileManager;
	
	public WorldTiles(){
		myTileLocations = new HashMap<Loc, GenericTileWrapper>();
		//myTileManager = new CanvasTileManager();
	}
	
	public void addToMap(Loc location, GenericTileWrapper tile){
		myTileLocations.put(location, tile);
	}
	
	public Map<Loc, GenericTileWrapper> getTileMap(){
		return myTileLocations;
	}
	
	
	
}
