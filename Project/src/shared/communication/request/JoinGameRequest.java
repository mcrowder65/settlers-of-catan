package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.Response;
import shared.definitions.CatanColor;

public class JoinGameRequest extends Request {
	int id;
	CatanColor color;
	public  JoinGameRequest(int id, CatanColor color) throws IllegalArgumentException {
		if (color == null) throw new IllegalArgumentException("Color cannot be null.");
		this.id = id;
		this.color = color;
		
	}
	
	public Response joinGame() {
		return new Response();
	}
	public JoinGameRequest(HttpExchange exchange){
		super(exchange);
	}
}
