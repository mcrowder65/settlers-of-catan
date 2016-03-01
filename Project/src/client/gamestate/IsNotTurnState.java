package client.gamestate;

import client.controller.Facade;
import shared.definitions.TradeOffer;

/**
 * class that represents the gameState when the player is waiting for other players to play - its not the players turn
 * @author Brennen
 *
 */
public class IsNotTurnState extends GameState {
	
	/**
	 * constructor for the IsNotTurnState 
	 * @param facade
	 */
	public IsNotTurnState(Facade facade){
		super(facade);
	}
	
	/**
	 * asks the facade if the player can accept the trade offer that was proposed
	 * @return True or False 
	 */
	@Override
	public boolean canAcceptTrade(TradeOffer offer){
		return facade.canAcceptTrade(offer);
	}
	/**
	 * returns the text needed to update the state Panel on the gui
	 */
	@Override
	public String getGameStatePanelText() {
		return "Wait for your Turn";
	}
	/**
	 * the game state panel should not be enabled during this state of the game
	 */
	@Override
	public boolean isGameStatePanelEnabled() {
		return false;
	}
}
