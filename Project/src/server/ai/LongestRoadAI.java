package server.ai;

import java.util.HashSet;

import server.facade.IAIFacade;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;

public class LongestRoadAI extends AIBase {

	public LongestRoadAI(String name, CatanColor color, int playerID, int playerIndex, IAIFacade facade) {
		super(name, color, playerID, playerIndex, facade);
		preferredResources.add(ResourceType.BRICK);
		preferredResources.add(ResourceType.WOOD);
		
		preferredActions.add(AIActionPreference.ROADS);
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
