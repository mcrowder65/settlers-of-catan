package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.request.AcceptTradeCommand;
import shared.communication.response.GetModelResponse;
/**
 * creates moves commands and calles execute on them
 * @author Brennen
 *
 */
public class ServerMovesFacade implements IMovesFacade {
	
	/**
	 * creates a chat command and calls execute on it
	 * @param HttpExchange exchange
	 */
	@Override
	public GetModelResponse sendChat(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * creates buyDevCard command and calls execute on it
	 * @param HttpExchange exchange
	 */
	@Override
	public GetModelResponse buyDevCard(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates buildRoad command and calls execute on it
	 * 
	 */
	@Override
	public GetModelResponse buildRoad(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates buildSettlement command and calls execute on it
	 */
	@Override
	public GetModelResponse buildSettlement(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates buildCity command and calls execute on it
	 */
	@Override
	public GetModelResponse buildCity(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates discardCards command and calls execute on it
	 */
	@Override
	public GetModelResponse discardCards(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates acceptTrade command and calls execute on it
	 */
	@Override
	public GetModelResponse acceptTrade(HttpExchange exchange) {
		AcceptTradeCommand command = new AcceptTradeCommand(exchange);
		return command.execute();
	}
	/**
	 * creates maritimeTrade command and calls execute on it
	 */
	@Override
	public GetModelResponse maritimeTrade(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates offerTrade command and calls execute on it
	 */
	@Override
	public GetModelResponse offerTrade(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates monopoly command and calls execute on it
	 */
	@Override
	public GetModelResponse monopoly(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates monument command and calls execute on it
	 */
	@Override
	public GetModelResponse monument(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates yearOfPlenty command and calls execute on it
	 */
	@Override
	public GetModelResponse yearOfPlenty(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates soldier command and calls execute on it
	 */
	@Override
	public GetModelResponse soldier(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates roadBuilding command and calls execute on it
	 */
	@Override
	public GetModelResponse roadBuilding(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates rollNumber command and calls execute on it
	 */
	@Override
	public GetModelResponse rollNumber(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates robPlayer command and calls execute on it
	 */
	@Override
	public GetModelResponse robPlayer(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * creates finishTurn command and calls execute on it
	 */
	@Override
	public GetModelResponse finishTurn(HttpExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

}
