package communication.moves;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.Game;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.request.BuyDevCardCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;

public class BuyDevCardCommandTest {

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
		System.out.println("BuyDevCardCommand test");
		
		ServerPlayer player = Game.instance().getGameId(0).getServerPlayers()[0];
		ServerTurnTracker tracker = Game.instance().getGameId(0).getServerTurnTracker();
		assertTrue(player.getOldDevCards().isEmpty());
		assertTrue(player.getNewDevCards().isEmpty());
		
		player.setCities(2);
		
		BuyDevCardCommand command = new BuyDevCardCommand(0,0);
		GetModelResponse response = command.execute();
		
		//Not enough resources
		assertFalse(response.isSuccess());
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getNewDevCards().isEmpty());
		
		//Enough resources, not your turn
		player.setResources(new ResourceList(1,1,1,1,1));
		tracker.setCurrentTurn(2);
		
		response = command.execute();
		assertFalse(response.isSuccess());
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getNewDevCards().isEmpty());
		
		
		//Valid
		tracker.setCurrentTurn(0);
		response = command.execute();
		assertTrue(response.isSuccess());
	}

}







