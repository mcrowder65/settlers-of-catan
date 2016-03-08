package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IInnerGameFacade;
import server.facade.IMovesFacade;
import server.facade.IUserFacade;

public class AddAIHandler implements HttpHandler {

	IInnerGameFacade facade;
	
	
	public AddAIHandler(IInnerGameFacade facade) {
		this.facade = facade;
	}
	
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
	}

}
