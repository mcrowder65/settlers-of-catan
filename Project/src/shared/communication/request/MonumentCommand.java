package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;

public class MonumentCommand extends MoveCommand {

	public MonumentCommand(int playerIndex) throws IllegalArgumentException {
		super(playerIndex);
		this.type = "Monument";
	}
	
	public MonumentCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	@Override
	public GetModelResponse execute() {
		return null;
	}


}
