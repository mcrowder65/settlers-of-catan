package server.ai;

import java.util.HashSet;

import server.facade.IAIFacade;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;

public class NormalAI extends AIBase {

	public NormalAI(String name, CatanColor color, int playerID, int playerIndex, IAIFacade facade) {
		super(name, color, playerID, playerIndex, facade);
		preferredResources.add(ResourceType.WHEAT);
		preferredResources.add(ResourceType.ORE);
		preferredResources.add(ResourceType.BRICK);
		preferredResources.add(ResourceType.WOOD);
		preferredResources.add(ResourceType.SHEEP);
		
		preferredActions.add(AIActionPreference.ROADS);
		preferredActions.add(AIActionPreference.SETTLEMENTS);
		preferredActions.add(AIActionPreference.CITIES);
		preferredActions.add(AIActionPreference.DEVCARDS);
	}
	
	HashSet<ResourceType> preferredResources = new HashSet<ResourceType>();
	HashSet<AIActionPreference> preferredActions = new HashSet<AIActionPreference>();

	@Override
	public HashSet<ResourceType> getPreferredResources() {
		return preferredResources;
	}

	@Override
	public HashSet<AIActionPreference> getPreferredActions() {
		return preferredActions;
	}


}
