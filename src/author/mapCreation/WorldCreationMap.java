package author.mapCreation;

import java.util.HashMap;
import java.util.Map;
import location.Loc;

public class WorldCreationMap {

        
	private Map<Loc, GenericTileWrapper> myTileMap;
	
	public WorldCreationMap() {
		myTileMap = new HashMap<Loc, GenericTileWrapper>();
	}
	
	public void put(Loc location, GenericTileWrapper tile){
		myTileMap.put(location, tile);
	}
	
	public void remove(Loc location){
		myTileMap.remove(location);
	}
	
	public Map<Loc, GenericTileWrapper> getTilesInWindow(int x1, int x2, int y1, int y2){
		Map<Loc, GenericTileWrapper> currentWindowMap = new HashMap<Loc, GenericTileWrapper>();
		for (int i = x1; i <= x2; i++){
			for (int j = y1; j <= y2; j++){
				Loc currentLoc = new Loc(i, j);
				if (myTileMap.containsKey(currentLoc)){
					currentWindowMap.put(currentLoc, myTileMap.get(currentLoc));
				}
			}
		}
		return currentWindowMap;
	}
	
	public Map<Loc, GenericTileWrapper> getWorldTileMap() {
	    return myTileMap;
	}
	
}
