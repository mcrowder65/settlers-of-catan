package server.facade;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
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
		CreateGameResponse response = request.createGame();
		if (response.isSuccess()) {
			Game.instance().getPersistenceProvider().addGame(
					response.getGame().getId(),
					Game.instance().getGameId(response.getGame().getId()),
					response.getGame().getTitle());
		}
		return response;
	}

	/**
	 * joins a game and gets the response
	 * @param exchange HttpExchange 
	 */
	@Override
	public Response joinGame(HttpExchange exchange) {
		JoinGameRequest request = new JoinGameRequest(exchange);
		Response response = request.joinGame();
		if (response.isSuccess()) {
			Game.instance().getPersistenceProvider().joinUser(request.getPlayerID(), request.getId(), request.getColor(), request.getPlayerIndex());
			
		}
		return response;
	}

}
