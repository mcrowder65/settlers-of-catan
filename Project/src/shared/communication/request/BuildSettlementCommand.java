package shared.communication.request;

import shared.locations.VertexLocation;

public class BuildSettlementCommand extends MoveCommand {

	
	boolean free;
	VertexLocation vertexLocation;
	
	
	public BuildSettlementCommand(int playerIndex, boolean free, VertexLocation vertexLocation)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(vertexLocation == null) throw new IllegalArgumentException("Location can't be NULL");
		
		
		this.free = free;
		this.vertexLocation = vertexLocation;
		
		
	}

	@Override
	public String getMoveType() {
		return "buildSettlement";
	}

	public boolean isFree() {
		return free;
	}
	public VertexLocation getLocation() {
		return vertexLocation;
	}
}
