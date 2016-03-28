package communication.moves;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.Game;
import server.util.ServerPlayer;
import shared.communication.request.MonumentCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.DevCardList;

public class MonumentCommandTest {

	@Before
	public void setUp() throws Exception {
		new Setups().SetupGame("Playing");
	}

	@Test
	public void test() {
		System.out.println("MonumentCommand test");
		
		ServerPlayer player = Game.instance().getGameId(0).getServerPlayers()[0];
		
		MonumentCommand command = new MonumentCommand(0,0);
		GetModelResponse response;
		
		//Not enough victory points + monuments
		//8 victory points + 1 monument
		player.setVictoryPoints(8);
		player.setOldDevCards(new DevCardList(0,1,0,0,0));
		response = command.execute();
		assertFalse(response.isSuccess());
		
		//Valid
		player.setVictoryPoints(9);
		response = command.execute();
		assertTrue(response.isSuccess());
		
	}

}
