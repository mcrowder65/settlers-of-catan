package shared.communication.request;

import shared.communication.response.ListGamesResponse;

public class ListGamesRequest extends Request {

	public ListGamesRequest() throws IllegalArgumentException {
		
	}
	
	public ListGamesResponse listGames() {
		return new ListGamesResponse(0,null);
	}
}
