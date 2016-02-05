package shared.communication.request;

import shared.locations.MirrorVertexLocation;
import shared.locations.VertexLocation;

public class BuildSettlementCommand extends MoveCommand {

	
	boolean free;
	MirrorVertexLocation vertexLocation;
	
	
	public BuildSettlementCommand(int playerIndex, boolean free, VertexLocation vertexLocation)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(vertexLocation == null) throw new IllegalArgumentException("Location can't be NULL");
		
		
		this.free = free;
		this.vertexLocation = new MirrorVertexLocation(vertexLocation);
		
		this.type = "buildSettlement";
	}


	public boolean isFree() {
		return free;
	}
	public MirrorVertexLocation getLocation() {
		return vertexLocation;
	}
}
