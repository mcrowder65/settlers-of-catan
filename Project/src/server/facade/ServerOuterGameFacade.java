package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.request.ListGamesRequest;
import shared.communication.response.CreateGameResponse;
import shared.communication.response.ListGamesResponse;
import shared.communication.response.Response;

public class ServerOuterGameFacade implements IOuterGameFacade {

	@Override
	public ListGamesResponse listGames(HttpExchange exchange) {
		ListGamesRequest response = new ListGamesRequest(exchange);
		return response.listGames();
	}

	@Override
	public CreateGameResponse createGame(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response joinGame(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

}
