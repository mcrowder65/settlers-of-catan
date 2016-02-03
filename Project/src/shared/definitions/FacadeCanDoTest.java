package shared.definitions;

import static org.junit.Assert.*;

import org.junit.Test;

import client.controller.Facade;
import client.data.GameManager;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.EdgeValue;
import shared.locations.HexLocation;

public class FacadeCanDoTest {

	
	@Test
	public void canDiscardCardsFalse() {
		
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(0,0,0,0,0);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		boolean canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == false);
		
		canDiscard = true;
		resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == false);
		
		canDiscard = true;
		resources = new ResourceList(2,3,1,1,0);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == false);
		
		canDiscard = true;
		resources = new ResourceList(1,4,1,0,0);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == false);
		
		canDiscard = true;
		resources = new ResourceList(1,4,1,1,0);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == false);
	}
	
	@Test
	public void canDiscardCardsTrue() {
		
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,2,3,2,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		boolean canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == true);
		
		canDiscard = true;
		resources = new ResourceList(10,0,0,0,0);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == true);
		
		canDiscard = true;
		resources = new ResourceList(2,3,1,1,1);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == true);
		
		canDiscard = true;
		resources = new ResourceList(0,4,3,0,1);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == true);
		
		canDiscard = true;
		resources = new ResourceList(19,10,10,10,10);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == true);
	}

	@Test
	public void canRollNumberWrongTurn(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,2,3,2,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(1);
		gameModel.setTurnTracker(turnTracker);
		
		boolean canRoll = facade.canRollNumber();
		assertTrue(canRoll == false);
		
		canRoll = true;
		turnTracker.setCurrentTurn(2);
		canRoll = facade.canRollNumber();
		assertTrue(canRoll == false);
		
		canRoll = true;
		turnTracker.setCurrentTurn(3);
		canRoll = facade.canRollNumber();
		assertTrue(canRoll == false);
		
	}
	
	@Test
	public void canRollNumberWrongStatus(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,2,3,2,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Playing");
		
		boolean canRoll = facade.canRollNumber();
		assertTrue(canRoll == false);
		
		canRoll = true;
		turnTracker.setStatus("Robbing");
		canRoll = facade.canRollNumber();
		assertTrue(canRoll == false);
		
		canRoll = true;
		turnTracker.setStatus("Discarding");
		canRoll = facade.canRollNumber();
		assertTrue(canRoll == false);
		
		canRoll = true;
		turnTracker.setStatus("FirstRound");
		canRoll = facade.canRollNumber();
		assertTrue(canRoll == false);
		
		canRoll = true;
		turnTracker.setStatus("SecondRound");
		canRoll = facade.canRollNumber();
		assertTrue(canRoll == false);
		
		canRoll = true;
		turnTracker.setStatus("rolling");
		canRoll = facade.canRollNumber();
		assertTrue(canRoll == false);
		
		canRoll = true;
		turnTracker.setStatus("Rollin");
		canRoll = facade.canRollNumber();
		assertTrue(canRoll == false);
		
	}
	
	@Test
	public void canRollNumberCorrect(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,2,3,2,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Rolling");
		
		boolean canRoll = facade.canRollNumber();
		assertTrue(canRoll == true);
		

		
	}
	
	public void canBuildRoadNotTurn(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,2,3,2,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(1);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("PLaying");
		
		HexLocation hexLoc = new HexLocation(0,0);
		EdgeLocation location = new EdgeLocation(hexLoc, EdgeDirection.North);
		
		boolean canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		turnTracker.setCurrentTurn(2);
		
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
	}
	
	@Test
	public void canBuildRoadNotStatus(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,2,3,2,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Rolling");
		
		HexLocation hexLoc = new HexLocation(0,0);
		EdgeLocation location = new EdgeLocation(hexLoc, EdgeDirection.North);
		
		boolean canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		turnTracker.setStatus("Playin");
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		turnTracker.setStatus("asdfasdf");
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
	}
	
	@Test
	public void canBuildRoadNoPreviousRoads(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,2,3,2,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Playing");
		
		GameMap map = new GameMap();
		gameModel.setMap(map);
		
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
		
		EdgeLocation location = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		
		boolean canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		location = new EdgeLocation(hex1Loc, EdgeDirection.North);
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		location = new EdgeLocation(hex2Loc, EdgeDirection.NorthWest);
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		location = new EdgeLocation(hex3Loc, EdgeDirection.SouthWest);
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		location = new EdgeLocation(hex4Loc, EdgeDirection.NorthEast);
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		location = new EdgeLocation(hex5Loc, EdgeDirection.NorthEast);
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
	}
	
	@Test
	public void canBuildRoadNoOtherRoad(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,2,3,2,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Playing");
		
		GameMap map = new GameMap();
		gameModel.setMap(map);
		
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
		HexLocation waterLocation = new HexLocation(-3,1);
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
		Hex hexWater = new Hex(waterLocation, ResourceType.WOOD,1);
		
		Hex[] allHexes = {homeHex,hex1,hex2,hex3,hex4,hex5,hex6,hex7,hex8,hex9,hex10,hex11,hexWater};
		map.setHexes(allHexes);
		
		EdgeLocation location = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,location);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//Can lay road
		location = new EdgeLocation(homeHexLoc, EdgeDirection.NorthEast);
		road = new EdgeValue(0,location);
		boolean canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == true);
		
		//added road
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		canBuild = true;
		
		//build road on top of another road
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		//building road next to your two roads
		location = new EdgeLocation(homeHexLoc, EdgeDirection.SouthEast);
		road = new EdgeValue(0,location);
	    canBuild = facade.canBuildRoad(location);
	    assertTrue(canBuild == true);
	    map.buildRoad(road);
	    assertTrue(map.getRoads().length == 6);
	    canBuild = false;
	    
		//building road on other hex next to other ones
	    location = new EdgeLocation(hex6Loc, EdgeDirection.South);
		road = new EdgeValue(0,location);
	    canBuild = facade.canBuildRoad(location);
	    assertTrue(canBuild == true);
	    map.buildRoad(road);
	    assertTrue(map.getRoads().length == 8);
	    
	    //building road another player - next to already built roads
	    location = new EdgeLocation(hex6Loc, EdgeDirection.SouthEast);
		road = new EdgeValue(1,location);
		turnTracker.setCurrentTurn(1);
		Player player1 = new Player();
		player1.setPlayerID(1);
		gameModel.setLocalPlayer(player1);
	    canBuild = facade.canBuildRoad(location);
	    assertTrue(canBuild == false);
	    
	    //building a road on water
	    location = new EdgeLocation(waterLocation, EdgeDirection.SouthEast);
		road = new EdgeValue(1,location);
	    canBuild = facade.canBuildRoad(location);
	    assertTrue(canBuild == false);
	    
	}
	
	@Test
	public void canBuildRoadNearWater(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,2,3,2,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Playing");
		
		GameMap map = new GameMap();
		gameModel.setMap(map);
		
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
		HexLocation hex12Loc = new HexLocation(2,-2);
		HexLocation hex13Loc = new HexLocation(2,-1);
		HexLocation hex14Loc = new HexLocation(2,0);
		HexLocation waterLocation = new HexLocation(-3,1);
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
		Hex hex12 = new Hex(hex12Loc, ResourceType.WOOD,1);
		Hex hex13 = new Hex(hex13Loc, ResourceType.WOOD,1);
		Hex hex14 = new Hex(hex14Loc, ResourceType.WOOD,1);
		Hex hexWater = new Hex(waterLocation, ResourceType.WOOD,1);
		
		Hex[] allHexes = {homeHex,hex1,hex2,hex3,hex4,hex5,hex6,hex7,hex8,hex9,hex10,hex11,hex12,hex13,hex14,hexWater};
		map.setHexes(allHexes);
		
		EdgeLocation location = new EdgeLocation(hex14Loc, EdgeDirection.NorthEast);
		EdgeValue road = new EdgeValue(0,location);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 1);
		
		//Can lay road
		location = new EdgeLocation(hex14Loc, EdgeDirection.SouthEast);
		road = new EdgeValue(0,location);
		boolean canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == true);
	}
	
	@Test
	public void canBuildResourceIssue(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(0,0,0,0,0);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Playing");
		DevCardList devCards = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(devCards);
		
		GameMap map = new GameMap();
		gameModel.setMap(map);
		
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
		HexLocation hex12Loc = new HexLocation(2,-2);
		HexLocation hex13Loc = new HexLocation(2,-1);
		HexLocation hex14Loc = new HexLocation(2,0);
		HexLocation waterLocation = new HexLocation(-3,1);
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
		Hex hex12 = new Hex(hex12Loc, ResourceType.WOOD,1);
		Hex hex13 = new Hex(hex13Loc, ResourceType.WOOD,1);
		Hex hex14 = new Hex(hex14Loc, ResourceType.WOOD,1);
		Hex hexWater = new Hex(waterLocation, ResourceType.WOOD,1);
		
		Hex[] allHexes = {homeHex,hex1,hex2,hex3,hex4,hex5,hex6,hex7,hex8,hex9,hex10,hex11,hex12,hex13,hex14,hexWater};
		map.setHexes(allHexes);
		
		EdgeLocation location = new EdgeLocation(hex14Loc, EdgeDirection.NorthEast);
		EdgeValue road = new EdgeValue(0,location);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 1);
		
		//0 of any resource
		location = new EdgeLocation(hex14Loc, EdgeDirection.SouthEast);
		road = new EdgeValue(0,location);
		boolean canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		//1 brick 0 of everything else
		resources = new ResourceList(1,0,0,0,0);
		player.setResources(resources);
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		//0 brick 1 of everything else
		resources = new ResourceList(0,1,1,1,1);
		player.setResources(resources);
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == false);
		
		//1 brick 1 of everything else
		resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == true);
		
		//1 brick 1 wood 0 of everything else
		resources = new ResourceList(1,0,0,0,1);
		player.setResources(resources);
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == true);
		
		//10 of everything
		resources = new ResourceList(10,10,10,10,10);
		player.setResources(resources);
		canBuild = facade.canBuildRoad(location);
		assertTrue(canBuild == true);
		
	}

	
}
