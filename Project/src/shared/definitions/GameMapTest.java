package shared.definitions;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.EdgeValue;

public class GameMapTest {

	@Test
	public void testIsLand(){
		GameMap map = new GameMap();
		
		//Testing center of Map
		HexLocation location1 = new HexLocation(0,0);
		Boolean isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(0,-1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(0,-2);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(0,1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(0,2);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(1,-2);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(1,-1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(1,0);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(1,1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(2,-2);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(2,-1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(2,0);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(-1,-1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(-1,0);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(-1,1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(-1,2);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(-2,0);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(-2,1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		location1 = new HexLocation(-2,2);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == true);
		
		//Testing Water
		location1 = new HexLocation(3,3);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(3,-1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(2,1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(1,2);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(2,2);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(0,3);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(-2,3);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(-3,1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(-3,0);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(-2,-1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(-1,-2);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(-2,-2);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(0,-3);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(1,-3);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(1000,0);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(0,3000);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(-5,1);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
		
		location1 = new HexLocation(0,5);
		isLand1 = map.isLand(location1);
		assertTrue(isLand1 == false);
	}
	
	@Test
	public void testHasRoadAllPlayers(){
		GameMap map = new GameMap();
		
		HexLocation hexLocation = new HexLocation(0,0);
		EdgeLocation edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.North);
		int owner = 0;
		EdgeValue edgeValue = new EdgeValue(owner,edgeLocation);
		map.buildRoad(edgeValue);
		
		HexLocation hexLocation2 = new HexLocation(1,1);
		EdgeLocation edgeLocation2 = new EdgeLocation(hexLocation2, EdgeDirection.NorthWest);
		int owner2 = 0;
		EdgeValue edgeValue2 = new EdgeValue(owner2,edgeLocation2);
		map.buildRoad(edgeValue2);
		
		HexLocation hexLocation3 = new HexLocation(-1,2);
		EdgeLocation edgeLocation3 = new EdgeLocation(hexLocation3, EdgeDirection.NorthEast);
		int owner3 = 0;
		EdgeValue edgeValue3 = new EdgeValue(owner3,edgeLocation3);
		map.buildRoad(edgeValue3);
		
		HexLocation hexLocation4 = new HexLocation(-2,0);
		EdgeLocation edgeLocation4 = new EdgeLocation(hexLocation4, EdgeDirection.South);
		int owner4 = 0;
		EdgeValue edgeValue4 = new EdgeValue(owner4,edgeLocation4);
		map.buildRoad(edgeValue4);
		
		HexLocation hexLocation5 = new HexLocation(2,0);
		EdgeLocation edgeLocation5 = new EdgeLocation(hexLocation5, EdgeDirection.SouthWest);
		int owner5 = 0;
		EdgeValue edgeValue5 = new EdgeValue(owner5,edgeLocation5);
		map.buildRoad(edgeValue5);
		
		Boolean cantLay = map.hasRoadAllPlayers(edgeLocation);
		assertTrue(cantLay == true);
		
		cantLay = map.hasRoadAllPlayers(edgeLocation2);
		assertTrue(cantLay == true);
		
		cantLay = map.hasRoadAllPlayers(edgeLocation3);
		assertTrue(cantLay == true);
		
		cantLay = map.hasRoadAllPlayers(edgeLocation4);
		assertTrue(cantLay == true);
		
		cantLay = map.hasRoadAllPlayers(edgeLocation5);
		assertTrue(cantLay == true);
		
	}
	
	@Test
	public void testHasRoadPersonalTrue(){
		GameMap map = new GameMap();
		
		HexLocation hexLocation = new HexLocation(0,0);
		EdgeLocation edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.North);
		int owner = 0;
		EdgeValue edgeValue = new EdgeValue(owner,edgeLocation);
		map.buildRoad(edgeValue);
		
		HexLocation hexLocation2 = new HexLocation(1,1);
		EdgeLocation edgeLocation2 = new EdgeLocation(hexLocation2, EdgeDirection.NorthWest);
		int owner2 = 0;
		EdgeValue edgeValue2 = new EdgeValue(owner2,edgeLocation2);
		map.buildRoad(edgeValue2);
		
		HexLocation hexLocation3 = new HexLocation(-1,2);
		EdgeLocation edgeLocation3 = new EdgeLocation(hexLocation3, EdgeDirection.NorthEast);
		int owner3 = 1;
		EdgeValue edgeValue3 = new EdgeValue(owner3,edgeLocation3);
		map.buildRoad(edgeValue3);
		
		HexLocation hexLocation4 = new HexLocation(-2,0);
		EdgeLocation edgeLocation4 = new EdgeLocation(hexLocation4, EdgeDirection.South);
		int owner4 = 2;
		EdgeValue edgeValue4 = new EdgeValue(owner4,edgeLocation4);
		map.buildRoad(edgeValue4);
		
		HexLocation hexLocation5 = new HexLocation(2,0);
		EdgeLocation edgeLocation5 = new EdgeLocation(hexLocation5, EdgeDirection.SouthWest);
		int owner5 = 2;
		EdgeValue edgeValue5 = new EdgeValue(owner5,edgeLocation5);
		map.buildRoad(edgeValue5);
		
		boolean isPersonal = map.hasRoadPersonal(edgeValue);
		assertTrue(isPersonal == true);
		isPersonal = map.hasRoadPersonal(edgeValue2);
		assertTrue(isPersonal == true);
		isPersonal = map.hasRoadPersonal(edgeValue4);
		assertTrue(isPersonal == true);
		
	}
	
	@Test
	public void testHasRoadPersonalFalse(){
		GameMap map = new GameMap();
		
		HexLocation hexLocation = new HexLocation(0,0);
		EdgeLocation edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.North);
		int owner = 0;
		EdgeValue edgeValue = new EdgeValue(owner,edgeLocation);
		map.buildRoad(edgeValue);
		
		HexLocation hexLocation2 = new HexLocation(1,1);
		EdgeLocation edgeLocation2 = new EdgeLocation(hexLocation2, EdgeDirection.NorthWest);
		int owner2 = 0;
		EdgeValue edgeValue2 = new EdgeValue(owner2,edgeLocation2);
		map.buildRoad(edgeValue2);
		
		
		boolean isPersonal = map.hasRoadPersonal(edgeValue);
		assertTrue(isPersonal == true);
		isPersonal = map.hasRoadPersonal(edgeValue);
		assertTrue(isPersonal == true);
		
		//new owner
		HexLocation hexLocation3 = new HexLocation(1,1);
		EdgeLocation edgeLocation3 = new EdgeLocation(hexLocation3, EdgeDirection.NorthWest);
		int owner3 = 1;
		EdgeValue edgeValue3 = new EdgeValue(owner3,edgeLocation3);
		isPersonal = map.hasRoadPersonal(edgeValue3);
		assertTrue(isPersonal == false);
		
	}

	@Test
	public void testGetOppositeHex(){
		GameMap map = new GameMap();
		HexLocation homeHexLoc = new HexLocation(0,0);
		HexLocation hex1Loc = new HexLocation(0,1);
		HexLocation hex2Loc = new HexLocation(0,2);
		HexLocation hex3Loc = new HexLocation(0,-1);
		HexLocation hex4Loc = new HexLocation(0,-2);
		HexLocation hex5Loc = new HexLocation(1,1);
		HexLocation hex6Loc = new HexLocation(1,-1);
		HexLocation hex7Loc = new HexLocation(1,-2);
		HexLocation hex8Loc = new HexLocation(-1,2);
		HexLocation hex9Loc = new HexLocation(-1,1);
		HexLocation hex10Loc = new HexLocation(-1,-1);
		HexLocation hex11Loc = new HexLocation(-1,0);
		Hex homeHex = new Hex(homeHexLoc, ResourceType.WOOD,1);
		Hex hex1 = new Hex(hex1Loc, ResourceType.WOOD,1);
		Hex hex2 = new Hex(hex2Loc, ResourceType.WOOD,1);
		Hex hex3 = new Hex(hex3Loc, ResourceType.WOOD,1);
		Hex hex4 = new Hex(hex4Loc, ResourceType.WOOD,1);
		Hex hex5 = new Hex(hex5Loc, ResourceType.WOOD,1);
		Hex hex6 = new Hex(hex6Loc, ResourceType.WOOD,1);
		Hex hex7 = new Hex(hex7Loc, ResourceType.WOOD,1);
		Hex hex8 = new Hex(hex8Loc, ResourceType.WOOD,1);
		Hex hex9 = new Hex(hex9Loc, ResourceType.WOOD,1);
		Hex hex10 = new Hex(hex10Loc, ResourceType.WOOD,1);
		Hex hex11 = new Hex(hex11Loc, ResourceType.WOOD,1);
		
		
		Hex[] allHexes = {homeHex,hex1,hex2,hex3,hex4,hex5,hex6,hex7,hex8,hex9,hex10,hex11};
		map.setHexes(allHexes);
		HexLocation location = map.getOppositeHex(homeHexLoc,EdgeDirection.North);
		int xCoord = location.getX();
		int yCoord = location.getY();
		assertTrue(xCoord == 0);
		assertTrue(yCoord == -1);
		
		location = map.getOppositeHex(homeHexLoc,EdgeDirection.South);
		xCoord = location.getX();
		yCoord = location.getY();
		assertTrue(xCoord == 0);
		assertTrue(yCoord == 1);
		
		location = map.getOppositeHex(hex1Loc,EdgeDirection.SouthEast);
		xCoord = location.getX();
		yCoord = location.getY();
		assertTrue(xCoord == 1);
		assertTrue(yCoord == 1);
		
		location = map.getOppositeHex(hex1Loc,EdgeDirection.SouthWest);
		xCoord = location.getX();
		yCoord = location.getY();
		assertTrue(xCoord == -1);
		assertTrue(yCoord == 2);
		
		location = map.getOppositeHex(hex1Loc,EdgeDirection.NorthEast);
		xCoord = location.getX();
		yCoord = location.getY();
		assertTrue(xCoord == 1);
		assertTrue(yCoord == 0);
		
		location = map.getOppositeHex(hex1Loc,EdgeDirection.NorthWest);
		xCoord = location.getX();
		yCoord = location.getY();
		assertTrue(xCoord == -1);
		assertTrue(yCoord == 1);
		assertTrue(xCoord !=0);
	}

	@Test
	public void canLayRoad(){

		GameMap map = new GameMap();
		HexLocation homeHexLoc = new HexLocation(0,0);
		HexLocation hex1Loc = new HexLocation(0,1);
		HexLocation hex2Loc = new HexLocation(0,2);
		HexLocation hex3Loc = new HexLocation(0,-1);
		HexLocation hex4Loc = new HexLocation(0,-2);
		HexLocation hex5Loc = new HexLocation(1,1);
		HexLocation hex6Loc = new HexLocation(1,-1);
		HexLocation hex7Loc = new HexLocation(1,-2);
		HexLocation hex8Loc = new HexLocation(-1,2);
		HexLocation hex9Loc = new HexLocation(-1,1);
		HexLocation hex10Loc = new HexLocation(-1,-1);
		HexLocation hex11Loc = new HexLocation(-1,0);
		Hex homeHex = new Hex(homeHexLoc, ResourceType.WOOD,1);
		Hex hex1 = new Hex(hex1Loc, ResourceType.WOOD,1);
		Hex hex2 = new Hex(hex2Loc, ResourceType.WOOD,1);
		Hex hex3 = new Hex(hex3Loc, ResourceType.WOOD,1);
		Hex hex4 = new Hex(hex4Loc, ResourceType.WOOD,1);
		Hex hex5 = new Hex(hex5Loc, ResourceType.WOOD,1);
		Hex hex6 = new Hex(hex6Loc, ResourceType.WOOD,1);
		Hex hex7 = new Hex(hex7Loc, ResourceType.WOOD,1);
		Hex hex8 = new Hex(hex8Loc, ResourceType.WOOD,1);
		Hex hex9 = new Hex(hex9Loc, ResourceType.WOOD,1);
		Hex hex10 = new Hex(hex10Loc, ResourceType.WOOD,1);
		Hex hex11 = new Hex(hex11Loc, ResourceType.WOOD,1);
		
		
		Hex[] allHexes = {homeHex,hex1,hex2,hex3,hex4,hex5,hex6,hex7,hex8,hex9,hex10,hex11};
		map.setHexes(allHexes);
		
		
		
		EdgeLocation local = new EdgeLocation(homeHexLoc, EdgeDirection.South);
		EdgeValue road1 = new EdgeValue(0,local);
		Boolean canLay = map.canLayRoad(road1);
		assertTrue(canLay == false);
		
		map.buildRoad(road1);
		assertTrue(map.getRoads().length == 2);
		
		EdgeLocation local2 = new EdgeLocation(homeHexLoc, EdgeDirection.SouthWest);
		EdgeValue road2 = new EdgeValue(0,local2);
		canLay = map.canLayRoad(road2);
		assertTrue(canLay == true);
		
		map.buildRoad(road2);
		assertTrue(map.getRoads().length == 4);
		
		canLay = map.canLayRoad(road2);
		assertTrue(canLay == false);
		
		EdgeLocation local3 = new EdgeLocation(homeHexLoc, EdgeDirection.SouthEast);
		EdgeValue road3 = new EdgeValue(1,local3);
		
		canLay = map.canLayRoad(road3);
		assertTrue(canLay == false);
		
		map.buildRoad(road3);
		canLay = map.canLayRoad(road3);
		assertTrue(canLay == false);
		
		EdgeLocation local4 = new EdgeLocation(homeHexLoc, EdgeDirection.NorthEast);
		EdgeValue road4 = new EdgeValue(1,local4);
		canLay = map.canLayRoad(road4);
		assertTrue(canLay == true);
		map.buildRoad(road4);
		canLay = map.canLayRoad(road4);
		assertTrue(canLay == false);
		
		canLay = true;
		EdgeLocation local5 = new EdgeLocation(hex6Loc, EdgeDirection.SouthWest);
		EdgeValue road5 = new EdgeValue(2,local5);
		canLay = map.canLayRoad(road5);
		assertTrue(canLay == false);
		
		canLay = true;
		EdgeLocation local6 = new EdgeLocation(hex6Loc, EdgeDirection.SouthWest);
		EdgeValue road6 = new EdgeValue(1,local6);
		canLay = map.canLayRoad(road6);
		assertTrue(canLay == false);
		
	}
	
	@Test 
	public void canBuildSettlement(){
		GameMap map = new GameMap();
		HexLocation homeHexLoc = new HexLocation(0,0);
		HexLocation hex1Loc = new HexLocation(0,1);
		HexLocation hex2Loc = new HexLocation(0,2);
		HexLocation hex3Loc = new HexLocation(0,-1);
		HexLocation hex4Loc = new HexLocation(0,-2);
		HexLocation hex5Loc = new HexLocation(1,1);
		HexLocation hex6Loc = new HexLocation(1,-1);
		HexLocation hex7Loc = new HexLocation(1,-2);
		HexLocation hex8Loc = new HexLocation(-1,2);
		HexLocation hex9Loc = new HexLocation(-1,1);
		HexLocation hex10Loc = new HexLocation(-1,-1);
		HexLocation hex11Loc = new HexLocation(-1,0);
		Hex homeHex = new Hex(homeHexLoc, ResourceType.WOOD,1);
		Hex hex1 = new Hex(hex1Loc, ResourceType.WOOD,1);
		Hex hex2 = new Hex(hex2Loc, ResourceType.WOOD,1);
		Hex hex3 = new Hex(hex3Loc, ResourceType.WOOD,1);
		Hex hex4 = new Hex(hex4Loc, ResourceType.WOOD,1);
		Hex hex5 = new Hex(hex5Loc, ResourceType.WOOD,1);
		Hex hex6 = new Hex(hex6Loc, ResourceType.WOOD,1);
		Hex hex7 = new Hex(hex7Loc, ResourceType.WOOD,1);
		Hex hex8 = new Hex(hex8Loc, ResourceType.WOOD,1);
		Hex hex9 = new Hex(hex9Loc, ResourceType.WOOD,1);
		Hex hex10 = new Hex(hex10Loc, ResourceType.WOOD,1);
		Hex hex11 = new Hex(hex11Loc, ResourceType.WOOD,1);
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthWest);
		VertexObject settlement = new VertexObject(0,location);
		
		boolean canBuild = map.canBuildSettlement(settlement);
		assertTrue(canBuild == false);
		
		EdgeLocation local = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road1 = new EdgeValue(0,local);
		map.buildRoad(road1);
		
		EdgeLocation local2 = new EdgeLocation(homeHexLoc, EdgeDirection.NorthEast);
		EdgeValue road2 = new EdgeValue(0,local2);
		map.buildRoad(road2);
		assertTrue(map.getRoads().length == 4);
	
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 3);
		
		canBuild = false;
		VertexLocation location2 = new VertexLocation(homeHexLoc, VertexDirection.East);
		VertexObject settlement2 = new VertexObject(0,location2);
		canBuild = map.canBuildSettlement(settlement2);
		assertTrue(canBuild == true);
		map.laySettlement(settlement2);
		assertTrue(map.getSettlements().length == 6);
		
		
		VertexLocation location3 = new VertexLocation(homeHexLoc, VertexDirection.East);
		VertexObject settlement3 = new VertexObject(0,location3);
		canBuild = map.canBuildSettlement(settlement3);
		assertTrue(canBuild == false);
		
		canBuild = true;
		VertexLocation location4 = new VertexLocation(homeHexLoc, VertexDirection.East);
		VertexObject settlement4 = new VertexObject(1,location4);
		canBuild = map.canBuildSettlement(settlement4);
		assertTrue(canBuild == false);
		
		canBuild = true;
		VertexLocation location5 = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		VertexObject settlement5 = new VertexObject(0,location5);
		canBuild = map.canBuildSettlement(settlement5);
		assertTrue(canBuild == false);
		
		EdgeLocation local3 = new EdgeLocation(hex3Loc, EdgeDirection.SouthEast);
		EdgeValue road3 = new EdgeValue(0,local3);
		boolean canBuildRd = map.canLayRoad(road3);
		assertTrue(canBuildRd == true);
		map.buildRoad(road3);
		assertTrue(map.getRoads().length == 6);
		
		canBuild = true;
		canBuild = map.canBuildSettlement(settlement5);
		assertTrue(canBuild == false);
		
		
		VertexLocation location6 = new VertexLocation(hex7Loc, VertexDirection.SouthWest);
		VertexObject settlement6 = new VertexObject(0,location6);
		canBuild = map.canBuildSettlement(settlement6);
		assertTrue(canBuild == true);
		
		map.laySettlement(settlement6);
		assertTrue(map.getSettlements().length == 9);
		
		
		
		
	}
	
	
}
