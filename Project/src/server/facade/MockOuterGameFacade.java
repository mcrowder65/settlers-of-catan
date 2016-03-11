package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.CreateGameResponse;
import shared.communication.response.ListGamesResponse;
import shared.communication.response.Response;
/**
 * facde to talk execute commands on the outergame server
 * @author Brennen
 *
 */
public class MockOuterGameFacade implements IOuterGameFacade {
	
	/**
	 * gets a the list of games from mock server
	 * @param HttpExchange exchange
	 */
	@Override
	public ListGamesResponse listGames(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * calls createGame on mock server
	 * @param HttpExchange exchange
	 */
	@Override
	public CreateGameResponse createGame(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * gets join game response from mock server
	 * @param HttpExchange exchange
	 */
	@Override
	public Response joinGame(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

}
