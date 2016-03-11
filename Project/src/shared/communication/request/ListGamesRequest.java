package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.ListGamesResponse;

public class ListGamesRequest extends Request {

	public ListGamesRequest() throws IllegalArgumentException {
		
	}
	
	public ListGamesResponse listGames() {
		return new ListGamesResponse(0,null);
	}
	public ListGamesRequest(HttpExchange exchange){
		super(exchange);
	}
}
