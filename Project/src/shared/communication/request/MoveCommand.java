package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;

public abstract class MoveCommand extends Request {

	private int playerIndex;
	protected String type;
	protected MoveCommand(int playerIndex) throws IllegalArgumentException {
		
		if (playerIndex < 0 || playerIndex > 3)
			throw new IllegalArgumentException("playerIndex must be between 0 and 3");
		this.playerIndex = playerIndex;
	}
	
	protected MoveCommand(HttpExchange exchange) {
		
	}
	
	public abstract GetModelResponse execute();

	public String getMoveType() {
		return type;
	}
	public int getPlayerIndex() {
		return playerIndex;
	}
	
	
}
