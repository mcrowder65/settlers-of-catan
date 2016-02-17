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
		else if(turnTracker.getStatus().equals("FirstRound")) gameState = new FirstRoundState(facade);
		else if(turnTracker.getStatus().equals("Playing")) gameState = new PlayingState(facade);
		else if(turnTracker.getStatus().equals("Robbing")) gameState = new RobbingState(facade);
		else if(turnTracker.getStatus().equals("Rolling")) gameState = new RollingState(facade);
		else if(turnTracker.getStatus().equals("SecondRound")) gameState = new SecondRoundState(facade);
		else
			System.out.println(turnTracker.getStatus() + " was not recognized by identifyState()");
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

	public boolean placeRoad(EdgeLocation edgeLoc) throws IllegalArgumentException,IllegalStateException{
		throw new IllegalStateException();
	}

	public boolean placeSettlement(VertexLocation vertLoc) throws IllegalArgumentException, IllegalStateException{
		throw new IllegalStateException();
	}

	public boolean placeCity(VertexLocation vertLoc) throws IllegalArgumentException, IllegalStateException{
		throw new IllegalStateException();
	}

	public boolean placeRobber(HexLocation hexLoc) throws IllegalArgumentException, IllegalStateException{
		throw new IllegalStateException();
	}
	
	public boolean cancelMove() throws IllegalStateException{
		throw new IllegalStateException();
	}

	public boolean playSoldierCard() throws IllegalStateException{	
		throw new IllegalStateException();
	}

	public boolean playRoadBuildingCard() throws IllegalStateException{	
		throw new IllegalStateException();
	}

	public boolean robPlayer(RobPlayerInfo victim) throws IllegalArgumentException, IllegalStateException{	
		throw new IllegalStateException();
	}
	
	public boolean discardCards(ResourceList list) throws IllegalArgumentException, IllegalStateException{
		throw new IllegalStateException();
	}	
	
	public int rollNumber() throws IllegalStateException{
		throw new IllegalStateException();
	}
	
	public boolean offerTrade(TradeOffer offer) throws IllegalStateException, IllegalArgumentException{
		throw new IllegalStateException();
	}

	public boolean canOfferTrade()  {
		return false;

	}

	public boolean canMaritimeTrade() {
		return false;
	}

	public boolean canFinishTurn(){

		return false;
	}

	public boolean canBuyDevCard() {

		return false;
	}

	public boolean canUseYearOfPlenty() {

		return false;
	}

	public boolean canUseRoadBuilder() {
		return false;
	}

	public boolean canUseSoldier() {

		return false;

	}

	public boolean canUseMonopoly() {
		return false;
	}

	public boolean canUseMonument() {

		return false;
	}

	public boolean canSendChat() {
		return true;
	}
	public boolean canAcceptTrade(TradeOffer offer) throws IllegalArgumentException {
		return false;
	}
	
	public boolean canRollNumber() {
		return false;
	}
	public boolean canDiscardCards() {
		return false;
	}
	public boolean finishTurn() throws IllegalStateException {
		throw new IllegalStateException();
	}
	public abstract String getGameStatePanelText();
	
}
