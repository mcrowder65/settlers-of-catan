package shared.communication.request;

import shared.locations.EdgeLocation;

public class BuildRoadCommand extends MoveCommand {

	public BuildRoadCommand(int playerIndex, boolean free, EdgeLocation roadLocation) throws IllegalArgumentException {
		super(playerIndex);
		if (roadLocation == null)
			throw new IllegalArgumentException("roadLocation cannot be null.");
		this.free = free;
		this.roadLocation = roadLocation;
		this.type = "buildRoad";
	}
	public boolean isFree() {
		return free;
	}
	public EdgeLocation getRoadLocation() {
		return roadLocation;
	}
	private boolean free;
	private EdgeLocation roadLocation;
	

}
