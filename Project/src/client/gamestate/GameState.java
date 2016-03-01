package client.gamestate;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import client.controller.Facade;
import client.data.RobPlayerInfo;
import shared.communication.response.GetModelResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameModel;
import shared.definitions.Player;
import shared.definitions.Port;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.definitions.TradeOffer;
import shared.definitions.TurnTracker;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * The abstract class of states the player & game is in.
 * By default, all non-overrided can-dos return false and actions throw an error.
 *
 */
public abstract class GameState {

	protected Facade facade; 
	
	/**
	 * constructor - sets the facade
	 * @param facade
	 */
	public GameState(Facade facade){
		this.facade = facade;
	}
	
	/**
	 * sets and returns the proper gameState as indicated by thye turnTracker
	 * @param turnTracker
	 * @return
	 */
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
	
	/**
	 * stub for canPlaceRoad - should be overridden in subClasses
	 * @param edgeLoc
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{

		return false;
	}
	
	/**
	 *stub for canPlaceRoad-RoadBuilder - should be overridden in subClasses
	 * @param location
	 * @return
	 */
	public boolean canPlaceRoadDevCard(EdgeLocation location){
		return false;
	}
	
	/**
	 *stub for placing road on local model - should be overridden in subClasses
	 * @param location
	 * @return
	 */
	public boolean placeRoadLocally(EdgeLocation location){
		return false;
	}

	/**
	 * stub for canPlaceSettlement - should be overridden in subClasses
	 * @param vertLoc
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean canPlaceSettlement(VertexLocation vertLoc) throws IllegalArgumentException{

		return false;
	}
	

	/**
	 * stub for canPlaceCity - should be overridden in subClasses
	 * @param vertLoc
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean canPlaceCity(VertexLocation vertLoc) throws IllegalArgumentException{

		return false;
	}

	/**
	 * stub for canPlaceRobber - should be overridden in subClasses
	 * @param hexLoc
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean canPlaceRobber(HexLocation hexLoc) throws IllegalArgumentException{
		return false;
	}

	/**
	 * stub for buildRoad - should be overridden in subClasses 
	 * @param edgeLoc
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	public boolean buildRoad(EdgeLocation edgeLoc) throws IllegalArgumentException,IllegalStateException{
		throw new IllegalStateException(); //throws and exception if wrong state
	}

	public boolean buildSettlement(VertexLocation vertLoc) throws IllegalArgumentException, IllegalStateException{
		throw new IllegalStateException(); //throws and exception if wrong state
	}

	public boolean buildCity(VertexLocation vertLoc) throws IllegalArgumentException, IllegalStateException{
		throw new IllegalStateException(); //throws and exception if wrong state
	}
	/**
	 * returns a list of all ports owned by a player
	 * @return ports
	 */
	public List<Port>getPersonalPorts(){
		List<Port> ports = new ArrayList();
		return ports;
	}
	
	/**
	 * getsets the local gameModel to null
	 * @return
	 * @throws IllegalStateException
	 */
	public boolean cancelMove() throws IllegalStateException{
		throw new IllegalStateException(); //throws and exception if wrong state
	}
	
	/**
	 * playsSoldier card on server - overridden by subclasses
	 * @param hexLoc
	 * @param victim
	 * @return
	 * @throws IllegalStateException
	 */
	public boolean playSoldierCard(HexLocation hexLoc, RobPlayerInfo victim) throws IllegalStateException{	
		throw new IllegalStateException(); //throws and exception if wrong state
	}

	/**
	 * playRoadBuilderCard on server - overridden by subclasses
	 * @param loc1
	 * @param loc2
	 * @return
	 * @throws IllegalStateException
	 */
	public boolean playRoadBuildingCard(EdgeLocation loc1, EdgeLocation loc2) throws IllegalStateException{	
		throw new IllegalStateException(); //throws and exception if wrong state
	}

	/**
	 * places the robber on server - overridden by subclasses
	 * @param hexLoc
	 * @param victim
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	public boolean placeRobber(HexLocation hexLoc, RobPlayerInfo victim) throws IllegalArgumentException, IllegalStateException{	
		throw new IllegalStateException(); //throws and exception if wrong state
	}
	
	/**
	 * discards cards on server - overridden by subclasses
	 * @param list
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	public boolean discardCards(ResourceList list) throws IllegalArgumentException, IllegalStateException{
		throw new IllegalStateException(); //throws and exception if wrong state
	}	
	
	/**
	 * rolls number on facade - overridden by subclasses
	 * @return
	 * @throws IllegalStateException
	 */
	public int rollNumber() throws IllegalStateException{
		throw new IllegalStateException(); //throws and exception if wrong state
	}
	/**
	 * offerTrade on server - overridden by subclasses
	 * @param offer
	 * @return
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 */
	public boolean offerTrade(TradeOffer offer) throws IllegalStateException, IllegalArgumentException{
		throw new IllegalStateException(); //throws and exception if wrong state
	}

	/**
	 * overridden by subclasses - calls facade
	 * @return false
	 */
	public boolean canOfferTrade()  {
		return false;

	}

	/**
	 *  overridden by subclasses - calls facade
	 * @return false
	 */
	public boolean canMaritimeTrade() {
		return false;
	}

	/**
	 *  overridden by subclasses - calls facade
	 * @return false
	 */
	public boolean canFinishTurn(){

		return false;
	}

	/**
	 *  overridden by subclasses - calls facade
	 * @return false
	 */
	public boolean canBuyDevCard() {

		return false;
	}
	
	/**
	 * overridden by subclasses - calls facade
	 * @return
	 */
	public boolean buyDevCard(){
		return false;
	}
	
	/**
	 * overridden by subclasses - calls facade
	 * @return
	 */
	public boolean canUseYearOfPlenty() {

		return false;
	}
	
	/**
	 * overridden by subclasses - calls facade
	 * @param resource1
	 * @param resource2
	 * @return false
	 */
	public boolean playYearOfPlenty(ResourceType resource1, ResourceType resource2) {
		return false;
	}

	/**
	 * overridden by subclasses - calls facade
	 * @return false
	 */
	public boolean canUseRoadBuilder() {
		return false;
	}

	/**
	 * overridden by subclasses - calls facade
	 * @return
	 */
	public boolean canUseSoldier() {

		return false;

	}

	/**
	 * overridden by subclasses - calls facade
	 * @return
	 */
	public boolean canUseMonopoly() {
		return false;
	}
	
	/**
	 * overridden by subclasses - calls facade
	 * @param resource
	 * @return
	 */
	public boolean useMonopoly(ResourceType resource) {
		return false;
	}

	/**
	 * overridden by subclasses - calls facade
	 * @return
	 */
	public boolean canUseMonument() {

		return false;
	}
	/**
	 * overridden by subclasses - calls facade
	 * @return
	 */
	public boolean playMonument(){
		return false;
	}
	/**
	 * overridden by subclasses - calls facade
	 * @return
	 */
	public boolean canSendChat() {
		return true;
	}
	/**
	 * overridden by subclasses - calls facade
	 * @param offer
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean canAcceptTrade(TradeOffer offer) throws IllegalArgumentException {
		return false;
	}
	/**
	 * overridden by subclasses - calls facade
	 * @return
	 */
	public boolean canRollNumber() {
		return false;
	}
	/**
	 * overridden by subclasses - calls facade
	 * @return
	 */
	public boolean canDiscardCards() {
		return false;
	}
	/**
	 * overridden by subclasses - calls facade
	 * @return
	 * @throws IllegalStateException
	 */
	public boolean finishTurn() throws IllegalStateException {
		throw new IllegalStateException(); //if current state is off
	}
	public abstract String getGameStatePanelText();
	public abstract boolean isGameStatePanelEnabled();
	
	/**
	 * adds observer to facade
	 * @param o
	 */
	public void addObserver(Observer o) {
		facade.addObserver(o);
	}
	/**
	 * deletes observer from facade
	 * @param o
	 */
	public void deleteObserver(Observer o) {
		facade.deleteObserver(o);
	}
	/**
	 * gets playerID from the facade
	 * @return
	 */
	public int getPlayerId() {
		return facade.getPlayerId();
	}
	/**
	 * gets playerIndex from the facade
	 * @return
	 */
	public int getPlayerIndex() {
		return facade.getPlayerIndex();
	}
	/**
	 * gets the player's color from the facade
	 * @return
	 */
	public CatanColor getPlayerColor() {
		return facade.getPlayerColor();
	}
	/**
	 * gets the model from the facade
	 * @return
	 */
	public GameModel fetchModel() {
		return facade.fetchModel();
	}
	/**
	 * gets all the players that can be robbed from the facade
	 * @param hexLoc
	 * @return
	 */
	public RobPlayerInfo[] getRobbablePlayers(HexLocation hexLoc) {
		return facade.getRobbablePlayers(hexLoc, getPlayerIndex());
	}
	/**
	 * gets a players resource given their index
	 * @return
	 */
	public ResourceList getPlayerResources() {
		return facade.getResources(getPlayerIndex());
	}
	
	/**
	 * method is overridden in the subclasses
	 * @return
	 */
	public boolean canBuyRoad() {
		return false;
	}
	
	/**
	 * method is overriden in the subclasses
	 * @return
	 */
	public boolean canBuySettlement() {
		return false;
	}
	
	/**
	 * method is overriden in the subclasses
	 * @return
	 */
	public boolean canBuyCity() {
		return false;
	}
	
}
