package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.CreateGameResponse;

public class CreateGameRequest extends Request {

	String name;
	boolean randomTiles;
	boolean randomNumbers;
	boolean randomPorts;
	
	public CreateGameRequest(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) {
		if (name == null) throw new IllegalArgumentException("name cannot be null.");
		this.name = name;
		this.randomNumbers = randomNumbers;
		this.randomTiles = randomTiles;
		this.randomPorts = randomPorts;
	}
	
	public CreateGameResponse createGame() {
		return new CreateGameResponse(0, null);
	}
	public CreateGameRequest(HttpExchange exchange){
		super(exchange);
	}
}
