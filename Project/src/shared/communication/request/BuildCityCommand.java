package shared.communication.request;

import shared.locations.EdgeLocation;
import shared.locations.MirrorVertexLocation;
import shared.locations.VertexLocation;

public class BuildCityCommand extends MoveCommand {
	
	public BuildCityCommand(int playerIndex, VertexLocation vertexLocation) throws IllegalArgumentException {
		super(playerIndex);
		if (vertexLocation == null)
			throw new IllegalArgumentException("vertexLocation cannot be null.");

		this.vertexLocation = new MirrorVertexLocation(vertexLocation);
		this.type = "buildCity";
	}


	public MirrorVertexLocation getLocation() {
		return vertexLocation;
	}
	private MirrorVertexLocation vertexLocation;

}
