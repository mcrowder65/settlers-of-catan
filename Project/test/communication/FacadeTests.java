package communication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import client.communication.HTTPProxy;
import client.controller.Facade;
import client.data.GameInfo;
import client.data.GameManager;
import shared.definitions.*;
import shared.locations.*;


/**
 * These tests are ordered alphabetically,
 * so you can login before doing moves.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FacadeTests {
	static GameManager game;
	static Facade facade;
	static GameModel gameModel;
	static HTTPProxy proxy;
	static int gameId;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		game = new GameManager();
		gameModel = new GameModel();
		proxy = new HTTPProxy(0, "localhost", 8081);
		game.updateModel(gameModel);
		game.setProxy(proxy);
		facade = new Facade(proxy, 3);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void aRegister(){
		boolean register = facade.register("matt", "crowder");
		boolean login = facade.login("matt", "crowder");
		if(register == false && login == false) fail();
		
	}
	@Test
	public void bLogin() {
		boolean login = facade.login("matt", "crowder");
		if(login == false) fail();
		login = facade.login("quinn", "snell");
		if(login == true) fail();
	}
	@Test
	public void cListGames(){
		GameInfo[] games = facade.listGames();
		if(games.length == 0) fail();
	}
	@Test
	public void dCreateGame(){
		int id = facade.createGame("matt", false, false, false);
		assertTrue(id != -1);
		gameId = id;
	}
	@Test
	public void eJoinGame(){
		boolean join = facade.joinGame(gameId, CatanColor.red);
		if(join == false) fail();
		boolean result;
		result = facade.addAI("LARGEST_ARMY");
		assertTrue(result);
		result = facade.addAI("LARGEST_ARMY");
		assertTrue(result);
		result = facade.addAI("LARGEST_ARMY");
		assertTrue(result);
		
	}
	@Test
	public void fRollNumber(){
		int result = facade.rollNumber();
		if(result > 12 || result < 2) fail();
	}
	@Test
	public void gSendChat() {
		boolean result = facade.sendChat("hi");
		assertTrue(result);
		
	}
	@Test
	public void hFinishTurn() {
		boolean result = facade.finishTurn();
		assertTrue(result);
		
	}
	@Test
	public void iBuildRoad() {
		boolean result = facade.buildRoad(new EdgeLocation(new HexLocation(0,0), EdgeDirection.North), true);
		assertTrue(result);
	}
	@Test
	public void jBuildSettlement() {
		boolean result = facade.buildSettlement(new VertexLocation(new HexLocation(0,0), VertexDirection.East), true);
		assertTrue(result);
	}
	@Test
	public void kBuildCity() {
		boolean result = facade.buildCity(new VertexLocation(new HexLocation(0,0), VertexDirection.East));
		assertTrue(result);
	}
	@Test
	public void lRobPlayer() {
		boolean result = facade.placeRobber(0, new HexLocation(0,0));
		assertTrue(result);
	
	}
	@Test
	public void mDiscardCards() {
		boolean result = facade.discardCards(new ResourceList(0,0,0,0,0));
		assertTrue(result);
		
	}
	@Test
	public void nOfferTrade() {
		boolean result = facade.offerTrade(new TradeOffer(0,1, (new ResourceList(0,0,0,0,0))));
		assertTrue(result);
	}
	@Test
	public void oAcceptTrade() {
		boolean result = facade.acceptTrade(true);
		assertTrue(result);
	}
	@Test
	public void pMaritimeTrade() {
		boolean result = facade.offerMaritimeTrade(2, ResourceType.WOOD, ResourceType.WHEAT);
		assertTrue(result);
	}
	@Test
	public void qSoldier() {
		boolean result = facade.playSoldier(0, new HexLocation(0,0));
		assertTrue(result);
	
	}
	@Test
	public void rYearOfPlenty() {
		boolean result = facade.playYearOfPlenty(ResourceType.WHEAT, ResourceType.WOOD);
		assertTrue(result);
	}
	@Test
	public void sMonopoly() {
		boolean result = facade.playMonopoly(ResourceType.WHEAT);
		assertTrue(result);
	}
	@Test
	public void tMonument() {
		boolean result = facade.playMonument();
		assertTrue(result);
	}
	@Test
	public void uRoadBuilding() {
		boolean result = facade.playRoadBuilding(new EdgeLocation(new HexLocation(0,0), EdgeDirection.North),
												 new EdgeLocation(new HexLocation(0,0), EdgeDirection.South));
		assertTrue(result);
		
	}
	@Test
	public void vBuyDevCard() {
		boolean result = facade.buyDevCard();
		assertTrue(result);
		
	}
}	
