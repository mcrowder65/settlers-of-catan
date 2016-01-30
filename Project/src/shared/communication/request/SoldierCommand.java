package shared.communication.request;

import shared.locations.HexLocation;

public class SoldierCommand extends MoveCommand {

	private HexLocation location;
	private int victimIndex;
	public SoldierCommand(int playerIndex, HexLocation location, int victimIndex) throws IllegalArgumentException {
		super(playerIndex);
		if (victimIndex < 0 || victimIndex > 3) 
			throw new IllegalArgumentException("victimIndex must be between 0 -3");
		if (location == null)
			throw new IllegalArgumentException("location can't be null.");

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
		return "Soldier";
	}

}
