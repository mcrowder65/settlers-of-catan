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
/**
 * This class executes the Discard Cards command,
 * extends MoveCommand
 * @author Manuel
 *
 */

public class DiscardCardsCommand extends MoveCommand {
	public DiscardCardsCommand(int playerIndex, ResourceList discardedCards) throws IllegalArgumentException {
		super(playerIndex);
		if (discardedCards == null)
			throw new IllegalArgumentException("discardedCards can't be null.");
		this.discardedCards = discardedCards;
		this.type = "discardCards";
	}
	public DiscardCardsCommand(HttpExchange exchange) {
		super(exchange);
		DiscardCardsCommand tmp = (DiscardCardsCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
		this.discardedCards = tmp.discardedCards;
	}

	/**
	 * Executes the logic for the discard cards command
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();	
			ResourceList cards = this.getDiscardedCards();	
			Game game = Game.instance();	
			GetModelResponse response = new GetModelResponse();
			ServerGameModel model = game.getGameId(gameIndex);		
			ServerGameMap map = model.getServerMap();		
			ServerTurnTracker turnTracker = model.getServerTurnTracker();		
			ServerPlayer player = model.getServerPlayers()[playerIndex];
			String status = turnTracker.getStatus();
			/*
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
			*/
			
			if(player.getNumOfCards()<7){
				response.setSuccess(false);
				response.setErrorMessage("Not enough cards to discard");
				return response;
			}
			
			if(!status.equals("Discarding")){
				response.setSuccess(false);
				response.setErrorMessage("Wrong status");
				return response;
			}
			
			if(player.getDiscarded() == true){
	 			response.setSuccess(false);
	 			response.setErrorMessage("Already discarded");
	 			return response;
	 		}
			
			player.discardCards(cards);
			if(model.allPlayersDiscarded() == true){
				turnTracker.setStatus("Robbing");
				
				//If it's the ai's turn and a human is done discarding
				turnTracker.handleAITurn(gameIDCookie);
				
	 		}
			model.setVersion(model.getVersion() + 1);
			response.setSuccess(true);
			response.setJson(model.toString());
			return response;
		}
	}

	ResourceList discardedCards;
	
	
	public ResourceList getDiscardedCards() {
		return discardedCards;
	}



}
