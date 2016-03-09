package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;

/**
 * This class handles the accept trade
 * @author Manuel
 *
 */

public class AcceptTradeCommand extends MoveCommand {

	private boolean willAccept;
	public AcceptTradeCommand(int playerIndex, boolean willAccept) throws IllegalArgumentException {
		super(playerIndex);
		this.willAccept = willAccept;
		this.type = "acceptTrade";
	}

	public AcceptTradeCommand(HttpExchange exchange) {
		super(exchange);
		
	}
	
	/**
	 * 
	 */
	@Override
	public GetModelResponse execute() {
		return null;
	}

	public boolean getWillAccept() {
		return willAccept;
	}
}
