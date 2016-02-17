package client.gamestate;

import client.controller.Facade;
import client.data.RobPlayerInfo;
import shared.communication.response.GetModelResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameModel;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.TradeOffer;
import shared.definitions.TurnTracker;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public abstract class GameState {

	protected Facade facade; 
	
	public GameState(Facade facade){
		this.facade = facade;
	}
	
	public GameState identifyState(TurnTracker turnTracker) {
		
		GameState gameState =  null; 
		if (!turnTracker.isMyTurn(facade.getPlayerIndex())) gameState = new IsNotTurnState(facade);
		else if(turnTracker.getStatus().equals("Discarding")) gameState = new DiscardingState(facade);
		else if(turnTracker.getStatus().equals("First Round")) gameState = new FirstRoundState(facade);
		else if(turnTracker.getStatus().equals("Playing")) gameState = new PlayingState(facade);
		else if(turnTracker.getStatus().equals("Robbing")) gameState = new RobbingState(facade);
		else if(turnTracker.getStatus().equals("Rolling")) gameState = new RollingState(facade);
		else if(turnTracker.getStatus().equals("Second Round")) gameState = new SecondRoundState(facade);
		
		return gameState;
	}
	
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{

		return false;
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) throws IllegalArgumentException{

		return false;
	}

	public boolean canPlaceCity(VertexLocation vertLoc) throws IllegalArgumentException{

		return false;
	}

	public boolean canPlaceRobber(HexLocation hexLoc) throws IllegalArgumentException{

		return false;
	}

	public boolean placeRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return false;
	}

	public boolean placeSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return false;
	}

	public boolean placeCity(VertexLocation vertLoc) throws IllegalArgumentException{
		return false;
	}

	public boolean placeRobber(HexLocation hexLoc) throws IllegalArgumentException{
		return false;
	}
	
	public boolean cancelMove() throws IllegalArgumentException{
		return false;
	}

	public boolean playSoldierCard() throws IllegalArgumentException{	
		return false;
	}

	public boolean playRoadBuildingCard() throws IllegalArgumentException{	
		return false;
	}

	public boolean robPlayer(RobPlayerInfo victim) throws IllegalArgumentException{	
		return false;
	}
	
	public boolean discardCards(ResourceList list) throws IllegalArgumentException{
		return false;
	}	
	
	public int rollNumber() throws IllegalArgumentException{
		return -1;
	}
	
	public boolean offerTrade(TradeOffer offer) throws IllegalArgumentException{
		return false;
	}

	public boolean canOfferTrade() throws IllegalArgumentException {
		return false;

	}

	public boolean canMaritimeTrade() throws IllegalArgumentException {
		return false;
	}

	public boolean canFinishTurn() throws IllegalArgumentException{

		return false;
	}

	public boolean canBuyDevCard() throws IllegalArgumentException{

		return false;
	}

	public boolean canUseYearOfPlenty() throws IllegalArgumentException{

		return false;
	}

	public boolean canUseRoadBuilder() throws IllegalArgumentException{
		return false;
	}

	public boolean canUseSoldier() throws IllegalArgumentException{

		return false;

	}

	public boolean canUseMonopoly() throws IllegalArgumentException{
		return false;
	}

	public boolean canUseMonument() throws IllegalArgumentException{

		return false;
	}

	public boolean canSendChat() throws IllegalArgumentException{
		return true;
	}
	public boolean canAcceptTrade(TradeOffer offer) throws IllegalArgumentException {
		return false;
	}
	
	public boolean canRollNumber() throws IllegalArgumentException{
		return false;
	}
	public boolean canDiscardCards() throws IllegalArgumentException{
		return false;
	}
	public abstract String getGameStatePanelText();
	
}
