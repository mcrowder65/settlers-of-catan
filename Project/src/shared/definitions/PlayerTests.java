package shared.definitions;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTests {

	@Test
	public void canBuildRoadTest() {
		Player player = new Player();
		
		ResourceList resources = new ResourceList(10,10,10,10,10);
		player.setResources(resources);
		boolean canLayRoad = player.canBuyRoad();
		assertTrue(canLayRoad == true);
		
		resources = new ResourceList(0,0,0,0,0);
		DevCardList devCardList = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(devCardList);
		player.setResources(resources);
		canLayRoad = player.canBuyRoad();
		assertTrue(canLayRoad == false);
		
		devCardList = new DevCardList(0,0,1,0,0);
		player.setOldDevCards(devCardList);
		canLayRoad = player.canBuyRoad();
		assertTrue(canLayRoad == true);
		
	}
	

}
