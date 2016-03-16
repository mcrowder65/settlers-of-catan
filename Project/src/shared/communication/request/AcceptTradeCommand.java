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
 			
 			if(brick < 0) {
 				for(int i = 0; i < Math.abs(brick); i++) {
 					sendingPlayer.removeBrick();
 					player.addBrick();
 				}
 			}
 			else if(brick > 0) {
 				for(int i = 0; i < Math.abs(brick); i++) {
 					player.removeBrick();
 					sendingPlayer.addBrick();
 				}
 			}
 			
 			if(wheat < 0) {
 				for(int i = 0; i < Math.abs(wheat); i++) {
 					sendingPlayer.removeWheat();
 					player.addWheat();
 				}
 			}
 			else if(wheat > 0) {
 				for(int i = 0; i < Math.abs(wheat); i++) {
 					player.removeWheat();
 					sendingPlayer.addWheat();
 				}
 			}
 			
 			if(ore < 0) {
 				for(int i = 0; i < Math.abs(ore); i++) {
 					sendingPlayer.removeOre();
 					player.addOre();
 				}
 			}
 			else if(ore > 0) {
 				for(int i = 0; i < Math.abs(ore); i++) {
 					player.removeOre();
 					sendingPlayer.addOre();
 				}
 			}
 			
 			if(sheep < 0) {
 				for(int i = 0; i < Math.abs(sheep); i++) {
 					sendingPlayer.removeSheep();
 					player.addSheep();
 				}
 			}
 			else if(sheep > 0) {
 				for(int i = 0; i < Math.abs(sheep); i++) {
 					player.removeSheep();
 					sendingPlayer.addSheep();
 				}
 			}
 			
 			if(wood < 0) {
 				for(int i = 0; i < Math.abs(wood); i++) {
 					sendingPlayer.removeWood();
 					player.addWood();
 				}
 			}
 			else if (wood > 0) {
 				for(int i = 0; i < Math.abs(wood); i++) {
 					player.removeWood();
 					sendingPlayer.addWood();
 				}
 			}
 			
 		}
 		
 		response.setSuccess(true);
		return response;
	}

	public boolean getWillAccept() {
		return willAccept;
	}
}
