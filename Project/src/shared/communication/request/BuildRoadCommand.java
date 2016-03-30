package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import shared.communication.response.GetModelResponse;
import shared.definitions.MessageLine;
import shared.locations.EdgeLocation;
import shared.locations.EdgeValue;
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
		BuildRoadCommand tmp = (BuildRoadCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.free = tmp.free;
		this.roadLocation = tmp.roadLocation;
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
	}

	/**
	 * Executes the logic for the build road command
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
			int playerIndex=this.getPlayerIndex();			
	 		int index =this.gameIDCookie;		
	 		GetModelResponse response = new GetModelResponse();
	 		EdgeLocation loc = this.getRoadLocation().getOriginal();		
	 		Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(index);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];		
	 		
			//making sure its the players turn		
			if(checkTurn(turnTracker,playerIndex) == false){
				
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response; 		
			}		
					
			String status = turnTracker.getStatus();		
			//making sure its the right status		
			if(checkStatus(status) == false){
				response.setSuccess(false);
				response.setErrorMessage("Wrong status");
				return response; 		
			}		
			
			//laying a road during the first two rounds
			if(status.equals("FirstRound") || status.equals("SecondRound")){		
				if(!map.canBuildRoadSetup(playerIndex,loc)){ //checking to see if the road can be built on the map	
					response.setSuccess(false);
					response.setErrorMessage("Cannot Build Road at that location");
					return response; 	
				}
				map.buildRoad(new EdgeValue(playerIndex,loc)); //builds the road on the map
				player.removeRoad();
				addGameLog(player,model); //updating gamelog
				response.setSuccess(true);
				model.setVersion(model.getVersion() + 1); //updates version
	            response.setJson(model.toString()); //creates response
				Game.instance().getGameId(gameIDCookie).findLongestRoad();
				return response; 	
			}		
			//checking the status
			if(status.equals("Playing")){		
				if(!player.resourcesToBuildRoad()){ //checks to see if the player has necessary resources
					response.setSuccess(false);
					response.setErrorMessage("Not enough resources to build road");
					return response; 
				}

				map.buildRoad(new EdgeValue(playerIndex,loc)); //building the road
				player.layRoad();
				addGameLog(player,model);
				response.setSuccess(true);
				model.setVersion(model.getVersion() + 1); //updating version
	            response.setJson(model.toString());
	            Game.instance().getGameId(gameIDCookie).findLongestRoad(); //checking for the longest road
				return response; 	
			}		
			
			//error occured
			response.setSuccess(false);
			response.setErrorMessage("Should not reach this point in the code");

			return response; 	
		}
	}

	/**
	 * adds proper message to the gamelog
	 * @param player
	 * @param model
	 */
	public void addGameLog(ServerPlayer player, ServerGameModel model){
		String message = player.getName() + " built a road";
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
	}
	
	/**
	 * checks to make sure the status allows for a road to be built	 			
	 * @param status
	 * @return boolean
	 */
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
