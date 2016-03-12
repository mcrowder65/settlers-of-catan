package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

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
		
	}
	/**
	 * This executes the road building.  return GetModelResponse
	 */
	@Override
	public GetModelResponse execute() {
		int playerIndex=0;		
 		int playerId = 0;		
 		int index =0;		
 				
 		EdgeLocation loc1 = null;
 		EdgeLocation loc2 = null;
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
			if(!player.canPlayRoadBuilding()){
				//need to return an error
			}
			if(!map.canUseRoadBuilder(playerIndex,loc1,loc2)){
				//need to return an error
			}
			map.buildRoad(new EdgeValue(playerIndex,loc1));
			map.buildRoad(new EdgeValue(playerIndex,loc2));
			player.layRoadBuilder();
			//need to return success
		}		
				
		//need to return an error
		return null;
	}

}
