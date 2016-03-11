package server.util;

import shared.definitions.GameMap;
import shared.definitions.GameModel;
import shared.definitions.Player;

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
	
}