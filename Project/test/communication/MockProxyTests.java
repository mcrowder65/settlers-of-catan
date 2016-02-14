package communication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.communication.MockProxy;
import shared.communication.response.*;
import shared.definitions.CatanColor;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.definitions.TradeOffer;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class MockProxyTests {

	MockProxy mock;
	@Before
	public void setUp() throws Exception {
		mock = new MockProxy();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {
		Response response = mock.login("user", "pass");
		assertTrue(response.isSuccess());
	}
	@Test
	public void testRegister() {
		Response response = mock.register("user", "pass");
		assertTrue(response.isSuccess());
	}
	@Test
	public void testListGames() {
		ListGamesResponse response = mock.listGames();
		assertTrue(response.isSuccess());
	}
	@Test
	public void testJoinGame() {
		Response response = mock.joinGame(3, CatanColor.puce);
		assertTrue(response.isSuccess());
	}
	@Test
	public void testCreateGame() {
		CreateGameResponse response = mock.createGame("game", false, false, true);
		assertTrue(response.isSuccess());
	}
	@Test
	public void testListAI() {
		ListAIResponse response = mock.listAI();
		assertTrue(response.isSuccess());
	}
	@Test
	public void testSendChat() {
		GetModelResponse response = mock.sendChat(0, "content");
		assertTrue(response.isSuccess());
	}
	@Test
	public void testBuildRoad() {
		GetModelResponse response = mock.buildRoad(0, new EdgeLocation(new HexLocation(0,0),EdgeDirection.North), true);
		assertTrue(response.isSuccess());
	}
	@Test
	public void testBuildSettlement() {
		GetModelResponse response = mock.buildSettlement(0, new VertexLocation(new HexLocation(0,0),VertexDirection.East), false);
		assertTrue(response.isSuccess());
	}
	@Test
	public void testBuildCity() {
		GetModelResponse response = mock.buildCity(0, new VertexLocation(new HexLocation(0,0),VertexDirection.East));
		assertTrue(response.isSuccess());
	}
	@Test
	public void testBuyDevCard() {
		GetModelResponse response = mock.buyDevCard(0);
		assertTrue(response.isSuccess());
	}
	@Test
	public void testRobPlayer() {
		GetModelResponse response = mock.robPlayer(0, 2, new HexLocation(0,0));
		assertTrue(response.isSuccess());
	}
	@Test
	public void testSoldier() {
		GetModelResponse response = mock.Soldier(0, 2, new HexLocation(0,0));
		assertTrue(response.isSuccess());
	}
	@Test
	public void testYearOfPlenty() {
		GetModelResponse response = mock.Year_Of_Plenty(0, ResourceType.BRICK, ResourceType.WHEAT);
		assertTrue(response.isSuccess());
	}
	@Test
	public void testMonument() {
		GetModelResponse response = mock.Monument(0);
		assertTrue(response.isSuccess());
	}
	@Test
	public void testMonopoly() {
		GetModelResponse response = mock.Monopoly(0,ResourceType.ORE);
		assertTrue(response.isSuccess());
	}
	@Test
	public void testRoadBuilding() {
		GetModelResponse response = mock.Road_Building(0, new EdgeLocation(new HexLocation(0,0),EdgeDirection.North), new EdgeLocation(new HexLocation(0,0),EdgeDirection.North));
		assertTrue(response.isSuccess());
	}
	@Test
	public void testOfferTrade() {
		GetModelResponse response = mock.offerTrade(0, new TradeOffer(2,3, new ResourceList(0,0,0,0,0)));
		assertTrue(response.isSuccess());
	}
	@Test
	public void testAcceptTrade() {
		GetModelResponse response = mock.acceptTrade(0, true);
		assertTrue(response.isSuccess());
	}
	@Test
	public void testMaritimeTrade() {
		GetModelResponse response = mock.maritimeTrade(0, 2, ResourceType.BRICK, ResourceType.SHEEP);
		assertTrue(response.isSuccess());
	}
	@Test
	public void testDiscardCards() {
		GetModelResponse response = mock.discardCards(0, new ResourceList(0,0,1,0,0));
		assertTrue(response.isSuccess());
	}
	@Test
	public void testFinishTurn() {
		GetModelResponse response = mock.finishTurn(0);
		assertTrue(response.isSuccess());
	}

}
