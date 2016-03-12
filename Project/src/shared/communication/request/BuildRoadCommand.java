package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import shared.communication.response.GetModelResponse;
import shared.locations.EdgeLocation;
import shared.locations.MirrorEdgeLocation;
import server.Game;		
import server.util.ServerGameMap;		
import server.util.ServerGameModel;		
import server.util.ServerPlayer;		
import server.util.ServerTurnTracker;
/**
 * This class executes the build road command,
 * extends MoveCommand
 * @author Manuel
 *
 */

public class BuildRoadCommand extends MoveCommand {

	public BuildRoadCommand(int playerIndex, boolean free, EdgeLocation roadLocation) throws IllegalArgumentException {
		super(playerIndex);
		if (roadLocation == null)
			throw new IllegalArgumentException("roadLocation cannot be null.");
		this.free = free;
		this.roadLocation = new MirrorEdgeLocation(roadLocation);
		this.type = "buildRoad";
	}
	public BuildRoadCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	/**
	 * Executes the logic for the build road command
	 */
	@Override
	public GetModelResponse execute() {
		int playerIndex=0;		
		 		int playerId = 0;		
		 		int index =0;		
		 				
		 		EdgeLocation loc = null;		
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
				if(checkStatus(status) == false){		
					return null; //Need to throw some error here		
				}		
						
				if(status.equals("FirstRound") || status.equals("SecondRound")){		
					map.canBuildRoadSetup(index,loc);		
				}		
				if(status.equals("Playing")){		
					boolean enoughResources = player.resourcesToBuildRoad();		
				}		
						
		  		return null;
	}
	public boolean checkTurn(ServerTurnTracker turnTracker, int playerIndex){		
		 		if(turnTracker.getCurrentTurn() == playerIndex){		
		 			return true;		
		 		}		
		 		return false;		
		 	}		
		 			
		 	public boolean checkStatus(String status){		
		 		if (status.equals("Playing") || status.equals("FirstRound") || status.equals("SecondRound")){		
		 			return true;		
		 		}		
		 		return false;		
		 	}
	public boolean isFree() {
		return free;
	}
	public MirrorEdgeLocation getRoadLocation() {
		 
		return roadLocation;
	}
	private boolean free;
	private MirrorEdgeLocation roadLocation;
	
	
}
