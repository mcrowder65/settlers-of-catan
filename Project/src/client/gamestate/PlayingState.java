package client.gamestate;

import client.controller.Facade;
import shared.definitions.TradeOffer;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class PlayingState extends GameState {

	public PlayingState(){
		
	}
	
	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.canBuildRoad(edgeLoc);
	}
	
	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.canBuildSettlement(vertLoc);
	}

	@Override
	public boolean canPlaceCity(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.canBuildCity(vertLoc);
	}

	
	@Override
	public boolean canOfferTrade() throws IllegalArgumentException {
		return facade.canOfferTrade();

	}

	@Override
	public boolean canMaritimeTrade() throws IllegalArgumentException {
		return facade.canMaritimeTrade();
	}

	@Override
	public boolean canFinishTurn() throws IllegalArgumentException{
		return facade.canFinishTurn();
	}

	@Override
	public boolean canBuyDevCard() throws IllegalArgumentException{
		return facade.canBuyDevCard();
	}

	@Override
	public boolean canUseYearOfPlenty() throws IllegalArgumentException{
		return facade.canUseYearOfPlenty();
	}

	@Override
	public boolean canUseRoadBuilder() throws IllegalArgumentException{
		return facade.canUseRoadBuilder();
	}

	@Override
	public boolean canUseSoldier() throws IllegalArgumentException{
		return canUseSoldier();
	}

	@Override
	public boolean canUseMonopoly() throws IllegalArgumentException{
		return facade.canUseMonopoly();
	}

	@Override
	public boolean canUseMonument() throws IllegalArgumentException{
		return facade.canUseMonument();
	}

	@Override
	public boolean placeRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.buildRoad(edgeLoc,true);
	}

	@Override
	public boolean placeSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildSettlement(vertLoc,true);
	}

	@Override
	public boolean placeCity(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildCity(vertLoc);
	}
	
	@Override
	public boolean offerTrade(TradeOffer offer) throws IllegalArgumentException{
		return facade.offerTrade(offer);
	}
	


}
