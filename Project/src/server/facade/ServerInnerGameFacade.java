package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.request.*;
import shared.communication.response.*;
/**
 * gets repsonse from specific games
 * @author Brennen
 *
 */
public class ServerInnerGameFacade implements IInnerGameFacade {

	/**
	 * gets a list of the AI types
	 * @param exchange HttpExchange
	 */
	@Override
	public ListAIResponse listAiTypes(HttpExchange exchange) {
		ListAIRequest request = new ListAIRequest(exchange);
		return request.listAITypes();
	}

	/**
	 * adds and AI 
	 * @param exchange HttpExchange
	 */
	@Override
	public Response addAi(HttpExchange exchange) {
		AddAIRequest request = new AddAIRequest(exchange);
		return request.addAI();
	}
	
	/**
	 * retrieves the model 
	 * @param exchange HttpExchange
	 */
	@Override
	public GetModelResponse getModel(HttpExchange exchange) {
		GetModelRequest request = new GetModelRequest(exchange);
		return request.getModel();
	}

}
