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

/**
 * class to represent the gameState when it is the player's turn and they can play
 * @author Brennen
 *
 */
public class PlayingState extends GameState {
	
	/**
	 * constructor for the Playing State
	 * @param facade
	 */
	public PlayingState(Facade facade){
		super(facade);
	}
	
	/**
	 * asks the facade can play a monopoly card
	 * @return True or false 
	 */
	@Override
	public boolean useMonopoly(ResourceType resource) {
		return facade.playMonopoly(resource);
	}
	/**
	 * asks the facade can play a monument card
	 * @return True or false 
	 */
	@Override
	public boolean playMonument(){
		return facade.playMonument();
	}
	/**
	 * gets a list of all the ports that the player has
	 * @return list of ports
	 */
	@Override
	public List<Port>getPersonalPorts(){
		return facade.getPersonalPorts();
	}
	/**
	 * asks the facade can play a YOP card
	 * @return True or false 
	 */
	@Override
	public boolean playYearOfPlenty(ResourceType resource1, ResourceType resource2) {
		return facade.playYearOfPlenty(resource1, resource2);
	}
	/**
	 * asks the facade can place a road at an edge location
	 * @return True or false 
	 */
	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.canBuildRoad(edgeLoc);
	}
	/**
	 * tells the facade to buy a dev card
	 * @return True or false 
	 */
	@Override
	public boolean buyDevCard() throws IllegalArgumentException{
		return facade.buyDevCard();
	}
	/**
	 * tells the facade to play the roadBuilding card
	 * @return True or false 
	 */
	@Override
	public boolean playRoadBuildingCard(EdgeLocation road1, EdgeLocation road2){	
		return facade.playRoadBuilding(road1,road2);
	}
	/**
	 * asks the facade if the player can lay a settlement in a specific location
	 * @return True or false 
	 */
	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.canBuildSettlement(vertLoc);
	}
	/**
	 * asks the facade if the player can lay a city in a specific location
	 * @return True or false 
	 */
	@Override
	public boolean canPlaceCity(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.canBuildCity(vertLoc);
	}
	/**
	 * asks the facade if the player can lay a road in a specific location via the dev card
	 * @return True or false 
	 */
	@Override
	public boolean canPlaceRoadDevCard(EdgeLocation location){
		return facade.canUseRoadBuilderPlacement(location);
	}
	/**
	 * places the road on the local model - for the road builder dev card
	 * @return True or false 
	 */
	@Override
	public boolean placeRoadLocally(EdgeLocation location){
		return facade.placeRoadBuilder(location);
	}
	/**
	 * asks the facade if the player can offer a trade
	 * @return True or false 
	 */
	@Override
	public boolean canOfferTrade() throws IllegalArgumentException {
		return facade.canOfferTrade();

	}
	/**
	 * asks the facade if the player can do maritimeTrade
	 * @return True or false 
	 */
	@Override
	public boolean canMaritimeTrade() throws IllegalArgumentException {
		return facade.canMaritimeTrade();
	}
	/**
	 * asks the facade if the player can finish their turn
	 * @return True or false 
	 */
	@Override
	public boolean canFinishTurn() throws IllegalArgumentException{
		return facade.canFinishTurn();
	}
	/**
	 * tells the facade to set local model to null when player cancels
	 * @return True or false 
	 */
	@Override
	public boolean cancelMove() throws IllegalStateException{
		facade.NullifyTemp();
		return true;
	}
	/**
	 * asks the facade if the player can buy a dev card
	 * @return True or false 
	 */
	@Override
	public boolean canBuyDevCard() throws IllegalArgumentException{
		return facade.canBuyDevCard();
	}
	/**
	 * asks the facade if the player can use YOP
	 * @return True or false 
	 */
	@Override
	public boolean canUseYearOfPlenty() throws IllegalArgumentException{
		return facade.canUseYearOfPlenty();
	}
	/**
	 * asks the facade if the player can use roadBuilder
	 * @return True or false 
	 */
	@Override
	public boolean canUseRoadBuilder() throws IllegalArgumentException{
		return facade.canUseRoadBuilder();
	}
	/**
	 * asks the facade if the player can play a soldier card
	 * @return True or false 
	 */
	@Override
	public boolean canUseSoldier() throws IllegalArgumentException{
		return facade.canUseSoldier();
	}

	/**
	 * asks the facade if the player can use a monopoly card
	 * @return True or false 
	 */
	@Override
	public boolean canUseMonopoly() throws IllegalArgumentException{
		return facade.canUseMonopoly();
	}

	/**
	 * asks the facade if the player can play a monopoly card
	 * @return True or false 
	 */
	@Override
	public boolean canUseMonument() throws IllegalArgumentException{
		return facade.canUseMonument();
	}

	/**
	 * tells the facade to build a road at a certain location
	 * @return True or false 
	 */
	@Override
	public boolean buildRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.buildRoad(edgeLoc,false);
	}
	/**
	 * tells the facade to build a settlement at a certain location
	 * @return True or false 
	 */
	@Override
	public boolean buildSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildSettlement(vertLoc,false);
	}
	/**
	 * tells the facade to build a city at a certain location
	 * @return True or false 
	 */
	@Override
	public boolean buildCity(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildCity(vertLoc);
	}
	/**
	 * tells the facade to offer a trade to another player
	 * @return True or false 
	 */
	@Override
	public boolean offerTrade(TradeOffer offer) throws IllegalArgumentException{
		return facade.offerTrade(offer);
	}
	/**
	 * tells the facade to finish the players turn
	 * @return True or false 
	 */
	@Override
	public boolean finishTurn() {
		return facade.finishTurn();
	}
	/**
	 * returns the text needed to update the state Panel on the gui
	 */
	@Override
	public String getGameStatePanelText() {
		return "Finish Turn";
	}
	/**
	 * the game state panel should not be enabled during this state of the game
	 */
	@Override
	public boolean isGameStatePanelEnabled() {
		return true;
	}
	/**
	 * tells the facade to play a soldier card
	 * @return True or false 
	 */
	@Override
	public boolean playSoldierCard(HexLocation hexLoc, RobPlayerInfo victim) throws IllegalArgumentException
	{
		//checks to make sure that a null vicitm or a victim w/ zero cards doesn't get sent in 
		if (victim == null || victim.getNumCards() == 0)
			return facade.playSoldier(-1, hexLoc);
		else
			return facade.playSoldier(victim.getPlayerIndex(), hexLoc); 
		
	}
	/**
	 * asks the facade if the player can place a robber on a given hexLoc
	 * @return True or false 
	 */
	@Override
	public boolean canPlaceRobber(HexLocation hexLoc) {
		return facade.canPlaceRobber(hexLoc);
	}
	/**
	 * asks the facade if the player can buy a road
	 * @return True or false 
	 */
	@Override
	public boolean canBuyRoad() {
		return facade.canBuyRoad();
	}
	/**
	 * asks the facade if the player can buy a settlement
	 * @return True or false 
	 */
	@Override
	public boolean canBuySettlement() {
		return facade.canBuySettlement();
	}
	/**
	 * asks the facade if the player can buy a city
	 * @return True or false 
	 */
	@Override
	public boolean canBuyCity() {
		return facade.canBuyCity();
	}
	
}
