package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;
/**
 * This class is a build settlement handler. It handles building settlements.
 * @author mcrowder65
 *
 */
public class BuildSettlementHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * Constructor
	 * @param facade IMovesFacade type
	 */
	public BuildSettlementHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * Handles.. overrides HttpHandler handle method
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		GetModelResponse response = facade.buildSettlement(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
