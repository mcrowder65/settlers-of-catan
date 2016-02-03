package client.controller;

import shared.locations.*;
import client.communication.IProxy;
import client.data.GameInfo;
import client.data.GameManager;
import shared.communication.*;
import shared.communication.response.*;
import shared.definitions.*;

public class Facade {

	private GameManager game;
	private IProxy proxy;
	private int pass;

	public Facade()
	{
		game = new GameManager(proxy, pass);
	}
	public Facade(IProxy proxy, int pollingInterval)
	{
		game = new GameManager(proxy, pollingInterval);
		this.proxy = proxy;
		this.pass = pollingInterval;
	}
	
	public Facade (GameManager gameMan){
		this.game = gameMan;
	}
	



	/**
	 * Allows a user to log into the server.
	 * @param username, password
	 * @return True if the user successfully logged in.
	 */
	public boolean login(String username, String password){
		Response response = proxy.login(username, password);
		return response.isSuccess() == true ? true : false;
	}

	/**
	 * Allows the user to create an account and register
	 * @param username, password
	 * @return True if the user successfully registered.
	 */
	public boolean register(String username, String password){
		Response response = proxy.register(username, password);
		return response.isSuccess() == true ? true : false;
	}

	/**
	 * This method returns information(List) of all the existing games.
	 * @return An array of GameInfo with all the information for the existing games.
	 */
	public GameInfo[] listGames(){
		ListGamesResponse response = proxy.listGames();
		return response.getGames();
	}

	/**
	 * This method allows the user to join an existing game.
	 * @param gameId- Id of the game.
	 * @return True if the user joined successfully a game.
	 */
	public boolean joinGame(int gameId, CatanColor color){
		Response response = proxy.joinGame(gameId, color);
		return response.isSuccess() == true ? true : false;

	}

	/**
	 * This method allows the user to create a new game.
	 * @param name - Name of the game
	 * @return True if a game was created successfully.
	 */
	public boolean createGame(String name){
		return false;

	}

	/**
	 * This method allows the user to quit the game.
	 */
	public void quit(){

	}













	/**
	 * The user selects cards to be discarded to leave him with a total of 7 cards
	 * @param list - List of cards that the user will be discarding
	 * @return True if the cards were discarded successfully
	 */
	public boolean discardCards(ResourceList list){
		return false;

	}	

	/**
	 * This method allows the user to roll the dice
	 * @return The values rolled by the dice
	 */
	public String rollNumber(){
		//GameModel somewhere, roll number
		//Returns the 2 digits as a string so it can 
		//be accessed as an array. ex: "12", String[0]: 1, String[1]:2
		return null;
	}
	/**
	 * This method allows the user to lay a road on the map
	 * @param location - The location where the user wants to place the road
	 * @return True if the road was placed
	 */
	public boolean buildRoad(EdgeLocation location){
		int playerInTurn = game.getModel().getLocalPlayer().getPlayerIndex();
		EdgeValue road = new EdgeValue(playerInTurn, location);
		game.getModel().getMap().buildRoad(road);

		return true;

	}

	/**
	 * This method allows the user to lay a settlement on the map
	 * @param location - The location where the user wants to place the settlement
	 * @return True if the settlement was placed
	 */
	public boolean buildSettlement(VertexLocation location){
		int playerInTurn = game.getModel().getLocalPlayer().getPlayerIndex();
		VertexObject settlement = new VertexObject(playerInTurn, location);
		game.getModel().getMap().buildSettlement(settlement);

		return true;

	}

	/**
	 * This method allows the user to lay a city on the map
	 * @param location - The location where the user wants to place the city
	 * @return True if the city was placed
	 */
	public boolean buildCity(VertexLocation location){
		int playerInTurn = game.getModel().getLocalPlayer().getPlayerIndex();
		VertexObject city = new VertexObject(playerInTurn, location);
		game.getModel().getMap().buildCity(city);

		return true;

	}

	/**
	 * This method allows the user to offer a trade to other players
	 * @param offer - The offer
	 * @return True if the offer was offered successfully
	 */
	public boolean offerTrade(TradeOffer offer){
		return false;

	}

	/**
	 * This method allows the user to offer a trade to other players
	 * @param offer - The offer
	 * @return True if the offer was offered successfully
	 */
	public boolean offerMaritimeTrade(TradeOffer offer){
		return false;

	}

	/**
	 * This method terminates the Player's turn
	 * @return
	 */
	public boolean finishTurn(){
		game.getModel().getTurnTracker().advanceTurn();
		game.getModel().getLocalPlayer().setPlayedDevCard(false);	

		return true;
	}

	/**
	 * This method allows the user to buy a Development Card
	 * @return True if the card was bought successfully
	 */
	public boolean buyDevCard(){
		return false;

	}

	/**
	 * This method allows the user to play a Year of Plenty Card
	 * @param resource1 - The resource that the user picked to get
	 * @param resource2 - The resource that the user picked to get
	 * @return True if the card was played
	 */
	public boolean playYearOfPlentyCard(ResourceType resource1, ResourceType resource2){
		game.getModel().getLocalPlayer().playYearOfPlentyCard();
		//How to add resources to player
		//How to subtract them from bank

		return true;

	}

	public boolean playRoadBuilder(EdgeLocation road1, EdgeLocation road2){
		//There's not a roadbuilder in player
		//How to add resources to player
		//How to subtract them from bank
		return true;
	}

	/**
	 * This method allows the user to play a Soldier Card
	 * @param victimIndex - The index of the player that will be the victim
	 * @param location - The location where the robber will be placed
	 * @return True if the Soldier Card was played successfully
	 */
	public boolean playSoldierCard(){
		game.getModel().getLocalPlayer().playSoldierCard();
		//The robber should be place through the placeRobber method
		
		return true;
	}

	/**
	 * This method allows the user to play a Monopoly Card
	 * @param resource - The resource that the user picked to get
	 * @return True if the card was played
	 */
	public boolean playMonopolyCard(ResourceType resource){
		game.getModel().getLocalPlayer().playMonopolyCard();
		//How to add resources to player
				//How to subtract them from bank
		return false;
	}

	public boolean playMonument(){
		int playerInTurn = game.getModel().getLocalPlayer().getPlayerIndex();
		game.getModel().setWinner(playerInTurn);
		
		return true;
	}

	/**
	 * This method allows the user to move the Robber to a different Hex
	 * @param location - The Hex location where the user wants to move the Robber
	 * @return True if the Robber was moved
	 */
	public boolean placeRobber(HexLocation location, int victimIndex){
		game.getModel().getMap().setRobber(location);
		//Who takes care of making sure every player discards cards?
		
		return true;

	}

	/**
	 * This method allows the user to send a message in the chat
	 * @param message - The message by the user
	 * @return True if the message was sent
	 */
	public boolean sendChat(String message){
		//Who does this?
		
		return false;

	}

	/**
	 * This method allows the user to accept an offer from other players
	 * @param offer
	 * @return True if the offer was accepted successfully
	 */
	public boolean acceptTrade(TradeOffer offer){
		return false;

	}
	//*************************************************************************
	//************************canDo's******************************************
	/**
	 * This method determines if the Player can discard cards
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canDiscardCards(){
		boolean canDiscard = game.getModel().getLocalPlayer().hasMoreThanSeven();
		return canDiscard;
	}

	/**
	 * This method determines if the Dice can be rolled
	 * @return True if the dice can be rolled
	 */
	public boolean canRollNumber(){
		boolean isTurn = false;
		if(game.getModel().getLocalPlayer().getPlayerIndex() == game.getModel().getTurnTracker().getCurrentTurn())
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
		if(game.getModel().getLocalPlayer().getPlayerIndex() == game.getModel().getTurnTracker().getCurrentTurn())
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
		boolean enoughResources = game.getModel().getLocalPlayer().canBuildRoad();
		if(enoughResources == false){
			return false;
		}
		int owner = game.getModel().getLocalPlayer().getPlayerIndex();
		EdgeValue value = new EdgeValue(owner,location);
		boolean canLayOnMap = game.getModel().getMap().canLayRoad(value);
		if(canLayOnMap == false){
			return false;
		}
		return false;
	}

	/**
	 * This method determines if the user can lay a settlement at the location
	 * @param location - Where the user wants to lay the settlement
	 * @return True if the settlement can be placed in the location
	 */
	public boolean canBuildSettlement(VertexLocation location) throws IllegalArgumentException {
		boolean isTurn = false;
		if(game.getModel().getLocalPlayer().getPlayerIndex() == game.getModel().getTurnTracker().getCurrentTurn())
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
		boolean enoughResources = game.getModel().getLocalPlayer().canBuildSettlement();
		if(enoughResources == false){
			return false;
		}
		int owner = game.getModel().getLocalPlayer().getPlayerIndex();
		VertexObject vertex = new VertexObject(owner,location);
		boolean canLayOnMap = game.getModel().getMap().canBuildSettlement(vertex);
		if(canLayOnMap == false){
			return false;
		}
		return false;

	}

	/**
	 * This method determines if the user can lay a city at the location
	 * @param location - Where the user wants to lay the city
	 * @return True if the city can be placed in the location
	 */
	public boolean canBuildCity(VertexLocation location) throws IllegalArgumentException {
		boolean isTurn = false;
		if(game.getModel().getLocalPlayer().getPlayerIndex() == game.getModel().getTurnTracker().getCurrentTurn())
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
		boolean enoughResources = game.getModel().getLocalPlayer().canBuildSettlement();
		if(enoughResources == false){
			return false;
		}
		int owner = game.getModel().getLocalPlayer().getPlayerIndex();
		VertexObject vertex = new VertexObject(owner,location);
		boolean canLayOnMap = game.getModel().getMap().canBuildCity(vertex);
		if(canLayOnMap == false){
			return false;
		}
		return false;
	}

	/**
	 * This method determines if the user can offer a trade
	 * @param offer
	 * @return True if the user can offer the trade
	 */
	public boolean canOfferTrade(TradeOffer offer) throws IllegalArgumentException {
		boolean isTurn = false;
		if(game.getModel().getLocalPlayer().getPlayerIndex() == game.getModel().getTurnTracker().getCurrentTurn())
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
		boolean enoughResources = game.getModel().getLocalPlayer().canOfferTrade();
		
		return enoughResources;
	}


	/**
	 * This method determines if the user can offer a Maritime trade
	 * @param offer
	 * @return True if the user can offer the trade
	 */
	public boolean canMaritimeTrade(TradeOffer offer) throws IllegalArgumentException {
		boolean isTurn = false;
		int playerIndex = game.getModel().getLocalPlayer().getPlayerIndex();
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
		
		//Still need to check and see if the bank has enough resources
		return hasPort;
	}

	/**
	 * This method determines if the player can finish the turn
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canFinishTurn(){
		boolean isTurn = false;
		if(game.getModel().getLocalPlayer().getPlayerIndex() == game.getModel().getTurnTracker().getCurrentTurn())
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
	public boolean canBuyDevCard(int playerIndex){
		boolean isTurn = false;
		if(game.getModel().getLocalPlayer().getPlayerIndex() == game.getModel().getTurnTracker().getCurrentTurn())
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
		boolean enoughResources = game.getModel().getLocalPlayer().canBuyDevCard();
		if(enoughResources == false){
			return false;
		}

		return true;
	}

	/**
	 * This method determines if the player can use the Year of Plenty Dev Card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseYearOfPlenty(int playerIndex){
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer();
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

	/**
	 * This method determines if the player can use the Road Builder Dev card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseRoadBuilder(int playerIndex){
		boolean isTurn = true;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer();
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
	public boolean canUseSoldier(int playerIndex){
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer();
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
	public boolean canUseMonopoly(int playerIndex){
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer();
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
	public boolean canUseMonument(int playerIndex){
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer();
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
	public boolean canPlaceRobber(HexLocation location){
		boolean isTurn = false;
		GameModel model = game.getModel();
		Player localPlayer = model.getLocalPlayer();
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
	public boolean canSendChat(){

		return true;
	}
	/**
	 * This method determines if the user can accept an offer
	 * @param offer
	 * @return True if the user can accept the offer
	 */
	public boolean canAcceptTrade(TradeOffer offer) throws IllegalArgumentException {
		boolean canAccept = game.getModel().getLocalPlayer().canAcceptTrade(offer);
		return canAccept;
	}


	//^^^^^^^^^^^^^^^^^^^^  TA'S   ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	//*******************************************************

	/**
	 * This method allows a user to buy a road
	 * @return True if a road can be bought
	 */
	public boolean buyRoad(){
		game.getModel().getLocalPlayer().chargeBasicRoad();
		
		return true;

	}

	/**
	 * This method allows a user to buy a settlement
	 * @return True if a settlement can be bought
	 */
	public boolean buySettlement(){
		game.getModel().getLocalPlayer().chargeBasicSettlement();
		
		return true;

	}

	/**
	 * This method allows a user to buy a city
	 * @return True if a city can be bought
	 */
	public boolean buyCity(){
		game.getModel().getLocalPlayer().chargeBasicCity();
		
		return true;

	}

	/**
	 * This method determines if it's the user's turn
	 * @return True if it's the user's turn
	 */
	public boolean isTurn(){
		if(game.getModel().getLocalPlayer().getPlayerIndex() == game.getModel().getTurnTracker().getCurrentTurn())
		{
			return true;
		}
		return false;

	}

	
	/**
	 * This method determines if the user can buy a road
	 * @return True if the user can buy a road
	 */
	public boolean canBuyRoad(){
		if(game.getModel().getLocalPlayer().canBuildRoad()){
			return true;
		}
		return false;

	}
	

	/**
	 * This method determines if the user can buy a settlement
	 * @return True if the user can buy a settlement
	 */
	public boolean canBuySettlement(){
		if(game.getModel().getLocalPlayer().canBuildSettlement()){
			return true;
		}
	
		return false;

	}

	/**
	 * This method determines if the user can buy a city
	 * @return True if the user can buy a city
	 */
	public boolean canBuyCity(){
		if(game.getModel().getLocalPlayer().canBuildCity()){
			return true;
		}
		
		return false;

	}


	/**
	 * This method determines if the user can retract from the offer
	 * @return True if the user can
	 */
	public boolean canUndoTrade(){
		return false;
	}


	/**
	 * This method determines if the user can play a Development Card
	 * @param type - The type of card
	 * @return True if the user can play a Development card
	 */
	public boolean canPlayDevCard(DevCardType type){
		if(game.getModel().getLocalPlayer().canPlayDevCard()){
			return true;
		}
		
		return false;
	}

	/**
	 * This method determines if the user can sign in
	 * @return True if the user can sign in
	 */
	public boolean canSignIn(){
		return false;
	}

	/**
	 * This method determines if the user can register
	 * @return True if the user can register
	 */
	public boolean canRegister(){
		return false;
	}

	/**
	 * This method determines if the user can join a game
	 * @return True if the user can join a game
	 */
	public boolean canJoinGame(){
		return false;
	}

	/**
	 * This method determines if the user can rejoin a game
	 * @return True if the user can rejoin a game
	 */
	public boolean canRejoinGame(){
		return false;
	}

	/**
	 * This method determines if the user can pick a color
	 * @param color
	 * @return True if the user can pick that color
	 */
	public boolean canPickColor(CatanColor color){
		return false;
	}

	/**
	 * This method determines if an AI player can be added
	 * @return True if the user can add an AI player
	 */
	public boolean canAddComputerPlayer(){
		return false;
	}



	/**
	 * This method determines if the user can rob a player
	 * @param victimIndex
	 * @return True if the player selected can be robbed
	 */
	public boolean canRob(int victimIndex)throws IllegalArgumentException {
		return false;
	}

	/**
	 * This method determines if the user can be robbed
	 * @return True if the user can be robbed
	 */
	public boolean canBeRobbed(){
		return false;
	}





	/**
	 * Asks how many cards the player has to discard
	 * @return
	 * Returns the number of cards the player must discard (0 if not in discard state)
	 */
	public int mustDiscard(){
		return 0;
	}

	/**
	 * Asks if the player must play the Robber
	 * @return True if the robber is played
	 */
	public boolean mustPlayRobber(){
		return false;
	}

}