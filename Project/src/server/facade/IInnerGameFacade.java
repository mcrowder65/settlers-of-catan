package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.*;

public interface IInnerGameFacade {

	ListAIResponse listAiTypes(HttpExchange exchange);
	Response addAi(HttpExchange exchange);
	GetModelResponse getModel(HttpExchange exchange);
}
