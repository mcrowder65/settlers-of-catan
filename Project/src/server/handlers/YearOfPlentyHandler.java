package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;

public class YearOfPlentyHandler implements HttpHandler {

	IMovesFacade facade;
	
	
	public YearOfPlentyHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		GetModelResponse response = facade.yearOfPlenty(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());

	}

}
