package communication.moves;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.Game;
import shared.communication.request.AcceptTradeCommand;
import shared.definitions.ResourceList;
import shared.definitions.TradeOffer;

public class AcceptTradeCommandTest {

	
	
	
	@Before
	public void setUp() throws Exception {
		new Setups().SetupGame("Playing");
		
		TradeOffer trade = new TradeOffer();
		trade.setReceiver(0);
		trade.setSender(1);
		trade.setOffer(new ResourceList(1,-1,0,0,0));
		Game.instance().getGameId(0).setTradeOffer(trade);
	}

	@Test
	public void test() {
		AcceptTradeCommand command = new AcceptTradeCommand();
	}

}
