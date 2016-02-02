package shared.definitions;

import static org.junit.Assert.*;

import org.junit.Test;

import client.controller.Facade;
import client.data.GameManager;

public class FacadeCanDoTest {

	@Test
	public void canDiscardCardsFalse() {
		
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(0,0,0,0,0);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		boolean canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == false);
		
		canDiscard = true;
		resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == false);
		
		canDiscard = true;
		resources = new ResourceList(2,3,1,1,0);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == false);
		
		canDiscard = true;
		resources = new ResourceList(1,4,1,0,0);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == false);
		
		canDiscard = true;
		resources = new ResourceList(1,4,1,1,0);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == false);
	}

}
