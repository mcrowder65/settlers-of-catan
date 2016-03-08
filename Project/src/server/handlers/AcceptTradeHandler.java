package server.handlers;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;

public class AcceptTradeHandler implements HttpHandler {

	IMovesFacade facade;
	
	
	public AcceptTradeHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		GetModelResponse response = facade.acceptTrade(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
