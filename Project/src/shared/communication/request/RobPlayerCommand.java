package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
/**
 * This is the rob player command. Extends MoveCommand
 * @author mcrowder65
 *
 */
public class RobPlayerCommand extends MoveCommand {

	HexLocation location;
	int victimIndex;
	
	public RobPlayerCommand(int playerIndex, HexLocation location, int victimIndex) throws IllegalArgumentException {
		super(playerIndex);
		if (victimIndex < -1 || victimIndex > 3) 
			throw new IllegalArgumentException("victimIndex must be between -1 -3");
		if(location == null) throw new IllegalArgumentException("Location can't be NULL");
		
		this.location = location;
		this.victimIndex = victimIndex;
		this.type = "robPlayer";
	}

	public RobPlayerCommand(HttpExchange exchange) {
		super(exchange);
		
	}
	/**
	 * This executes the rob player.
	 */
	@Override
	public GetModelResponse execute() {
		int gameIndex = this.gameIDCookie;
		int playerIndex = this.getPlayerIndex();	
		int victimIndex = this.getVictimIndex();
		HexLocation locaiton = this.getLocation();
		Game game = Game.instance();		
 		ServerGameModel model = game.getGameId(gameIndex);		
 		ServerGameMap map = model.getServerMap();		
 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
 		ServerPlayer player = model.getServerPlayers()[playerIndex];
 		ServerPlayer victim = model.getServerPlayers()[victimIndex];
 		GetModelResponse response = new GetModelResponse();
 		String status = turnTracker.getStatus();	
 		
 		//making sure its the players turn		
		if(checkTurn(turnTracker,playerIndex) == false){		
			response.setSuccess(false);
			response.setErrorMessage("Wrong turn");
			return response; //Need to throw some error here		
		}	
		
		//check status
		if(!status.equals("Robbing")){
			response.setSuccess(false);
			response.setErrorMessage("Wrong status");
			return response; //Need to throw some error here
		}
		
		//victim has no resources
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
		map.setRobber(location);
		return response; 

	}
	
	public HexLocation getLocation() {
		return location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}

	
}
