package communication.moves;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.Game;
import server.util.ServerPlayer;
import shared.communication.request.OfferTradeCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
import shared.definitions.TradeOffer;

public class OfferTradeCommandTest {

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
		System.out.println("OfferTradeCommand test");
		 ServerPlayer[] players = Game.instance().getGameId(0).getServerPlayers();
		 
		 players[0].setResources(new ResourceList(1,1,0,0,0));
		 players[1].setResources(new ResourceList(0,0,0,1,1));
		 
		 ResourceList tradeResc = new ResourceList(1,1, 0, -1, -1);
		 TradeOffer trade = new TradeOffer();
		 trade.setOffer(tradeResc);
		 trade.setReceiver(1);
		 trade.setSender(0);
		 
		 OfferTradeCommand command = new OfferTradeCommand(0, 1, tradeResc);
		 command.setGameCookie(0);
		 GetModelResponse response = command.execute();
		 
		 assertTrue(response.isSuccess());
		 assertTrue(Game.instance().getGameId(0).getTradeOffer().equals(trade));
		 
		 
	}

}
