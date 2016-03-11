package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.*;

/**
 * Handles the requests from the server that have to do
 * with the outer functionality of the game.
 * @author Manuel
 *
 */
public interface IOuterGameFacade {

	ListGamesResponse listGames(HttpExchange exchange);
	CreateGameResponse createGame(HttpExchange exchange);
	Response joinGame(HttpExchange exchange);
}
