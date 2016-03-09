package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;

/**
 * This class executes the finish turn command, 
 * extends MoveCommand
 * @author Manuel
 *
 */

public class FinishTurnCommand extends MoveCommand {

	public FinishTurnCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
		this.type = "finishTurn";
	}
	public FinishTurnCommand(HttpExchange exchange) {
		super(exchange);

	}
	/**
	 * Executes the logic to process the finish turn command
	 */
	@Override
	public GetModelResponse execute() {
		return null;
	}

}
