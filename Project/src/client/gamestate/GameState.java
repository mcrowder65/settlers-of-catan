package client.gamestate;

import client.controller.Facade;
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

	protected Facade facade;
	
	public GameState identifyState(String state) {
		
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
}
