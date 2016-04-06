package server.facade;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
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
		Response response = request.addAI();
		if (response.isSuccess()) {
			Game.instance().getPersistenceProvider().joinUser(request.getUserId(), request.getGameId(), request.getColor(), request.getPlayerIndex());
		}
		return response;
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
