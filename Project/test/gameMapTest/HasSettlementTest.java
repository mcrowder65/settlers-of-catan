package gameMapTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.GameMap;
import shared.locations.VertexDirection;
import shared.locations.VertexObject;

public class HasSettlementTest {

	
	private GameMap map = new GameMap();
	public VertexObject[] settlements = {
			map.vertexObjectFactory(1, 0, -2, VertexDirection.NorthWest),
			map.vertexObjectFactory(2, -2, 2, VertexDirection.NorthEast),
			map.vertexObjectFactory(3, 2, -1, VertexDirection.SouthWest)
	};
		
	
	@Before
	public void setUp() throws Exception {
		map.setSettlements(settlements);
		
	}

	@Test
	public void test() {
		//Player doesn't have settlement
		assertFalse(map.hasSettlement(map.vertexObjectFactory(0, 0, -2, VertexDirection.NorthWest)));
		
		assertFalse(map.hasSettlement(map.vertexObjectFactory(2, 2, 0, VertexDirection.East)));
		
		assertFalse(map.hasSettlement(map.vertexObjectFactory(3, 1, 0, VertexDirection.East)));
		
		
		//Player does have settlement
		assertTrue(map.hasSettlement(map.vertexObjectFactory(3, 2, -1, VertexDirection.SouthWest)));
		
		assertTrue(map.hasSettlement(map.vertexObjectFactory(2, -2, 2, VertexDirection.NorthEast)));
		
		assertTrue(map.hasSettlement(map.vertexObjectFactory(1, 0, -2, VertexDirection.NorthWest)));
	}

}
