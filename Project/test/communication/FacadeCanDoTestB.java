package communication;




import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.controller.Facade;
import client.data.GameManager;
import shared.definitions.DevCardList;
import shared.definitions.GameMap;
import shared.definitions.GameModel;
import shared.definitions.Hex;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.definitions.TradeOffer;
import shared.definitions.TurnTracker;
import shared.locations.HexLocation;

public class FacadeCanDoTestB {

	GameManager game;
	Facade facade;
	GameModel gameModel;
	Player player;
	ResourceList resources;
	GameMap map;
	TurnTracker turn;
	HexLocation robber;


	@Before
	public void setUp() throws Exception {
		game = new GameManager();
		facade = new Facade(game);
		gameModel = new GameModel();
		player = new Player();
		player.setPlayerIndex(0);
		player.setPlayerID(0);
		gameModel.setLocalPlayerTest(player);
		map = new GameMap();
		turn = new TurnTracker();
		turn.setCurrentTurn(0);
		resources = new ResourceList(0,0,0,0,0);
		game.updateModel(gameModel);
		robber = new HexLocation(2,2);




		Hex[] hexMap = {
				new Hex(new HexLocation(-2,0),ResourceType.BRICK,0),
				new Hex(new HexLocation(-1,-1),ResourceType.BRICK,0),
				new Hex(new HexLocation(0,-2),ResourceType.BRICK,0),
				new Hex(new HexLocation(-2,1),ResourceType.BRICK,0),
				new Hex(new HexLocation(-1,0),ResourceType.BRICK,0),
				new Hex(new HexLocation(1,-2),ResourceType.BRICK,0),
				new Hex(new HexLocation(-2,2),ResourceType.BRICK,0),
				new Hex(new HexLocation(-1,1),ResourceType.BRICK,0),
				new Hex(new HexLocation(0,0),ResourceType.BRICK,0),
				new Hex(new HexLocation(1,-1),ResourceType.BRICK,0),
				new Hex(new HexLocation(2,-2),ResourceType.BRICK,0),
				new Hex(new HexLocation(-1,2),ResourceType.BRICK,0),
				new Hex(new HexLocation(0,1),ResourceType.BRICK,0),
				new Hex(new HexLocation(1,0),ResourceType.BRICK,0),
				new Hex(new HexLocation(2,-1),ResourceType.BRICK,0),
				new Hex(new HexLocation(0,2),ResourceType.BRICK,0),
				new Hex(new HexLocation(1,1),ResourceType.BRICK,0),
				new Hex(new HexLocation(2,0),ResourceType.BRICK,0)
		};

		gameModel.setMap(map);
		gameModel.getMap().setHexes(hexMap);
		gameModel.getMap().setRobber(robber);
		gameModel.setTurnTracker(turn);
		gameModel.setLocalPlayer(player);
	}

	@Test
	public void testCanAcceptTradeInsufficientResources() {
		TradeOffer offer = new TradeOffer(0,0,new ResourceList(0,0,0,0,0));
		//Not enough resources - all of them
		player.setResources(new ResourceList(1,1,1,1,1));
		offer.setOffer(new ResourceList(-3,-5,-2,-5,-4));
		assertFalse(facade.canAcceptTrade(offer));

		//Not enough resources - one of them
		//Resources for player are still (1,1,1,1,1)
		offer.setOffer(new ResourceList(0,0,0,0,-2));
		assertFalse(facade.canAcceptTrade(offer));
		

	}

	@Test
	public void testCanAcceptTrade() {
		//Empty offer - Resources
		player.setResources(new ResourceList(3,3,3,3,3));
		TradeOffer offer = new TradeOffer(0,0,new ResourceList(0,0,0,0,0));
		assertTrue(facade.canAcceptTrade(offer));

		//Empty offer - No resources
		player.setResources(resources);
		assertTrue(facade.canAcceptTrade(offer));


		//Enough resources
		player.setResources(new ResourceList(5,5,5,5,5));
		offer.setOffer(new ResourceList(-2,-4,-1,0,-2));
		assertTrue(facade.canAcceptTrade(offer));
	}

	@Test
	public void testCanSendChat() {
		assertTrue(facade.canSendChat());
	}

	@Test
	public void testCanPlaceRobberTurns() {

		//Test Map was created correctly
		assertTrue(gameModel.getMap().isLand(new HexLocation(0,0)));
		assertFalse(gameModel.getMap().isLand(new HexLocation(5,0)));
		gameModel.getTurnTracker().setStatus("Robbing");
		
		//Player status == Robbing / Valid location
		assertTrue(facade.canPlaceRobber(new HexLocation(0,0)));
		assertTrue(facade.canPlaceRobber(new HexLocation(1,1)));
		assertTrue(facade.canPlaceRobber(new HexLocation(1,-2)));
		assertTrue(facade.canPlaceRobber(new HexLocation(-2,2)));
		assertTrue(facade.canPlaceRobber(new HexLocation(-1,-1)));
		
		//change turn
		turn.setCurrentTurn(1);
		assertTrue(facade.canPlaceRobber(new HexLocation(0,0)));
		assertTrue(facade.canPlaceRobber(new HexLocation(1,1)));
		
		//change turn
		turn.setCurrentTurn(2);
		assertTrue(facade.canPlaceRobber(new HexLocation(0,0)));
		assertTrue(facade.canPlaceRobber(new HexLocation(1,1)));
		
		//change turn
		turn.setCurrentTurn(3);
		assertTrue(facade.canPlaceRobber(new HexLocation(0,0)));
		assertTrue(facade.canPlaceRobber(new HexLocation(1,1)));

	}		
	
	
	@Test
	public void testCanPlaceRobber() {

		//Test Map was created correctly
		assertTrue(gameModel.getMap().isLand(new HexLocation(0,0)));
		assertFalse(gameModel.getMap().isLand(new HexLocation(5,0)));


		//Player status == Robbing / Invalid location
		gameModel.getTurnTracker().setStatus("Robbing");
		assertFalse(facade.canPlaceRobber(new HexLocation(10,10)));
		assertFalse(facade.canPlaceRobber(new HexLocation(0,10)));
		assertFalse(facade.canPlaceRobber(new HexLocation(3,-1)));
		assertFalse(facade.canPlaceRobber(new HexLocation(-3,-3)));
		assertFalse(facade.canPlaceRobber(new HexLocation(2,2)));


		//Player status == Robbing / Valid location
		assertTrue(facade.canPlaceRobber(new HexLocation(0,0)));
		assertTrue(facade.canPlaceRobber(new HexLocation(1,1)));
		assertTrue(facade.canPlaceRobber(new HexLocation(1,-2)));
		assertTrue(facade.canPlaceRobber(new HexLocation(-2,2)));
		assertTrue(facade.canPlaceRobber(new HexLocation(-1,-1)));


	}		
	
	@Test
	public void testCanUseMonumentMultipule() {

		// 2 cards 9 vp
		DevCardList oldDev = new DevCardList(0,1,0,0,0);
		DevCardList newDev = new DevCardList(0,1,0,0,0);
		gameModel.getLocalPlayer(0).setOldDevCards(oldDev);
		gameModel.getLocalPlayer(0).setNewDevCards(newDev);
		gameModel.getLocalPlayer(0).setVictoryPoints(9);

		assertTrue(facade.canUseMonument());

		//8vp
		gameModel.getLocalPlayer(0).setVictoryPoints(8);
		assertTrue(facade.canUseMonument());
		

	}
	
	@Test
	public void testCanUseMonument() {

		//canPlay
		DevCardList oldDev = new DevCardList(0,1,0,0,0);
		DevCardList newDev = new DevCardList(0,0,0,0,0);
		gameModel.getLocalPlayer(0).setOldDevCards(oldDev);
		gameModel.getLocalPlayer(0).setNewDevCards(newDev);
		gameModel.getLocalPlayer(0).setVictoryPoints(9);

		assertTrue(facade.canUseMonument());


		// Not Enough Victory points
		gameModel.getLocalPlayer(0).setVictoryPoints(0);
		oldDev = new DevCardList(0,0,0,0,0);
		gameModel.getLocalPlayer(0).setOldDevCards(oldDev);
		assertFalse(facade.canUseMonument());

		//can Play new dev 
		gameModel.getLocalPlayer(0).setVictoryPoints(9);
		newDev = new DevCardList(0,1,0,0,0);
		gameModel.getLocalPlayer(0).setNewDevCards(newDev);
		assertTrue(facade.canUseMonument());


	}
	
	@Test
	public void testCanUseMonopolyTrue() {
		player.setPlayedDevCard(false);
		//Player status != Playing / Can play
		turn.setStatus("Playing");
		DevCardList oldDev = new DevCardList(1,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseMonopoly());
		
		//more than 1
		oldDev = new DevCardList(4,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseMonopoly());
		
		
	}
	
	@Test
	public void testCanUseMonopolyFalse() {
		player.setPlayedDevCard(false);
		//Start True
		
		DevCardList oldDev = new DevCardList(1,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseMonopoly());
		
		//Player status != Playing / Can't play
		oldDev = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseMonopoly());
		
		//Player status == Playing / Can't play
		turn.setStatus("Playing");
		DevCardList newDev = new DevCardList(0,0,0,0,0);
		player.setNewDevCards(newDev);
		assertFalse(facade.canUseMonopoly());
		

	}
	
	@Test
	public void testCanUseSoldierNewDev() {
		player.setPlayedDevCard(false);
		//Player status != Playing / Can play
		turn.setStatus("Playing");
		DevCardList oldDev = new DevCardList(0,0,0,0,0);
		
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseSoldier());
		
		//set new dev cards
		DevCardList newDev = new DevCardList(1,1,1,1,1);
		player.setNewDevCards(newDev);
		
	}
	
	
	
	@Test
	public void testCanUseSoldier() {
		player.setPlayedDevCard(false);
		// Can play
		turn.setStatus("Robbing");
		DevCardList oldDev = new DevCardList(0,0,0,1,0);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseSoldier());
		
		//0 dev cards
		oldDev = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseSoldier());
		
		//Can play
		oldDev = new DevCardList(0,0,0,1,0);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseSoldier());
	}
	
	@Test
	public void testCanUseRoadBuilderFalse() {
		player.setPlayedDevCard(false);
		//Player status != Playing / Can play
		turn.setStatus("Playing");
		DevCardList oldDev = new DevCardList(0,0,1,0,0);
		player.setOldDevCards(oldDev);
		player.setRoads(2);
		assertTrue(facade.canUseRoadBuilder());
		
		//Player status != Playing / Can't play
		oldDev = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseRoadBuilder());
		
		//Player status == Playing / Can't play
		turn.setStatus("Playing");
		DevCardList newDev = new DevCardList(0,0,0,0,0);
		player.setNewDevCards(newDev);
		assertFalse(facade.canUseRoadBuilder());
	
	}
	
	@Test
	public void testCanUseRoadBuilder() {
		player.setPlayedDevCard(false);
		player.setRoads(2);
		// Cant play
		
		DevCardList oldDev = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseRoadBuilder());
		
		//Player status == Playing / Can play
		oldDev = new DevCardList(0,0,1,0,0);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseRoadBuilder());
	}
	
	@Test
	public void testCanUseYearOfPlentyNewDev() {
		player.setPlayedDevCard(false);
		//Player status != Playing / Can play
		turn.setStatus("Playing");
		DevCardList oldDev = new DevCardList(0,0,0,0,1);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseYearOfPlenty());
		
		//set new devCards only
		oldDev = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(oldDev);
		DevCardList newDev = new DevCardList(0,0,0,0,1);
		player.setNewDevCards(newDev);
		assertFalse(facade.canUseYearOfPlenty());
		
	}
	
	@Test
	public void testCanUseYearOfPlenty() {
		player.setPlayedDevCard(false);
		// Can play
		DevCardList oldDev = new DevCardList(0,0,0,0,1);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseYearOfPlenty());
		
		// Can't play
		oldDev = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseYearOfPlenty());
		
		// Can play
		oldDev = new DevCardList(0,0,0,0,1);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseYearOfPlenty());
	}


}













