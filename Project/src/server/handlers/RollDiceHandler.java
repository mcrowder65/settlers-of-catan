package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;

public class RollDiceHandler implements HttpHandler {

	IMovesFacade facade;
	
	
	public RollDiceHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}