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
		int gameIndex = this.gameIDCookie;
		int playerIndex = this.getPlayerIndex();	
		VertexLocation loc = this.getLocation().getOriginal();		
 		Game game = Game.instance();	
 		GetModelResponse response = new GetModelResponse();
 		ServerGameModel model = game.getGameId(gameIndex);		
 		ServerGameMap map = model.getServerMap();		
 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
 		ServerPlayer player = model.getServerPlayers()[playerIndex];
 		String status = turnTracker.getStatus();
 		VertexObject vertex = new VertexObject(playerIndex,loc);
 		//making sure its the players turn		
		if(checkTurn(turnTracker,playerIndex) == false){		
			response.setSuccess(false);
			response.setErrorMessage("Wrong turn");
			return response; //Need to throw some error here		
		}
		if(status.equals("Playing")){
			if(!player.canBuildCity()){
				//return that there was an error
				response.setSuccess(false);
				response.setErrorMessage("Wrong status");
				return response;
			}
			if(!map.canBuildCity(vertex)){
				response.setSuccess(false);
				response.setErrorMessage("Bad Location");
				return response;
			}
			
			map.layCity(vertex);
			player.layCity();
			
			//return that there was a success
			response.setSuccess(true);
			return response;
			
		}
		response.setSuccess(false);
		response.setErrorMessage("Unreachable");
		return response;
	}


	public MirrorVertexLocation getLocation() {
		return vertexLocation;
	}
	private MirrorVertexLocation vertexLocation;

}
