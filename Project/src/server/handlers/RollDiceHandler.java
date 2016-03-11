package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;
/**
 * handler for when the player rolls dice
 * @author Brennen
 *
 */
public class RollDiceHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * constructor for RollDiceHandler
	 * @param facade
	 */
	public RollDiceHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls rollDice on IMovesFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		GetModelResponse response = facade.rollNumber(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
