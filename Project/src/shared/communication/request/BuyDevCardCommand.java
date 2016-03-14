package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;

/**
 * This class executes the Buy Dev Card Command,
 * extends MoveCommand
 * @author Manuel
 *
 */

public class BuyDevCardCommand extends MoveCommand {

	public BuyDevCardCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
		this.type = "buyDevCard";
	}

	public BuyDevCardCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	/**
	 * Executes the logic for the buy dev card command
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
			if(!player.canBuyDevCard()){
				//need to throw some error here
			}
			if(mode.isBankEmpty() == true){
				//need to return some error here
			}
			ResourceType resource = model.generateRandomResource();
			while(resource == ResourceType.NONE){
				resource = model.generateRandomResource();
			}
			player.buyDevCard(resource);
			model.buyFromBank(resource);
			//need to return a success here
			
		}
		return null;//need to throw some error here
	}
}
