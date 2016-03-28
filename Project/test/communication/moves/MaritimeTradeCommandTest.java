package communication.moves;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.Game;
import server.util.ServerGameMap;
import server.util.ServerPlayer;
import shared.communication.request.MaritimeTradeCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;

public class MaritimeTradeCommandTest {

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
		System.out.println("MaritimeTradeCommand test");
		
		ServerPlayer player = Game.instance().getGameId(0).getServerPlayers()[0];
		ServerGameMap map = Game.instance().getGameId(0).getServerMap();
		ResourceList bank = Game.instance().getGameId(0).getBank();
		map.buildSettlement(new VertexObject(0, new VertexLocation(new HexLocation(-1,-2), VertexDirection.SouthEast)), false);
		MaritimeTradeCommand command = new MaritimeTradeCommand(0,0,2, ResourceType.WHEAT, ResourceType.ORE);
		GetModelResponse response;
		
		//Not enough resource input
		player.setResources(new ResourceList(0,0,0,1,2));
		response = command.execute();
		assertFalse(response.isSuccess());
		
		
		//Bank doesn't have resource output
		player.setResources(new ResourceList(0,0,0,2,0));
		bank = new ResourceList(2,0,2,2,2);
		Game.instance().getGameId(0).setBank(bank);
		response = command.execute();
		assertFalse(response.isSuccess());
		
		//Valid
		bank = new ResourceList(0,2,0,0,0);
		Game.instance().getGameId(0).setBank(bank);
		response = command.execute();
		assertTrue(response.isSuccess());
	}

}
