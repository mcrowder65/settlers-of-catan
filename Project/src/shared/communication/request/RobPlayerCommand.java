package shared.communication.request;

import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class RobPlayerCommand extends MoveCommand {

	HexLocation location;
	int victimIndex;
	
	public RobPlayerCommand(int playerIndex, HexLocation location, int victimIndex) throws IllegalArgumentException {
		super(playerIndex);
		if (victimIndex < 0 || victimIndex > 3) 
			throw new IllegalArgumentException("victimIndex must be between 0 -3");
		if(location == null) throw new IllegalArgumentException("Location can't be NULL");
		
		this.location = location;
		this.victimIndex = victimIndex;
		
	}

	public HexLocation getLocation() {
		return location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}

	@Override
	public String getMoveType() {
		return "robPlayer";
	}

}
