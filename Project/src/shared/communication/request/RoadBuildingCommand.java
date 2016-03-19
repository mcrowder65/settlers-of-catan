package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.locations.EdgeLocation;
import shared.locations.EdgeValue;
import shared.locations.MirrorEdgeLocation;
/**
 * Building a road command
 * @author mcrowder65
 *
 */
public class RoadBuildingCommand extends MoveCommand {
	MirrorEdgeLocation spot1;
	MirrorEdgeLocation spot2;
	
	public EdgeLocation getSpot1() {
		return spot1.getOriginal();
	}

	public EdgeLocation getSpot2() {
		return spot2.getOriginal();
	}

	public RoadBuildingCommand(int playerIndex, EdgeLocation spot1, EdgeLocation spot2)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(spot1 == null) throw new IllegalArgumentException("Spot1 can't be NULL");
		if(spot2 == null) throw new IllegalArgumentException("Spot2 can't be NULL");
		
		this.spot1 = new MirrorEdgeLocation(spot1);
		this.spot2 = new MirrorEdgeLocation(spot2);
		this.type = "Road_Building";
	}

	
	public RoadBuildingCommand(HttpExchange exchange) {
		super(exchange);
		RoadBuildingCommand tmp = (RoadBuildingCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.spot1 = tmp.spot1;
		this.spot2 = tmp.spot2;
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
		
	}
	/**
	 * This executes the road building.  return GetModelResponse
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();	
	 				
	 		EdgeLocation loc1 = this.getSpot1();	
	 		EdgeLocation loc2 = this.getSpot2();
	 		Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		GetModelResponse response = new GetModelResponse();
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
	 				
			//making sure its the players turn		
			if(checkTurn(turnTracker,playerIndex) == false){		
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response; //Need to throw some error here		
			}		
					
			String status = turnTracker.getStatus();		
			//making sure its the right status				
			if(status.equals("Playing")){		
				if(!player.canPlayRoadBuilding()){
					response.setSuccess(false);
					response.setErrorMessage("Player cannot play card");
					return response;
				}
				if(!map.canUseRoadBuilder(playerIndex,loc1,loc2)){
					response.setSuccess(false);
					response.setErrorMessage("Wrong Location");
					return response;
				}
				map.buildRoad(new EdgeValue(playerIndex,loc1));
				map.buildRoad(new EdgeValue(playerIndex,loc2));
				player.layRoadBuilder();
				model.setVersion(model.getVersion() + 1);
				response.setSuccess(true);
				response.setJson(model.toString());
				return response;
			}		
					
			//need to return an error
			response.setSuccess(false);
			response.setErrorMessage("Wrong status");
			return response;
		}
	}

}
