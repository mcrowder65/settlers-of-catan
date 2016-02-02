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
	
	@Test
	public void canDiscardCardsTrue() {
		
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		Player player = new Player();
		ResourceList resources = new ResourceList(1,2,3,2,1);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		
		boolean canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == true);
		
		canDiscard = true;
		resources = new ResourceList(10,0,0,0,0);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == true);
		
		canDiscard = true;
		resources = new ResourceList(2,3,1,1,1);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == true);
		
		canDiscard = true;
		resources = new ResourceList(0,4,3,0,1);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == true);
		
		canDiscard = true;
		resources = new ResourceList(19,10,10,10,10);
		player.setResources(resources);
		canDiscard = facade.canDiscardCards();
		assertTrue(canDiscard == true);
	}

}
