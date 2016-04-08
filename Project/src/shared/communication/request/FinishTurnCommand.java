package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.MessageLine;
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
		FinishTurnCommand tmp = (FinishTurnCommand)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;

	}
	public FinishTurnCommand(String json) {
		FinishTurnCommand tmp = (FinishTurnCommand)Translator.jsonToObject(json, this.getClass());
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
			ServerPlayer player = model.getServerPlayers()[playerIndex];
			
		
			if(!checkTurn(turnTracker, playerIndex)) {
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response;
				
			}
			
			model.setVersion(model.getVersion() + 1);
			//turnTracker.advanceTurn();
			
			if(status.equals("FirstRound")){
				if (playerIndex == 3){ 
					turnTracker.setStatus("SecondRound");
				}
				else{
					turnTracker.advanceTurn();
				}
			} 
			else if (status.equals("SecondRound")) {
				if (playerIndex == 0){
					turnTracker.setStatus("Rolling");
				}
				else{
					turnTracker.decreaseTurn();
				}
			} 
			else {
				turnTracker.advanceTurn();
				turnTracker.setStatus("Rolling");
			}
			
			addGameLog(player,model);
			turnTracker.handleAITurn(gameIndex, turnTracker.getCurrentTurn());
			player.updateOldDevCard();
			player.setPlayedDevCard(false);
			response.setJson(model.toString());
			response.setSuccess(true);
			
			return response;
		}
		
	}
	public void addGameLog(ServerPlayer player, ServerGameModel model){
		String message = player.getName() + "'s turn just ended";
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
	}

}
