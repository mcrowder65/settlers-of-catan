package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.definitions.GameModel;
import shared.definitions.MirrorResourceType;
import shared.definitions.ResourceType;

public class YearOfPlentyCommand extends MoveCommand {

	MirrorResourceType resource1;
	MirrorResourceType resource2;
	
	public YearOfPlentyCommand(int playerIndex, ResourceType resource1, ResourceType resource2)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(resource1 == null) throw new IllegalArgumentException("Resource1 can't be NULL");
		if(resource2 == null) throw new IllegalArgumentException("Resource2 can't be NULL");
		
		if(resource1 == ResourceType.NONE) throw new IllegalArgumentException("Resource1 can't be None");
		if(resource2 == ResourceType.NONE) throw new IllegalArgumentException("Resource2 can't be None");
		
		this.resource1 = MirrorResourceType.getResource(resource1);
		this.resource2 = MirrorResourceType.getResource(resource2);
		this.type = "Year_of_Plenty";
	}
	
	public YearOfPlentyCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	@Override
	public GameModel execute() {
		return null;
	}

	public ResourceType getResource1() {
		return MirrorResourceType.getOriginal(resource1);
	}

	public ResourceType getResource2() {
		return MirrorResourceType.getOriginal(resource2);
	}

	

}
