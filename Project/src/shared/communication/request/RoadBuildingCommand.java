package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
import shared.locations.EdgeLocation;
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
		return null;
	}

}
