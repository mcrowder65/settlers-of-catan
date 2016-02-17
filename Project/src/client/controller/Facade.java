package client.controller;

import shared.locations.*;

import java.util.List;
import java.util.Observer;
import java.util.Random;

import client.communication.IProxy;
import client.data.GameInfo;
import client.data.GameManager;
import shared.communication.*;
import shared.communication.response.*;
import shared.definitions.*;

public class Facade {

	private GameManager game;
	private IProxy proxy;
	private int playerId;

	public Facade(IProxy proxy, int pollingInterval, GameManager game)
	{
		this.proxy = proxy;
		this.game = game;
	
	}
	public Facade(IProxy proxy, int pollingInterval)
	{
		this.proxy = proxy;
		game = new GameManager(proxy, pollingInterval);
	}
	/**
	 * This constructor is used for testing only!
	 * @param gameMan
	 */
	public Facade (GameManager gameMan) {
		this.game = gameMan;
	}

	public int getPlayerIndex() {
		return game.getModel().getLocalIndex(playerId);
	}
	public int getPlayerId() {
		return game.getProxy().getPlayerId();
	}
	public void startPoller() {
		game.getPoller().startPolling();
	}
	public void stopPoller() {
		game.getPoller().stopPolling();
	}
	public void addObserver(Observer o) {
		game.addObserver(o);
	}
	public void deleteObserver(Observer o) {
		game.deleteObserver(o);
	}
	/**
	 * Allows a user to log into the server.
	 * @param username, password
	 * @return True if the user successfully logged in.
	 */
	public boolean login(String username, String password) throws IllegalArgumentException{
		Response response = proxy.login(username, password);
		playerId = proxy.getPlayerId();
		return response.isSuccess();
	}

	/**
	 * Allows the user to create an account and register
	 * @param username, password
	 * @return True if the user successfully registered.
	 */
	public boolean register(String username, String password) throws IllegalArgumentException{
		Response response = proxy.register(username, password);
		playerId = proxy.getPlayerId();
		return response.isSuccess();
	}

	/**
	 * This method returns information(List) of all the existing games.
	 * @return An array of GameInfo with all the information for the existing games.
	 */
	public GameInfo[] listGames() throws IllegalArgumentException{
		ListGamesResponse response = proxy.listGames();
		return response.getGames();
	}

	public List<AIType> listAI() {
		ListAIResponse response = proxy.listAI();
		return response.getAITypes();
	}
	/**
	 * This method allows the user to join an existing game.
	 * @param gameId- Id of the game.
	 * @return True if the user joined successfully a game.
	 */
	public boolean joinGame(int gameId, CatanColor color) throws IllegalArgumentException{
		Response response = proxy.joinGame(gameId, color);
		return response.isSuccess();
	}

	/**
	 * This method allows the user to create a new game.
	 * @param name - Name of the game
	 * @return the Game Id.
	 */
	public int createGame(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) throws IllegalArgumentException{
		CreateGameResponse response = proxy.createGame(name, randomTiles, randomNumbers, randomPorts);
		return response.isSuccess() ? response.getGame().getId() : -1;
	}

	/**
	 * This method allows the user to quit the game.
	 */
	public void quit() throws IllegalArgumentException{

	}


	/**
	 * The user selects cards to be discarded to leave him with a total of 7 cards
	 * @param list - List of cards that the user will be discarding
	 * @return True if the cards were discarded successfully
	 */
	public boolean discardCards(ResourceList list) throws IllegalArgumentException{
		GetModelResponse response = proxy.discardCards(game.getModel().getLocalIndex(playerId), list);
		return response.isSuccess();
	}	

	/**
	 * This method allows the user to roll the dice
	 * @return The values rolled by the dice
	 */
	public int rollNumber() throws IllegalArgumentException{
		int result = roll() + roll();
		GetModelResponse response = proxy.rollNumber(game.getModel().getLocalIndex(playerId),result);
		return response.isSuccess() == true ? result : -1;
	}
	public int roll() throws IllegalArgumentException{
		return new Random().nextInt((6 - 1) + 1) + 1;
	}
	
	public boolean addAI(String aiType) throws IllegalArgumentException {
		Response response = proxy.addAI(aiType);
		return response.isSuccess();
	}
	
	
	/**
	 * This method allows the user to lay a road on the map
	 * @param location - The location where the user wants to place the road
	 * @return True if the road was placed
	 */
	public boolean buildRoad(EdgeLocation location, boolean free) throws IllegalArgumentException{
		GetModelResponse response = proxy.buildRoad(game.getModel().getLocalIndex(playerId),location, free);
		return response.isSuccess();

	}

	/**
	 * This method allows the user to lay a settlement on the map
	 * @param location - The location where the user wants to place the settlement
	 * @return True if the settlement was placed
	 */
	public boolean buildSettlement(VertexLocation location, boolean free) throws IllegalArgumentException{
		GetModelResponse response = proxy.buildSettlement(game.getModel().getLocalIndex(playerId),location, free);
		return response.isSuccess();

	}

	/**
	 * This method allows the user to lay a city on the map
	 * @param location - The location where the user wants to place the city
	 * @return True if the city was placed
	 */
	public boolean buildCity(VertexLocation location) throws IllegalArgumentException{
		GetModelResponse response = proxy.buildCity(game.getModel().getLocalIndex(playerId),location);
		return response.isSuccess();

	}

	/**
	 * This method allows the user to offer a trade to other players
	 * @param offer - The offer
	 * @return True if the offer was offered successfully
	 */
	public boolean offerTrade(TradeOffer offer) throws IllegalArgumentException{
		GetModelResponse response = proxy.offerTrade(game.getModel().getLocalIndex(playerId),offer);
		return response.isSuccess();
	}

	/**
	 * This method allows the user to perform a maritime trade
	 * @return True if the offer was offered successfully
	 */
	public boolean offerMaritimeTrade(int ratio, ResourceType input, ResourceType output) throws IllegalArgumentException{
		GetModelResponse response = proxy.maritimeTrade(game.getModel().getLocalIndex(playerId),ratio, input, output);
		return response.isSuccess();
	}

	/**
	 * This method terminates the Player's turn
	 * @return
	 */
	public boolean finishTurn() throws IllegalArgumentException{
		GetModelResponse response = proxy.finishTurn(game.getModel().getLocalIndex(playerId));
		return response.isSuccess();
	}

	/**
	 * This method allows the user to buy a Development Card
	 * @return True if the card was bought successfully
	 */
	public boolean buyDevCard() throws IllegalArgumentException{
		GetModelResponse response = proxy.buyDevCard(game.getModel().getLocalIndex(playerId));
		return response.isSuccess();
	}

	/**
	 * This method allows the user to play a Year of Plenty Card
	 * @param resource1 - The resource that the user picked to get
	 * @param resource2 - The resource that the user picked to get
	 * @return True if the card was played
	 */
	public boolean playYearOfPlenty(ResourceType resource1, ResourceType resource2) throws IllegalArgumentException{
		GetModelResponse response = proxy.Year_Of_Plenty(game.getModel().getLocalIndex(playerId),resource1, resource2);
		return response.isSuccess();

	}

	public boolean playRoadBuilding(EdgeLocation spot1, EdgeLocation spot2) throws IllegalArgumentException{
		GetModelResponse response = proxy.Road_Building(game.getModel().getLocalIndex(playerId),spot1, spot2);
		return response.isSuccess();
	}

	/**
	 * This method allows the user to play a Soldier Card
	 * @param victimIndex - The index of the player that will be the victim
	 * @param location - The location where the robber will be placed
	 * @return True if the Soldier Card was played successfully
	 */
	public boolean playSoldier(int victimIndex, HexLocation location) throws IllegalArgumentException{
		GetModelResponse response = proxy.Soldier(game.getModel().getLocalIndex(playerId),victimIndex, location);
		return response.isSuccess();
	}

	/**
	 * This method allows the user to play a Monopoly Card
	 * @param resource - The resource that the user picked to get
	 * @return True if the card was played
	 */
	public boolean playMonopoly(ResourceType resource) throws IllegalArgumentException{
		GetModelResponse response = proxy.Monopoly(game.getModel().getLocalIndex(playerId),resource);
		return response.isSuccess();
	}

	public boolean playMonument() throws IllegalArgumentException{
		GetModelResponse response = proxy.Monument(game.getModel().getLocalIndex(playerId));
		return response.isSuccess();
	}

	/**
	 * This method allows the user to move the Robber to a different Hex
	 * @param victimIndex - the index of the victim
	 * @param location - The Hex location where the user wants to move the Robber
	 * @return True if the Robber was moved
	 */
	public boolean placeRobber(int victimIndex, HexLocation location) throws IllegalArgumentException{
		GetModelResponse response = proxy.robPlayer(game.getModel().getLocalIndex(playerId),victimIndex, location);
		return response.isSuccess();

	}

	/**
	 * This method allows the user to send a message in the chat
	 * @param message - The message by the user
	 * @return True if the message was sent
	 */
	public boolean sendChat(String message) throws IllegalArgumentException{
		GetModelResponse response = proxy.sendChat(game.getModel().getLocalIndex(playerId),message);
		return response.isSuccess();

	}

	/**
	 * This method allows the user to accept an offer from other players
	 * @param willAccept
	 * True if you will accept a trade offer, false if you reject
	 * @return True if the offer was accepted successfully
	 */
	public boolean acceptTrade(boolean willAccept) throws IllegalArgumentException{
		GetModelResponse response = proxy.acceptTrade(game.getModel().getLocalIndex(playerId),willAccept);
		return response.isSuccess();

	}
	//*************************************************************************
	//************************canDo's******************************************
	/**
	 * This method determines if the Player can discard cards
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canDiscardCards() throws IllegalArgumentException{
		boolean canDiscard = game.getModel().getLocalPlayer(playerId).hasMoreThanSeven();
		return canDiscard;
	}

	/**
	 * This method determines if the Dice can be rolled
	 * @return True if the dice can be rolled
	 */
	public boolean canRollNumber() throws IllegalArgumentException{
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = game.getModel().getTurnTracker().getStatus();
		if(status == "Rolling"){
			return true;
		}
		return false;

	}

	/**
	 * This method determines if the user can lay a road at the location
	 * @param location - Where the user wants to lay the road
	 * @return True if the road can be placed in the location
	 */
	public boolean canBuildRoad(EdgeLocation location) throws IllegalArgumentException {
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = game.getModel().getTurnTracker().getStatus();
		if(status != "Playing" && status != "FirstRound" && status != "SecondRound"){
			return false;
		}
		
		boolean enoughResources = game.getModel().getLocalPlayer(playerId).canBuildRoad();
		if(enoughResources == false){
			return false;
		}
		int owner = game.getModel().getLocalIndex(playerId);
		EdgeValue value = new EdgeValue(owner,location);
		boolean canLayOnMap = false;
		if(status == "FirstRound" || status == "SecondRound"){
			canLayOnMap = game.getModel().getMap().hasRoadAllPlayers(location);
		}
		else{
			canLayOnMap = game.getModel().getMap().canLayRoad(value);
		}
		
		if(canLayOnMap == false){
			return false;
		}
		return true;
	}

	/**
	 * This method determines if the user can lay a settlement at the location
	 * @param location - Where the user wants to lay the settlement
	 * @return True if the settlement can be placed in the location
	 */
	public boolean canBuildSettlement(VertexLocation location) throws IllegalArgumentException {
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = game.getModel().getTurnTracker().getStatus();
		if(status != "Playing" && status != "FirstRound" && status != "SecondRound"){
			return false;
		}
		boolean enoughResources = game.getModel().getLocalPlayer(playerId).canBuildSettlement();
		if(enoughResources == false){
			return false;
		}
		int owner = game.getModel().getLocalIndex(playerId);
		VertexObject vertex = new VertexObject(owner,location);
		boolean canLayOnMap = false;
		boolean canLay = false;
		if(status == "FirstRound" || status == "SecondRound"){
			canLayOnMap = game.getModel().getMap().hasMunicipality(location);
			if(canLayOnMap == false){
				return true;
			}
		}
		else{
			canLayOnMap = game.getModel().getMap().canBuildSettlement(vertex);
			if(canLayOnMap == false){
				return false;
			}
		}
		
	
		return true;

	}

	/**
	 * This method determines if the user can lay a city at the location
	 * @param location - Where the user wants to lay the city
	 * @return True if the city can be placed in the location
	 */
	public boolean canBuildCity(VertexLocation location) throws IllegalArgumentException {
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = game.getModel().getTurnTracker().getStatus();
		if(status != "Playing"){
			return false;
		}
		boolean enoughResources = game.getModel().getLocalPlayer(playerId).canBuildCity();
		if(enoughResources == false){
			return false;
		}
		int owner = game.getModel().getLocalIndex(playerId);
		VertexObject vertex = new VertexObject(owner,location);
		boolean canLayOnMap = game.getModel().getMap().canBuildCity(vertex);
		if(canLayOnMap == false){
			return false;
		}
		return true;
	}

	/**
	 * This method determines if the user can offer a trade
	 * @param offer
	 * @return True if the user can offer the trade
	 */
	public boolean canOfferTrade() throws IllegalArgumentException {
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = game.getModel().getTurnTracker().getStatus();
		if(status != "Playing"){
			return false;
		}
		boolean enoughResources = game.getModel().getLocalPlayer(playerId).canOfferTrade();

		return enoughResources;
	}


	/**
	 * This method determines if the user can offer a Maritime trade
	 * @param offer
	 * @return True if the user can offer the trade
	 */
	public boolean canMaritimeTrade() throws IllegalArgumentException {
		boolean isTurn = false;
		int playerIndex = game.getModel().getLocalIndex(playerId);
		if(playerIndex == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = game.getModel().getTurnTracker().getStatus();
		if(status != "Playing"){
			return false;
		}
		boolean hasPort = game.getModel().getMap().hasPort(playerIndex);

		//bank is not empty
		if(game.getModel().getBank().isEmpty()){
			//Bank was empty
			return false;
		}

		//player has at least 2 of the same
		if(!game.getModel().getLocalPlayer(playerId).enoughResourceCardsToTrade()){
			//Not enough resources to offer a maritime trade. 
			return false; 
		}

		return hasPort;
	}

	/**
	 * This method determines if the player can finish the turn
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canFinishTurn() throws IllegalArgumentException{
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = game.getModel().getTurnTracker().getStatus();
		if(status == "Rolling"){
			return false;
		}
		return true;
	}

	/**
	 * This method determines if the player can buy a Development card
	 * @return True if the player can buy a Development card
	 */
	public boolean canBuyDevCard() throws IllegalArgumentException{
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = game.getModel().getTurnTracker().getStatus();
		if(status != "Playing"){
			return false;
		}
		boolean enoughResources = game.getModel().getLocalPlayer(playerId).canBuyDevCard();
		if(enoughResources == false){
			return false;
		}

		if(game.getModel().getDeck().isEmpty()){
			//Deck is empty, no Dev Cards available for purchase. 
			return false;
		}

		return true;
	}

	/**
	 * This method determines if the player can use the Year of Plenty Dev Card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseYearOfPlenty() throws IllegalArgumentException{
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);
		if(model.getTurnTracker().getCurrentTurn() == localPlayer.getPlayerIndex()){
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = model.getTurnTracker().getStatus();
		if(status != "Playing"){
			return false;
		}
		boolean playerCheck = localPlayer.canPlayYearOfPlentyCard();
		if(playerCheck == false){
			return false;
		}

		return true;
	}
	
	public boolean canUseRoadBuilderPlacement(EdgeLocation location){
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = game.getModel().getTurnTracker().getStatus();
		if(status != "Playing"){
			return false;
		}
		
		int owner = game.getModel().getLocalIndex(playerId);
		EdgeValue value = new EdgeValue(owner,location);
		boolean canLayOnMap = false;
		canLayOnMap = game.getModel().getMap().canLayRoad(value);
		if(canLayOnMap == false){
			return false;
		}
		return true;
	}

	/**
	 * This method determines if the player can use the Road Builder Dev card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseRoadBuilder() throws IllegalArgumentException{
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);
		if(model.getTurnTracker().getCurrentTurn() == localPlayer.getPlayerIndex()){
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = model.getTurnTracker().getStatus();
		if(status != "Playing"){
			return false;
		}
		boolean playerCheck = localPlayer.canPlayRoadBuilding();
		if(playerCheck == false){
			return false;
		}

		return true;
	}
	/**
	 * This method determines if the player can use the Soldier Dev card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseSoldier() throws IllegalArgumentException{
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);
		if(model.getTurnTracker().getCurrentTurn() == localPlayer.getPlayerIndex()){
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = model.getTurnTracker().getStatus();
		if(status != "Playing"){
			return false;
		}
		boolean playerCheck = localPlayer.canPlaySoldierCard();
		if(playerCheck == false){
			return false;
		}

		return true;

	}

	/**
	 * This method determines if the player can use the Monopoly Dev Card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseMonopoly() throws IllegalArgumentException{
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);
		if(model.getTurnTracker().getCurrentTurn() == localPlayer.getPlayerIndex()){
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = model.getTurnTracker().getStatus();
		if(status != "Playing"){
			return false;
		}
		boolean playerCheck = localPlayer.canPlayMonopolyCard();
		if(playerCheck == false){
			return false;
		}

		return true;
	}

	/**
	 * This method determines if the player can use the Monument Dev card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseMonument() throws IllegalArgumentException{
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);
		if(model.getTurnTracker().getCurrentTurn() == localPlayer.getPlayerIndex()){
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = model.getTurnTracker().getStatus();
		if(status != "Playing"){
			return false;
		}
		boolean playerCheck = localPlayer.canPlayMonumentCard();
		if(playerCheck == false){
			return false;
		}

		return true;
	}

	/**
	 * This method determines if the user can move the Robber
	 * @return True if the user can move the Robber
	 */
	public boolean canPlaceRobber(HexLocation location) throws IllegalArgumentException{
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);
		if(model.getTurnTracker().getCurrentTurn() == localPlayer.getPlayerIndex()){
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		String status = model.getTurnTracker().getStatus();
		if(status != "Robbing"){
			return false;
		}
		boolean mapCheck = model.getMap().canLayRobber(location);
		if(mapCheck == false){
			return false;
		}

		return true;
	}

	/**
	 * This method determines if the user can send a chat
	 * @return True if the message can be sent
	 */
	public boolean canSendChat() throws IllegalArgumentException{
		return true;
	}
	/**
	 * This method determines if the user can accept an offer
	 * @param offer
	 * @return True if the user can accept the offer
	 */
	public boolean canAcceptTrade(TradeOffer offer) throws IllegalArgumentException {
		boolean canAccept = game.getModel().getLocalPlayer(playerId).canAcceptTrade(offer);
		return canAccept;
	}










}