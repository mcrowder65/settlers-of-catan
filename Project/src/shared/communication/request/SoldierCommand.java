package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
import shared.locations.HexLocation;

public class SoldierCommand extends MoveCommand {

	private HexLocation location;
	private int victimIndex;
	public SoldierCommand(int playerIndex, HexLocation location, int victimIndex) throws IllegalArgumentException {
		super(playerIndex);
		if (victimIndex < -1 || victimIndex > 3) 
			throw new IllegalArgumentException("victimIndex must be between -1 -3");
		if (location == null)
			throw new IllegalArgumentException("location can't be null.");

		this.location = location;
		this.victimIndex = victimIndex;
		this.type = "Soldier";
	}
	
	public SoldierCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	@Override
	public GetModelResponse execute() {
		return null;
	}

	public HexLocation getLocation() {
		return location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}


}
