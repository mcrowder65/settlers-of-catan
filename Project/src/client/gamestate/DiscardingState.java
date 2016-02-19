package client.gamestate;

import client.controller.Facade;
import shared.definitions.ResourceList;

public class DiscardingState extends GameState {

	public DiscardingState(Facade facade){
		super(facade);
	}
	
	@Override
	public boolean discardCards(ResourceList list){
		return facade.discardCards(list);
	}
	@Override 
	public boolean canDiscardCards(){
		return facade.canDiscardCards();
	}
	@Override
	public String getGameStatePanelText() {
		return "Discard";
	}
	@Override
	public boolean isGameStatePanelEnabled() {
		return false;
	}
}
