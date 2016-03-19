package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import shared.communication.response.ListGamesResponse;

public class ListGamesRequest extends Request {

	public ListGamesRequest() throws IllegalArgumentException {
		
	}
	//TODO if i add ai's to one client and the other client is waiting in the list games menu
	//     it doesn't update the 2nd clients window saying which ai's are in there.
	public ListGamesResponse listGames() {
		synchronized(Game.instance().lock){
			ListGamesResponse listResponse = new ListGamesResponse(Game.instance().getGamesList());
			return listResponse;
		}
	}
	public ListGamesRequest(HttpExchange exchange){
		super(exchange);

     	
	}
}
