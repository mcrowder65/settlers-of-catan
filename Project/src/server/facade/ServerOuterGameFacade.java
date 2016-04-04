package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.request.*;
import shared.communication.response.*;
/**
 * gets and creates games and allowing to join game
 * @author Brennen
 *
 */
public class ServerOuterGameFacade implements IOuterGameFacade {

	/**
	 * gets a list of games
	 * @param exchange HttpExchange 
	 */
	@Override
	public ListGamesResponse listGames(HttpExchange exchange) {
		ListGamesRequest request = new ListGamesRequest(exchange);
		return request.listGames();
	}

	/**
	 * Creates a game and gets the response
	 * @param exchange HttpExchange 
	 */
	@Override
	public CreateGameResponse createGame(HttpExchange exchange) {
		CreateGameRequest request = new CreateGameRequest(exchange);
		return request.createGame();
	}

	/**
	 * joins a game and gets the response
	 * @param exchange HttpExchange 
	 */
	@Override
	public Response joinGame(HttpExchange exchange) {
		JoinGameRequest request = new JoinGameRequest(exchange);
		return request.joinGame();
	}

}
