package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;;

/**
 * Handles the requests from the server that have to do
 * with the move commands.
 * @author Manuel
 *
 */
public interface IMovesFacade {

	GetModelResponse sendChat(HttpExchange exchange);
	GetModelResponse buyDevCard(HttpExchange exchange);
	GetModelResponse buildRoad(HttpExchange exchange);
	GetModelResponse buildSettlement(HttpExchange exchange);
	GetModelResponse buildCity(HttpExchange exchange);
	GetModelResponse discardCards(HttpExchange exchange);
	GetModelResponse acceptTrade(HttpExchange exchange);
	GetModelResponse maritimeTrade(HttpExchange exchange);
	GetModelResponse offerTrade(HttpExchange exchange);
	GetModelResponse monopoly(HttpExchange exchange);
	GetModelResponse monument(HttpExchange exchange);
	GetModelResponse yearOfPlenty(HttpExchange exchange);
	GetModelResponse soldier(HttpExchange exchange);
	GetModelResponse roadBuilding(HttpExchange exchange);
	GetModelResponse rollNumber(HttpExchange exchange);
	GetModelResponse robPlayer(HttpExchange exchange);
	GetModelResponse finishTurn(HttpExchange exchange);
}
