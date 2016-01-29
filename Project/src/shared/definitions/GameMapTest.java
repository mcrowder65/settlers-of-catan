package shared.definitions;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.locations.HexLocation;
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
}
