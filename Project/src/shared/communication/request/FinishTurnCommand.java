package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.ServerGameModel;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.TurnTracker;

/**
 * This class executes the finish turn command, 
 * extends MoveCommand
 * @author Manuel
 *
 */

public class FinishTurnCommand extends MoveCommand {

	public FinishTurnCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
		this.type = "finishTurn";
	}
	public FinishTurnCommand(HttpExchange exchange) {
		super(exchange);

	}
	/**
	 * Executes the logic to process the finish turn command
	 */
	@Override
	public GetModelResponse execute() {
		int gameIndex = this.gameIDCookie;
		int playerIndex = this.getPlayerIndex();
		
		Game game = Game.instance();
		ServerGameModel model = game.getGameId(gameIndex);
		ServerTurnTracker turnTracker = model.getServerTurnTracker();
		GetModelResponse response = new GetModelResponse();
		
		
		//Check to see if it's the player's turn
		if(checkTurn(turnTracker, playerIndex)) {
			model.getTurnTracker().advanceTurn();
			model.getTurnTracker().setStatus("Rolling");	
			response.setSuccess(true);
		}
		else {
			response.setSuccess(false);
			response.setErrorMessage("Wrong turn");
		}		
	
		return response;
	}

}
