package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;

public class MonumentHandler implements HttpHandler {

	IMovesFacade facade;
	
	
	public MonumentHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		GetModelResponse response = facade.monument(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
