package communication.moves;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.Game;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import shared.communication.request.BuildSettlementCommand;
import shared.communication.request.FinishTurnCommand;
import shared.communication.request.RobPlayerCommand;
import shared.communication.request.SoldierCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.DevCardList;
import shared.definitions.ResourceList;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class RobPlayerCommandTest {

	@Before
	public void setUp() throws Exception {
		new Setups().SetupGame("Playing");
	}

	@Test
	public void test() {
		ServerGameModel model = Game.instance().getGameId(0);
		ServerPlayer[] players = model.getServerPlayers();
		
		players[0].addResourceCards(new ResourceList(3, 5, 1, 7, 5)); //brick, ore, sheep, wheat, wood
		players[1].addResourceCards(new ResourceList(1, 1, 1, 1, 1));
		
		players[0].addDevCard(new DevCardList(0, 0, 0, 1, 0)); //monopoly, monument, roadBuilding, soldier, yearOfPlenty
		HexLocation loc = new HexLocation(0, 2);
		VertexLocation vertLoc = new VertexLocation(loc, VertexDirection.NorthWest);
		BuildSettlementCommand settlementCommand = new BuildSettlementCommand(1, true, vertLoc);
		settlementCommand.setGameCookie(0);
		GetModelResponse response = settlementCommand.execute();
		assertFalse(response.isSuccess()); //it isn't player 1's turn.
		
		finishTurn(0);
		
		response = settlementCommand.execute();
		assertFalse(response.isSuccess()); //status isn't playing, should be false
		Game.instance().getGameId(0).getServerTurnTracker().setStatus("Playing");

		response = settlementCommand.execute();
		
		assertTrue(response.isSuccess());
		
		assertTrue(players[1].getResources().getWood() == 0);
		assertTrue(players[1].getResources().getBrick() == 0);
		assertTrue(players[1].getResources().getSheep() == 0);
		assertTrue(players[1].getResources().getWheat() == 0);
		assertTrue(players[1].getResources().getOre() == 1);
		//player 1 built a settlement just fine. only 1 ore remaining.
		
		finishTurn(1);
		finishTurn(2);
		finishTurn(3);
		RobPlayerCommand robPlayer = new RobPlayerCommand(0, loc, 1);
		robPlayer.setGameCookie(0);
		//player 0's turn now
		response = robPlayer.execute();
		assertFalse(response.isSuccess()); //should be false, needs to be in robbing status
		
		Game.instance().getGameId(0).getServerTurnTracker().setStatus("Robbing");
//		response = soldier.execute();
		assertTrue(players[0].getResources().getOre() == 5); //player 0 has 5 ore... needs to be correct 
		response = robPlayer.execute();
		assertTrue(response.isSuccess());
		
		assertTrue(players[1].getResources().getOre() == 0); // player 1 only had ore left, so now that's gone.
		assertTrue(players[0].getResources().getOre() == 6); //player 0 had 5 ore before, now he has 6 ore
	}
	private void finishTurn(int playerIndex){
		FinishTurnCommand finishTurn = new FinishTurnCommand(playerIndex, 0); //0 = gameIndex
		GetModelResponse response = finishTurn.execute();
		assertTrue(response.isSuccess());
	}
}
