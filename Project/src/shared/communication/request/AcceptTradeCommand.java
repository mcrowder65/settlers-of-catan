package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.definitions.TradeOffer;

/**
 * This class executes the accept trade command,
 * extends MoveCommand
 * @author Manuel
 *
 */

public class AcceptTradeCommand extends MoveCommand {

	private boolean willAccept;
	public AcceptTradeCommand(int playerIndex, boolean willAccept) throws IllegalArgumentException {
		super(playerIndex);
		this.willAccept = willAccept;
		this.type = "acceptTrade";
	}

	public AcceptTradeCommand(HttpExchange exchange) {
		super(exchange);
		AcceptTradeCommand tmp = (AcceptTradeCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
		this.willAccept = tmp.willAccept;

	}

	/**
	 * Executes the logic to process the AcceptTrade command 
	 */
	@Override
	public GetModelResponse execute() {
		int gameIndex = this.gameIDCookie;
		int playerIndex = this.getPlayerIndex();		
		Game game = Game.instance();	
		GetModelResponse response = new GetModelResponse();
		ServerGameModel model = game.getGameId(gameIndex);		
		ServerGameMap map = model.getServerMap();		
		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
		ServerPlayer player = model.getServerPlayers()[playerIndex];
		String status = turnTracker.getStatus();
		TradeOffer offer = model.getTradeOffer();
		try {
			response.setCookie("Set-cookie", "catan.user=" +
					URLEncoder.encode("{" +
				       "\"authentication\":\"" + "1142128101" + "\"," +
			           "\"name\":\"" + userCookie + "\"," +
					   "\"password\":\"" + passCookie + "\"," + 
			           "\"playerID\":" + playerIDCookie + "}", "UTF-8" ) + ";catan.game=" + gameIDCookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if(offer.getReciever() != playerIndex){
			response.setSuccess(false);
			response.setErrorMessage("Wrong Player");
			return response;
		}

		ResourceList resources = offer.getOffer();
		int brick = resources.getBrick();
		int wheat = resources.getWheat();
		int ore = resources.getOre();
		int sheep = resources.getSheep();
		int wood = resources.getWood();
		if(player.canAcceptTrade(resources) == false){
			response.setSuccess(false);
			response.setErrorMessage("Cannot Accept Trade");
			return response;
		}

		if(willAccept == true){
			int sender = offer.getSender();
			ServerPlayer sendingPlayer = model.getServerPlayers()[sender];
			//if negative take away from the sending and add to the sender
			//if resources positive take away from the sender and add them to the sending
			//player has the income offer. sending player sends the offer
			distributeResources(ResourceType.BRICK, brick, player, sendingPlayer);
			distributeResources(ResourceType.WHEAT, wheat, player, sendingPlayer);
			distributeResources(ResourceType.ORE, ore, player, sendingPlayer);
			distributeResources(ResourceType.SHEEP, sheep, player, sendingPlayer);
			distributeResources(ResourceType.WOOD, wood, player, sendingPlayer);
		}

		response.setSuccess(true);
		return response;
	}

	/**
	 * This method is in charge of removing and adding the corresponding resources according
	 * to the offer accepted. 
	 * @param type
	 * @param qty
	 * @param player
	 * @param sendingPlayer
	 */
	public void distributeResources(ResourceType type, int qty, ServerPlayer player, ServerPlayer sendingPlayer) {
		if(qty < 0) {
			sendingPlayer.getResources().removeResource(type, Math.abs(qty));
			player.getResources().addResource(type, Math.abs(qty));
		}
		else if(qty > 0) {
			player.getResources().removeResource(type, qty);
			sendingPlayer.getResources().addResource(type, qty);
		}
	}
	

	public boolean getWillAccept() {
		return willAccept;
	}
}
