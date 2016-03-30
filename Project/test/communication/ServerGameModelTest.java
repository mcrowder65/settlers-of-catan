package communication;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.definitions.DevCardList;
import shared.definitions.DevCardType;
import shared.definitions.GameMap;
import shared.definitions.Hex;
import shared.definitions.MessageLine;
import shared.definitions.MessageList;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;

public class ServerGameModelTest {

	ServerGameModel model;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Testing ServerGameModel");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ServerPlayer player0 = new ServerPlayer();
		player0.setPlayerID(0);
		player0.setVictoryPoints(0);
		player0.setRoads(5);
		player0.setSoldiers(3);
		ServerPlayer player1 = new ServerPlayer();
		player1.setPlayerID(1);
		player1.setVictoryPoints(0);
		player1.setRoads(15);
		ServerPlayer player2 = new ServerPlayer();
		player2.setPlayerID(2);
		player2.setVictoryPoints(0);
		player2.setRoads(15);
		ServerPlayer player3 = new ServerPlayer();
		player3.setPlayerID(3);
		player3.setVictoryPoints(0);
		player3.setRoads(15);
		ServerTurnTracker turnTracker = new ServerTurnTracker(0,"Rolling",-1,-1);
		model = new ServerGameModel();
		ServerPlayer[] serverPlayers = new ServerPlayer[4];
		serverPlayers[0] = player0;
		serverPlayers[1] = player1;
		serverPlayers[2] = player2;
		serverPlayers[3] = player3;
		model.setServerPlayers(serverPlayers);
		model.setServerTurnTracker(turnTracker);
		model.setDeck(new DevCardList(1,1,1,1,1));
		
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
		ServerGameMap map = new ServerGameMap();
		map.setHexes(hexMap);
		map.setRobber(new HexLocation(2,0));
		model.setServerGameMap(map);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getLocalIndexTest() {
		System.out.println("Testing getLocalIndexTest in ServerGameModel");
		boolean worked = model.getLocalIndex(0) == 0 ? true : false;
		assert(worked == true);
		worked = model.getLocalIndex(1) == 1 ? true : false;
		assert(worked == true);
	}
	
	@Test
	public void setLargestArmyTest(){
		System.out.println("Testing setLargestArmy in ServerGameModel");
		//setting it first time
		model.findLargestArmy(); //this calls setLargestArmy
		assertTrue(model.getServerTurnTracker().getLargestArmy() == 0);
	}
	
	@Test
	public void setLongestRoadTest(){
		System.out.println("Testing setLongestRoad in ServerGameModel");
		//setting it first time
		model.findLongestRoad(); //this method calls setLongestRoad
		assertTrue(model.getServerTurnTracker().getLongestRoad() == 0);
		
	}
	
	@Test
	public void getPositive(){
		System.out.println("Testing getPositive in ServerGameModel");
		int num = model.getPositive(-1);
		assert(num == 1);
		
		num = model.getPositive(1);
		assert(num == 0);
		
	}
	
	@Test
	public void getNegative(){
		System.out.println("Testing getNegative in ServerGameModel");
		int num = model.getPositive(-1);
		assertTrue(num == 1);
		
		num = model.getPositive(1);
		assertTrue(num == 0);
	}
	
	@Test
	public void normalizeResourceListTest(){
		System.out.println("Testing normalizeResourceList in ServerGameModel");
		ResourceList list = new ResourceList(1,-1,0,0,0);
		ResourceList list2 = model.normalizeResourceList(list);
		assertTrue(list2.getBrick() == 0);
		assertTrue(list2.getOre() == 1);
	}
	
	@Test
	public void getRecievingResourceListTest(){
		System.out.println("Testing getRecievingResourceList in ServerGameModel");
		ResourceList list = new ResourceList(1,-1,0,0,0);
		list = model.normalizeResourceList(list);
		assertTrue(list.getBrick() == 0);
		assertTrue(list.getOre() == 1);
	}
	
	@Test
	public void findLargestArmyTest(){
		System.out.println("Testing findLargestArmyTest in ServerGameModel");
		model.findLargestArmy();
		assert(model.getServerTurnTracker().getLargestArmy() == 0);
		
		//player changed
		model.getServerPlayers()[1].setSoldiers(5);
		model.findLargestArmy();
		assert(model.getServerTurnTracker().getLargestArmy() == 1);
		
	}

	@Test
	public void findLongestRoadTest(){
		System.out.println("Testing findLongestRoad in ServerGameModel");
		model.findLongestRoad();
		assertTrue(model.getServerTurnTracker().getLongestRoad() == 0);
		
		//player changed 
		model.getServerPlayers()[1].setRoads(4);
		model.findLongestRoad();
		assertTrue(model.getServerTurnTracker().getLongestRoad() == 1);
	}
	
	@Test
	public void getNumPlayersTest(){
		System.out.println("Testing getNumPlayers in ServerGameModel");
		assert(model.getAmtOfPlayers() == 4);
	}
	
	@Test
	public void getLocalPlayerTest(){
		System.out.println("Testing getLocalPlayer in ServerGameModel");
		assertTrue(model.getLocalPlayer(0).getPlayerID() == 0);
		
		//another one
		assertTrue(model.getLocalPlayer(1).getPlayerID() == 0);
	}
	
	@Test
	public void buyFromDeckTest(){
		System.out.println("Testing buyFromDeck in ServerGameModel");
		model.buyFromDeck(DevCardType.SOLDIER);
		assert(model.getDeck().getSoldier() == 0);
		
		//another one
		model.buyFromDeck(DevCardType.MONOPOLY);
		assert(model.getDeck().getMonopoly() == 0);
	}
	
	@Test
	public void isBankEmptyTest(){
		System.out.println("Testing isBankEmpty in ServerGameModel");
		model.setBank(new ResourceList(1,1,1,1,1));
		assertFalse(model.isBankEmpty());
		
		model.setBank(new ResourceList(0,0,0,0,1));
		assertFalse(model.isBankEmpty());
		
		model.setBank(new ResourceList(0,0,0,0,0));
		assertTrue(model.isBankEmpty());
	}
	
	@Test
	public void isDeckEmptyTest(){
		System.out.println("Testing isDeckEmptyin ServerGameModel");
		model.setDeck(new DevCardList(1,1,1,1,1));
		assertFalse(model.isDeckEmpty());
		
		model.setDeck(new DevCardList(0,0,0,0,1));
		assertFalse(model.isDeckEmpty());
		
		model.setDeck(new DevCardList(0,0,0,0,0));
		assertTrue(model.isDeckEmpty());
		
	}
	
	@Test
	public void addChatTest(){
		System.out.println("Testing addChat ServerGameModel");
		model.setChat(new MessageList());
		model.addChatMessage(new MessageLine("Hello","Brennen"));
		assertTrue(model.getChat().getLines()[0].getSource().equals("Brennen"));
		assertTrue(model.getChat().getLines()[0].getMessage().equals("Hello"));
		
	}
	
	@Test
	public void addGameLogTest(){
		System.out.println("Testing addGameLog ServerGameModel");
		model.setLog(new MessageList());
		model.addGameLogMessage(new MessageLine("Hello","Brennen"));
		assertTrue(model.getLog().getLines()[0].getSource().equals("Brennen"));
		assertTrue(model.getLog().getLines()[0].getMessage().equals("Hello"));
	}
	
	@Test
	public void allPlayersDiscardedTest(){
		System.out.println("Testing allPlayersDiscarded ServerGameModel");
		assertFalse(model.allPlayersDiscarded());
		model.getServerPlayers()[0].setDiscarded(true);
		assertFalse(model.allPlayersDiscarded());
		model.getServerPlayers()[1].setDiscarded(true);
		model.getServerPlayers()[2].setDiscarded(true);
		model.getServerPlayers()[3].setDiscarded(true);
		assertTrue(model.allPlayersDiscarded());
	}
	
	@Test 
	public void getPlayersByIndexTest(){
		System.out.println("Testing getPlayersByIndex ServerGameModel");
		assertTrue(model.getPlayerByIndex(0).getPlayerID() == model.getServerPlayers()[0].getPlayerID());
		assertTrue(model.getPlayerByIndex(3).getPlayerID() == model.getServerPlayers()[3].getPlayerID());
	}
	
	@Test
	public void issueResourcesNormalPlayTest(){
		System.out.println("Testing issueResourcesNormalPlay ServerGameModel");
		
		//setting with settlement
		model.getServerMap().laySettlement(new VertexObject(0,new VertexLocation(new HexLocation(0,0),VertexDirection.NorthWest)), false); 
		model.getServerPlayers()[0].setResources(new ResourceList(0,0,0,0,0));
		model.getServerPlayers()[1].setResources(new ResourceList(0,0,0,0,0));
		model.getServerPlayers()[2].setResources(new ResourceList(0,0,0,0,0));
		model.getServerPlayers()[3].setResources(new ResourceList(0,0,0,0,0));
		model.issueResourcesNormalPlay(9);
		assert(model.getServerPlayers()[0].getResources().getOre() == 1);
		
		//testing with cities
		model.getServerMap().layCity(new VertexObject(0,new VertexLocation(new HexLocation(0,0),VertexDirection.NorthWest))); 
		model.issueResourcesNormalPlay(9);
		assert(model.getServerPlayers()[0].getResources().getOre() == 3);
		
		
	}
	
	
	
	
	
	
}






