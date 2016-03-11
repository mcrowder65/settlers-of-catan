package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;
/**
 * Discard cards handler. It handles discarding cards
 * @author mcrowder65
 *
 */
public class DiscardCardsHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * Constructor
	 * @param facade IMovesFacade type
	 */
	public DiscardCardsHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * Handles... overrides HttpHandler handle method
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		GetModelResponse response = facade.discardCards(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
