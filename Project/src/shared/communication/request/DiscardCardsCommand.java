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
	private transient boolean suppressAIHandling = false;
	
	public void doSuppressAIHandling() {
		suppressAIHandling = true;
	}
	
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
	//For testing
	public DiscardCardsCommand(int playerIndex, int gameIndex, ResourceList discardedCards) {
		super(playerIndex);
		this.gameIDCookie = gameIndex;
		this.discardedCards = discardedCards;
	}

	/**
	 * Executes the logic for the discard cards command
	 */
	@Override
	public GetModelResponse execute() {
		try{
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
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
			
			//checking to see if the player doesnt have enough cards
			if(player.getNumOfCards()<8){
				response.setSuccess(false);
				response.setErrorMessage("Not enough cards to discard");
				return response;
			}
			
			//checking the status
			if(!status.equals("Discarding")){
				response.setSuccess(false);
				response.setErrorMessage("Wrong status");
				return response;
			}
			//checking to see if the player already discarded this round
			if(player.getDiscarded() == true){
	 			response.setSuccess(false);
	 			response.setErrorMessage("Already discarded");
	 			return response;
	 		}
			//executing the discard
			player.discardCards(cards);
			if(model.allPlayersDiscarded() == true){
				turnTracker.setStatus("Robbing");
				
				//If it's the ai's turn and a human is done discarding
				if (!suppressAIHandling) //Don't want to fire this when the ai will handle it anyway
					turnTracker.handleAITurn(gameIDCookie, turnTracker.getCurrentTurn());
			
					
	 		}
			model.setVersion(model.getVersion() + 1); //updating the version num
			response.setSuccess(true);
			
			response.setJson(model.toString());
			return response;
		}
		}catch(Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
	}

	ResourceList discardedCards;
	
	
	public ResourceList getDiscardedCards() {
		return discardedCards;
	}



}
