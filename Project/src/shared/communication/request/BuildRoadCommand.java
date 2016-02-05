package shared.communication.request;

import client.utils.Translator;
import shared.locations.EdgeLocation;
import shared.locations.MirrorEdgeLocation;

public class BuildRoadCommand extends MoveCommand {

	public BuildRoadCommand(int playerIndex, boolean free, EdgeLocation roadLocation) throws IllegalArgumentException {
		super(playerIndex);
		if (roadLocation == null)
			throw new IllegalArgumentException("roadLocation cannot be null.");
		this.free = free;
		this.roadLocation = new MirrorEdgeLocation(roadLocation);
		this.type = "buildRoad";
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
