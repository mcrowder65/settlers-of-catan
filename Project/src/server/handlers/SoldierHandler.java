package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;
/**
 * handler for playing a soldier card
 * @author Brennen
 *
 */
public class SoldierHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * constructor for SoldierHandler
	 * @param facade
	 */
	public SoldierHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls soldier on the IMovesFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		GetModelResponse response = facade.soldier(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
