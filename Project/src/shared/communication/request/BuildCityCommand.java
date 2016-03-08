package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
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
	
	public BuildCityCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	@Override
	public GetModelResponse execute() {
		return null;
	}


	public MirrorVertexLocation getLocation() {
		return vertexLocation;
	}
	private MirrorVertexLocation vertexLocation;

}
