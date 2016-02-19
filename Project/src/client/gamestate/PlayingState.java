package client.gamestate;

import client.controller.Facade;
import shared.definitions.TradeOffer;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class PlayingState extends GameState {

	private EdgeLocation road1;
	private EdgeLocation road2;
	
	public PlayingState(Facade facade){
		super(facade);
	}
	
	
	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.canBuildRoad(edgeLoc);
	}
	
	@Override
	public boolean playRoadBuildingCard(){	
		if(road1 == null || road2 == null){
			return false;
		}
		boolean worked = facade.playRoadBuilding(road1,road2);
		if(worked == false){
			return false;
		}
		facade.deleteRoad(road1);
		facade.deleteRoad(road2);
		road1 = null;
		road2 = null;
		return true;
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
	public boolean canPlaceRoadDevCard(EdgeLocation location){
		return facade.canUseRoadBuilderPlacement(location);
	}
	@Override
	public boolean placeRoadDevCard(EdgeLocation location){
		if(facade.placeRoadBuilder(location) == true){
			if(road1 == null){
				road1 = location;
			}
			else if(road2 == null){
				road2 = location;
			}
			return true;
		}
		
		return false;
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
		return facade.buildRoad(edgeLoc,false);
	}

	@Override
	public boolean placeSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildSettlement(vertLoc,false);
	}

	@Override
	public boolean placeCity(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildCity(vertLoc);
	}
	
	@Override
	public boolean offerTrade(TradeOffer offer) throws IllegalArgumentException{
		return facade.offerTrade(offer);
	}
	
	@Override
	public boolean finishTurn() {
		return facade.finishTurn();
	}
	
	@Override
	public String getGameStatePanelText() {
		return "Finish Turn";
	}
	@Override
	public boolean isGameStatePanelEnabled() {
		return true;
	}
}
