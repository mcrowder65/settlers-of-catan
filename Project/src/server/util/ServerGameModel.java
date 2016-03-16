package server.util;

import java.util.Random;

import client.utils.Translator;
import shared.definitions.DevCardList;
import shared.definitions.DevCardType;
import shared.definitions.GameMap;
import shared.definitions.GameModel;
import shared.definitions.MessageLine;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;		
  		  
  public class ServerGameModel extends GameModel{		  
  			  	
 	private ServerGameMap serverMap;		
 	private ServerPlayer[] serverPlayers;		
 	private ServerTurnTracker serverTurnTracker;		
  			  	
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
 		if (serverPlayers.length == 4)
 			throw new IllegalStateException("Already 4 players");
 		serverPlayers[serverPlayers.length] = player;
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