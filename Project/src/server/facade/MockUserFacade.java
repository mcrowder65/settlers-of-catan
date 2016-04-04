package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.Response;
/**
 * gets the canned response for logging in
 * @author Brennen
 *
 */
public class MockUserFacade implements IUserFacade {

	/**
	 * calls login on the mock server and gets response
	 * @param exchange HttpExchange 
	 */
	@Override
	public Response login(HttpExchange exchange) {
		return new Response(200, "Success");
	}
	
	/**
	 * calls register on the mock server and gets response 
	 * HttpExchange exchange
	 */
	@Override
	public Response register(HttpExchange exchange) {
		return new Response(200, "Success");
	}

}
