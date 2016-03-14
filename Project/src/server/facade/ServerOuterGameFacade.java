package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.request.*;
import shared.communication.response.*;

public class ServerOuterGameFacade implements IOuterGameFacade {

	@Override
	public ListGamesResponse listGames(HttpExchange exchange) {
		ListGamesRequest request = new ListGamesRequest(exchange);
		return request.listGames();
	}

	@Override
	public CreateGameResponse createGame(HttpExchange exchange) {
		CreateGameRequest request = new CreateGameRequest(exchange);
		return request.createGame();
	}

	@Override
	public Response joinGame(HttpExchange exchange) {
		JoinGameRequest request = new JoinGameRequest(exchange);
		return request.joinGame();
	}

}
