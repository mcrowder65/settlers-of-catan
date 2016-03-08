package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;

public class RollNumberCommand extends MoveCommand {

	private int number;
	public RollNumberCommand(int playerIndex, int number) throws IllegalArgumentException {
		super(playerIndex);
		if (number < 2 || number > 12) 
			throw new IllegalArgumentException("number must be between 2 - 12");
		this.number = number;
		type = "rollNumber";
	}
	
	public RollNumberCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	@Override
	public GetModelResponse execute() {
		return null;
	}

	public int getNumber() {
		return number;
	}

	
}
