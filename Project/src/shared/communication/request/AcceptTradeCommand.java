package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
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
 		if(player.canAcceptTrade(resources) == false){
 			response.setSuccess(false);
			response.setErrorMessage("Cannot Accept Trade");
			return response;
 		}
 		
 		if(willAccept == true){
 			int sender = offer.getSender();
 			ServerPlayer sendingPlayer = model.getServerPlayers()[sender];
 			
 		}
 		
 		

		return null;
	}

	public boolean getWillAccept() {
		return willAccept;
	}
}
