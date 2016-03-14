package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.locations.EdgeLocation;
/**
 * This executes the monument action. Extends MoveCommand
 * @author mcrowder65
 */
public class MonumentCommand extends MoveCommand {

	public MonumentCommand(int playerIndex) throws IllegalArgumentException {
		super(playerIndex);
		this.type = "Monument";
	}
	
	public MonumentCommand(HttpExchange exchange) {
		super(exchange);
		
	}
	/**
	 * This executes the monument command. returns GetModelResponse
	 */
	@Override
	public GetModelResponse execute() {
		int gameIndex = this.gameIDCookie;
		int playerIndex = this.getPlayerIndex();	
 					
 		Game game = Game.instance();		
 		ServerGameModel model = game.getGameId(gameIndex);		
 		ServerGameMap map = model.getServerMap();		
 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
 		ServerPlayer player = model.getServerPlayers()[playerIndex];
 		GetModelResponse response = new GetModelResponse();
 				
		//making sure its the players turn		
		if(checkTurn(turnTracker,playerIndex) == false){		
			response.setSuccess(false);
			response.setErrorMessage("Wrong turn");
			return response; //Need to throw some error here		
		}		
				
		String status = turnTracker.getStatus();		
		//making sure its the right status		
		if(status.equals("Playing")){
			if(!player.canPlayMonumentCard()){
				response.setSuccess(false);
				response.setErrorMessage("Player cannot play monument");
				return response;
			}
			player.playMonument();
			response.setSuccess(true);
			return response;
		}
		response.setSuccess(false);
		response.setErrorMessage("Wrong status");
		return response; //need to return some error here
	}


}
