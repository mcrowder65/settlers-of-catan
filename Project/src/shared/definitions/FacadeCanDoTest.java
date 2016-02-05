package shared.definitions;

import static org.junit.Assert.*;

import org.junit.Test;

import client.controller.Facade;
import client.data.GameManager;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.EdgeValue;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;

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

	@Test
	public void canBuildSettlementWrongTurn(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(2,2,2,2,2);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(1);
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//build road 2
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		boolean canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		
		//trying with player 2
		turnTracker.setCurrentTurn(2);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//trying with player 3
		turnTracker.setCurrentTurn(2);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		//trying with correct player
		turnTracker.setCurrentTurn(0);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == true);
		
	
	}
	
	@Test
	public void canBuildSettlementResources(){
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//build road 2
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		boolean canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Everything except wood
		resources = new ResourceList(1,1,1,1,0);
		player.setResources(resources);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Everything except brick
		resources = new ResourceList(0,1,1,1,1);
		player.setResources(resources);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Everything except sheep
		resources = new ResourceList(1,1,0,1,1);
		player.setResources(resources);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Everything except wheat
		resources = new ResourceList(1,1,1,0,1);
		player.setResources(resources);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		//exact amount needed
		resources = new ResourceList(1,0,1,1,1);
		player.setResources(resources);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == true);
		
		//trying with more than enough
		resources = new ResourceList(10,10,10,10,10);
		player.setResources(resources);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == true);
		
	
	}
	
	@Test
	public void canBuildSettlementStatus(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,1,1,1,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Rolling");
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//build road 2
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		boolean canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		//random status
		turnTracker.setStatus("random");
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		//Robbing status
		turnTracker.setStatus("Robbing");
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		//Discarding status
		turnTracker.setStatus("Discarding");
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		//FirstRound status
		turnTracker.setStatus("FirstRound");
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		//SecondRound status
		turnTracker.setStatus("SecondRound");
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		//Playing status
		turnTracker.setStatus("Playing");
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == true);
	
	}
	
	@Test
	public void canBuildSettlementPositioning(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,1,1,1,1);
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//build road 2
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		boolean canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == true);
		
		VertexObject settlement = new VertexObject(0,location);
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 3);
		
		//try to lay on top of settlement
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//try to lay just one vertex away from other settlement
		location = new VertexLocation(homeHexLoc, VertexDirection.NorthWest);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//try to lay one vertex way but on a diff hex
		location = new VertexLocation(hex3Loc, VertexDirection.SouthWest);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//try to lay no roads around
		location = new VertexLocation(hex3Loc, VertexDirection.NorthEast);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//try to lay in water
		location = new VertexLocation(waterLocation, VertexDirection.NorthEast);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		Player player2 = new Player();
		player2.setPlayerIndex(2);
		player2.setOldDevCards(devCards);
		player2.setResources(resources);
		gameModel.setLocalPlayer(player2);
		turnTracker.setCurrentTurn(2);
		
		canBuild = true;
		//Opponent tries to lay a settlement on top of yours
		location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Opponent tries to lay a connecting to two of your roads
		location = new VertexLocation(homeHexLoc, VertexDirection.West);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Opponent tries to lay in the middle of your two roads
		location = new VertexLocation(homeHexLoc, VertexDirection.NorthWest);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Opponent tries to lay where no roads connect
		location = new VertexLocation(hex6Loc, VertexDirection.SouthWest);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Opponent tires to lay in water
		location = new VertexLocation(waterLocation, VertexDirection.SouthWest);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		//building opponent roads
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.SouthWest);
		road = new EdgeValue(2,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 6);
		
		canBuild = true;
		//Opponent tries to lay a connecting to two of your roads
		location = new VertexLocation(homeHexLoc, VertexDirection.West);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == false);
		
		//build opponent roads
		roadLocation = new EdgeLocation(hex1Loc, EdgeDirection.NorthWest);
		road = new EdgeValue(2,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 8);
		
		//build opponent settlement
		location = new VertexLocation(hex1Loc, VertexDirection.West);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == true);
	
	}
	
	@Test
	public void canBuildSettlementByWater(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,1,1,1,1);
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(hex12Loc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 1);
		
		//build road 2
		roadLocation = new EdgeLocation(hex12Loc, EdgeDirection.NorthEast);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//testing next to water
		VertexLocation location = new VertexLocation(hex12Loc, VertexDirection.NorthWest);
		boolean canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == true);
		
		//building settlement
		VertexObject settlement = new VertexObject(0,location);
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 2);
		
		//testing on the other side of the two roads - still next to the water
		location = new VertexLocation(hex12Loc, VertexDirection.East);
		canBuild = facade.canBuildSettlement(location);
		assertTrue(canBuild == true);
		
		//building settlement
		settlement = new VertexObject(0,location);
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 3);
		

	
	}

	@Test
	public void canFinishTurnWrongTurn(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(0,0,0,0,0);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		//testing with player 1
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(1);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Playing");
		
		boolean canFinish = facade.canFinishTurn();
		assertTrue(canFinish == false);
		
		canFinish = true;
		//testing with player 2
		turnTracker.setCurrentTurn(2);
		canFinish = facade.canFinishTurn();
		assertTrue(canFinish == false);
		
		canFinish = true;
		//testing with player 3
		turnTracker.setCurrentTurn(3);
		canFinish = facade.canFinishTurn();
		assertTrue(canFinish == false);
		
		//testing with correct turn
		turnTracker.setCurrentTurn(0);
		canFinish = facade.canFinishTurn();
		assertTrue(canFinish == true);
		
	}
	
	@Test
	public void canFinishTurnWrongStatus(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(0,0,0,0,0);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		//testing with status as rolling
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Rolling");
		
		boolean canFinish = facade.canFinishTurn();
		assertTrue(canFinish == false);
		
		//testing with robbing
		turnTracker.setStatus("Robbing");
		canFinish = facade.canFinishTurn();
		assertTrue(canFinish == true);
		
		canFinish = false;
		//testing with playing
		turnTracker.setStatus("Playing");
		canFinish = facade.canFinishTurn();
		assertTrue(canFinish == true);
		
		canFinish = false;
		//testing with discarding
		turnTracker.setStatus("Discarding");
		canFinish = facade.canFinishTurn();
		assertTrue(canFinish == true);
		
		canFinish = false;
		//testing with FirstRound
		turnTracker.setStatus("FirstRound");
		canFinish = facade.canFinishTurn();
		assertTrue(canFinish == true);
		
		canFinish = false;
		//testing with SecondRound
		turnTracker.setStatus("SecondRound");
		canFinish = facade.canFinishTurn();
		assertTrue(canFinish == true);

	}

	@Test
	public void canBuildCityWrongTurn(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(5,5,5,5,5);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(1);
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//build road 2
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		VertexObject settlement = new VertexObject(0,location);
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 3);
		boolean canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//trying with player 2
		turnTracker.setCurrentTurn(2);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//trying with player 3
		turnTracker.setCurrentTurn(2);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		//trying with correct player
		turnTracker.setCurrentTurn(0);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == true);
	}
	
	@Test
	public void canBuildCityResources(){
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//build road 2
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		VertexObject settlement = new VertexObject(0,location);
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 3);
		boolean canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//one of each resource
		resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//two of each resource
		resources = new ResourceList(2,2,2,2,2);
		player.setResources(resources);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//one off ore
		resources = new ResourceList(5,2,5,5,5);
		player.setResources(resources);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//one off grain
		resources = new ResourceList(5,5,5,1,5);
		player.setResources(resources);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		
		//exactly what is needed
		resources = new ResourceList(0,3,0,2,0);
		player.setResources(resources);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == true);
		
		//More than enough
		resources = new ResourceList(5,5,5,5,5);
		player.setResources(resources);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == true);

	}
	
	@Test
	public void canBuildCityWrongStatus(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(5,5,5,5,5);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Rolling");
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//build road 2
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		VertexObject settlement = new VertexObject(0,location);
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 3);
		boolean canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		//Status = robbing
		turnTracker.setStatus("Robbing");
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		//Status = Discarding
		turnTracker.setStatus("Discarding");
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		//Status = FirstRound
		turnTracker.setStatus("FirstRound");
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		//Status = SecondRound
		turnTracker.setStatus("SecondRound");
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		//Status = playing
		turnTracker.setStatus("Playing");
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == true);
	}

	@Test
	public void canBuildCityNothingOnBoard(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(5,5,5,5,5);
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
		
		//Home Location
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		VertexObject settlement = new VertexObject(0,location);
		boolean canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Inland Location 1
		location = new VertexLocation(hex5Loc, VertexDirection.NorthWest);
		settlement = new VertexObject(0,location);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Inland Location 2
		location = new VertexLocation(hex10Loc, VertexDirection.SouthWest);
		settlement = new VertexObject(0,location);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//Edge Location
		location = new VertexLocation(waterLocation, VertexDirection.NorthWest);
		settlement = new VertexObject(0,location);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
	
	}
	
	@Test
	public void canBuildCityRoadsOnly(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(5,5,5,5,5);
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//build road 2
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		VertexObject settlement = new VertexObject(0,location);

		//two Road Location 1
		boolean canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		location = new VertexLocation(homeHexLoc, VertexDirection.SouthWest);
		settlement = new VertexObject(0,location);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		//checking to make sure you can lay once settlements are actually laid down
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 3);
		
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == true);
		
		location = new VertexLocation(homeHexLoc, VertexDirection.SouthEast);
		settlement = new VertexObject(0,location);
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 6);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == true);
	
	}
	
	@Test
	public void canBuildCityOnCity(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(5,5,5,5,5);
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//build road 2
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		VertexObject settlement = new VertexObject(0,location);

		//two Road Location 1
		boolean canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		location = new VertexLocation(homeHexLoc, VertexDirection.West);
		settlement = new VertexObject(0,location);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		//checking to make sure you can lay once settlements are actually laid down
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 3);
		
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == true);
		map.layCity(settlement);
		assertTrue(map.getCities().length == 3);
		
		//Check laying a city on another city
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		//laying another settlement and city
		location = new VertexLocation(homeHexLoc, VertexDirection.SouthEast);
		settlement = new VertexObject(0,location);
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 3);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == true);
		
		map.layCity(settlement);
		assertTrue(map.getCities().length == 6);
		
		//city on city check 2
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
	}
	
	@Test
	public void canBuildCityPlacement(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(5,5,5,5,5);
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
		
		//build road 1
		EdgeLocation roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 2);
		
		//build road 2
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		
		VertexLocation location = new VertexLocation(homeHexLoc, VertexDirection.NorthEast);
		VertexObject settlement = new VertexObject(0,location);

		//two Road Location 1
		boolean canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		location = new VertexLocation(homeHexLoc, VertexDirection.West);
		settlement = new VertexObject(0,location);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		//checking to make sure you can lay once settlements are actually laid down
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 3);
		
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == true);
		map.layCity(settlement);
		assertTrue(map.getCities().length == 3);
		
		
		//Build a city next to a city
		roadLocation = new EdgeLocation(homeHexLoc, EdgeDirection.SouthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 6);
		location = new VertexLocation(homeHexLoc, VertexDirection.SouthWest);
		settlement = new VertexObject(0,location);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
		
		canBuild = true;
		//opponent on top of your city
		Player player2 = new Player();
		player2.setPlayerIndex(2);
		player2.setResources(resources);
		gameModel.setLocalPlayer(player2);
		turnTracker.setCurrentTurn(2);
		player2.setOldDevCards(devCards);
		settlement = new VertexObject(2,location);
		canBuild = facade.canBuildCity(location);
		assertTrue(canBuild == false);
	}
	
	@Test
	public void citySettlementInteraction(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		
		ResourceList resources = new ResourceList(5,5,5,5,5);
		game.updateModel(gameModel);
		
		player.setPlayerIndex(0);
		player2.setPlayerIndex(1);
		player3.setPlayerIndex(2);
		player4.setPlayerIndex(3);
		player.setResources(resources);
		player2.setResources(resources);
		player3.setResources(resources);
		player4.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		gameModel.setTurnTracker(turnTracker);
		turnTracker.setStatus("Playing");
		DevCardList devCards = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(devCards);
		player2.setOldDevCards(devCards);
		player3.setOldDevCards(devCards);
		player4.setOldDevCards(devCards);
		
		
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
		
		//build road 1 player 1
		EdgeLocation roadLocation = new EdgeLocation(hex12Loc, EdgeDirection.North);
		EdgeValue road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 1);
		
		//build road 2 player 1
		roadLocation = new EdgeLocation(hex12Loc, EdgeDirection.NorthWest);
		road = new EdgeValue(0,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 3);
		
		
		VertexLocation location = new VertexLocation(hex12Loc, VertexDirection.NorthEast);
		VertexObject settlement = new VertexObject(0,location);
		
		//canLaySettlement Player1 
		boolean canLaySettlement = map.canBuildSettlement(settlement);
		assertTrue(canLaySettlement == true);

		//canLaySettlement Player3
		gameModel.setLocalPlayer(player3);
		turnTracker.setCurrentTurn(2);
		location = new VertexLocation(hex12Loc, VertexDirection.NorthEast);
		settlement = new VertexObject(2,location);
		canLaySettlement = map.canBuildSettlement(settlement);
		assertTrue(canLaySettlement == false);
		
		//build settlement Player1
		turnTracker.setCurrentTurn(0);
		gameModel.setLocalPlayer(player);
		location = new VertexLocation(hex12Loc, VertexDirection.NorthEast);
		settlement = new VertexObject(0,location);
		canLaySettlement = facade.canBuildSettlement(location);
		assertTrue(canLaySettlement == true);
		map.laySettlement(settlement);
		assertTrue(map.getSettlements().length == 1);
		
		//can player1 upgrade city
		boolean canLayCity = facade.canBuildCity(location);
		assertTrue(canLayCity == true);
		
		//build player4 road1
		turnTracker.setCurrentTurn(3);
		gameModel.setLocalPlayer(player4);
		
		
		roadLocation = new EdgeLocation(hex12Loc, EdgeDirection.SouthEast);
		road = new EdgeValue(3,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 4);
		
		//can Player4 build settlement between player 1 and his other road
		location = new VertexLocation(hex12Loc, VertexDirection.East);
		settlement = new VertexObject(3,location);
		canLaySettlement = facade.canBuildSettlement(location);
		assertTrue(canLaySettlement == false);
		
		//can player4 build on the other side of his 1 road
		location = new VertexLocation(hex12Loc, VertexDirection.SouthEast);
		settlement = new VertexObject(3,location);
		canLaySettlement = facade.canBuildSettlement(location);
		assertTrue(canLaySettlement == false);
		
		//build player4's other road
		roadLocation = new EdgeLocation(hex12Loc, EdgeDirection.South);
		road = new EdgeValue(3,roadLocation);
		map.buildRoad(road);
		assertTrue(map.getRoads().length == 6);
		
		//player 4 now tries to build settlement on his two roads
		location = new VertexLocation(hex12Loc, VertexDirection.SouthWest);
		settlement = new VertexObject(3,location);
		canLaySettlement = facade.canBuildSettlement(location);
		assertTrue(canLaySettlement == true);
		
		map.laySettlement(settlement);
		
		//player 4 now tries to build a city
		location = new VertexLocation(hex12Loc, VertexDirection.SouthWest);
		settlement = new VertexObject(3,location);
		canLayCity = facade.canBuildCity(location);
		assertTrue(canLaySettlement == true);
		
	}
	
	@Test
	public void canOfferTradeTestWrongTurn(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,1,1,1,1);
		game.updateModel(gameModel);
		
		player.setPlayerIndex(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(1);
		turnTracker.setStatus("Playing");
		gameModel.setTurnTracker(turnTracker);
		
		TradeOffer tradeOffer = new TradeOffer();
		ResourceList offer = new ResourceList(0,0,0,0,0);
		tradeOffer.setOffer(offer);
		
		//Player1's turn 
		boolean canOffer = facade.canOfferTrade();
		assertTrue(canOffer == false);
		
		canOffer = true;
		//Player2's 
		turnTracker.setCurrentTurn(2);
		canOffer = facade.canOfferTrade();
		assertTrue(canOffer == false);
		
		//correctPlayer
		turnTracker.setCurrentTurn(0);
		canOffer = facade.canOfferTrade();
		assertTrue(canOffer == true);
		
		
		
		
	}
	
	@Test
	public void canOfferTradeTestWrongStatus(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,1,1,1,1);
		game.updateModel(gameModel);
		
		player.setPlayerIndex(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		turnTracker.setStatus("Playing");
		gameModel.setTurnTracker(turnTracker);
		
		//TradeOffer tradeOffer = new TradeOffer();
		//ResourceList offer = new ResourceList(0,0,0,0,0);
		//tradeOffer.setOffer(offer);
		
		//correctStatus
		turnTracker.setCurrentTurn(0);
		boolean canOffer = facade.canOfferTrade();
		assertTrue(canOffer == true);
		
		turnTracker.setStatus("Discarding");
		canOffer = facade.canOfferTrade();
		assertTrue(canOffer == false);
		
		canOffer = true;
		turnTracker.setStatus("Rolling");
		canOffer = facade.canOfferTrade();
		assertTrue(canOffer == false);
		
		canOffer = true;
		turnTracker.setStatus("FirstRound");
		canOffer = facade.canOfferTrade();
		assertTrue(canOffer == false);
		
		canOffer = true;
		turnTracker.setStatus("SecondRound");
		canOffer = facade.canOfferTrade();
		assertTrue(canOffer == false);
		
	}
	
	@Test
	public void canOfferTradeTestResources(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(0,0,0,0,0);
		game.updateModel(gameModel);
		
		player.setPlayerIndex(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		turnTracker.setStatus("Playing");
		gameModel.setTurnTracker(turnTracker);
		
		TradeOffer tradeOffer = new TradeOffer();
		ResourceList offer = new ResourceList(3,0,0,0,0);
		tradeOffer.setOffer(offer);
		
		//no resources
		turnTracker.setCurrentTurn(0);
		boolean canOffer = facade.canOfferTrade();
		assertTrue(canOffer == false);
		
		//1 resource
		resources = new ResourceList(1,0,0,0,0);
		player.setResources(resources);
		canOffer = facade.canOfferTrade();
		assertTrue(canOffer == true);
		
		canOffer = true;
		//tons of resources
		resources = new ResourceList(10,10,10,10,10);
		player.setResources(resources);
		canOffer = facade.canOfferTrade();
		assertTrue(canOffer == true);
	}
	
	@Test
	public void canMaritimeTradeWrongTurn(){
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(2,2,2,2,2);
		game.updateModel(gameModel);
		
		player.setPlayerIndex(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(1);
		turnTracker.setStatus("Playing");
		gameModel.setTurnTracker(turnTracker);
		ResourceList bank = new ResourceList(1,1,1,1,1);
		gameModel.setBank(bank);
		
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
		
		Port port = new Port(ResourceType.WOOD, hex12Loc, EdgeDirection.NorthEast,3);
		
		Port[] allPorts = {port};
		map.setPorts(allPorts);
		
		VertexLocation location = new VertexLocation(hex12Loc, VertexDirection.NorthEast);
		VertexObject settlement = new VertexObject(0,location);
		map.laySettlement(settlement);
		
		
		boolean canTrade = facade.canMaritimeTrade();
		assertTrue(canTrade == false);
		canTrade = true;
		
		//player3
		turnTracker.setCurrentTurn(2);
		canTrade = facade.canMaritimeTrade();
		assertTrue(canTrade == false);
		canTrade = true;
		
		//player4
		turnTracker.setCurrentTurn(3);
		canTrade = facade.canMaritimeTrade();
		assertTrue(canTrade == false);
		canTrade = true;
		
		//player0
		turnTracker.setCurrentTurn(0);
		canTrade = facade.canMaritimeTrade();
		assertTrue(canTrade == true);
		canTrade = true;
		
		
		
	}
	
	
}
