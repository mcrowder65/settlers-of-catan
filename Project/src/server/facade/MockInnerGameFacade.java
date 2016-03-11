package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
import shared.communication.response.ListAIResponse;
import shared.communication.response.Response;
/**
 * stores canned responses for inner game
 * @author Brennen
 *
 */
public class MockInnerGameFacade implements IInnerGameFacade {

	/**
	 * lists the AI types
	 * @param HttpExchange
	 */
	@Override
	public ListAIResponse listAiTypes(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * adds an AI to the game
	 * @param HttpExchange exchange
	 */
	@Override
	public Response addAi(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * gets the gameModel
	 * @param HttpExchange exchange
	 */
	@Override
	public GetModelResponse getModel(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

}
