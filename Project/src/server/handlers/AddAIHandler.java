package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IInnerGameFacade;
import server.facade.IMovesFacade;
import server.facade.IUserFacade;
import shared.communication.response.GetModelResponse;
/**
 * Handles adding an AI
 * @author mcrowder65
 *
 */
public class AddAIHandler implements HttpHandler {

	IInnerGameFacade facade;
	
	/**
	 * Constructor
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
		GetModelResponse response = (GetModelResponse) facade.addAi(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
