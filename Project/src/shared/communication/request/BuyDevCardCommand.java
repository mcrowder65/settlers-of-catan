package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.definitions.GameModel;

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
	public GameModel execute() {
		return null;
	}
}
