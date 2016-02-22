package client.gamestate;

import java.util.List;
import java.util.Observer;

import client.controller.Facade;
import client.data.RobPlayerInfo;
import shared.communication.response.GetModelResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameModel;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
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
		if(turnTracker.getStatus().equals("Discarding")) gameState = new DiscardingState(facade);
		else if (!turnTracker.isMyTurn(facade.getPlayerIndex())) gameState = new IsNotTurnState(facade);
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
	
	public boolean canPlaceRoadDevCard(EdgeLocation location){
		return false;
	}
	
	public boolean placeRoadLocally(EdgeLocation location){
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

	public boolean buildRoad(EdgeLocation edgeLoc) throws IllegalArgumentException,IllegalStateException{
		throw new IllegalStateException();
	}

	public boolean buildSettlement(VertexLocation vertLoc) throws IllegalArgumentException, IllegalStateException{
		throw new IllegalStateException();
	}

	public boolean buildCity(VertexLocation vertLoc) throws IllegalArgumentException, IllegalStateException{
		throw new IllegalStateException();
	}

	
	public boolean cancelMove() throws IllegalStateException{
		throw new IllegalStateException();
	}

	public boolean playSoldierCard(HexLocation hexLoc, RobPlayerInfo victim) throws IllegalStateException{	
		throw new IllegalStateException();
	}

	public boolean playRoadBuildingCard(EdgeLocation loc1, EdgeLocation loc2) throws IllegalStateException{	
		throw new IllegalStateException();
	}

	public boolean placeRobber(HexLocation hexLoc, RobPlayerInfo victim) throws IllegalArgumentException, IllegalStateException{	
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
	
	public boolean buyDevCard(){
		return false;
	}
	
	public boolean canUseYearOfPlenty() {

		return false;
	}
	
	public boolean playYearOfPlenty(ResourceType resource1, ResourceType resource2) {
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
	
	public boolean useMonopoly(ResourceType resource) {
		return false;
	}

	public boolean canUseMonument() {

		return false;
	}
	
	public boolean playMonument(){
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
	public abstract boolean isGameStatePanelEnabled();
	
	public void addObserver(Observer o) {
		facade.addObserver(o);
	}
	public void deleteObserver(Observer o) {
		facade.deleteObserver(o);
	}

	public int getPlayerId() {
		return facade.getPlayerId();
	}
	public int getPlayerIndex() {
		return facade.getPlayerIndex();
	}
	public CatanColor getPlayerColor() {
		return facade.getPlayerColor();
	}
	public GameModel fetchModel() {
		return facade.fetchModel();
	}
	public RobPlayerInfo[] getRobbablePlayers(HexLocation hexLoc) {
		return facade.getRobbablePlayers(hexLoc, getPlayerIndex());
	}
	public ResourceList getPlayerResources() {
		return facade.getResources(getPlayerIndex());
	}
	
}
