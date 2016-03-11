package server.facade;

import com.sun.net.httpserver.HttpExchange;

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
	 * @param HttpExchange exchange
	 */
	@Override
	public GetModelResponse sendChat(HttpExchange exchange) {
		SendChatCommand command = new SendChatCommand(exchange);
		return command.execute();
	}

	/**
	 * creates buyDevCard command and calls execute on it
	 * @param HttpExchange exchange
	 */
	@Override
	public GetModelResponse buyDevCard(HttpExchange exchange) {
		BuyDevCardCommand command = new BuyDevCardCommand(exchange);
		return command.execute();
	}
	/**
	 * creates buildRoad command and calls execute on it
	 * 
	 */
	@Override
	public GetModelResponse buildRoad(HttpExchange exchange) {
		BuildRoadCommand command = new BuildRoadCommand(exchange);
		return command.execute();
	}
	/**
	 * creates buildSettlement command and calls execute on it
	 */
	@Override
	public GetModelResponse buildSettlement(HttpExchange exchange) {
		BuildSettlementCommand command = new BuildSettlementCommand(exchange);
		return command.execute();
	}
	/**
	 * creates buildCity command and calls execute on it
	 */
	@Override
	public GetModelResponse buildCity(HttpExchange exchange) {
		BuildCityCommand command = new BuildCityCommand(exchange);
		return command.execute();
	}
	/**
	 * creates discardCards command and calls execute on it
	 */
	@Override
	public GetModelResponse discardCards(HttpExchange exchange) {
		DiscardCardsCommand command = new DiscardCardsCommand(exchange);
		return command.execute();
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
		MaritimeTradeCommand command = new MaritimeTradeCommand(exchange);
		return command.execute();
	}
	/**
	 * creates offerTrade command and calls execute on it
	 */
	@Override
	public GetModelResponse offerTrade(HttpExchange exchange) {
		OfferTradeCommand command = new OfferTradeCommand(exchange);
		return command.execute();
	}
	/**
	 * creates monopoly command and calls execute on it
	 */
	@Override
	public GetModelResponse monopoly(HttpExchange exchange) {
		MonopolyCommand command = new MonopolyCommand(exchange);
		return command.execute();
	}
	/**
	 * creates monument command and calls execute on it
	 */
	@Override
	public GetModelResponse monument(HttpExchange exchange) {
		MonumentCommand command = new MonumentCommand(exchange);
		return command.execute();
	}
	/**
	 * creates yearOfPlenty command and calls execute on it
	 */
	@Override
	public GetModelResponse yearOfPlenty(HttpExchange exchange) {
		YearOfPlentyCommand command = new YearOfPlentyCommand(exchange);
		return command.execute();
	}
	/**
	 * creates soldier command and calls execute on it
	 */
	@Override
	public GetModelResponse soldier(HttpExchange exchange) {
		SoldierCommand command = new SoldierCommand(exchange);
		return command.execute();
	}
	/**
	 * creates roadBuilding command and calls execute on it
	 */
	@Override
	public GetModelResponse roadBuilding(HttpExchange exchange) {
		RoadBuildingCommand command = new RoadBuildingCommand(exchange);
		return command.execute();
	}
	/**
	 * creates rollNumber command and calls execute on it
	 */
	@Override
	public GetModelResponse rollNumber(HttpExchange exchange) {
		RollNumberCommand command = new RollNumberCommand(exchange);
		return command.execute();
	}
	/**
	 * creates robPlayer command and calls execute on it
	 */
	@Override
	public GetModelResponse robPlayer(HttpExchange exchange) {
		RobPlayerCommand command = new RobPlayerCommand(exchange);
		return command.execute();
	}
	/**
	 * creates finishTurn command and calls execute on it
	 */
	@Override
	public GetModelResponse finishTurn(HttpExchange exchange) {
		FinishTurnCommand command = new FinishTurnCommand(exchange);
		return command.execute();
	}

}
