package communication.moves;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.Game;
import shared.communication.request.BuildCityCommand;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;

public class BuildCityCommandTest {

	@Before
	public void setUp() throws Exception {
		new Setups().SetupGame("Playing");
		
	}

	@Test
	public void test() {
		System.out.print("BuildCityCommand test");
		VertexLocation loc = new VertexLocation(
				new HexLocation(0,0), VertexDirection.NorthEast);
		VertexObject obj = new VertexObject(0, loc);
				
		Game.instance().getGameId(0).getServerPlayers()[0].setResources(new ResourceList(0,3,0,2,0));
		Game.instance().getGameId(0).getServerMap().buildSettlement(
				obj, false);
							
		int prevSettlements = Game.instance().getGameId(0).getServerPlayers()[0].getSettlements();
		int prevCities = Game.instance().getGameId(0).getServerPlayers()[0].getCities();
		
		BuildCityCommand command = new BuildCityCommand(0, loc);
		command.setGameCookie(0);
		GetModelResponse response = command.execute();
		assertTrue(response.isSuccess());
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getSettlements() == prevSettlements + 1);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getCities() == prevCities - 1);
		
		assertTrue(Game.instance().getGameId(0).getServerMap().hasCity(obj));
		
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getBrick() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getWheat() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getWood() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getSheep() == 0);
		assertTrue(Game.instance().getGameId(0).getServerPlayers()[0].getResources().getOre() == 0);
		
	}

}
