package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IInnerGameFacade;
import server.facade.IMovesFacade;
import server.facade.IUserFacade;
/**
 * Handles adding an AI
 * @author mcrowder65
 *
 */
public class AddAIHandler implements HttpHandler {

	IInnerGameFacade facade;
	
	/**
	 * the specific handler function for adding AI's
	 * @param facade - IInnerGameFacade facade
	 */
	public AddAIHandler(IInnerGameFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * Handles... overrides HttpHandler handle method
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		facade.addAi(exchange);
	}

}
