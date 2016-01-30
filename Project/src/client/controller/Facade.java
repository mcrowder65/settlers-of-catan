package client.controller;

import shared.locations.*;
import client.communication.IProxy;
import client.data.GameInfo;
import client.data.GameManager;
import shared.definitions.*;

public class Facade {

	private GameManager game;
	private IProxy proxy;
	private int pass;
	
	public Facade ()
	{
		game = new GameManager(proxy,pass);
	}
	
	
	
	/**
	 * Allows a user to log into the server.
	 * @param username, password
	 * @return True if the user successfully logged in.
	 */
	public boolean login(String username, String password){
		return false;
		
	}
	
	/**
	 * Allows the user to create an account and register
	 * @param username, password
	 * @return True if the user successfully registered.
	 */
	public boolean register(String username, String password){
		return false;
		
	}
	 
	/**
	 * This method returns information(List) of all the existing games.
	 * @return An array of GameInfo with all the information for the existing games.
	 */
	public GameInfo[] listGames(){
		return null;
		
	}
	
	/**
	 * This method allows the user to join an existing game.
	 * @param gameId- Id of the game.
	 * @return True if the user joined successfully a game.
	 */
	public boolean joinGame(int gameId){
		return false;
		
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
	 * This method allows the user to roll the dice
	 * @return The values rolled by the dice
	 */
	public int rollDice(){
		return 0;
		
	}
	
	/**
	 * This method allows a user to buy a road
	 * @return True if a road can be bought
	 */
	public boolean buyRoad(){
		return false;
		
	}
	
	/**
	 * This method allows a user to buy a settlement
	 * @return True if a settlement can be bought
	 */
	public boolean buySettlement(){
		return false;
		
	}
	
	/**
	 * This method allows a user to buy a city
	 * @return True if a city can be bought
	 */
	public boolean buyCity(){
		return false;
		
	}
	
	/**
	 * This method allows the user to lay a road on the map
	 * @param location - The location where the user wants to place the road
	 * @return True if the road was placed
	 */
	public boolean layRoad(EdgeLocation location){
		return false;
		
	}
	
	/**
	 * This method allows the user to lay a settlement on the map
	 * @param location - The location where the user wants to place the settlement
	 * @return True if the settlement was placed
	 */
	public boolean laySettlement(EdgeLocation location){
		return false;
		
	}
	
	/**
	 * This method allows the user to lay a city on the map
	 * @param location - The location where the user wants to place the city
	 * @return True if the city was placed
	 */
	public boolean layCity(EdgeLocation location){
		return false;
		
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
	 * This method allows the user to offer a trade to other players
	 * @param offer - The offer
	 * @return True if the offer was offered successfully
	 */
	public boolean offerTrade(TradeOffer offer){
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
	
	/**
	 * This method allows the user to buy a Development Card
	 * @return True if the card was bought successfully
	 */
	public boolean buyDevCard(){
		return false;
		
	}
	
	/**
	 * This method allows the user to play a Monopoly Card
	 * @param resource - The resource that the user picked to get
	 * @return True if the card was played
	 */
	public boolean playMonopolyCard(ResourceType resource){
		return false;
		
	}
	
	/**
	 * This method allows the user to play a Year of Plenty Card
	 * @param resource1 - The resource that the user picked to get
	 * @param resource2 - The resource that the user picked to get
	 * @return True if the card was played
	 */
	public boolean playYearOfPlentyCard(ResourceType resource1, ResourceType resource2){
		return false;
		
	}
	
	/**
	 * This method allows the user to play a Soldier Card
	 * @param victimIndex - The index of the player that will be the victim
	 * @param location - The location where the robber will be placed
	 * @return True if the Soldier Card was played successfully
	 */
	public boolean playSoldierCard(int victimIndex, HexLocation location){
		return false;
		
	}
	
	/**
	 * This method allows the user to move the Robber to a different Hex
	 * @param location - The Hex location where the user wants to move the Robber
	 * @return True if the Robber was moved
	 */
	public boolean moveRobber(HexLocation location){
		return false;
		
	}
	
	/**
	 * This method allows the user to send a message in the chat
	 * @param message - The message by the user
	 * @return True if the message was sent
	 */
	public boolean sendMessage(String message){
		return false;
		
	}

	
	
	//*************************************************************************
	//************************canDo's******************************************
	/**
	 * This method determines if the Player can discard cards
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canDiscardCards(int playerIndex){
		return true;
	}
	
	/**
	 * This method determines if the Dice can be rolled
	 * @return True if the dice can be rolled
	 */
	public boolean canRollNumber(int playerIndex){
		return true;
	}
	
	/**
	 * This method determines if the user can lay a road at the location
	 * @param location - Where the user wants to lay the road
	 * @return True if the road can be placed in the location
	 */
	public boolean canBuildRoad(EdgeLocation location) throws IllegalArgumentException {
		return false;
	}
	
	/**
	 * This method determines if the user can lay a settlement at the location
	 * @param location - Where the user wants to lay the settlement
	 * @return True if the settlement can be placed in the location
	 */
	public boolean canBuildSettlement(EdgeLocation location) throws IllegalArgumentException {
		return false;
	}
	
	/**
	 * This method determines if the user can lay a city at the location
	 * @param location - Where the user wants to lay the city
	 * @return True if the city can be placed in the location
	 */
	public boolean canBuildCity(EdgeLocation location) throws IllegalArgumentException {
		return false;
	}
	
	/**
	 * This method determines if the user can offer a trade
	 * @param offer
	 * @return True if the user can offer the trade
	 */
	public boolean canOfferTrade(TradeOffer offer) throws IllegalArgumentException {
		return false;
	}
	
	
	/**
	 * This method determines if the user can offer a Maritime trade
	 * @param offer
	 * @return True if the user can offer the trade
	 */
	public boolean canMaritimeTrade(TradeOffer offer) throws IllegalArgumentException {
		return false;
	}
	
	/**
	 * This method determines if the player can finish the turn
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canFinishTurn(int playerIndex){
		return false;
	}
	
	/**
	 * This method determines if the player can buy a Development card
	 * @return True if the player can buy a Development card
	 */
	public boolean canBuyDevCard(int playerIndex){
		return false;
	}
	
	/**
	 * This method determines if the player can use the Year of Plenty Dev Card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseYearOfPlenty(int playerIndex){
		return false;
	}
	
	/**
	 * This method determines if the player can use the Road Builder Dev card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseRoadBuilder(int playerIndex){
		return false;
	}
	/**
	 * This method determines if the player can use the Soldier Dev card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseSoldier(int playerIndex){
		return false;
	}
	
	/**
	 * This method determines if the player can use the Monopoly Dev Card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseMonopoly(int playerIndex){
		return false;
	}
	
	/**
	 * This method determines if the player can use the Monument Dev card
	 * @param playerIndex
	 * @return True if the player can
	 */
	public boolean canUseMonument(int playerIndex){
		return false;
	}
	
	/**
	 * This method determines if the user can move the Robber
	 * @return True if the user can move the Robber
	 */
	public boolean canPlaceRobber(){
		return false;
	}
	
	
	//^^^^^^^^^^^^^^^^^^^^  TA'S   ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	//*******************************************************
	
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
	 * This method determines if the Dice can be rolled
	 * @return True if the dice can be rolled
	 */
	public boolean canRollDice(){
		return false;
		
	}
	
	/**
	 * This method determines if the user can buy a settlement
	 * @return True if the user can buy a settlement
	 */
	public boolean canBuySettlement(){
		return false;
		
	}
	
	/**
	 * This method determines if the user can buy a city
	 * @return True if the user can buy a city
	 */
	public boolean canBuyCity(){
		return false;
		
	}
	
	/**
	 * This method determines if the user can buy a road
	 * @return True if the user can buy a road
	 */
	public boolean canBuyRoad(){
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
	 * This method determines if the user can accept an offer
	 * @param offer
	 * @return True if the user can accept the offer
	 */
	public boolean canAcceptTrade(TradeOffer offer) throws IllegalArgumentException {
		return false;
	}
	
	/**
	 * This method determines if the user can play a Development Card
	 * @param type - The type of card
	 * @return True if the user can play a Development card
	 */
	public boolean canPlayDevCard(DevCardType type){
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
	 * This method determines if the user can finish its turn
	 * @return True if the user can finish its turn
	 */
	public boolean canFinishTurn(){
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