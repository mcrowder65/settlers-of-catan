package communication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.util.ServerPlayer;
import shared.definitions.CatanColor;
import shared.definitions.DevCardList;
import shared.definitions.ResourceList;

public class ServerPlayerTest {

	ServerPlayer player = new ServerPlayer();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Testing ServerPlayer");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Testing ServerPlayer completed");
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void layRoadTest() {
		System.out.println("Testing chargeBasicRoad in ServerPlayer");
		//setting it up
		player.setResources(new ResourceList(1,0,0,0,1));
		player.setRoads(1);
		player.layRoad();
		
		//check road number
		assertTrue(player.getRoads() == 0);
		
		//check Resources
		assertTrue(player.getResources().getBrick() == 0);
		assertTrue(player.getResources().getWood() == 0);

	}
	
	@Test
	public void layRoadBuilderTest() {
		System.out.println("Testing layRoadBuilder in ServerPlayer");
		//setting it up
		player.setOldDevCards(new DevCardList(0,0,1,0,0));
		player.setRoads(2);
		player.layRoadBuilder();
		
		//check road number
		assertTrue(player.getRoads() == 0);
		
		//check DevCards
		assertTrue(player.getOldDevCards().getRoadBuilding() == 0);

	}
	
	@Test
	public void laySettlementTest() {
		System.out.println("Testing layRoadBuilder in ServerPlayer");
		//setting it up
		player.setResources(new ResourceList(1,1,1,1,1));
		player.setSettlements(1);
		player.laySettlement();
		
		//check road number
		assertTrue(player.getSettlements() == 0);
		
		//check Resources
		assertTrue(player.getResources().getBrick() == 0);
		assertTrue(player.getResources().getWood() == 0);
		assertTrue(player.getResources().getSheep() == 0);
		assertTrue(player.getResources().getWheat() == 0);
		assertTrue(player.getResources().getOre() == 1);

	}

}
