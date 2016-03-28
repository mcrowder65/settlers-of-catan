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

public class SoldierCommandTest {

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
		SoldierCommand soldier = new SoldierCommand(0, loc, 1);
		soldier.setGameCookie(0);
		VertexLocation vertLoc = new VertexLocation(loc, VertexDirection.NorthWest);
		BuildSettlementCommand settlementCommand = new BuildSettlementCommand(1, true, vertLoc, 0);
		GetModelResponse response = settlementCommand.execute();
		assertFalse(response.isSuccess()); //it isn't player 1's turn.
		response = soldier.execute();
		assertFalse(response.isSuccess()); //needs to wait one more turn to play soldier
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
		
		//player 0's turn now
		response = soldier.execute();
		assertFalse(response.isSuccess()); //should be false, needs to be in Playing status
		
		Game.instance().getGameId(0).getServerTurnTracker().setStatus("Playing");
		response = soldier.execute();
		assertTrue(response.isSuccess());
		
		assertTrue(players[1].getResources().getOre() == 0);
		assertTrue(players[0].getResources().getOre() == 6);
	}
	
	private void finishTurn(int playerIndex){
		FinishTurnCommand finishTurn = new FinishTurnCommand(playerIndex, 0); //0 = gameIndex
		GetModelResponse response = finishTurn.execute();
		assertTrue(response.isSuccess());
	}
}
