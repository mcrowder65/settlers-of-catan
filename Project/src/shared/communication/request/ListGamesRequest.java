package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import shared.communication.response.ListGamesResponse;

public class ListGamesRequest extends Request {

	public ListGamesRequest() throws IllegalArgumentException {
		
	}
	
	public ListGamesResponse listGames() {
		ListGamesResponse listResponse = new ListGamesResponse(Game.instance().getGamesList());
		return listResponse;
	}
	public ListGamesRequest(HttpExchange exchange){
		super(exchange);

     	
	}
}
