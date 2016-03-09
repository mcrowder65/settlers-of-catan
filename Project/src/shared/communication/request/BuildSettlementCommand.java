package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
import shared.locations.MirrorVertexLocation;
import shared.locations.VertexLocation;
/**
 * This class executes the build settlement command,
 * extends MoveCommand
 * 
 * @author Manuel
 *
 */

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
	public BuildSettlementCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	/**
	 * Executes the logic for the build settlement command
	 */
	@Override
	public GetModelResponse execute() {
		return null;
	}

	public boolean isFree() {
		return free;
	}
	public MirrorVertexLocation getLocation() {
		return vertexLocation;
	}
}
