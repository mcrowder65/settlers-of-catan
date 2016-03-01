package client.gamestate;

import client.controller.Facade;
/**
 * game state to represent when the player is rolling
 * @author Brennen
 *
 */
public class RollingState extends GameState {
	/**
	 * constructor for the rolling state
	 * @param facade
	 */
	public RollingState(Facade facade){
		super(facade);
	}
	/**
	 * rolls the number on the facade
	 * @return the number that was rolled
	 */
	@Override
	public int rollNumber() throws IllegalArgumentException{
		return facade.rollNumber();
	}
	/**
	 * asks the facade if the player can roll
	 * 
	 */
	@Override
	public boolean canRollNumber(){
		return facade.canRollNumber();
	}
	/**
	 * sets the text on the gameState panel
	 */
	@Override
	public String getGameStatePanelText() {
		return "Roll the Dice";
	}
	/**
	 * the gameState panel is disabled during this state
	 */
	@Override
	public boolean isGameStatePanelEnabled() {
		return false;
	}
}
