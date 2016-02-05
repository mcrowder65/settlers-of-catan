package shared.communication.request;

import shared.locations.EdgeLocation;
import shared.locations.MirrorEdgeLocation;

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


}
