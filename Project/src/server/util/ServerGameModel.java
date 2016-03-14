package server.util;

import java.util.Random;

import shared.definitions.GameMap;
  import shared.definitions.GameModel;		  
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
 	
 	public void buyFromBank(ResourceType resource){
 		ResourceList bank = getBank();
 		if(resource == ResourceType.WOOD){
 			bank.setWood(bank.getWood()-1);
 		}
 		if(resource == ResourceType.WHEAT){
 			bank.setWheat(bank.getWheat()-1);
 		}
 		if(resource == ResourceType.BRICK){
 			bank.setBrick(bank.getBrick()-1);
 		}
 		if(resource == ResourceType.SHEEP){
 			bank.setSheep(bank.getSheep()-1);
 		}
 		if(resource == ResourceType.ORE){
 			bank.setOre(bank.getOre()-1);
 		}
 		
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
 			return ResourceType.WHEAT;
 		}
 		if(randomNum == 4){
 			return ResourceType.ORE;
 		}
 		if(randomNum == 5){
 			return ResourceType.BRICK;
 		}
 		
 		return ResourceType.BRICK;
 	}
  			  	
 } 