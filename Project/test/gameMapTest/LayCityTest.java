package gameMapTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.GameMap;
import shared.locations.VertexDirection;

public class LayCityTest {

	private GameMap map;

	@Before
	public void setUp() throws Exception {
		map = new GameMap();
		//Laying of Settlements
		map.laySettlement(map.vertexObjectFactory(0, 0, 0, VertexDirection.East));
		map.laySettlement(map.vertexObjectFactory(1, 2, -2, VertexDirection.NorthWest));
		map.laySettlement(map.vertexObjectFactory(3, -1, -1, VertexDirection.NorthWest));

		//Laying of Cities. (Settlements should be substituted by cities).
		map.layCity(map.vertexObjectFactory(0, 0, 0, VertexDirection.East));
		map.layCity(map.vertexObjectFactory(1, 2, -2, VertexDirection.NorthWest));
		map.layCity(map.vertexObjectFactory(3, -1, -1, VertexDirection.NorthWest));

	}


	@Test
	public void test() {
		//Player laid settlement at spot on land - correct
		assertTrue(map.hasCity(map.vertexObjectFactory(0, 0, 0, VertexDirection.East)));

		assertTrue(map.hasCity(map.vertexObjectFactory(0, 1, 0, VertexDirection.NorthWest)));

		assertTrue(map.hasCity(map.vertexObjectFactory(0, 1, -1, VertexDirection.SouthWest)));

		//Player laid settlement at spot on vertex next to sea - correct
		assertTrue(map.hasCity(map.vertexObjectFactory(1, 2, -2, VertexDirection.NorthWest)));

		assertTrue(map.hasCity(map.vertexObjectFactory(1, 1, -2, VertexDirection.East)));

		assertTrue(map.hasCity(map.vertexObjectFactory(3, -1, -1, VertexDirection.NorthWest)));

		//Check that settlements were deleted correctly from the array
		assertFalse(map.hasSettlement(map.vertexObjectFactory(0, 0, 0, VertexDirection.East)));
		assertFalse(map.hasSettlement(map.vertexObjectFactory(0, 1, 0, VertexDirection.NorthWest)));
		assertFalse(map.hasSettlement(map.vertexObjectFactory(0, 1, -1, VertexDirection.SouthWest)));
		
		
		assertFalse(map.hasSettlement(map.vertexObjectFactory(1, 2, -2, VertexDirection.NorthWest)));
		assertFalse(map.hasSettlement(map.vertexObjectFactory(1, 1, -2, VertexDirection.East)));
		
		assertFalse(map.hasSettlement(map.vertexObjectFactory(3, -1, -1, VertexDirection.NorthWest)));



		//There's no checking of incorrect information because one of the pre-conditions
		//for this method is that the location is valid (empty, on land), so it's assumed
		//that when this method is called, the only responsibility of this method is to lay
		//the settlement properly on the corresponding hexes.
	}

}
