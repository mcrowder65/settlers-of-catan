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
		int playerIndex=0;		
 		int playerId = 0;		
 		int index =0;		
 					
 		Game game = Game.instance();		
 		ServerGameModel model = game.getGameId(index);		
 		ServerGameMap map = model.getServerMap();		
 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
 		ServerPlayer player = model.getLocalServerPlayer(playerId);		
 				
		//making sure its the players turn		
		if(checkTurn(turnTracker,playerIndex) == false){		
			return null; //Need to throw some error here		
		}		
				
		String status = turnTracker.getStatus();		
		//making sure its the right status		
		if(status.equals("Playing")){
			if(!player.canPlayMonumentCard()){
				//need to return some error here
			}
			player.playMonument();
			//need to return a success here
		}
		return null; //need to return some error here
	}


}
