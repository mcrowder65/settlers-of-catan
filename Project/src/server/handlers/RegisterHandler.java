package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import server.facade.IUserFacade;
/**
 * Handler for when a user wants to register
 * @author Brennen
 *
 */
public class RegisterHandler implements HttpHandler {

	IUserFacade facade;
	
	/**
	 * constructor for RegisterHandler
	 * @param facade
	 */
	public RegisterHandler(IUserFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls register on the IUserFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
