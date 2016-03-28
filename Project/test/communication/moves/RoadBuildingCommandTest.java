package communication.moves;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.Game;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import shared.communication.request.BuildSettlementCommand;
import shared.communication.request.FinishTurnCommand;
import shared.communication.request.RoadBuildingCommand;
import shared.communication.request.RobPlayerCommand;
import shared.communication.request.SoldierCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.DevCardList;
import shared.definitions.ResourceList;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
public class RoadBuildingCommandTest {

	@Before
	public void setUp() throws Exception {
		new Setups().SetupGame("Playing");
	}
	@After
	public void tearDown() throws Exception {
		Game.Reset();
	}
	@Test
	public void test() {
		System.out.println("RoadBuildingCommand test");
		ServerGameModel model = Game.instance().getGameId(0);
		ServerPlayer[] players = model.getServerPlayers();
		
		players[0].addResourceCards(new ResourceList(3, 5, 1, 7, 5)); //brick, ore, sheep, wheat, wood
		players[1].addResourceCards(new ResourceList(2, 1, 2, 2, 2));
		
		players[0].addDevCard(new DevCardList(0, 0, 0, 1, 0)); //monopoly, monument, roadBuilding, soldier, yearOfPlenty
		HexLocation loc = new HexLocation(0, 2);
		VertexLocation vertLoc = new VertexLocation(loc, VertexDirection.NorthWest);
		BuildSettlementCommand settlementCommand = new BuildSettlementCommand(1, true, vertLoc, 0);
		GetModelResponse response = settlementCommand.execute();
		assertFalse(response.isSuccess()); //it isn't player 1's turn.
		
		finishTurn(0);
		
		response = settlementCommand.execute();
		assertFalse(response.isSuccess()); //status isn't playing, should be false
		Game.instance().getGameId(0).getServerTurnTracker().setStatus("Playing");

		response = settlementCommand.execute();
		
		assertTrue(response.isSuccess());
		
		assertTrue(players[1].getResources().getWood() == 1);
		assertTrue(players[1].getResources().getBrick() == 1);
		assertTrue(players[1].getResources().getSheep() == 1);
		assertTrue(players[1].getResources().getWheat() == 1);
		//player 1 built a settlement just fine. only 1 of each remaining (excluding ore)
		players[1].addDevCard(new DevCardList(0, 0, 1, 0, 0));
		HexLocation hexLoc1 = new HexLocation(1,1);
		EdgeLocation spot1 = new EdgeLocation(hexLoc1, EdgeDirection.SouthWest);
		HexLocation hexLoc2 = new HexLocation(0,1);
		EdgeLocation spot2 = new EdgeLocation(hexLoc2, EdgeDirection.NorthEast);
		RoadBuildingCommand roadBuilder = new RoadBuildingCommand(1, spot1, spot2);
		roadBuilder.setGameCookie(0);
		response = roadBuilder.execute();
		assertFalse(response.isSuccess()); //isn't player 1's second turn after buying road building card, should fail.
		finishTurn(1);
		finishTurn(2);
		finishTurn(3);
		finishTurn(0);
		
		response = roadBuilder.execute();
		assertFalse(response.isSuccess()); //should fail, status is rolling atm.
		Game.instance().getGameId(0).getServerTurnTracker().setStatus("Playing");
		
		response = roadBuilder.execute();
		assertTrue(response.isSuccess());
	}
	private void finishTurn(int playerIndex){
		FinishTurnCommand finishTurn = new FinishTurnCommand(playerIndex, 0); //0 = gameIndex
		GetModelResponse response = finishTurn.execute();
		assertTrue(response.isSuccess());
	}
	

}
