package gameMapTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.GameMap;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;

public class HasMunicipalityTest {

	private GameMap map = new GameMap();
	public VertexObject[] settlements = {
			map.vertexObjectFactory(1, 0, -2, VertexDirection.NorthWest),
			map.vertexObjectFactory(2, -2, 2, VertexDirection.NorthEast),
			map.vertexObjectFactory(3, 2, -1, VertexDirection.SouthWest)
	};
	
	public VertexObject[] cities = {
			map.vertexObjectFactory(0, 2, -2, VertexDirection.NorthEast),
			map.vertexObjectFactory(1, 0, -2, VertexDirection.SouthEast),
			map.vertexObjectFactory(2, -2, 0, VertexDirection.West),
			map.vertexObjectFactory(3, 0, 0, VertexDirection.East),
			map.vertexObjectFactory(3, 0, 0, VertexDirection.NorthEast),
			map.vertexObjectFactory(3, 0, 0, VertexDirection.NorthWest),
			map.vertexObjectFactory(3, 0, 0, VertexDirection.West),
			map.vertexObjectFactory(3, 0, 0, VertexDirection.SouthWest),
			map.vertexObjectFactory(3, 0, 0, VertexDirection.SouthEast)
	};
	
	
	@Before
	public void setUp() throws Exception {
		map.setSettlements(settlements);
		map.setCities(cities);
	}

	@Test
	public void test() {
		//Doesn't have anything
		assertFalse(map.hasMunicipality(new VertexLocation(new HexLocation(1,1),VertexDirection.East)));
		
		assertFalse(map.hasMunicipality(new VertexLocation(new HexLocation(1,-1),VertexDirection.SouthWest)));
		
		
		//Has settlement
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(0,-2),VertexDirection.NorthWest)));
		
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(-2,2),VertexDirection.NorthEast)));
		
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(2,-1),VertexDirection.SouthWest)));
		
		//Has city
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(2,-2),VertexDirection.NorthEast)));
		
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(0,-2),VertexDirection.SouthEast)));
		
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(-2,0),VertexDirection.West)));
		
		
		//Full hex
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(0,0),VertexDirection.East)));
		
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(0,0),VertexDirection.NorthEast)));
		
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(0,0),VertexDirection.NorthWest)));
		
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(0,0),VertexDirection.West)));
		
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(0,0),VertexDirection.SouthWest)));
		
		assertTrue(map.hasMunicipality(new VertexLocation(new HexLocation(0,0),VertexDirection.SouthEast)));
		
	}

}
