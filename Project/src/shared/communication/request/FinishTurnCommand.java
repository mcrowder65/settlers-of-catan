package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;

public class FinishTurnCommand extends MoveCommand {

	public FinishTurnCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
		this.type = "finishTurn";
	}
	public FinishTurnCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	@Override
	public GetModelResponse execute() {
		return null;
	}

}
