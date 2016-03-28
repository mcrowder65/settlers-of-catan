package communication.moves;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.Game;
import server.util.*;
import shared.communication.request.FinishTurnCommand;
import shared.communication.request.YearOfPlentyCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.DevCardList;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;

public class YearOfPlentyCommandTest {

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
		System.out.println("YearOfPlentyCommand test");
		
		ServerGameModel model = Game.instance().getGameId(0);
		ServerPlayer[] players = model.getServerPlayers();
		players[0].addYearOfPlenty();
		ServerPlayer player = players[0];
		YearOfPlentyCommand command = new YearOfPlentyCommand(0, ResourceType.BRICK, ResourceType.ORE);
		command.setGameCookie(0);
		GetModelResponse response = command.execute();
		assertFalse(response.isSuccess()); //can't play on same turn
		
		player.addDevCard(new DevCardList(0, 0, 0, 0, 1));
		
		finishTurn(0);
		finishTurn(1);
		finishTurn(2);
		finishTurn(3);
		int currentTurn = Game.instance().getGameId(0).getServerTurnTracker().getCurrentTurn();
		response = command.execute();
		assertFalse(response.isSuccess()); //should fail, needs to have Playing as status
		Game.instance().getGameId(0).getServerTurnTracker().setStatus("Playing");
		response = command.execute();

		assertTrue(response.isSuccess()); //next turn, so play card
		ResourceList playerResources = player.getResources();
		if(playerResources.getBrick() != 1)
			fail("You just played a year of plenty and requested brick, you should have at least one brick");
		if(playerResources.getOre() != 1)
			fail("You just played a year of plenty and requested ore, you should have at least one ore");
	}
	private void finishTurn(int playerIndex){
		FinishTurnCommand finishTurn = new FinishTurnCommand(playerIndex, 0); //0 = gameIndex
		GetModelResponse response = finishTurn.execute();
		assertTrue(response.isSuccess());
	}

}
