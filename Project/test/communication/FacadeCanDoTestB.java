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
	public void testCanAcceptTrade() {
		//Empty offer - Resources
		player.setResources(new ResourceList(3,3,3,3,3));
		TradeOffer offer = new TradeOffer(0,0,new ResourceList(0,0,0,0,0));
		assertTrue(facade.canAcceptTrade(offer));

		//Empty offer - No resources
		player.setResources(resources);
		assertTrue(facade.canAcceptTrade(offer));

		//Not enough resources - all of them
		player.setResources(new ResourceList(1,1,1,1,1));
		offer.setOffer(new ResourceList(3,5,2,5,4));
		assertFalse(facade.canAcceptTrade(offer));

		//Not enough resources - one of them
		//Resources for player are still (1,1,1,1,1)
		offer.setOffer(new ResourceList(0,0,0,0,2));
		assertFalse(facade.canAcceptTrade(offer));


		//Enough resources
		player.setResources(new ResourceList(5,5,5,5,5));
		offer.setOffer(new ResourceList(2,4,1,0,2));
		assertTrue(facade.canAcceptTrade(offer));
	}

	@Test
	public void testCanSendChat() {
		assertTrue(facade.canSendChat());
	}

	@Test
	public void testCanPlaceRobber() {

		//Test Map was created correctly
		assertTrue(gameModel.getMap().isLand(new HexLocation(0,0)));
		assertFalse(gameModel.getMap().isLand(new HexLocation(5,0)));


		//Player status != Robbing/ Invalid location
		gameModel.getTurnTracker().setStatus("Playing");
		assertFalse(facade.canPlaceRobber(new HexLocation(5,5)));
		assertFalse(facade.canPlaceRobber(new HexLocation(0,3)));
		assertFalse(facade.canPlaceRobber(new HexLocation(2,-3)));
		assertFalse(facade.canPlaceRobber(new HexLocation(-3,0)));

		//Player status != Robbing/ Valid location 
		assertFalse(facade.canPlaceRobber(new HexLocation(1,1)));
		assertFalse(facade.canPlaceRobber(new HexLocation(2,0)));
		assertFalse(facade.canPlaceRobber(new HexLocation(2,-2)));
		assertFalse(facade.canPlaceRobber(new HexLocation(1,0)));
		assertFalse(facade.canPlaceRobber(new HexLocation(-2,1)));



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
	public void testCanUseMonument() {

		//Player status != Playing / Can play
		gameModel.getTurnTracker().setStatus("Robbing");
		DevCardList oldDev = new DevCardList(0,1,0,0,0);
		DevCardList newDev = new DevCardList(0,0,0,0,0);
		gameModel.getLocalPlayer().setOldDevCards(oldDev);
		gameModel.getLocalPlayer().setNewDevCards(newDev);
		gameModel.getLocalPlayer().setVictoryPoints(9);

		assertFalse(facade.canUseMonument());


		//Player status != Playing / Can / Not Enough Victory points
		gameModel.getLocalPlayer().setVictoryPoints(0);
		oldDev = new DevCardList(0,0,0,0,0);
		gameModel.getLocalPlayer().setOldDevCards(oldDev);
		assertFalse(facade.canUseMonument());

		newDev = new DevCardList(0,1,0,0,0);
		gameModel.getLocalPlayer().setNewDevCards(newDev);
		assertFalse(facade.canUseMonument());


		//Player status == Playing / Can't play
		gameModel.getTurnTracker().setStatus("Playing");
		assertFalse(facade.canUseMonument());


		//Player status == Playing / Can play / Not enough victory points
		gameModel.getLocalPlayer().setVictoryPoints(0);
		oldDev = new DevCardList(0,1,0,0,0);
		gameModel.getLocalPlayer().setOldDevCards(oldDev);
		assertFalse(facade.canUseMonument());

		//Player status == Playing / Can play 
		gameModel.getLocalPlayer().setVictoryPoints(9);
		assertTrue(facade.canUseMonument());

	}
	
	@Test
	public void testCanUseMonopoly() {
		player.setPlayedDevCard(false);
		//Player status != Playing / Can play
		turn.setStatus("Robbing");
		DevCardList oldDev = new DevCardList(1,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseMonopoly());
		
		//Player status != Playing / Can't play
		oldDev = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseMonopoly());
		
		//Player status == Playing / Can't play
		turn.setStatus("Playing");
		DevCardList newDev = new DevCardList(0,0,0,0,0);
		player.setNewDevCards(newDev);
		assertFalse(facade.canUseMonopoly());
		
		//Player status == Playing / Can play
		oldDev = new DevCardList(1,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseMonopoly());
	}
	
	@Test
	public void testCanUseSoldier() {
		player.setPlayedDevCard(false);
		//Player status != Playing / Can play
		turn.setStatus("Robbing");
		DevCardList oldDev = new DevCardList(0,0,0,1,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseSoldier());
		
		//Player status != Playing / Can't play
		oldDev = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseSoldier());
		
		//Player status == Playing / Can't play
		turn.setStatus("Playing");
		DevCardList newDev = new DevCardList(0,0,0,0,0);
		player.setNewDevCards(newDev);
		assertFalse(facade.canUseSoldier());
		
		//Player status == Playing / Can play
		oldDev = new DevCardList(0,0,0,1,0);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseSoldier());
	}
	
	@Test
	public void testCanUseRoadBuilder() {
		player.setPlayedDevCard(false);
		//Player status != Playing / Can play
		turn.setStatus("Robbing");
		DevCardList oldDev = new DevCardList(0,0,1,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseRoadBuilder());
		
		//Player status != Playing / Can't play
		oldDev = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseRoadBuilder());
		
		//Player status == Playing / Can't play
		turn.setStatus("Playing");
		DevCardList newDev = new DevCardList(0,0,0,0,0);
		player.setNewDevCards(newDev);
		assertFalse(facade.canUseRoadBuilder());
		
		//Player status == Playing / Can play
		oldDev = new DevCardList(0,0,1,0,0);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseRoadBuilder());
	}
	
	@Test
	public void testCanUseYearOfPlenty() {
		player.setPlayedDevCard(false);
		//Player status != Playing / Can play
		turn.setStatus("Robbing");
		DevCardList oldDev = new DevCardList(0,0,0,0,1);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseYearOfPlenty());
		
		//Player status != Playing / Can't play
		oldDev = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(oldDev);
		assertFalse(facade.canUseYearOfPlenty());
		
		//Player status == Playing / Can't play
		turn.setStatus("Playing");
		DevCardList newDev = new DevCardList(0,0,0,0,0);
		player.setNewDevCards(newDev);
		assertFalse(facade.canUseYearOfPlenty());
		
		//Player status == Playing / Can play
		oldDev = new DevCardList(0,0,0,0,1);
		player.setOldDevCards(oldDev);
		assertTrue(facade.canUseYearOfPlenty());
	}


}













