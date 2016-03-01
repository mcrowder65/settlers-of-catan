package client.gamestate;

import client.controller.Facade;
import client.data.RobPlayerInfo;
import shared.locations.HexLocation;

/**
 * class to represent the gameState when a player is robbing
 * @author Brennen
 *
 */
public class RobbingState extends GameState {

	/**
	 * constructor for the robbing state
	 * @param facade
	 */
	public RobbingState(Facade facade){
		super(facade);
	}
	/**
	 * returns the text needed to update the state Panel on the gui
	 */
	@Override
	public String getGameStatePanelText() {
		return "Choose who to rob";
	}
	/**
	 * the game state panel should not be enabled during this state of the game
	 */
	@Override
	public boolean isGameStatePanelEnabled() {
		return false;
	}
	/**
	 * tells the server to place a robber on a given hex and rob a certain victim
	 */
	@Override
	public boolean placeRobber(HexLocation hexLoc, RobPlayerInfo victim) throws IllegalArgumentException
	{
		//makes sure the victim to be robbed is not null or that they actually have resources to rob
		if (victim == null || victim.getNumCards() == 0)
			return facade.placeRobber(-1, hexLoc);
		else
			return facade.placeRobber(victim.getPlayerIndex(), hexLoc);
		
	}
	/**
	 * asks the facade if the robber can be placed on a certain hex location
	 * @return True or false
	 */
	@Override
	public boolean canPlaceRobber(HexLocation hexLoc) {
		return facade.canPlaceRobber(hexLoc);
	}
}
