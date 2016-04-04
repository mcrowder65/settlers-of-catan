package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
/**
 * This executes a move, extends request. Abunch of other
 * classes extend this abstract class.
 * @author mcrowder65
 */
public abstract class MoveCommand extends Request {

	protected int playerIndex;
	protected String type;
	protected GetModelResponse modelResponse;
	protected MoveCommand(int playerIndex) throws IllegalArgumentException {
		
		if (playerIndex < 0 || playerIndex > 3)
			throw new IllegalArgumentException("playerIndex must be between 0 and 3");
		this.playerIndex = playerIndex;
	}
	/**
	 * THIS INITIALIZER IS ONLY FOR AI'S DO NOT USE IT
	 * IF YOU DON'T KNOW WHAT YOU ARE DOING WITH IT
	 * @param playerIndex index of player
	 * @param gameID index of game
	 */
	protected MoveCommand(int playerIndex, int gameID){
		this.playerIndex = playerIndex;
		this.gameIDCookie = gameID;
	}
	protected MoveCommand(HttpExchange exchange) {
		super(exchange);
	}
	public abstract GetModelResponse execute();
	
	public GetModelResponse getModelResponse() {
		return this.modelResponse;
	}

	public String getMoveType() {
		return type;
	}
	public int getPlayerIndex() {
		return playerIndex;
	}
	public int getGameCookie() {
		return gameIDCookie;
	}
	/**
	 * checks to see if its a players turn
	 * @param turnTracker
	 * @param playerIndex
	 * @return boolean
	 */
	public boolean checkTurn(ServerTurnTracker turnTracker, int playerIndex){		
		if(turnTracker.getCurrentTurn() == playerIndex){		
		 	return true;		
		 }		
		 return false;		
	}		
	
	/**
	 * DO NOT USE THIS SETTER EXCEPT FOR AI!!!!!!!!!!!!!!
	 * @param id
	 */
	public void setGameCookie(int id) {
		this.gameIDCookie = id;
	}
	
	
}
