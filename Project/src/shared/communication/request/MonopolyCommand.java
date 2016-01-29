package shared.communication.request;

import shared.definitions.ResourceType;

public class MonopolyCommand extends MoveCommand {

	ResourceType resource;
	
	public MonopolyCommand(int playerIndex, ResourceType resource) throws IllegalArgumentException {
		super(playerIndex);

		if(resource == null) throw new IllegalArgumentException("Resource can't be NULL");
		this.resource = resource;
	}

	@Override
	public String getMoveType() {
		return "Monopoly";
	}

}
