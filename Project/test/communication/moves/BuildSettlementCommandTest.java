package communication.moves;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.Game;
import shared.communication.request.BuildRoadCommand;
import shared.communication.request.BuildSettlementCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.EdgeValue;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;

public class BuildSettlementCommandTest {

	@Before
	public void setUp() throws Exception {
		new Setups().SetupGame("Playing");
		
	}

	@Test
	public void test() {
		System.out.print("BuildSettlementCommand test");
		VertexLocation loc = new VertexLocation(
				new HexLocation(0,0), VertexDirection.NorthEast);
		VertexObject obj = new VertexObject(0, loc);
		
		int prevSettlements = Game.instance().getGameId(0).getServerPlayers()[0].getSettlements();
		
		
		Game.instance().getGameId(0).getServerPlayers()[0].setResources(new ResourceList(1,0,1,1,1));
				

		BuildSettlementCommand command = new BuildSettlementCommand(0, false, loc);
		command.setGameCookie(0);
		GetModelResponse response = command.execute();
		assertTrue(response.isSuccess());
		
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getSettlements() == prevSettlements - 1);
		
		assertTrue(Game.instance().getGameId(0).getServerMap().hasSettlement(obj));
		
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getBrick() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getWheat() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getWood() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getSheep() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getOre() == 0);
		
		
	}

}
