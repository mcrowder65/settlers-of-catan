package communication;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.util.ServerGameMap;
import shared.definitions.Hex;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.EdgeValue;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;

public class ServerGameMapTest {

	ServerGameMap map = new ServerGameMap();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Testing ServergGameMap");
	}
	
	
	@Before
	public void setUp() throws Exception {
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
		
		EdgeLocation Eloc2 = new EdgeLocation(Hloc1, EdgeDirection.NorthWest);
		assertTrue(map.canBuildRoadNormal(0,Eloc2));
	}
	
	@Test
	public void getMunicipalityOnHexTest(){
		System.out.println("Testing the getMunicipalityOnHex in ServerGameMap");
		List<VertexObject> municipalities = map.getMunicipalityOnHex(new HexLocation(0,0)); 
		
		//Testing with no cities
		assertTrue(municipalities.size() == 0);
		
		//testing with one city
		map.layCity(new VertexObject(0, new VertexLocation(new HexLocation(0,0),VertexDirection.NorthEast)));
		municipalities = map.getMunicipalityOnHex(new HexLocation(0,0));
		assertTrue(municipalities.size() == 1);
		
		//testing with one city and one settlement
		map.laySettlement(new VertexObject(0, new VertexLocation(new HexLocation(1,2),VertexDirection.NorthEast)),false);
		municipalities = map.getMunicipalityOnHex(new HexLocation(0,0));
		assertTrue(municipalities.size() == 1);
		
		VertexObject city = municipalities.get(0);
		assertTrue(city.getLocation().getHexLoc().equals(new HexLocation(0,0)));
		assertTrue(city.getLocation().getDir().equals(VertexDirection.NorthEast));

	}
	
	@Test
	public void getCityOnHexTest(){
		System.out.println("Testing the getCityOnHex in ServerGameMap");
		List<VertexObject> municipalities = map.getCityOnHex(new HexLocation(0,0)); 
		
		//Testing with no cities
		assertTrue(municipalities.size() == 0);
		
		//testing with one settlement
		map.laySettlement(new VertexObject(0, new VertexLocation(new HexLocation(1,2),VertexDirection.NorthEast)),false);
		municipalities = map.getCityOnHex(new HexLocation(1,2));
		assertTrue(municipalities.size() == 0);
		
		//testing with one city
		map.layCity(new VertexObject(0, new VertexLocation(new HexLocation(1,2),VertexDirection.NorthEast)));
		municipalities = map.getCityOnHex(new HexLocation(1,2));
		assertTrue(municipalities.size() == 1);
		
		VertexObject city = municipalities.get(0);
		assertTrue(city.getLocation().getHexLoc().equals(new HexLocation(1,2)));
		assertTrue(city.getLocation().getDir().equals(VertexDirection.NorthEast));
	}
	
	@Test
	public void getSettlementOnHexTest(){
		System.out.println("Testing the getSettlementOnHex in ServerGameMap");
		List<VertexObject> municipalities = map.getSettlementOnHex(new HexLocation(0,0)); 
		
		//Testing with no settlements
		assertTrue(municipalities.size() == 0);
		
		//testing with one city
		map.layCity(new VertexObject(0, new VertexLocation(new HexLocation(1,2),VertexDirection.NorthEast)));
		municipalities = map.getSettlementOnHex(new HexLocation(1,2));
		assertTrue(municipalities.size() == 0);
		
		//testing with one settlement
		map.laySettlement(new VertexObject(0, new VertexLocation(new HexLocation(1,2),VertexDirection.NorthEast)),false);
		municipalities = map.getSettlementOnHex(new HexLocation(1,2));
		assertTrue(municipalities.size() == 1);
		
	
		
		VertexObject city = municipalities.get(0);
		assertTrue(city.getLocation().getHexLoc().equals(new HexLocation(1,2)));
		assertTrue(city.getLocation().getDir().equals(VertexDirection.NorthEast));
	}

	@Test
	public void canUseRoadBuilderTest(){
		System.out.println("Testing the canUseRoadBuilderTest in ServerGameMap");
		
		HexLocation Hloc1 = new HexLocation(3,3);
		
		//north side water hex
		EdgeLocation Eloc = new EdgeLocation(Hloc1, EdgeDirection.North);
		assertFalse(map.canUseRoadBuilder(0,Eloc,Eloc));
		
		//testing with no connecting roads
		HexLocation Hloc2 = new HexLocation(0,0);
		EdgeLocation Eloc2 = new EdgeLocation(Hloc2, EdgeDirection.North);
		EdgeLocation Eloc3 = new EdgeLocation(Hloc2, EdgeDirection.NorthEast);
		assertFalse(map.canUseRoadBuilder(0,Eloc2,Eloc3));
		
		//testing w/ success
		EdgeLocation Eloc4 = new EdgeLocation(Hloc2, EdgeDirection.NorthWest);
		map.buildRoad(new EdgeValue(0,Eloc4));
		assertTrue(map.canUseRoadBuilder(0,Eloc2,Eloc3));
	}
}
