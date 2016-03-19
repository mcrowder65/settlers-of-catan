package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
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
	/**
	 * THIS INITIALIZER IS ONLY FOR AI'S DO NOT USE IT
	 * IF YOU DON'T KNOW WHAT YOU ARE DOING WITH IT
	 * @param playerIndex index of player
	 * @param gameID index of game
	 */
	public FinishTurnCommand(int playerIndex, int gameID){
		super(playerIndex, gameID);
	}
	public FinishTurnCommand(HttpExchange exchange) {
		super(exchange);
		FinishTurnCommand tmp = (FinishTurnCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;

	}
	/**
	 * Executes the logic to process the finish turn command
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();
			
			Game game = Game.instance();
			ServerGameModel model = game.getGameId(gameIndex);
			ServerTurnTracker turnTracker = model.getServerTurnTracker();
			GetModelResponse response = new GetModelResponse();
			String status = turnTracker.getStatus();
			
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
			
			if(status.equals("FirstRound") && playerIndex ==3){
				turnTracker.setStatus("SecondRound");
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				response.setSuccess(true);
				return response;
			}
			if(status.equals("FirstRound")&& playerIndex != 3){
				turnTracker.advanceTurn();
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				response.setSuccess(true);
				return response;
			}
			if(status.equals("SecondRound") && playerIndex !=0){
				turnTracker.decreaseTurn();
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				response.setSuccess(true);
				return response;
			}
			if(status.equals("SecondRound") && playerIndex == 0){
				turnTracker.setStatus("Rolling");
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				response.setSuccess(true);
				return response;
			}
			
			//Check to see if it's the player's turn
			if(checkTurn(turnTracker, playerIndex)) {
				turnTracker.advanceTurn();
				turnTracker.setStatus("Rolling");
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				response.setSuccess(true);
				return response;
			}
			else {
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				
			}		
			return response;
		}
		
	}

}
