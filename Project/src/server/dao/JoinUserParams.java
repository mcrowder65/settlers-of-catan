package server.dao;

import shared.definitions.CatanColor;

public class JoinUserParams {
	
	private int userID;
	private int gameID;
	private CatanColor color;
	private int playerIndex;
	
	public JoinUserParams(int userID, int gameID, CatanColor color, int playerIndex){
		this.userID = userID;
		this.gameID = gameID;
		this.color = color;
		this.playerIndex = playerIndex;
	}
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public CatanColor getColor() {
		return color;
	}
	public void setColor(CatanColor color) {
		this.color = color;
	}
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
	
	
	
	
	
}
