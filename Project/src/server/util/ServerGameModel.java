package server.util;

import java.util.List;
import java.util.Random;

import client.utils.Translator;
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
  		  
  public class ServerGameModel extends GameModel{		  
  			  	
 	private ServerGameMap serverMap;		
 	private ServerPlayer[] serverPlayers;		
 	private ServerTurnTracker serverTurnTracker;
 	private ServerPlayer localPlayer;
 	public int getLocalIndex(int playerId) {
		for (int n = 0; n < serverPlayers.length; n++) {
			if (serverPlayers[n] != null && serverPlayers[n].getPlayerID() == playerId) 
				return n;
		}
		return -1;
	}
 	public void initServerPlayers(){
 		serverPlayers = new ServerPlayer[4];
 		serverPlayers[0] = localPlayer;
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
	/**
	 * this function was made to get a local index or return the size of the players so that
	 * i can join the game.
	 * @param playerId int
	 * @return index of the player or return the size of the player array so we know what to add
	 */
	public int getLocalIndexJoinGame(int playerId){
		int index = 0;
		for(int n = 0; n < serverPlayers.length; n++){
//			if(players[n] != null && players[n].getPlayerID() == playerId)
//				return n;
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
 		//player.addResourceCards(new ResourceList());
 		player.setCities(0);
 		player.setDiscarded(false);
 		player.setMonuments(0);
 		player.setNewDevCards(new DevCardList());
 		player.setOldDevCards(new DevCardList());
 		player.setResources(new ResourceList());
 		player.setRoads(0);
 		player.setSettlements(0);
 		player.setSoldiers(0);
 		player.setVictoryPoints(0);
 		player.setPlayedDevCard(false);
 		serverPlayers[amt] = player;
 	}
 	
 	public int getPositive(int resource){
 		if(resource>0){
 			return 0;
 		}
 		if(resource<0){
 			return Math.abs(resource);
 		}
 		return 0;
 	}
 	
 	
 	public ResourceList normalizeResourceList(ResourceList resource){
 		
 		int brick = getPositive(resource.getBrick());
 		int wheat = getPositive(resource.getWheat());
 		int ore = getPositive(resource.getOre());
 		int wood = getPositive(resource.getWood());
 		int sheep = getPositive(resource.getSheep());
 		
 		ResourceList normalized = new ResourceList(brick,ore,sheep,wheat,wood);
 		return normalized;
 	}
 	
 	public boolean allPlayersDiscarded(){
 		for(int i=0; i<serverPlayers.length; i++){
 			if(serverPlayers[i].getDiscarded() == false){
 				return false;
 			}
 		}
 		return true;
 	}
 		 	
	/**
	 * Issues resources depending on the number rolled
	 * @param numRolled
	 */
 	public void issueResourcesNormalPlay(int numRolled){
 		Hex[] allHexes = serverMap.getHexes();
 		
 		for(int i=0; i<allHexes.length; i++){
 			Hex loc = allHexes[i];
 			if(loc.getNumber() == numRolled){
 				if(!serverMap.getRobber().equals(loc.getLocation())){
 					List<VertexObject> municipalities = serverMap.getMunicipalityOnHex(loc.getLocation());
 					if(municipalities.size()>0){
 						for(int x=0; x<municipalities.size(); x++){
 							int owner = municipalities.get(x).getOwner();
 							ResourceType resource = loc.getResource();
 							serverPlayers[owner].getResources().addResource(resource,1);
 						}
 					}
 				}
 			}
 		}
 	}
 	
 	public void buyFromDeck(DevCardType card){
 		DevCardList deck = getDeck();
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
 	
	public boolean isDeckEmpty(){
 		DevCardList deck = getDeck();
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
 	
 	public DevCardType generateRandomDevCard(){
 		DevCardList deck = getDeck();
 		int randomNum = new Random().nextInt((5 - 1) + 1) + 1;
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
 	
 	public void addChatMessage(MessageLine line){
 		getChat().addMessage(line);
 	}
 	
 	@Override
 	public String toString() {
 		return Translator.modelToJson(this);
 	}
  			  	
 } 