package client.gamestate;

import client.controller.Facade;
import shared.definitions.TradeOffer;

public class IsNotTurnState extends GameState {
	
	public IsNotTurnState(Facade facade){
		super(facade);
	}
	
	@Override
	public boolean canAcceptTrade(TradeOffer offer){
		return facade.canAcceptTrade(offer);
	}
	@Override
	public String getGameStatePanelText() {
		return "Wait for your Turn";
	}
	@Override
	public boolean isGameStatePanelEnabled() {
		return false;
	}
}
