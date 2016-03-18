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
	protected MoveCommand(int playerIndex) throws IllegalArgumentException {
		
		if (playerIndex < 0 || playerIndex > 3)
			throw new IllegalArgumentException("playerIndex must be between 0 and 3");
		this.playerIndex = playerIndex;
	}
	
	protected MoveCommand(HttpExchange exchange) {
		super(exchange);
		
		
	}
	
	
	public abstract GetModelResponse execute();

	public String getMoveType() {
		return type;
	}
	public int getPlayerIndex() {
		return playerIndex;
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
	
	
}
