package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;

/**
 * This class executes the accept trade command,
 * extends MoveCommand
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
	 * Executes the logic to process the AcceptTrade command 
	 */
	@Override
	public GetModelResponse execute() {
		return null;
	}

	public boolean getWillAccept() {
		return willAccept;
	}
}
