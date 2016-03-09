package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;

/**
 * This class executes the Buy Dev Card Command
 * @author Manuel
 *
 */

public class BuyDevCardCommand extends MoveCommand {

	public BuyDevCardCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
		this.type = "buyDevCard";
	}

	public BuyDevCardCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	/**
	 * Executes the logic for the buy dev card command
	 */
	@Override
	public GetModelResponse execute() {
		return null;
	}
}
