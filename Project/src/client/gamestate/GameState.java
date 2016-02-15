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

	public void placeRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{

	}

	public void placeSettlement(VertexLocation vertLoc) throws IllegalArgumentException{

	}

	public void placeCity(VertexLocation vertLoc) throws IllegalArgumentException{

	}

	public void placeRobber(HexLocation hexLoc) throws IllegalArgumentException{

	}
	
	public void cancelMove() throws IllegalArgumentException{

	}

	public void playSoldierCard() throws IllegalArgumentException{	

	}

	public void playRoadBuildingCard() throws IllegalArgumentException{	

	}

	public void robPlayer(RobPlayerInfo victim) throws IllegalArgumentException{	

	}
	
	public void discardCards(ResourceList list) throws IllegalArgumentException{
		
	}	
	
	public int rollNumber() throws IllegalArgumentException{
		return -1;
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
	
	public boolean canRollNumber() throws IllegalArgumentException{
		return false;
	}
}
