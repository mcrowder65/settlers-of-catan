package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
import shared.locations.VertexLocation;
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
		
	}

	/**
	 * Executes the logic for the discard cards command
	 */
	@Override
	public GetModelResponse execute() {
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
 		response.setSuccess(true);
		return response;

	}

	ResourceList discardedCards;
	
	
	public ResourceList getDiscardedCards() {
		return discardedCards;
	}



}
