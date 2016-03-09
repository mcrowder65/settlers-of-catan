package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.*;

/**
 * Handles the requests from the server that have to do
 * with the Inner functionality of the game, but not the Move commands. 
 * @author Manuel
 *
 */


public interface IInnerGameFacade {

	ListAIResponse listAiTypes(HttpExchange exchange);
	Response addAi(HttpExchange exchange);
	GetModelResponse getModel(HttpExchange exchange);
}
