package shared.communication.request;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class BuildCityCommand extends MoveCommand {
	
	public BuildCityCommand(int playerIndex, boolean free, VertexLocation vertexLocation) throws IllegalArgumentException {
		super(playerIndex);
		if (vertexLocation == null)
			throw new IllegalArgumentException("vertexLocation cannot be null.");
		this.free = free;
		this.vertexLocation = vertexLocation;
	}
	public boolean isFree() {
		return free;
	}
	public VertexLocation getRoadLocation() {
		return vertexLocation;
	}
	private boolean free;
	private VertexLocation vertexLocation;
	@Override
	public String getMoveType() {
		return "buildCity";
	}

}
