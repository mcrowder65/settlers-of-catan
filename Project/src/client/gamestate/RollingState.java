package client.gamestate;

import client.controller.Facade;

public class RollingState extends GameState {

	public RollingState(){
		
	}
	
	@Override
	public int rollNumber() throws IllegalArgumentException{
		return facade.rollNumber();
	}
	
	@Override
	public boolean canRollNumber(){
		return facade.canRollNumber();
	}
	
}
