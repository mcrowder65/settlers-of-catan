package client.gamestate;

import java.util.ArrayList;
import java.util.List;

import client.controller.Facade;
import client.data.RobPlayerInfo;
import shared.definitions.Port;
import shared.definitions.ResourceType;
import shared.definitions.TradeOffer;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class PlayingState extends GameState {

	
	
	public PlayingState(Facade facade){
		super(facade);
	}
	
	@Override
	public boolean useMonopoly(ResourceType resource) {
		return facade.playMonopoly(resource);
	}
	
	@Override
	public boolean playMonument(){
		return facade.playMonument();
	}
	
	@Override
	public List<Port>getPersonalPorts(){
		return facade.getPersonalPorts();
	}
	
	@Override
	public boolean playYearOfPlenty(ResourceType resource1, ResourceType resource2) {
		return facade.playYearOfPlenty(resource1, resource2);
	}
	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.canBuildRoad(edgeLoc);
	}
	
	@Override
	public boolean buyDevCard() throws IllegalArgumentException{
		return facade.buyDevCard();
	}
	
	@Override
	public boolean playRoadBuildingCard(EdgeLocation road1, EdgeLocation road2){	
		return facade.playRoadBuilding(road1,road2);
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
	public boolean placeRoadLocally(EdgeLocation location){
		return facade.placeRoadBuilder(location);
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
	public boolean cancelMove() throws IllegalStateException{
		facade.NullifyTemp();
		return true;
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
		return facade.canUseSoldier();
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
	public boolean buildRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.buildRoad(edgeLoc,false);
	}

	@Override
	public boolean buildSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildSettlement(vertLoc,false);
	}

	@Override
	public boolean buildCity(VertexLocation vertLoc) throws IllegalArgumentException{
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
	
	@Override
	public boolean playSoldierCard(HexLocation hexLoc, RobPlayerInfo victim) throws IllegalArgumentException
	{
		if (victim == null || victim.getNumCards() == 0)
			return facade.playSoldier(-1, hexLoc);
		else
			return facade.playSoldier(victim.getPlayerIndex(), hexLoc);
		
	}
	
	@Override
	public boolean canPlaceRobber(HexLocation hexLoc) {
		return facade.canPlaceRobber(hexLoc);
	}
	
	@Override
	public boolean canBuyRoad() {
		return facade.canBuyRoad();
	}
	
	@Override
	public boolean canBuySettlement() {
		return facade.canBuySettlement();
	}
	
	@Override
	public boolean canBuyCity() {
		return facade.canBuyCity();
	}
	
}
