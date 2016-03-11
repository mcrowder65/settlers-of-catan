package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
import shared.communication.response.ListAIResponse;
import shared.communication.response.Response;
/**
 * gets repsonse from specific games
 * @author Brennen
 *
 */
public class ServerInnerGameFacade implements IInnerGameFacade {

	/**
	 * gets a list of the AI types
	 * @param HttpExchange exchange
	 */
	@Override
	public ListAIResponse listAiTypes(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * adds and AI 
	 * @param HttpExchange exchange
	 */
	@Override
	public Response addAi(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * retrieves the model 
	 * @param HttpExchange exchange
	 */
	@Override
	public GetModelResponse getModel(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

}
