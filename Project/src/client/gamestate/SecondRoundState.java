package client.gamestate;

import client.controller.Facade;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 * class to represent the game state during the second round of play 
 * @author Brennen
 *
 */
public class SecondRoundState extends GameState {
	
	/**
	 * constructor for this state
	 * @param facade
	 */
	public SecondRoundState(Facade facade){
		super(facade);
	}
	/**
	 * asks the facade if the player can place a road
	 * @return True/False depending on if the call was successful
	 */
	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.canBuildRoad(edgeLoc);
	}
	/**
	 * asks the facade if the player can place a settlement
	 * @return True/False depending on if the call was successful
	 */
	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.canBuildSettlement(vertLoc);
	}
	/**
	 * calls the facade to build a road
	 * @return True/False depending on if the call was successful
	 */
	@Override
	public boolean buildRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.buildRoad(edgeLoc,true);
	}
	/**
	 * calls the facade to build a settlement
	 * @return True/False depending on if the call was successful
	 */
	@Override
	public boolean buildSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildSettlement(vertLoc,true);
	}
	/**
	 * returns the text needed to update the state Panel on the gui
	 */
	@Override
	public String getGameStatePanelText() {
		return "Place Initial Pieces";
	}
	/**
	 * the game state panel should not be enabled during this state of the game
	 */
	@Override
	public boolean isGameStatePanelEnabled() {
		return false;
	}
	/**
	 * calls the facade when then player decides to finish their turn
	 * @return True/False depending on if the call was successful
	 */
	@Override
	public boolean finishTurn() {
		return facade.finishTurn();
	}
}
