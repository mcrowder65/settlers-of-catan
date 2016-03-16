package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.*;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
/**
 * This class executes the soldier moves. Extends MoveCommand
 * @author mcrowder65
 *
 */
public class SoldierCommand extends MoveCommand {

	private HexLocation location;
	private int victimIndex;
	public SoldierCommand(int playerIndex, HexLocation location, int victimIndex) throws IllegalArgumentException {
		super(playerIndex);
		if (victimIndex < -1 || victimIndex > 3) 
			throw new IllegalArgumentException("victimIndex must be between -1 -3");
		if (location == null)
			throw new IllegalArgumentException("location can't be null.");

		this.location = location;
		this.victimIndex = victimIndex;
		this.type = "Soldier";
	}
	
	public SoldierCommand(HttpExchange exchange) {
		super(exchange);
		
	}
	/**
	 * Executes the Soldier - returns a GetModelResponse
	 */
	@Override
	public GetModelResponse execute() {
		int gameIndex = this.gameIDCookie;
		int playerIndex = this.getPlayerIndex();	
		HexLocation robberLoc = this.getLocation();		
 		Game game = Game.instance();	
 		GetModelResponse response = new GetModelResponse();
 		ServerGameModel model = game.getGameId(gameIndex);		
 		ServerGameMap map = model.getServerMap();		
 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
 		ServerPlayer player = model.getServerPlayers()[playerIndex];
 		ServerPlayer victim = model.getServerPlayers()[victimIndex];
 		String status = turnTracker.getStatus();
 		
 		if(checkTurn(turnTracker,playerIndex) == false){		
			response.setSuccess(false);
			response.setErrorMessage("Wrong turn");
			return response; 		
		}
 		
 		if(!status.equals("Playing")){
 			response.setSuccess(false);
			response.setErrorMessage("Wrong status");
			return response;
 		}
 		
 		if(player.getOldDevCards().getSoldier() < 1){
 			response.setSuccess(false);
			response.setErrorMessage("No dev card to play");
			return response;
 		}
 		
 		if(map.isLand(robberLoc) == false){
 			response.setSuccess(false);
			response.setErrorMessage("Invalid Hex Location");
			return response;
 		}
 		
 		if(victim.getResources().isEmpty()){
			response.setSuccess(true);
			return response; 
		}
 		
 		ResourceType resource = model.generateRandomResource();
		
		while(victim.getResources().hasResource(resource) == false){
			resource = model.generateRandomResource();
		}
		
		victim.removeResource(resource);
		player.addResource(resource);
		response.setSuccess(true);
		map.setRobber(robberLoc);
		player.playSoldierCard();
		return response; 

	
	}

	public HexLocation getLocation() {
		return location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}


}
