package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import server.facade.IUserFacade;
import shared.communication.response.GetModelResponse;
/**
 * Handles logging a user in
 * @author Brennen
 *
 */
public class LoginHandler implements HttpHandler {

	IUserFacade facade;
	
	/**
	 * constructor for LoginHandler
	 * sets the facade
	 * @param facade
	 */
	public LoginHandler(IUserFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls login on the IUserFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		GetModelResponse response = (GetModelResponse) facade.login(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
