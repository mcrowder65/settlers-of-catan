package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;

public class BuyDevCardCommand extends MoveCommand {

	public BuyDevCardCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
		this.type = "buyDevCard";
	}

	public BuyDevCardCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	@Override
	public GetModelResponse execute() {
		return null;
	}
}
