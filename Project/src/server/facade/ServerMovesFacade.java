package server.facade;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import shared.communication.request.AcceptTradeCommand;
import shared.communication.request.BuildCityCommand;
import shared.communication.request.BuildRoadCommand;
import shared.communication.request.BuildSettlementCommand;
import shared.communication.request.BuyDevCardCommand;
import shared.communication.request.DiscardCardsCommand;
import shared.communication.request.FinishTurnCommand;
import shared.communication.request.MaritimeTradeCommand;
import shared.communication.request.MonopolyCommand;
import shared.communication.request.MonumentCommand;
import shared.communication.request.OfferTradeCommand;
import shared.communication.request.RoadBuildingCommand;
import shared.communication.request.RobPlayerCommand;
import shared.communication.request.RollNumberCommand;
import shared.communication.request.SendChatCommand;
import shared.communication.request.SoldierCommand;
import shared.communication.request.YearOfPlentyCommand;
import shared.communication.response.GetModelResponse;
/**
 * creates moves commands and calles execute on them
 * @author Brennen
 *
 */
public class ServerMovesFacade implements IMovesFacade {

	/**
	 * creates a chat command and calls execute on it
	 * @param exchange HttpExchange
	 */
	@Override
	public GetModelResponse sendChat(HttpExchange exchange) {
		SendChatCommand command = new SendChatCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}

	/**
	 * creates buyDevCard command and calls execute on it
	 * @param exchange HttpExchange
	 */
	@Override
	public GetModelResponse buyDevCard(HttpExchange exchange) {
		BuyDevCardCommand command = new BuyDevCardCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates buildRoad command and calls execute on it
	 * 
	 */
	@Override
	public GetModelResponse buildRoad(HttpExchange exchange) {
		BuildRoadCommand command = new BuildRoadCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;	
	}
	/**
	 * creates buildSettlement command and calls execute on it
	 */
	@Override
	public GetModelResponse buildSettlement(HttpExchange exchange) {
		BuildSettlementCommand command = new BuildSettlementCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates buildCity command and calls execute on it
	 */
	@Override
	public GetModelResponse buildCity(HttpExchange exchange) {
		BuildCityCommand command = new BuildCityCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates discardCards command and calls execute on it
	 */
	@Override
	public GetModelResponse discardCards(HttpExchange exchange) {
		DiscardCardsCommand command = new DiscardCardsCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates acceptTrade command and calls execute on it
	 */
	@Override
	public GetModelResponse acceptTrade(HttpExchange exchange) {
		AcceptTradeCommand command = new AcceptTradeCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates maritimeTrade command and calls execute on it
	 */
	@Override
	public GetModelResponse maritimeTrade(HttpExchange exchange) {
		MaritimeTradeCommand command = new MaritimeTradeCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates offerTrade command and calls execute on it
	 */
	@Override
	public GetModelResponse offerTrade(HttpExchange exchange) {
		OfferTradeCommand command = new OfferTradeCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates monopoly command and calls execute on it
	 */
	@Override
	public GetModelResponse monopoly(HttpExchange exchange) {
		MonopolyCommand command = new MonopolyCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates monument command and calls execute on it
	 */
	@Override
	public GetModelResponse monument(HttpExchange exchange) {
		MonumentCommand command = new MonumentCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates yearOfPlenty command and calls execute on it
	 */
	@Override
	public GetModelResponse yearOfPlenty(HttpExchange exchange) {
		YearOfPlentyCommand command = new YearOfPlentyCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates soldier command and calls execute on it
	 */
	@Override
	public GetModelResponse soldier(HttpExchange exchange) {
		SoldierCommand command = new SoldierCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates roadBuilding command and calls execute on it
	 */
	@Override
	public GetModelResponse roadBuilding(HttpExchange exchange) {
		RoadBuildingCommand command = new RoadBuildingCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates rollNumber command and calls execute on it
	 */
	@Override
	public GetModelResponse rollNumber(HttpExchange exchange) {
		RollNumberCommand command = new RollNumberCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates robPlayer command and calls execute on it
	 */
	@Override
	public GetModelResponse robPlayer(HttpExchange exchange) {
		RobPlayerCommand command = new RobPlayerCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}
	/**
	 * creates finishTurn command and calls execute on it
	 */
	@Override
	public GetModelResponse finishTurn(HttpExchange exchange) {
		FinishTurnCommand command = new FinishTurnCommand(exchange);
		GetModelResponse response = command.execute();
		if(response.isSuccess()){
			Game.instance().getProvider().addCommand(command);
		}
		return response;
	}

}
