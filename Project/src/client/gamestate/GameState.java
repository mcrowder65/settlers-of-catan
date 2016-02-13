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
	
	public boolean canPlaceRoad(EdgeLocation edgeLoc, Facade facade) throws IllegalArgumentException{

		return false;
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc, Facade facade) throws IllegalArgumentException{

		return false;
	}

	public boolean canPlaceCity(VertexLocation vertLoc, Facade facade) throws IllegalArgumentException{

		return false;
	}

	public boolean canPlaceRobber(HexLocation hexLoc, Facade facade) throws IllegalArgumentException{

		return false;
	}

	public void placeRoad(EdgeLocation edgeLoc, Facade facade) throws IllegalArgumentException{

	}

	public void placeSettlement(VertexLocation vertLoc, Facade facade) throws IllegalArgumentException{

	}

	public void placeCity(VertexLocation vertLoc, Facade facade) throws IllegalArgumentException{

	}

	public void placeRobber(HexLocation hexLoc, Facade facade) throws IllegalArgumentException{

	}
	
	public void cancelMove(Facade facade) throws IllegalArgumentException{

	}

	public void playSoldierCard(Facade facade) throws IllegalArgumentException{	

	}

	public void playRoadBuildingCard(Facade facade) throws IllegalArgumentException{	

	}

	public void robPlayer(RobPlayerInfo victim, Facade facade) throws IllegalArgumentException{	

	}
	
	public void discardCards(ResourceList list, Facade facade) throws IllegalArgumentException{
		
	}	
	
	public int rollNumber(Facade facade) throws IllegalArgumentException{
		return -1;
	}
	
	public void offerTrade(TradeOffer offer, Facade facade) throws IllegalArgumentException{
	
	}

	public boolean canOfferTrade(Facade facade) throws IllegalArgumentException {
		return false;

	}

	public boolean canMaritimeTrade(Facade facade) throws IllegalArgumentException {
		return false;
	}

	public boolean canFinishTurn(Facade facade) throws IllegalArgumentException{

		return false;
	}

	public boolean canBuyDevCard(Facade facade) throws IllegalArgumentException{

		return false;
	}

	public boolean canUseYearOfPlenty(Facade facade) throws IllegalArgumentException{

		return false;
	}

	public boolean canUseRoadBuilder(Facade facade) throws IllegalArgumentException{
		return false;
	}

	public boolean canUseSoldier(Facade facade) throws IllegalArgumentException{

		return false;

	}

	public boolean canUseMonopoly(Facade facade) throws IllegalArgumentException{
		return false;
	}

	public boolean canUseMonument(Facade facade) throws IllegalArgumentException{

		return false;
	}

	public boolean canSendChat(Facade facade) throws IllegalArgumentException{
		return true;
	}
	public boolean canAcceptTrade(TradeOffer offer,Facade facade) throws IllegalArgumentException {
		return false;
	}
	
	public boolean canRollNumber(Facade facade) throws IllegalArgumentException{
		return false;
	}
}
