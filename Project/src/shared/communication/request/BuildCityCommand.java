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
 * This class executes the build city command,
 * extends MoveCommand
 * @author Manuel
 *
 */

public class BuildCityCommand extends MoveCommand {
	
	public BuildCityCommand(int playerIndex, VertexLocation vertexLocation) throws IllegalArgumentException {
		super(playerIndex);
		if (vertexLocation == null)
			throw new IllegalArgumentException("vertexLocation cannot be null.");

		this.vertexLocation = new MirrorVertexLocation(vertexLocation);
		this.type = "buildCity";
	}
	
	public BuildCityCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	/**
	 * Executes the logic to process the build city command
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
		if(status.equals("Playing")){
			if(!player.canBuildCity()){
				//return that there was an error
			}
			if(!map.canBuildCity(vertex)){
				//return that there was an error
			}
			
			map.layCity(vertex);
			player.layCity();
			
			//return that there was a success
			
		}
		//return that there was an error
		return null;
	}


	public MirrorVertexLocation getLocation() {
		return vertexLocation;
	}
	private MirrorVertexLocation vertexLocation;

}
