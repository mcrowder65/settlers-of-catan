package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.request.AcceptTradeCommand;
import shared.communication.response.GetModelResponse;

public class ServerMovesFacade implements IMovesFacade {

	@Override
	public GetModelResponse sendChat(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse buyDevCard(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse buildRoad(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse buildSettlement(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse buildCity(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse discardCards(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse acceptTrade(HttpExchange exchange) {
		AcceptTradeCommand command = new AcceptTradeCommand(exchange);
		return command.execute();
	}

	@Override
	public GetModelResponse maritimeTrade(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse offerTrade(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse monopoly(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse monument(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse yearOfPlenty(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse soldier(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse roadBuilding(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse rollNumber(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse robPlayer(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetModelResponse finishTurn(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

}
