package server.util;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import client.utils.Translator;
import server.ai.AIBase;
import server.ai.LargestArmyAI;
import server.facade.IAIFacade;
import server.facade.ServerAIFacade;
import shared.definitions.CatanColor;
import shared.definitions.DevCardList;
import shared.definitions.DevCardType;
import shared.definitions.GameMap;
import shared.definitions.GameModel;
import shared.definitions.Hex;
import shared.definitions.MessageLine;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;		
import shared.locations.VertexObject;

/**
 * contains added methods to the model
 * inherits from the GameModel class
 * @author Brennen
 *
 */
public class ServerGameModel extends GameModel{		  
  			  	
 	private ServerGameMap serverMap;		
 	private ServerPlayer[] serverPlayers;		
 	private ServerTurnTracker serverTurnTracker;
 	private ServerPlayer localPlayer;
 	private ArrayList<String> aiNames;
 	private ArrayList<CatanColor> unusedAIColors;
 	public int getLocalIndex(int playerId) {
		for (int n = 0; n < serverPlayers.length; n++) {
			if (serverPlayers[n] != null && serverPlayers[n].getPlayerID() == playerId) 
				return n;
		}
		return -1;
	}
 	/**
 	 * Returns a AI ServerPlayer so you can do ai stuff or null if it's not an ai
 	 * @return ServerPlayer currentPlayer
 	 */
 	public ServerPlayer isAiTurn(){
 		ServerPlayer currentPlayer = serverPlayers[serverTurnTracker.getCurrentTurn()];
 		if(currentPlayer.getPlayerID() < 0) //AI's have id's less than 0.
 			return currentPlayer;
 		return null;
 	}
 	public void setLargestArmy(int newArmy, int oldArmy, boolean firstTime){
 		if(!firstTime){
 			int oldArmyVictoryPoints = serverPlayers[oldArmy].getVictoryPoints();
 			serverPlayers[oldArmy].setVictoryPoints(oldArmyVictoryPoints - 2);
 			
 		}
 		int newArmyVictoryPoints = serverPlayers[newArmy].getVictoryPoints();
 		serverPlayers[newArmy].setVictoryPoints(newArmyVictoryPoints + 2);
 		if(serverPlayers[newArmy].getVictoryPoints() > 9){
 			this.setWinner(newArmy);
 		}
 	}
 	public void findLargestArmy(){
 		if(serverTurnTracker.getLargestArmy() == -1){
	 		for(int i = 0; i < serverPlayers.length; i++){
	 			if(serverPlayers[i].getSoldiers() >= 3){
	 				serverTurnTracker.setLargestArmy(i);
	 				setLargestArmy(i, -1, true);
	 			}
	 		}
 		}
 		else{
 			int oldArmy = serverTurnTracker.getLargestArmy();
 			for(int i = 0; i < serverPlayers.length; i++){
 				if(i != oldArmy && serverPlayers[i].getSoldiers() > serverPlayers[oldArmy].getSoldiers()){
 					serverTurnTracker.setLargestArmy(i);
 					setLargestArmy(i, oldArmy, false);
 				}
 			}
 		}
 	}
 	public void initServerPlayers(){
 		serverPlayers = new ServerPlayer[4];
 		serverPlayers[0] = localPlayer;
 	}
 	public void setLongestRoad(int playerIndex, boolean firstTime){
 		if(!firstTime){
	 		int oldLongestRoad = serverTurnTracker.getLongestRoad();
	 		int oldVictoryPoints = serverPlayers[oldLongestRoad].getVictoryPoints();
	 		serverPlayers[oldLongestRoad].setVictoryPoints(oldVictoryPoints - 2);
	 		
 		}
 		int newVictoryPoints = serverPlayers[playerIndex].getVictoryPoints();
 		serverPlayers[playerIndex].setVictoryPoints(newVictoryPoints + 2);
 		if(serverPlayers[playerIndex].getVictoryPoints() > 9){
 			this.setWinner(playerIndex);
 		}
 		serverTurnTracker.setLongestRoad(playerIndex);
 		
 	}
 	public void findLongestRoad(){
 		int currentLongestRoad = serverTurnTracker.getLongestRoad();

		if(currentLongestRoad != -1){
			for(int i = 0; i < serverPlayers.length; i++){//TODO configure victory points associated
				if(serverPlayers[i].getRoads() < serverPlayers[currentLongestRoad].getRoads()
						&& serverPlayers[i].getRoads() <= 10){ //amount remaining not total.
					setLongestRoad(i, false);
				}
			}
		}
		else{
			for(int i = 0; i < serverPlayers.length; i++){
				if(serverPlayers[i].getRoads() <= 10)
					setLongestRoad(i, true);
			}
		}
 	}
 	public ServerPlayer getLocalPlayer(int playerId) {
 		if(localPlayer != null)
 			return localPlayer;
		for (int n = 0; n < serverPlayers.length; n++) {
			if (serverPlayers[n] != null && serverPlayers[n].getPlayerID() == playerId){ 
				localPlayer = serverPlayers[n];
				return serverPlayers[n];
			}
		}
		return null;
	}
 	public int getAmtOfPlayers(){
 		int size = 0;
 		for(int i = 0; i < 4; i++){
 			if(serverPlayers[i] != null)
 				size++;
 		}
 		return size;
 	}
 	public void setPlayer(ServerPlayer player){
 		unusedAIColors.add(serverPlayers[player.getPlayerIndex()].getColor()); //add back color
 		unusedAIColors.remove(player.getColor()); //remove color to be added
 		serverPlayers[player.getPlayerIndex()] = player;
 	}
 	public void initAINames(){
 		aiNames = new ArrayList<String>();
 		aiNames.add("Steve");
		aiNames.add("Bentz");
		aiNames.add("Snell");
		aiNames.add("Andrew");
		aiNames.add("Trent");
		aiNames.add("MrBurns");

 	}
 	public void initAIColors(){
 		//TODO make it so user colors are taken out of here.
 		unusedAIColors = new ArrayList<CatanColor>();
 		unusedAIColors.add(CatanColor.red);
 		unusedAIColors.add(CatanColor.orange);
 		unusedAIColors.add(CatanColor.yellow);
 		unusedAIColors.add(CatanColor.blue);
 		unusedAIColors.add(CatanColor.green); 
 		unusedAIColors.add(CatanColor.purple);
 		unusedAIColors.add(CatanColor.puce); 
 		unusedAIColors.add(CatanColor.white);
 		unusedAIColors.add(CatanColor.brown);
 	}
 	public String chooseAIName(){
 		Random rand = new Random();
		int max =  aiNames.size() - 1;
		int min = 0;
		int ai = rand.nextInt(max - min + 1) + min;
		String aiChoice = aiNames.get(ai);
		aiNames.remove(ai);
		return aiChoice;
 	}
 	public CatanColor chooseAIColor(){
 		Random rand = new Random();
		int max =  unusedAIColors.size() - 1;
		int min = 0;
		int ai = rand.nextInt(max - min + 1) + min;
		CatanColor colorChoice = unusedAIColors.get(ai);
		unusedAIColors.remove(ai);
		return colorChoice;
 	}
	/**
	 * this function was made to get a local index or return the size of the players so that
	 * i can join the game.
	 * @param playerId int
	 * @return index of the player or return the size of the player array so we know what to add
	 */
	public int getLocalIndexJoinGame(int playerId){
		int index = 0;
		for(int n = 0; n < serverPlayers.length; n++){
			if(serverPlayers[n] != null)
				index++; //keep adding to index if there are players 
		}
		return index;
	}		  	
 	public void setServerGameMap(ServerGameMap serverMap){		
 		this.serverMap = serverMap;		
 	}		
 	public void setServerPlayers(ServerPlayer[] serverPlayers){		
 		this.serverPlayers = serverPlayers;		
	}		
	public void setServerTurnTracker(ServerTurnTracker serverTurnTracker){		
		this.serverTurnTracker = serverTurnTracker;		
	}		
	public ServerGameMap getServerMap() {		
		return serverMap;		
	}		
	public ServerPlayer[] getServerPlayers() {		
		return serverPlayers;		
	}		
	public ServerTurnTracker getServerTurnTracker() {		
		return serverTurnTracker;		
	}		
			
 	public ServerPlayer getLocalServerPlayer(int playerId) {		
 		for (int n = 0; n < serverPlayers.length; n++) {		
 			if (serverPlayers[n] != null && serverPlayers[n].getPlayerID() == playerId) 		
 				return serverPlayers[n];		
 		}		
 		return null;		
 	}
 	
 	public void addPlayer(ServerPlayer player) throws IllegalStateException {
 	
 			
 		int amt = 0;
 		for(int i = 0; i < 4; i++){
 			amt = serverPlayers[i] != null ? amt+1 : amt;
 		}
 		if(amt == 4)
 			throw new IllegalStateException("Already 4 players");
 		player.setCities(4);
 		player.setDiscarded(false);
 		player.setMonuments(0);
 		player.setNewDevCards(new DevCardList());
 		player.setOldDevCards(new DevCardList());
 		player.setResources(new ResourceList());
 		player.setRoads(15);
 		player.setSettlements(5);
 		player.setSoldiers(0);
 		player.setVictoryPoints(0);
 		player.setPlayedDevCard(false);
 		unusedAIColors.remove(player.getColor());
 		
 		serverPlayers[amt] = player;
 	}
 	
 	/**
 	 * gets the positive amt of a negative resource sent in
 	 * used when evaluating trading
 	 * @param resource
 	 * @return int
 	 */
 	public int getPositive(int resource){
 		if(resource>0){
 			return 0; //returns 0 if the resource is positive
 		}
 		if(resource<0){
 			return Math.abs(resource); //returns the positve amt of the neg int
 		}
 		return 0;
 	}
 	
 	/**
 	 * sets negative resource to 0 
 	 * used to evaluate trading
 	 * @param resource
 	 * @return
 	 */
 	public int getNegative(int resource){
 		if(resource<0){
 			return 0; //returns 0 if neg num is passed in
 		}
 		return resource; //returns the num passed in if the num is positive
 	}
 	
 	/**
 	 * creates a resouceList for all the resources the player is going to give up in a trade
 	 * @param resource
 	 * @return
 	 */
 	public ResourceList normalizeResourceList(ResourceList resource){
 		
 		//makes the neg amts positive
 		int brick = getPositive(resource.getBrick());
 		int wheat = getPositive(resource.getWheat());
 		int ore = getPositive(resource.getOre());
 		int wood = getPositive(resource.getWood());
 		int sheep = getPositive(resource.getSheep());
 		
 		//creates the new list
 		ResourceList normalized = new ResourceList(brick,ore,sheep,wheat,wood);
 		return normalized;
 	}
 	
 	/**
 	 * creates a resourcelist is of all the resources that the player will recieve in a trade
 	 * @param resource
 	 * @return
 	 */
 	public ResourceList getRecievingResourceList(ResourceList resource){
 		
 		//sets all the neg amounts to 0
 		int brick = getNegative(resource.getBrick());
 		int wheat = getNegative(resource.getWheat());
 		int ore = getNegative(resource.getOre());
 		int wood = getNegative(resource.getWood());
 		int sheep = getNegative(resource.getSheep());
 		
 		//creates new list
 		ResourceList normalized = new ResourceList(brick,ore,sheep,wheat,wood);
 		return normalized;
 	}
 	
 	/**
 	 * purchase a card from the bank's deck
 	 * @param card
 	 */
 	public void buyFromDeck(DevCardType card){
 		DevCardList deck = getDeck();//getting the current deck
 		
 		//removing the card from the deck
 		if(card == DevCardType.SOLDIER){
 			deck.setSoldier(deck.getSoldier()-1);
 		}
 		if(card == DevCardType.YEAR_OF_PLENTY){
 			deck.setYearOfPlenty(deck.getYearOfPlenty()-1);
 		}
 		if(card == DevCardType.MONUMENT){
 			deck.setMonument(deck.getMonument()-1);
 		}
 		if(card == DevCardType.MONOPOLY){
 			deck.setMonopoly(deck.getMonopoly()-1);
 		}
 		if(card == DevCardType.ROAD_BUILD){
 			deck.setRoadBuilding(deck.getRoadBuilding()-1);
 		}
 		
 		this.setDeck(deck);
 		
 	}
 	
 	/**
 	 * returns true if bank is empty
 	 * @return
 	 */
 	public boolean isBankEmpty(){
 		ResourceList bank = getBank();
 		
 		//getting all the resources from the bank
 		int brick = bank.getBrick();
 		int wood = bank.getWood();
 		int ore = bank.getOre();
 		int sheep = bank.getSheep();
 		int wheat = bank.getWheat();
 		if(brick == 0 && wood ==0 && ore ==0 && sheep ==0 && wheat ==0){
 			return true;
 		}
 		return false;
 	}
 	
 	/**
 	 * checks to see if the bank decks empty
 	 * @return
 	 */
	public boolean isDeckEmpty(){
 		DevCardList deck = getDeck();
 		
 		//getting dev cards from deck
 		int soldier = deck.getSoldier();
 		int YOP = deck.getYearOfPlenty();
 		int monument = deck.getMonument();
 		int roadBuilder = deck.getRoadBuilding();
 		int monopoly = deck.getMonopoly();
 		if(soldier == 0 && YOP ==0 && monument ==0 && roadBuilder ==0 && monopoly ==0){
 			return true;
 		}
 		return false;
 	}
	
	/**
	 * generates a random resource 
	 * @return
	 */
	public ResourceType generateRandomResource(){
		int randomNum = new Random().nextInt((5 - 1) + 1) + 1;
		if(randomNum == 1){
			return ResourceType.WOOD;
		}
		if(randomNum == 2){
			return ResourceType.SHEEP;
		}
		if(randomNum == 3){
			return ResourceType.BRICK;
		}
		if(randomNum == 4){
			return ResourceType.ORE;
		}
		if(randomNum == 5){
			return ResourceType.WHEAT;
		}
		return ResourceType.WOOD;
	}
 	
	/**
	 * generates a random dev card
	 * used when player purchases a dev card
	 * @return
	 */
 	public DevCardType generateRandomDevCard(){
 		DevCardList deck = getDeck();
 		int randomNum = new Random().nextInt((5 - 1) + 1) + 1; //generates the random int
 		if(randomNum == 1){
 			if(deck.getSoldier() > 0){
 				return DevCardType.SOLDIER;
 			}
 			else{
 				return DevCardType.NONE;
 			}
 		}
 		if(randomNum == 2){
 			if(deck.getYearOfPlenty()>0){
 				return DevCardType.YEAR_OF_PLENTY;
 			}
 			else{
 				return DevCardType.NONE;
 			}
 		}
 		if(randomNum == 3){
 			if(deck.getMonopoly() >0){
 				return DevCardType.MONOPOLY;
 			}
 			else{
 				return DevCardType.NONE;
 			}
 		}
 		if(randomNum == 4){
 			if(deck.getRoadBuilding() > 0){
 				return DevCardType.ROAD_BUILD;
 			}
 			else{
 				return DevCardType.NONE;
 			}
 		}
 		if(randomNum == 5){
 			if(deck.getMonument() >0){
 				return DevCardType.MONUMENT;
 			}
 			else{
 				return DevCardType.NONE;
 			}
 			
 		}
 		
 		return DevCardType.ROAD_BUILD;
 	}
 	
 	/**
 	 * adds a message to chat
 	 * @param line
 	 */
 	public void addChatMessage(MessageLine line){
 		getChat().addMessage(line);
 	}
 	/**
 	 * adds a messge to the game log
 	 * @param line
 	 */
 	public void addGameLogMessage(MessageLine line){
 		getLog().addMessage(line);
 	}
 	
 	/**
 	 * converts the model to json
 	 */
 	@Override
 	public String toString() {
 		return Translator.modelToJson(this);
 	}
 	
 	/**
 	 * checks to see if all the players have discarded
 	 * @return
 	 */
 	public boolean allPlayersDiscarded(){
 		//goes through all the players
 		for(int i=0; i<serverPlayers.length; i++){
 			if(serverPlayers[i].getDiscarded() == false){
 				return false;
 			}
 		}
 		return true;
 	}

 	/**
 	 * this returns the player by index but does not change the original
 	 * @param index of player in ServerPlayers array
 	 * @return ServerPlayer
 	 */
 	public ServerPlayer getPlayerByIndex(int index){
 		return new ServerPlayer(serverPlayers[index]);
 	}
 	
 	/**
 	 * issues resources to the players depending on which number was rolled
 	 * @param numRolled
 	 */
	public void issueResourcesNormalPlay(int numRolled){
 		Hex[] allHexes = serverMap.getHexes(); //getting all hexes from map
 		
 		//looping through all the hexes
 		for(int i=0; i<allHexes.length; i++){
 			Hex loc = allHexes[i];
 			if(loc.getNumber() == numRolled){
 				if(!serverMap.getRobber().equals(loc.getLocation())){ //checks to see if the robber is on the hex
 					List<VertexObject> settlements = serverMap.getSettlementOnHex(loc.getLocation()); //gets all settlements on the hex
 					//goes through the settlments on the hex and awards the resource of that hex to the player that owns the settlement
 					if(settlements.size()>0){
 						for(int x=0; x<settlements.size(); x++){
	 						int owner = settlements.get(x).getOwner();
	 						ResourceType resource = loc.getResource();
	 						serverPlayers[owner].getResources().addResource(resource,1); //awarding resource
 						}
 					}
 					List<VertexObject> cities = serverMap.getCityOnHex(loc.getLocation()); //getting all cities on a particular hex
 					//goes through the cities on the hex and awards the resource of that hex to the player that owns the settlement
 					if(cities.size()>0){
 						for(int x=0; x<cities.size(); x++){
	 						int owner = cities.get(x).getOwner();
	 						ResourceType resource = loc.getResource();
	 						serverPlayers[owner].getResources().addResource(resource,2);//awarding resource
 						}
 					}
 				}
 			}
 		}
 	}

  			  	
 } 