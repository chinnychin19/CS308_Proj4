package author.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import location.Loc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import constants.Constants;

import author.mapCreation.GenericTileWrapper;
import author.mapCreation.WorldCreationMap;

public class TestWorldCreationMap {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Do nothing
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Do nothing
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		// Do nothing
	}

	@Test
	public void test() {
		String dummyImagePath = Constants.IMG_FOLDER_FILEPATH + "/obstacles/flower.png";
		
		List<String> headphoneList = new ArrayList<String>();
		headphoneList.add("sony");
		headphoneList.add("bose");
		headphoneList.add("audiotechnica");
		headphoneList.add("apple");
		
		List<String> coffeeList = new ArrayList<String>();
		coffeeList.add("americano");
		coffeeList.add("black");
		coffeeList.add("mocha");
		coffeeList.add("latte");
		
		List<String> studentList = new ArrayList<String>();
		studentList.add("wes");
		studentList.add("mike");
		studentList.add("rob");
		studentList.add("matt");
		
		GenericTileWrapper headphone2 = new GenericTileWrapper("sony", "headphone", dummyImagePath);
		GenericTileWrapper student1 = new GenericTileWrapper("mike", "student", dummyImagePath);
		GenericTileWrapper coffee2 = new GenericTileWrapper("latte", "coffee", dummyImagePath);
		GenericTileWrapper student2 = new GenericTileWrapper("matt", "student", dummyImagePath);
		GenericTileWrapper student3 = new GenericTileWrapper("rob", "student", dummyImagePath);
		GenericTileWrapper headphone1 = new GenericTileWrapper("bose", "headphone", dummyImagePath);
		GenericTileWrapper student4 = new GenericTileWrapper("wes", "student", dummyImagePath);
		GenericTileWrapper coffee1 = new GenericTileWrapper("mocha", "coffee", dummyImagePath);
		GenericTileWrapper coffee3 = new GenericTileWrapper("americano", "coffee", dummyImagePath);
		GenericTileWrapper headphone3 = new GenericTileWrapper("audiotechnica", "headphone", dummyImagePath);
		GenericTileWrapper headphone4 = new GenericTileWrapper("apple", "headphone", dummyImagePath);
		GenericTileWrapper coffee4 = new GenericTileWrapper("black", "coffee", dummyImagePath);
		
		WorldCreationMap test1 = new WorldCreationMap();
		
		test1.put(new Loc(102, 155), headphone1);
		test1.put(new Loc(534, 213), headphone2);
		test1.put(new Loc(12, 532), headphone3);
		test1.put(new Loc(623, 89), headphone4);
		
		test1.put(new Loc(723, 653), student1);
		test1.put(new Loc(65, 23), student2);
		test1.put(new Loc(76, 1), student3);
		test1.put(new Loc(23, 90), student4);
		
		test1.put(new Loc(723, 653), coffee1);
		test1.put(new Loc(113, 5), coffee2);
		test1.put(new Loc(1, 54), coffee3);
		test1.put(new Loc(111, 555), coffee4);
		
		Map<String, List<Map<String, String>>> testMap = test1.generateJSONDataStructure();
		
		List<Map<String, String>> myHeadphoneMapList = testMap.get("headphone");
		List<Map<String, String>> myStudentMapList = testMap.get("student");
		List<Map<String, String>> myCoffeeMapList = testMap.get("headphone");
		
		for (Map<String, String> i : myHeadphoneMapList){
			
		}

		for (Map<String, String> i : myStudentMapList){
			assertTrue(testMap.get("headphone").contains(i));
		}
		
		for (Map<String, String> i : myCoffeeMapList){
			assertTrue(testMap.get("headphone").contains(i));
		}
		
	}

}
