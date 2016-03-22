package client.gamestate;

import client.controller.Facade;
import shared.definitions.ResourceList;

/**
 * This state represents the gameState when a player is discarding cards due to the robber
 * @author Brennen
 *
 */
public class DiscardingState extends GameState {
	
	/**
	 * constructor for the DiscardingState
	 * @param facade
	 */
	public DiscardingState(Facade facade){
		super(facade);
	}
	
	/**
	 * tells the facade to discard the ResourceList given
	 * @return True/False depending on if the call was successful 
	 */
	@Override
	public boolean discardCards(ResourceList list){
		return facade.discardCards(list);
	}
	/**
	 * asks the facade if the player canDiscard any cards ie - do the have more than 7 cards
	 * @return True/False depending on if the call was successful 
	 */
	@Override 
	public boolean canDiscardCards(){
		return facade.canDiscardCards();
	}
	/**
	 * returns the string for the panel label
	 */
	@Override
	public String getGameStatePanelText() {
		return "Discard";
	}
	/**
	 * game state panel should not be enabled during this state
	 */
	@Override
	public boolean isGameStatePanelEnabled() {
		return false;
	}
	
	
}
