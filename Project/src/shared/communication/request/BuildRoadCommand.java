package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import shared.communication.response.GetModelResponse;
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
			int playerIndex=this.getPlayerIndex();			
	 		int index =this.gameIDCookie;		
	 		GetModelResponse response = new GetModelResponse();
	 		EdgeLocation loc = this.getRoadLocation().getOriginal();		
	 		Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(index);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];		
	 		/*
	 		try {
				response.setCookie("Set-cookie", "catan.user=" +
						URLEncoder.encode("{" +
					       "\"authentication\":\"" + "1142128101" + "\"," +
				           "\"name\":\"" + userCookie + "\"," +
						   "\"password\":\"" + passCookie + "\"," + 
				           "\"playerID\":" + playerIDCookie + "}", "UTF-8" ) + ";catan.game=" + gameIDCookie);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			*/
	 		
			//making sure its the players turn		
			if(checkTurn(turnTracker,playerIndex) == false){
				
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response; //Need to throw some error here		
			}		
					
			String status = turnTracker.getStatus();		
			//making sure its the right status		
			if(checkStatus(status) == false){
				response.setSuccess(false);
				response.setErrorMessage("Wrong status");
				return response; //Need to throw some error here		
			}		
					
			if(status.equals("FirstRound") || status.equals("SecondRound")){		
				if(!map.canBuildRoadSetup(playerIndex,loc)){	
					response.setSuccess(false);
					response.setErrorMessage("Cannot Build Road at that location");
					return response; 	
				}
				map.buildRoad(new EdgeValue(playerIndex,loc));
				player.removeRoad();
				response.setSuccess(true);
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				return response; 	
			}		
			if(status.equals("Playing")){		
				if(!player.resourcesToBuildRoad()){		
					response.setSuccess(false);
					response.setErrorMessage("Not enough resources to build road");
					return response; 
				}
				if(!map.canBuildRoadNormal(playerIndex,loc)){
					response.setSuccess(false);
					response.setErrorMessage("Cannot build road at that location");
					return response; 	
				}
				map.buildRoad(new EdgeValue(playerIndex,loc));
				player.layRoad();
				response.setSuccess(true);
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				return response; 	
			}		
					
			response.setSuccess(false);
			response.setErrorMessage("Should not reach this point in the code");
			return response; 	
		}
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
