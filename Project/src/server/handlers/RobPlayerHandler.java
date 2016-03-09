package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
/**
 * Handler for robbing a player
 * @author Brennen
 *
 */
public class RobPlayerHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * constructor for RobPlayerHandler
	 * @param facade
	 */
	public RobPlayerHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls robPlayer on IMovesFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
