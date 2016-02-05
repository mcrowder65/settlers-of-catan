package gameMapTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.*;
import shared.locations.*;

public class deleteSettlementTest {

	private GameMap map = new GameMap();
	public shared.locations.VertexObject[] settlements = {
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
		//invalid data - not in array
		
		map.deleteSettlement(new VertexLocation(new HexLocation(0,0), VertexDirection.NorthWest));
		assertEquals(3,map.getSettlements().length);
		
		map.deleteSettlement(new VertexLocation(new HexLocation(-2,0), VertexDirection.NorthEast));
		assertEquals(3,map.getSettlements().length);

		//correct data
		map.deleteSettlement(new VertexLocation(new HexLocation(2,-1), VertexDirection.SouthWest));
		assertEquals(2,map.getSettlements().length);

		
		map.deleteSettlement(new VertexLocation(new HexLocation(0,-2), VertexDirection.NorthWest));
		assertEquals(1,map.getSettlements().length);
		
		map.deleteSettlement(new VertexLocation(new HexLocation(-2,2), VertexDirection.NorthEast));
		assertEquals(0,map.getSettlements().length);
		
	}

}
