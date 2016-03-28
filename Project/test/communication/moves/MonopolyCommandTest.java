package communication.moves;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.Game;
import server.util.ServerPlayer;
import shared.communication.request.MonopolyCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.DevCardList;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;

public class MonopolyCommandTest {

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
		System.out.println("MonopolyCommand test");
	     ServerPlayer[] players = Game.instance().getGameId(0).getServerPlayers();
	     DevCardList devs = new DevCardList();
	     devs.setMonopoly(2);
	     
	     players[0].setResources(new ResourceList(1,0,0,0,0));
	     players[0].setOldDevCards(devs);
	     players[1].setResources(new ResourceList(2,0,0,0,0));
	     players[2].setResources(new ResourceList(3,0,0,0,0));
	     players[3].setResources(new ResourceList(4,0,0,0,0));
	     
	     MonopolyCommand command = new MonopolyCommand(0, ResourceType.BRICK);
	     command.setGameCookie(0);
	     GetModelResponse response = command.execute();
	     assertTrue(response.isSuccess());
	     
	     assertTrue(players[0].getResources().getBrick() == 10);
	     assertTrue(players[1].getResources().getBrick() == 0);
	     assertTrue(players[2].getResources().getBrick() == 0);
	     assertTrue(players[3].getResources().getBrick() == 0);
	     assertTrue(players[0].getOldDevCards().getMonopoly() == 1);
	     
	     
	     response = command.execute();
	     assertTrue(!response.isSuccess());
	     assertTrue(players[0].getOldDevCards().getMonopoly() == 1);
	     
	}

}
