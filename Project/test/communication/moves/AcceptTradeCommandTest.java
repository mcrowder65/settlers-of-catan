package communication.moves;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.Game;
import shared.communication.request.AcceptTradeCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
import shared.definitions.TradeOffer;

public class AcceptTradeCommandTest {

	
	
	
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
		System.out.println("AcceptTradeCommand test");
		TradeOffer trade = new TradeOffer();
		trade.setReceiver(0);
		trade.setSender(1);
		trade.setOffer(new ResourceList(1,-1,0,0,0));
		Game.instance().getGameId(0).setTradeOffer(trade);
		
		Game.instance().getGameId(0).getServerPlayers()[0].setResources(new ResourceList(0,1,0,0,0));
		Game.instance().getGameId(0).getServerPlayers()[1].setResources(new ResourceList(1,0,0,0,0));
		
		
		
		AcceptTradeCommand command = new AcceptTradeCommand(0, true);
		command.setGameCookie(0);
		GetModelResponse response = command.execute();
		assertTrue(response.isSuccess());
		
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getBrick() == 1);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[1].getResources().getBrick() == 0);
		
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getOre() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[1].getResources().getOre() == 1);
		
	}

}
