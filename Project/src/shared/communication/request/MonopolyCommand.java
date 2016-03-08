package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.definitions.GameModel;
import shared.definitions.MirrorResourceType;
import shared.definitions.ResourceType;

public class MonopolyCommand extends MoveCommand {

	MirrorResourceType resource;
	
	public MonopolyCommand(int playerIndex, ResourceType resource) throws IllegalArgumentException {
		super(playerIndex);

		if(resource == null) throw new IllegalArgumentException("Resource can't be NULL");
		if(resource == ResourceType.NONE) throw new IllegalArgumentException("Resource can't be None");
		this.resource = MirrorResourceType.getResource(resource);
		this.type = "Monopoly";
	}

	public MonopolyCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	@Override
	public GameModel execute() {
		return null;
	}
	
	public ResourceType getResource() {
		return MirrorResourceType.getOriginal(resource);
	}
}
