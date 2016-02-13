package client.gamestate;

import client.controller.Facade;

public class RollingState extends GameState {

	public RollingState(){
		
	}
	
	@Override
	public int rollNumber(Facade facade) throws IllegalArgumentException{
		return facade.rollNumber();
	}
	
	@Override
	public boolean canRollNumber(Facade facade){
		return facade.canRollNumber();
	}
	
}
