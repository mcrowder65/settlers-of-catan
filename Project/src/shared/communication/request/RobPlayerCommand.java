package shared.communication.request;

import shared.locations.VertexLocation;

public class RobPlayerCommand extends MoveCommand {

	VertexLocation location;
	int victimIndex;
	
	public RobPlayerCommand(int playerIndex, VertexLocation location, int victimIndex) throws IllegalArgumentException {
		super(playerIndex);
		
		if(location == null) throw new IllegalArgumentException("Location can't be NULL");
		
		this.location = location;
		this.victimIndex = victimIndex;
		
	}

	@Override
	public String getMoveType() {
		return "robPlayer";
	}

}
