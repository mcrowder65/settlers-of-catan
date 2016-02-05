package shared.communication.request;

import shared.definitions.ResourceType;

public class MonopolyCommand extends MoveCommand {

	ResourceType resource;
	
	public MonopolyCommand(int playerIndex, ResourceType resource) throws IllegalArgumentException {
		super(playerIndex);

		if(resource == null) throw new IllegalArgumentException("Resource can't be NULL");
		if(resource == ResourceType.NONE) throw new IllegalArgumentException("Resource can't be None");
		this.resource = resource;
		this.type = "Monopoly";
	}

	public ResourceType getResource() {
		return resource;
	}
}
