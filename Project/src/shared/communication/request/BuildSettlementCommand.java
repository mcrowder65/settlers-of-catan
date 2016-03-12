package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.locations.EdgeLocation;
import shared.locations.MirrorVertexLocation;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;
/**
 * This class executes the build settlement command,
 * extends MoveCommand
 * 
 * @author Manuel
 *
 */

public class BuildSettlementCommand extends MoveCommand {

	
	
	boolean free;
	MirrorVertexLocation vertexLocation;
	
	
	public BuildSettlementCommand(int playerIndex, boolean free, VertexLocation vertexLocation)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(vertexLocation == null) throw new IllegalArgumentException("Location can't be NULL");
		
		
		this.free = free;
		this.vertexLocation = new MirrorVertexLocation(vertexLocation);
		
		this.type = "buildSettlement";
	}
	public BuildSettlementCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	/**
	 * Executes the logic for the build settlement command
	 */
	@Override
	public GetModelResponse execute() {
		
		int playerId = 0;
		int gameIndex = 0;
		int playerIndex = 0;
		VertexLocation loc = null;		
 		Game game = Game.instance();		
 		ServerGameModel model = game.getGameId(gameIndex);		
 		ServerGameMap map = model.getServerMap();		
 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
 		ServerPlayer player = model.getLocalServerPlayer(playerId);	
 		playerIndex = model.getLocalIndex(playerId);
 		String status = turnTracker.getStatus();
 		VertexObject vertex = new VertexObject(playerIndex,loc);
 		//making sure its the players turn		
		if(checkTurn(turnTracker,playerIndex) == false){		
			return null; //Need to throw some error here		
		}
		if(status.equals("FirstRound") || status.equals("SecondRound")){
			if(!map.canBuildSettlementFirstRound(vertex)){
				//need to return some error here
			}
			map.buildSettlement(vertex);
			//need to return that it was succesful 
		}
		if(status.equals("Playing")){
			if(!player.canBuildSettlement() || !map.canBuildSettlement(vertex)){
				//need to return some error
			}
			map.buildSettlement(vertex);
			player.laySettlement();
			//need to return successful
		}
		
		//need to return some error
		return null;
	}
	

	public boolean isFree() {
		return free;
	}
	public MirrorVertexLocation getLocation() {
		return vertexLocation;
	}
}
