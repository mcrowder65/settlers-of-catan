package client.controller;

import shared.locations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Random;

import client.communication.IProxy;
import client.data.GameInfo;
import client.data.GameManager;
import client.data.PlayerInfo;
import client.data.RobPlayerInfo;
import shared.communication.*;
import shared.communication.response.*;
import shared.definitions.*;
/**
 * This class bridges the gap between all of the controllers/gui and the server/local model
 * @author Brennen
 *
 */
public class Facade {
	private GameManager game;
	private GameModel tempModel = null; //holds a local model - used for roadbuilding card
	private IProxy proxy;
	private int playerId;

	/**
	 * constructor for the facade
	 * @param proxy
	 * @param pollingInterval
	 * @param game
	 */
	public Facade(IProxy proxy, int pollingInterval, GameManager game)
	{
		this.proxy = proxy;
		this.game = game;
	
	}
	/**
	 * a second constructor for the facade
	 * @param proxy
	 * @param pollingInterval
	 */
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
	public void updateNotify(){
		game.updates();
	}
	/**
	 * sets up the temporary model
	 */
	public void setTempModel(){
		tempModel= game.getModel();
	}

	public int getPlayerIndex() {
		return game.getModel().getLocalIndex(playerId);
	}
	public int getPlayerId() {
		return game.getProxy().getPlayerId();
	}
	public CatanColor getPlayerColor() {
		return game.getModel().getLocalPlayer(playerId).getColor();
	}
	/**
	 * sets the tempModel to null
	 */
	public void NullifyTemp(){
		this.tempModel = null;
	}
	public void startPoller() {
		game.getPoller().startPolling();
	}
	public void stopPoller() {
		game.getPoller().stopPolling();
	}
	public int getPollingInterval() {
		return game.getPoller().getInterval();
	}
	public void addObserver(Observer o) {
		game.addObserver(o);
	}
	public void deleteObserver(Observer o) {
		game.deleteObserver(o);
	}
	public DevCardList getDevCards(){
		return game.getModel().getLocalPlayer(playerId).getOldDevCards();
	}
	/**
	 * sets the local player id
	 * @param playerId
	 */
	public void setPlayerId(int playerId){
		this.playerId = playerId;
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
	/**
	 * gest a list of the AI
	 * @return
	 */
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
		while(result == 7){
			result = roll() + roll();
		}
		GetModelResponse response = proxy.rollNumber(game.getModel().getLocalIndex(playerId),result);
		return response.isSuccess() == true ? result : -1;
	}
	public int roll() throws IllegalArgumentException{
		return new Random().nextInt((6 - 1) + 1) + 1;
	}
	/**
	 * this method adds an AI to the game
	 * @param aiType
	 * @return
	 * @throws IllegalArgumentException
	 */
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
	/**
	 * this method allows the user to play a roadBuilding card
	 * @param spot1
	 * @param spot2
	 * @return
	 * @throws IllegalArgumentException
	 */
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

	/**
	 * THis method allows the user to play a monument card
	 * @return
	 * @throws IllegalArgumentException
	 */
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
	
	public List<Port> getPersonalPorts(){
		return game.getModel().getMap().getPersonalPorts(game.getModel().getLocalIndex(playerId));
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
	public PlayerInfo[] getPlayers(int gameID){
		PlayerInfo[] array = null;
		ListGamesResponse response = proxy.listGames();
		GameInfo[] games = response.getGames();
		for(int i = 0; i < games.length; i++){
			if(games[i].getId() == gameID){
				array = new PlayerInfo[games[i].getPlayers().size()];
				for(int x = 0; x < array.length; x++){
					array[x] = games[i].getPlayers().get(x);
				}
			}
		}
		
		return array;
	}
	//************************canDo's******************************************
	/**
	 * This method determines if the player can join the selected game
	 * 
	 */
	public boolean canJoinGame(int gameID, CatanColor color, int playerID){
		if(gameID < 0) return false;
		if(color == null) return false;
		ListGamesResponse response = proxy.listGames();
		GameInfo[] games = response.getGames();
		for(int i = 0; i < games.length; i++){
			GameInfo game = games[i];
			List<PlayerInfo> players = game.getPlayers();
			if(game.getId() == gameID){
				for(int x = 0; x < players.size(); x++){
					PlayerInfo player = players.get(x);
					if(player.getColor().equals(color) && playerID != player.getId()){
						return false;
					}
				}
			}
		}
		return true;
	}
	/**
	 * This method determines if the Player can discard cards
	 * @return True if the player can
	 */
	public boolean canDiscardCards() throws IllegalArgumentException{
		//checks to see if the numer of cards are greater than 7
		boolean canDiscard = game.getModel().getLocalPlayer(playerId).getNumOfCards() > 7;
		return canDiscard;
	}

	/**
	 * This method determines if the Dice can be rolled
	 * @return True if the dice can be rolled
	 */
	public boolean canRollNumber() throws IllegalArgumentException{
		//checks to make sure it is the players correct turn
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		//checks to make sure the status is correct for the rolling state
		String status = game.getModel().getTurnTracker().getStatus();
		if(status.equals( "Rolling")){
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
		//checks to make sure the location passed in is not null
		if(location == null){
			return false;
		}
		//checks to make sure its the players correct turn
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false; //if incorrect turn
		}
		//checks to make sure the player has the proper status to build a road
		String status = game.getModel().getTurnTracker().getStatus();
		if(!status.equals( "Playing") && !status.equals( "FirstRound") && !status.equals( "SecondRound")){
			return false;
		}
		int owner = game.getModel().getLocalIndex(playerId); //gets the local Player index ie owner of the road
		EdgeValue value = new EdgeValue(owner,location);
		boolean hasRoad = false;
		boolean canLayOnMap = false;
		//if the player is trying to lay a road during the first two rounds
		if(status.equals( "FirstRound") || status.equals( "SecondRound")){
			hasRoad = game.getModel().getMap().hasRoadAllPlayers(location); //checks to see if the location already has a road
			HexLocation loc = location.getHexLoc();
			boolean isLand = game.getModel().getMap().isLand(loc);
			EdgeDirection direction = location.getDir();
			HexLocation locOpp = game.getModel().getMap().getOppositeHex(loc,direction);
			//checking to see if the location is land or water
			if(isLand == false){
				isLand = game.getModel().getMap().isLand(locOpp);
			}
			if(isLand == false || hasRoad == true){
				return false;
			}
			boolean canLayThisRound = game.getModel().getMap().canLayRoadFirstRounds(value);//asking the map to make sure the location is free and if the player can lay the road      
			if(canLayThisRound == false){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			//this is used if the player is playing a road without using a roadBuilder card
			if(this.tempModel == null){
				HexLocation loc = location.getHexLoc();
				boolean isLand = game.getModel().getMap().isLand(loc);
				EdgeDirection direction = location.getDir();
				HexLocation locOpp = game.getModel().getMap().getOppositeHex(loc,direction);
				//checks to make sure that the location is actually land
				if(isLand == false){
					isLand = game.getModel().getMap().isLand(locOpp);
				}
				if(isLand == false){
					return false;
				}
				canLayOnMap = game.getModel().getMap().canLayRoad(value);//asking the map to make sure the location is free and if the player can lay the road      
			}
			else{
				//this is used to see if the player can place a road using the road builder card
				HexLocation loc = location.getHexLoc();
				boolean isLand = game.getModel().getMap().isLand(loc);
				EdgeDirection direction = location.getDir();
				HexLocation locOpp = game.getModel().getMap().getOppositeHex(loc,direction);
				//checks to make sure that the location is actually land
				if(isLand == false){
					isLand = game.getModel().getMap().isLand(locOpp);
				}
				if(isLand == false){
					return false;
				}
				canLayOnMap = this.tempModel.getMap().canLayRoad(value);//asking the map to make sure the location is free and if the player can lay the road      
			}
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
		//checks to make sure its the players turn
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		//checks to make sure the status is correct
		String status = game.getModel().getTurnTracker().getStatus();
		if(!status.equals( "Playing") && !status.equals( "FirstRound") && !status.equals( "SecondRound")){
			return false;
		}
		//checks to see if the player has enough resources to lay a settlement - only used outside of the first 2 rounds
		if(!status.equals("FirstRound") && !status.equals("SecondRound")){
			boolean enoughResources = game.getModel().getLocalPlayer(playerId).canBuildSettlement();
			if(enoughResources == false){
				return false;
			}
		}
		
		int owner = game.getModel().getLocalIndex(playerId);
		VertexObject vertex = new VertexObject(owner,location);
		boolean canLayOnMap = false;
		boolean canLay = false;
		//used if the player is laying a settlement during the first two rounds
		if(status.equals( "FirstRound") || status.equals( "SecondRound")){
			boolean canLaySet = game.getModel().getMap().canBuildSettlementFirstRound(vertex); //asking the map if the player can build a settlement in that location
			if(canLaySet == false){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			//used if the player is laying a settlement outside the first two rounds
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
		//checks to make sure its the players turn
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		//checks to make sure the status is correct
		String status = game.getModel().getTurnTracker().getStatus();
		if(!status.equals( "Playing")){
			return false;
		}
		//checks to make sure the player has enough resources to build a city
		boolean enoughResources = game.getModel().getLocalPlayer(playerId).canBuildCity();
		if(enoughResources == false){
			return false;
		}
		//asks the map if the player can build a city and a certain location
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
	 * @return True if the user can offer the trade
	 */
	public boolean canOfferTrade() throws IllegalArgumentException {
		boolean isTurn = false;
		//checks to make sure its the players turn
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		//checks to make sure the status is playing
		String status = game.getModel().getTurnTracker().getStatus();
		if(!status.equals( "Playing")){
			return false;
		}
		//checks to make sure the player has enough resources to do the trade
		boolean enoughResources = game.getModel().getLocalPlayer(playerId).canOfferTrade();

		return enoughResources;
	}


	/**
	 * This method determines if the user can offer a Maritime trade
	 * @return True if the user can offer the trade
	 */
	public boolean canMaritimeTrade() throws IllegalArgumentException {
		//checks to make sure its the player's turn
		boolean isTurn = false;
		int playerIndex = game.getModel().getLocalIndex(playerId);
		if(playerIndex == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		//checks to make sure the status is correct
		String status = game.getModel().getTurnTracker().getStatus();
		if(!status.equals( "Playing")){
			return false;
		}
		//checks to make sure the player has a port
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
	 * @return True if the player can
	 */
	public boolean canFinishTurn() throws IllegalArgumentException{
		//checks to make sure its the players turn
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		//checks to make sure the status is correct
		String status = game.getModel().getTurnTracker().getStatus();
		if(status.equals( "Rolling")){
			return false;
		}
		return true;
	}

	/**
	 * This method determines if the player can buy a Development card
	 * @return True if the player can buy a Development card
	 */
	public boolean canBuyDevCard() throws IllegalArgumentException{
		//checks to make sure its the player's turn
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		//checks to make sure the staus is correct
		String status = game.getModel().getTurnTracker().getStatus();
		if(!status.equals( "Playing")){
			return false;
		}
		//checks to make sure the player has enough resources
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
	 * @return True if the player can
	 */
	public boolean canUseYearOfPlenty() throws IllegalArgumentException{
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);
		return localPlayer.canPlayYearOfPlentyCard(); 
	}
	
	public boolean canUseRoadBuilderPlacement(EdgeLocation location){
		//checks to make sure its the players turn
		boolean isTurn = false;
		if(game.getModel().getLocalIndex(playerId) == game.getModel().getTurnTracker().getCurrentTurn())
		{
			isTurn = true;
		}
		if(isTurn == false){
			return false;
		}
		//making sure the status is correct
		String status = game.getModel().getTurnTracker().getStatus();
		if(!status.equals( "Playing")){
			return false;
		}
		
		int owner = game.getModel().getLocalIndex(playerId);
		EdgeValue value = new EdgeValue(owner,location);
		boolean canLayOnMap = false;
		canLayOnMap = game.getModel().getMap().canLayRoad(value); //seeing if the road can be laid on the map
		if(canLayOnMap == false){
			return false;
		}
		return true;
	}
	/**
	 * places the road on the tempModel 
	 * used in the roadBuilder card 
	 * @param location
	 * @return
	 */
	public boolean placeRoadBuilder(EdgeLocation location){
		int owner = game.getModel().getLocalIndex(playerId);
		EdgeValue value = new EdgeValue(owner,location);
		this.setTempModel();
		this.tempModel.getMap().buildRoad(value); //builds the road on the temp model
		return true;
	}
	
	/**
	 * deletes a road from the map
	 * @param location
	 */
	public void deleteRoad(EdgeLocation location){
		game.getModel().getMap().deleteRoad(location);
	}

	/**
	 * This method determines if the player can use the Road Builder Dev card
	 * @return True if the player can
	 */
	public boolean canUseRoadBuilder() throws IllegalArgumentException{
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);
		return localPlayer.canPlayRoadBuilding();
	}
	/**
	 * This method determines if the player can use the Soldier Dev card
	 * @return True if the player can
	 */
	public boolean canUseSoldier() throws IllegalArgumentException{
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);
		return localPlayer.canPlaySoldierCard();
	}

	/**
	 * This method determines if the player can use the Monopoly Dev Card
	 * @return True if the player can
	 */
	public boolean canUseMonopoly() throws IllegalArgumentException{
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);
		return localPlayer.canPlayMonopolyCard();
	}

	/**
	 * This method determines if the player can use the Monument Dev card
	 * @return True if the player can
	 */
	public boolean canUseMonument() throws IllegalArgumentException{
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer(playerId);	
		return localPlayer.canPlayMonumentCard();
	}

	/**
	 * This method determines if the user can move the Robber
	 * @return True if the user can move the Robber
	 */
	public boolean canPlaceRobber(HexLocation location) throws IllegalArgumentException{
		GameModel model = game.getModel();
		boolean mapCheck = model.getMap().canLayRobber(location); //checks the map to see if the robber can be placed there
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

	
	public GameModel fetchModel() {
		GetModelResponse response = proxy.getModel();
		return response.getModel();
	}

   
	public RobPlayerInfo[] getRobbablePlayers(HexLocation hexLoc, int playerIndex) {
		 boolean[] usedIndices = new boolean[4];
		 List<RobPlayerInfo> info = new ArrayList<RobPlayerInfo>();
		 List<VertexObject> municipalities = game.getModel().getMap().getBorderingMunicipalities(hexLoc);
		 for (VertexObject obj : municipalities) {
			 Player owner = game.getModel().getPlayers()[obj.getOwner()];
			 if (obj.getOwner() != playerIndex && owner.getNumOfCards() > 0  && !usedIndices[obj.getOwner()]) {
				 RobPlayerInfo robPlayer = new RobPlayerInfo( owner);
				 info.add(robPlayer);
				 usedIndices[obj.getOwner()] = true;
			 }
		 }
		 ;
		 RobPlayerInfo[] arrayInfo = new RobPlayerInfo[info.size()];
		 for (int n = 0; n < arrayInfo.length; n++) {
			 arrayInfo[n] = info.get(n);
		 }
		 
		 return arrayInfo;
	}
	public ResourceList getResources(int playerIndex) {
		return game.getModel().getPlayers()[playerIndex].getResources();
	}

	/**
	 * checks to see if the player can buy a road
	 * @return
	 */
	public boolean canBuyRoad() {
		//checks to make sure its the players turn
		if(game.getModel().getLocalIndex(playerId) != game.getModel().getTurnTracker().getCurrentTurn())
		{
			return false;
		}
		return game.getModel().getLocalPlayer(playerId).canBuildRoad(); //checks to see if the player has the necessary resources
	}
	
	/**
	 * checks to see if a player can buy a settlement
	 * @return
	 */
	public boolean canBuySettlement() {
		//making sure its the players turn
		if(game.getModel().getLocalIndex(playerId) != game.getModel().getTurnTracker().getCurrentTurn())
		{
			return false;
		}
		String status = game.getModel().getTurnTracker().getStatus(); //making sure its the correct status
		if(!status.equals( "Playing")){
			return false;
		}
		return game.getModel().getLocalPlayer(playerId).canBuildSettlement(); //checks to see if the player has the necessary resources
	}
	/**
	 * checks to see if a player can buy a city
	 * @return
	 */
	public boolean canBuyCity() {
		//makes sure its the player's turn
		if(game.getModel().getLocalIndex(playerId) != game.getModel().getTurnTracker().getCurrentTurn())
		{
			return false;
		}
		//makes sure the status is correct 
		String status = game.getModel().getTurnTracker().getStatus();
		if(!status.equals( "Playing")){
			return false;
		}
		return game.getModel().getLocalPlayer(playerId).canBuildCity(); //checks to see if the player has the necessary resources
	}
	private boolean mapSet = false;

	public boolean isMapSet() {
		return mapSet;
	}
	public void setMapSet(boolean mapSet) {
		this.mapSet = mapSet;
	}
	public int getPlayerRoads() {
		return game.getModel().getNumRoadsForPlayer(getPlayerIndex());
	}
	public int getPlayerSettlements() {
		return game.getModel().getNumSettlementsForPlayer(getPlayerIndex());
	}
	public int getPlayerCities() {
		return game.getModel().getNumCitiesForPlayer(getPlayerIndex());
	}
}