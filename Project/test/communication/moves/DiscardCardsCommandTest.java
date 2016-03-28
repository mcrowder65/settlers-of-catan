package communication.moves;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.Game;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.request.DiscardCardsCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;

public class DiscardCardsCommandTest {

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
		System.out.println("Discard Cards Command test");
		
		ServerPlayer player = Game.instance().getGameId(0).getServerPlayers()[0];
		ServerTurnTracker turnTracker = Game.instance().getGameId(0).getServerTurnTracker();
		player.setResources(new ResourceList(1,1,1,1,1));
		DiscardCardsCommand command = new DiscardCardsCommand(0,0, new ResourceList(0,1,1,1,1));
		GetModelResponse response;
		
		//Less than 8 cards
		response = command.execute();
		assertFalse(response.isSuccess());
		
		//Not discarding status
		player.setResources(new ResourceList(1,1,2,2,2));
		turnTracker.setStatus("Robbing");
		response = command.execute();
		assertFalse(response.isSuccess());
		
		
		//Valid
		turnTracker.setStatus("Discarding");
		response = command.execute();
		assertTrue(response.isSuccess());
		
	}

}
