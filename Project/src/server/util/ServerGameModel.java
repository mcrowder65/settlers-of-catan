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
 	
 	public ResourceType generateRandomResource(){
 		ResourceList bank = getBank();
 		int randomNum = new Random().nextInt((5 - 1) + 1) + 1;
 		if(randomNum == 1){
 			if(bank.getWood() > 0){
 				return ResourceType.WOOD;
 			}
 			else{
 				return ResourceType.NONE;
 			}
 		}
 		if(randomNum == 2){
 			if(bank.getSheep()>0){
 				return ResourceType.SHEEP;
 			}
 			else{
 				return ResourceType.NONE;
 			}
 		}
 		if(randomNum == 3){
 			if(bank.getWheat() >0){
 				return ResourceType.WHEAT;
 			}
 			else{
 				return ResourceType.NONE;
 			}
 		}
 		if(randomNum == 4){
 			if(bank.getOre() > 0){
 				return ResourceType.ORE;
 			}
 			else{
 				return ResourceType.NONE;
 			}
 		}
 		if(randomNum == 5){
 			if(bank.getBrick() >0){
 				return ResourceType.BRICK;
 			}
 			else{
 				return ResourceType.NONE;
 			}
 			
 		}
 		
 		return ResourceType.BRICK;
 	}
  			  	
 } 