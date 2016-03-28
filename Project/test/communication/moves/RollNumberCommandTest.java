package communication.moves;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.Game;
import server.util.*;
import shared.communication.request.*;
import shared.communication.response.GetModelResponse;
import shared.definitions.*;
import shared.locations.*;

public class RollNumberCommandTest {

	@Before
	public void setUp() throws Exception {
		new Setups().SetupGame("Playing");
	}

	@Test
	public void test() {
		ServerGameModel model = Game.instance().getGameId(0);
		ServerPlayer[] players = model.getServerPlayers();
		
		String status = Game.instance().getGameId(0).getServerTurnTracker().getStatus();
		ServerTurnTracker turnTracker = Game.instance().getGameId(0).getServerTurnTracker();
		players[0].addResourceCards(new ResourceList(1, 0, 1, 1, 1)); //brick, ore, sheep, wheat, wood
		HexLocation loc = new HexLocation(0, 2);
		VertexLocation vertLoc = new VertexLocation(loc, VertexDirection.NorthEast);
		BuildSettlementCommand settlementCommand = new BuildSettlementCommand(0, true, vertLoc, 0);
		GetModelResponse response = settlementCommand.execute();
		assertTrue(response.isSuccess());
		finishTurn(0);
		
		RollNumberCommand rollNumber = new RollNumberCommand(1, 8, 0); //playerIndex,dice roll, gameIndex / gameID
		assertTrue(rollNumber.getNumber() == 8);
		assertTrue(rollNumber.getMoveType().equals("rollNumber"));
		response = rollNumber.execute();
		assertTrue(response.isSuccess());
		assertTrue(players[0].getResources().getWheat() == 1);
		
		finishTurn(1);
		
		rollNumber = new RollNumberCommand(2, 10, 0);
		response = rollNumber.execute();
		assertTrue(response.isSuccess());
		assertTrue(players[0].getResources().getSheep() == 1);
		finishTurn(2);
		
		rollNumber = new RollNumberCommand(3, 4, 0);
		response = rollNumber.execute();
		assertTrue(response.isSuccess());
		assertTrue(players[0].getResources().getWood() == 1); //i want to test all three hexes bordering that settlement
		
	}
	private void finishTurn(int playerIndex){
		FinishTurnCommand finishTurn = new FinishTurnCommand(playerIndex, 0); //0 = gameIndex
		GetModelResponse response = finishTurn.execute();
		assertTrue(response.isSuccess());
	}
}
