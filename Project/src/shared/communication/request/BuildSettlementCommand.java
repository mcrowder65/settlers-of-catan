package shared.communication.request;

import shared.locations.VertexLocation;

public class BuildSettlementCommand extends MoveCommand {

	
	boolean free;
	VertexLocation location;
	
	
	public BuildSettlementCommand(int playerIndex, boolean free, VertexLocation location)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(location == null) throw new IllegalArgumentException("Location can't be NULL");
		
		
		this.free = free;
		this.location = location;
		
		
	}

	@Override
	public String getMoveType() {
		return "buildSettlement";
	}

}
