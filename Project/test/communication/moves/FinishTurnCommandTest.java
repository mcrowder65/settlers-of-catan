package communication.moves;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.Game;
import server.util.ServerTurnTracker;
import shared.communication.request.FinishTurnCommand;
import shared.communication.response.GetModelResponse;



public class FinishTurnCommandTest {

	@Before
	public void setUp() throws Exception {
		new Setups().SetupGame("Playing");
	}

	@Test
	public void test() {
		System.out.println("FinishTurnCommand test");
		
		ServerTurnTracker turnTracker = Game.instance().getGameId(0).getServerTurnTracker();
		FinishTurnCommand command = new FinishTurnCommand(0,0);
		GetModelResponse response;
		
		//Not your turn
		turnTracker.setCurrentTurn(2);
		response = command.execute();
		assertFalse(response.isSuccess());
		
		//Valid
		turnTracker.setCurrentTurn(0);
		response = command.execute();
		assertTrue(response.isSuccess());
	}

}
