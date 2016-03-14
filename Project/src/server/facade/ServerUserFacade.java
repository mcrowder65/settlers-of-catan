package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.request.LoginRequest;
import shared.communication.request.RegisterRequest;
import shared.communication.response.Response;
/**
 * used for logging in and registering
 * @author Brennen
 *
 */
public class ServerUserFacade implements IUserFacade {
	/**
	 * logs users in and gets response
	 * @param HttpExchange 
	 */
	@Override
	public Response login(HttpExchange exchange) {
		LoginRequest request = new LoginRequest(exchange);
		return request.login();
	}

	/**
	 * registers user and gets response
	 * @param HttpExchange 
	 */
	@Override
	public Response register(HttpExchange exchange) {
		RegisterRequest request = new RegisterRequest(exchange);
		return request.register();
	}

}
