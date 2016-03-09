package server.handlers;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;
/**
 * accept trade handler. It handles accepting trades.
 * @author mcrowder65
 *
 */
public class AcceptTradeHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * Constructor
	 * @param facade IMovesFacade type
	 */
	public AcceptTradeHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * Handles... overrides HttpHandler handle method
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		GetModelResponse response = facade.acceptTrade(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
