package server.dao;

import shared.definitions.CatanColor;
/**
 * wrapper class to hold all the data to be stored in the join user table
 * @author Brennen
 */
public class JoinUserParams {
	
	private int userID;
	private int gameID;
	private CatanColor color;
	private int playerIndex;
	
	/**
	 * constuctor for the JoinUserParams
	 * @param userID
	 * @param gameID
	 * @param color
	 * @param playerIndex
	 */
	public JoinUserParams(int userID, int gameID, CatanColor color, int playerIndex){
		this.userID = userID;
		this.gameID = gameID;
		this.color = color;
		this.playerIndex = playerIndex;
	}
	
	/**
	 * gets the user id
	 * @return userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * sets the user id
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	/**
	 * gets the gameID
	 * @return gameID
	 */
	public int getGameID() {
		return gameID;
	}
	/**
	 * sets the game id
	 * @param gameID
	 */
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	/**
	 * gets the player's color
	 * @return color
	 */
	public CatanColor getColor() {
		return color;
	}
	/**
	 * sets the player's color
	 * @param color
	 */
	public void setColor(CatanColor color) {
		this.color = color;
	}
	/**
	 * gets the player's index
	 * @return playerIndex
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}
	/**
	 * sets the player's index
	 * @param playerIndex
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
}
