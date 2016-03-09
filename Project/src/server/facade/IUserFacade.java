package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.Response;

/**
 * Handles the requests from the server that have to do
 * with the user interaction with the client.
 * @author Manuel
 *
 */

public interface IUserFacade {
	Response login(HttpExchange exchange);
	Response register(HttpExchange exchange);
	
}
