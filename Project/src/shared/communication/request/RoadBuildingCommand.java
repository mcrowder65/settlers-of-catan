package shared.communication.request;

import shared.locations.EdgeLocation;

public class RoadBuildingCommand extends MoveCommand {

	EdgeLocation spot1;
	EdgeLocation spot2;
	
	public EdgeLocation getSpot1() {
		return spot1;
	}

	public EdgeLocation getSpot2() {
		return spot2;
	}

	public RoadBuildingCommand(int playerIndex, EdgeLocation spot1, EdgeLocation spot2)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(spot1 == null) throw new IllegalArgumentException("Spot1 can't be NULL");
		if(spot2 == null) throw new IllegalArgumentException("Spot2 can't be NULL");
		
		this.spot1 = spot1;
		this.spot2 = spot2;
		
	}

	@Override
	public String getMoveType() {
		return "Road_Building";
	}

}
