package gameMapTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.GameMap;
import shared.locations.VertexDirection;
import shared.locations.VertexObject;

public class VertexObjectFactoryTest {

	private GameMap map;
	private VertexObject testObject;
	
	@Before
	public void setUp() throws Exception {
		map = new GameMap();
	}
	
	
	@Test
	public void test() {
		//test1
		testObject = map.vertexObjectFactory(2, 0, -2, VertexDirection.NorthEast);
	
		
		assertEquals(2,testObject.getOwner());
		assertEquals(0, testObject.getLocation().getHexLoc().getX());
		assertEquals(-2, testObject.getLocation().getHexLoc().getY());
		assertEquals(VertexDirection.NorthEast, testObject.getLocation().getDirection());
		
		
		//test2
		testObject = map.vertexObjectFactory(1, 2, 0, VertexDirection.West);
		assertEquals(1,testObject.getOwner());
		assertEquals(2, testObject.getLocation().getHexLoc().getX());
		assertEquals(0, testObject.getLocation().getHexLoc().getY());
		assertEquals(VertexDirection.West, testObject.getLocation().getDirection());
		
		//test3
		testObject = map.vertexObjectFactory(3, 0, 1, VertexDirection.East);
		assertEquals(3,testObject.getOwner());
		assertEquals(0, testObject.getLocation().getHexLoc().getX());
		assertEquals(1, testObject.getLocation().getHexLoc().getY());
		assertEquals(VertexDirection.East, testObject.getLocation().getDirection());
		
		
			
	}
		
}
