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
import shared.definitions.MessageLine;
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
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
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
			
			//checking to make sure the reciever is correct
			if(offer.getReciever() != playerIndex){
				response.setSuccess(false);
				response.setErrorMessage("Wrong Player");
				return response;
			}
	
			//deconstucting the offer
			ResourceList resources = offer.getOffer();
			int brick = resources.getBrick();
			int wheat = resources.getWheat();
			int ore = resources.getOre();
			int sheep = resources.getSheep();
			int wood = resources.getWood();
			
			//executing the trade
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
				model.setVersion(model.getVersion() + 1);
			}
			addGameLog(offer); //adding to the game log
			
			model.setTradeOffer(null); //resetting the trade offer
			response.setJson(model.toString());
			response.setSuccess(true);
			
			this.modelResponse = response;
			return response;
		}
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
			sendingPlayer.getResources().addResource(type, Math.abs(qty));
			player.getResources().removeResource(type, Math.abs(qty));
		}
		else if(qty > 0) {
			player.getResources().addResource(type, qty);
			sendingPlayer.getResources().removeResource(type, qty);
		}
	}
	
	/**
	 * adding messages to the gameLog
	 * @param currentOffer
	 */
	public void addGameLog(TradeOffer currentOffer){
		
		String message;
		if (willAccept)
			message = "The trade was accepted.";
		else
			message = "The trade was not accepted.";
		
		MessageLine line = new MessageLine(message, Game.instance().getGameId(gameIDCookie).getServerPlayers()[currentOffer.getSender()].getName());
		Game.instance().getGameId(gameIDCookie).addGameLogMessage(line);
	}
	
	
	public boolean getWillAccept() {
		return willAccept;
	}
}
