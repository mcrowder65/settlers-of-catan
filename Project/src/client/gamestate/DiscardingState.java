package client.gamestate;

import client.controller.Facade;
import shared.definitions.ResourceList;

public class DiscardingState extends GameState {

	public DiscardingState(){
		
	}
	
	@Override
	public boolean discardCards(ResourceList list){
		return facade.discardCards(list);
	}
	@Override 
	public boolean canDiscardCards(){
		return facade.canDiscardCards();
	}
}
