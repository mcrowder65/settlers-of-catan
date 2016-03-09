package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
/**
 * This executes the monument action. Extends MoveCommand
 * @author mcrowder65
 */
public class MonumentCommand extends MoveCommand {

	public MonumentCommand(int playerIndex) throws IllegalArgumentException {
		super(playerIndex);
		this.type = "Monument";
	}
	
	public MonumentCommand(HttpExchange exchange) {
		super(exchange);
		
	}
	/**
	 * This executes the monument command. returns GetModelResponse
	 */
	@Override
	public GetModelResponse execute() {
		return null;
	}


}
