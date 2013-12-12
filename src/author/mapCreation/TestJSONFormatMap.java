package author.mapCreation;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import location.Loc;

public class TestJSONFormatMap {

	private static WorldCreationMap myWorldMap;
	
	public static void main(String[] args){
		initWorldMap();
	}
	
	private static void initWorldMap(){
		myWorldMap = new WorldCreationMap();
		myWorldMap.put(new Loc(10, 30), generateTileWrapper("flower", "obstacle"));
		myWorldMap.put(new Loc(104, 120), generateTileWrapper("rob", "cameron crazy"));
		myWorldMap.put(new Loc(104, 120), generateTileWrapper("grass", "obstacle"));
		myWorldMap.put(new Loc(214, 21), generateTileWrapper("mike", "cameron crazy"));
		myWorldMap.put(new Loc(103, 87), generateTileWrapper("bob", "cameron crazy"));
		myWorldMap.put(new Loc(901, 534), generateTileWrapper("computer", "obstacle"));
		myWorldMap.put(new Loc(85, 234), generateTileWrapper("gatorade", "obstacle"));
		myWorldMap.put(new Loc(654, 24), generateTileWrapper("pen", "obstacle"));
		myWorldMap.put(new Loc(65, 23), generateTileWrapper("json", "sucks"));
		myWorldMap.put(new Loc(85, 23), generateTileWrapper("yankees", "sucks"));
		Map<String, List<Map<String, String>>> crazyMap = myWorldMap.generateJSONDataStructure();
		System.out.println("yo");
	}
	
	private static GenericTileWrapper generateTileWrapper(String name, String type){
		BufferedImage bi = new BufferedImage(24,23,1);
		return new GenericTileWrapper(name, type, bi);
	}
	
}
