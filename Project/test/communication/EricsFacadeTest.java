package communication;

import org.junit.Test;

import client.controller.Facade;
import client.data.GameManager;
import shared.definitions.DevCardList;
import shared.definitions.GameModel;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.TurnTracker;

import static org.junit.Assert.*;

public class EricsFacadeTest {

	@Test
	public void testCanBuyDevCard() {
		GameManager game = new GameManager();
		Facade facade = new Facade(game);
		GameModel gameModel = new GameModel();
		gameModel.setTurnTracker(new TurnTracker());
		gameModel.getTurnTracker().setStatus("Playing");
		Player player = new Player();
		ResourceList resources = new ResourceList(0,1,1,1,0);
		game.updateModel(gameModel);
		
		player.setPlayerID(0);
		player.setResources(resources);
		gameModel.setLocalPlayer(player);
		gameModel.setDeck(new DevCardList(0,0,0,0,1));
		
		boolean result;
		
		//Good
		result = facade.canBuyDevCard(0);
		assertTrue(result);
		
		//Insufficient resources
		resources.setOre(0);
		gameModel.getLocalPlayer().setResources(resources);
		result = facade.canBuyDevCard(0);
		assertFalse(result);
		resources.setOre(1);
		
		resources.setWheat(0);
		gameModel.getLocalPlayer().setResources(resources);
		result = facade.canBuyDevCard(0);
		assertFalse(result);
		resources.setWheat(1);
		
		resources.setSheep(0);
		gameModel.getLocalPlayer().setResources(resources);
		result = facade.canBuyDevCard(0);
		assertFalse(result);
		resources.setSheep(1);
		
		//Insufficient deck
		gameModel.setDeck(new DevCardList(0,0,0,0,0));
		result = facade.canBuyDevCard(0);
		assertFalse(result);
		gameModel.setDeck(new DevCardList(0,0,0,0,1));
		
		
		//Wrong status
		gameModel.getTurnTracker().setStatus("Roll");
		result = facade.canBuyDevCard(0);
		assertFalse(result);
		gameModel.getTurnTracker().setStatus("Playing");
		
		//Not your turn
		gameModel.getTurnTracker().setCurrentTurn(3);
		result = facade.canBuyDevCard(0);
		assertFalse(result);
		gameModel.getTurnTracker().setCurrentTurn(0);
		
		
	}
}
