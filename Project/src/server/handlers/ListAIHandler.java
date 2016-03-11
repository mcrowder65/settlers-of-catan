package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IInnerGameFacade;
import server.facade.IMovesFacade;
import server.facade.IUserFacade;
import shared.communication.response.GetModelResponse;
import shared.communication.response.ListAIResponse;
/**
 * list ai handler. It handles listing the AI's
 * @author mcrowder65
 *
 */
public class ListAIHandler implements HttpHandler {

	IInnerGameFacade facade;
	
	/**
	 * Constructor
	 * @param facade IInnerGameFacade type
	 */
	public ListAIHandler(IInnerGameFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * Handles... overrides HttpHandler handle method
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		ListAIResponse response = facade.listAiTypes(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
