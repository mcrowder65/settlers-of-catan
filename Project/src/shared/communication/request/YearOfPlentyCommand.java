package shared.communication.request;

import shared.definitions.ResourceType;

public class YearOfPlentyCommand extends MoveCommand {

	ResourceType resource1;
	ResourceType resource2;
	
	public YearOfPlentyCommand(int playerIndex, ResourceType resource1, ResourceType resource2)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(resource1 == null) throw new IllegalArgumentException("Resource1 can't be NULL");
		if(resource2 == null) throw new IllegalArgumentException("Resource2 can't be NULL");
		
		if(resource1 == ResourceType.NONE) throw new IllegalArgumentException("Resource1 can't be None");
		if(resource2 == ResourceType.NONE) throw new IllegalArgumentException("Resource2 can't be None");
		
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	public ResourceType getResource1() {
		return resource1;
	}

	public ResourceType getResource2() {
		return resource2;
	}

	@Override
	public String getMoveType() {
		return "Year_of_Plenty";
	}

}
