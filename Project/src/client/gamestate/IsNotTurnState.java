package client.gamestate;

import client.controller.Facade;
import shared.definitions.TradeOffer;

public class IsNotTurnState extends GameState {
	
	public IsNotTurnState(){
		
	}
	
	@Override
	public boolean canAcceptTrade(TradeOffer offer){
		return facade.canAcceptTrade(offer);
	}
}
