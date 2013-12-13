package author.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import author.mapCreation.CanvasTileManager;

public class TestCanvasTileManager {
	
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
		// Do nothing
	}

	@After
	public void tearDown() throws Exception {
		// Do nothing
	}
	
	@Test
	public void testExpandView(){
		CanvasTileManager test1 = new CanvasTileManager(21, 14);
		CanvasTileManager test2 = new CanvasTileManager(15, 9);
		
		test1.expandView();
		test2.expandView();
		
		assertEquals("21 x 14 map", 24, test1.getTotalHorizontalTiles());
		assertEquals("21 x 14 map", 16, test1.getTotalVerticalTiles());
		
		assertEquals("15 x 9 map", 20, test2.getTotalHorizontalTiles());
		assertEquals("15 x 9 map", 12, test2.getTotalVerticalTiles());
		
		test1.expandView();
		test2.expandView();
		
		assertEquals("21 x 14 map", 27, test1.getTotalHorizontalTiles());
		assertEquals("21 x 14 map", 18, test1.getTotalVerticalTiles());
		
		assertEquals("15 x 9 map", 25, test2.getTotalHorizontalTiles());
		assertEquals("15 x 9 map", 15, test2.getTotalVerticalTiles());
		
		test1.expandView();
		test2.expandView();
		
		assertEquals("21 x 14 map", 30, test1.getTotalHorizontalTiles());
		assertEquals("21 x 14 map", 20, test1.getTotalVerticalTiles());
		
		assertEquals("15 x 9 map", 30, test2.getTotalHorizontalTiles());
		assertEquals("15 x 9 map", 18, test2.getTotalVerticalTiles());
	}
	
	@Test
	public void testContractView(){
		CanvasTileManager test1 = new CanvasTileManager(21, 14);
		CanvasTileManager test2 = new CanvasTileManager(15, 9);
		
		test1.contractView();
		test2.contractView();
		
		assertEquals("21 x 14 map", 18, test1.getTotalHorizontalTiles());
		assertEquals("21 x 14 map", 12, test1.getTotalVerticalTiles());
		
		assertEquals("15 x 9 map", 10, test2.getTotalHorizontalTiles());
		assertEquals("15 x 9 map", 6, test2.getTotalVerticalTiles());
		
		test1.contractView();
		test2.contractView();
		
		assertEquals("21 x 14 map", 15, test1.getTotalHorizontalTiles());
		assertEquals("21 x 14 map", 10, test1.getTotalVerticalTiles());
		
		assertEquals("15 x 9 map", 5, test2.getTotalHorizontalTiles());
		assertEquals("15 x 9 map", 3, test2.getTotalVerticalTiles());
		
		test1.contractView();
		test2.contractView();
		
		assertEquals("21 x 14 map", 12, test1.getTotalHorizontalTiles());
		assertEquals("21 x 14 map", 8, test1.getTotalVerticalTiles());
		
		assertEquals("15 x 9 map", 5, test2.getTotalHorizontalTiles());
		assertEquals("15 x 9 map", 3, test2.getTotalVerticalTiles());
		
	}
	
	@Test
	public void testTileAnchorX(){
		CanvasTileManager test1 = new CanvasTileManager(21, 14);
		CanvasTileManager test2 = new CanvasTileManager(15, 9);
		
		assertEquals("21 x 14 map", 0, test1.getTileAnchorX(0), 0.5);
		
		assertEquals("15 x 9 map", 0, test2.getTileAnchorX(0), 0.5);
		
	}
	
	@Test
	public void testTileAnchorY(){
		CanvasTileManager test1 = new CanvasTileManager(21, 14);
		CanvasTileManager test2 = new CanvasTileManager(15, 9);
		
		assertEquals("21 x 14 map", 0, test1.getTileAnchorY(0), 0.5);
		
		assertEquals("15 x 9 map", 0, test2.getTileAnchorY(0), 0.5);
	}

}
