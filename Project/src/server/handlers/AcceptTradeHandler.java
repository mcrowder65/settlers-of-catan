package server.handlers;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import server.facade.IMovesFacade;

public class AcceptTradeHandler implements HttpHandler {

	IMovesFacade facade;
	
	
	public AcceptTradeHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		facade.acceptTrade(exchange);

	}

}
