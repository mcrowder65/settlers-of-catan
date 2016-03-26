package communication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.util.ServerGameMap;
import shared.definitions.Hex;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.EdgeValue;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;

public class ServerGameMapTest {

	ServerGameMap map = new ServerGameMap();
	
	@Before
	public void setUp() throws Exception {
		System.out.println("ServerGameMap Testing");
		Hex[] hexMap = {
				new Hex(new HexLocation(-2,0),ResourceType.BRICK,1),
				new Hex(new HexLocation(-1,-1),ResourceType.WHEAT,2),
				new Hex(new HexLocation(0,-2),ResourceType.SHEEP,3),
				new Hex(new HexLocation(-2,1),ResourceType.SHEEP,4),
				new Hex(new HexLocation(-1,0),ResourceType.WHEAT,5),
				new Hex(new HexLocation(1,-2),ResourceType.ORE,6),
				new Hex(new HexLocation(-2,2),ResourceType.BRICK,7),
				new Hex(new HexLocation(-1,1),ResourceType.WOOD,8),
				new Hex(new HexLocation(0,0),ResourceType.ORE,9),
				new Hex(new HexLocation(1,-1),ResourceType.WOOD,10),
				new Hex(new HexLocation(2,-2),ResourceType.SHEEP,11),
				new Hex(new HexLocation(-1,2),ResourceType.WHEAT,12),
				new Hex(new HexLocation(0,1),ResourceType.WHEAT,1),
				new Hex(new HexLocation(1,0),ResourceType.BRICK,2),
				new Hex(new HexLocation(2,-1),ResourceType.BRICK,3),
				new Hex(new HexLocation(0,2),ResourceType.SHEEP,4),
				new Hex(new HexLocation(1,1),ResourceType.BRICK,5),
				new Hex(new HexLocation(2,0),ResourceType.ORE,6)
		};
		
		map.setHexes(hexMap);
		
	}

	@Test
	public void canBuildRoadSetUpTest() {
		System.out.println("Testing the canBuildRoadSetup in ServerGameMap");
		HexLocation Hloc1 = new HexLocation(3,3);
		
		//north side water hex
		EdgeLocation Eloc = new EdgeLocation(Hloc1, EdgeDirection.North);
		assertFalse(map.canBuildRoadSetup(0,Eloc));
		
		//southWest side water hex
		Eloc = new EdgeLocation(Hloc1, EdgeDirection.SouthWest);
		assertFalse(map.canBuildRoadSetup(0,Eloc));
		
		//testing north side of center hex
		Hloc1 = new HexLocation(0,0);
		Eloc = new EdgeLocation(Hloc1, EdgeDirection.North);
		
		assertTrue(map.canBuildRoadSetup(0,Eloc));
		
		//already a road there
		map.buildRoad(new EdgeValue(0,Eloc));
		assertFalse(map.canBuildRoadSetup(0,Eloc));
		
	}
	
	@Test
	public void canBuildRoadNormalTest(){
		System.out.println("Testing the canBuildRoadNormal in ServerGameMap");
		HexLocation Hloc1 = new HexLocation(3,3);
		
		//north side water hex
		EdgeLocation Eloc = new EdgeLocation(Hloc1, EdgeDirection.North);
		assertFalse(map.canBuildRoadNormal(0,Eloc));
		
		//southWest side water hex
		Eloc = new EdgeLocation(Hloc1, EdgeDirection.SouthWest);
		assertFalse(map.canBuildRoadNormal(0,Eloc));
		
		Hloc1 = new HexLocation(0,0);
		Eloc = new EdgeLocation(Hloc1, EdgeDirection.North);
		
		assertFalse(map.canBuildRoadNormal(0,Eloc));
		//already a road there
		map.buildRoad(new EdgeValue(0,Eloc));
		assertFalse(map.canBuildRoadNormal(0,Eloc));
		
	}

}
