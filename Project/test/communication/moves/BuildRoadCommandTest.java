package communication.moves;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.Game;
import shared.communication.request.BuildCityCommand;
import shared.communication.request.BuildRoadCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.EdgeValue;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;

public class BuildRoadCommandTest {

	@Before
	public void setUp() throws Exception {
		new Setups().SetupGame("Playing");
		
	}

	@Test
	public void test() {
		System.out.print("BuildRoadCommand test");
		EdgeLocation loc = new EdgeLocation(
				new HexLocation(0,0), EdgeDirection.NorthEast);
		EdgeValue edge = new EdgeValue(0, loc);
				
		
		int prevRoads = Game.instance().getGameId(0).getServerPlayers()[0].getRoads();
		
		
		Game.instance().getGameId(0).getServerPlayers()[0].setResources(new ResourceList(1,0,0,0,1));
				

		BuildRoadCommand command = new BuildRoadCommand(0, false, edge.getLocation());
		command.setGameCookie(0);
		GetModelResponse response = command.execute();
		assertTrue(response.isSuccess());
		
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getRoads() == prevRoads - 1);
		
		assertTrue(Game.instance().getGameId(0).getServerMap().hasRoadPersonal(edge));
		
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getBrick() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getWheat() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getWood() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getSheep() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getOre() == 0);
		
	}

}
