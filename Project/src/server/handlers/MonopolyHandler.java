package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;
/**
 * handler for when the user wants to play the monopoly cards
 * @author Brennen
 *
 */
public class MonopolyHandler implements HttpHandler {

	IMovesFacade facade;
	
	
	public MonopolyHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls monument on the IMovesFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		GetModelResponse response = facade.monopoly(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
