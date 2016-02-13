package client.gamestate;

import client.data.RobPlayerInfo;
import shared.communication.response.GetModelResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameModel;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.TradeOffer;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public abstract class GameState {

	public GameState identifyState(String state){
		
		GameState gameState = new IsNotTurnState();
		
		if(state.equals("Discarding")) gameState = new DiscardingState();
		else if(state.equals("First Round")) gameState = new FirstRoundState();
		else if(state.equals("Is Not Turn")) gameState = new IsNotTurnState();
		else if(state.equals("Playing")) gameState = new PlayingState();
		else if(state.equals("Robbing")) gameState = new RobbingState();
		else if(state.equals("Rolling")) gameState = new RollingState();
		else if(state.equals("Second Round")) gameState = new SecondRoundState();
		
		return gameState;
	}
	
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {

		return false;
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {

		return false;
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {

		return false;
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {

		return false;
	}

	public void placeRoad(EdgeLocation edgeLoc) {

	}

	public void placeSettlement(VertexLocation vertLoc) {

	}

	public void placeCity(VertexLocation vertLoc) {

	}

	public void placeRobber(HexLocation hexLoc) {

	}
	
	public void cancelMove() {

	}

	public void playSoldierCard() {	

	}

	public void playRoadBuildingCard() {	

	}

	public void robPlayer(RobPlayerInfo victim) {	

	}
	
	public void discardCards(ResourceList list) throws IllegalArgumentException{
		
	}	
	
	public void rollNumber() throws IllegalArgumentException{

	}
	
	public void offerTrade(TradeOffer offer) throws IllegalArgumentException{
	
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
	
}
