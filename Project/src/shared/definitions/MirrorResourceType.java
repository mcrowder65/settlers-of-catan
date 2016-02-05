package shared.definitions;

public enum MirrorResourceType {
	none, wood, brick, sheep, wheat, ore;
	
	public static MirrorResourceType getResource(ResourceType resource) {
		switch (resource) {
		case NONE:
			return none;
		case WOOD:
			return wood;
		case BRICK:
			return brick;
		case SHEEP:
			return sheep;
		case WHEAT:
			return wheat;
		case ORE:
			return ore;
		
		default:
			return none;
			
		}
	}
	
	public static ResourceType getOriginal(MirrorResourceType resource) {
		switch (resource) {
		case none:
			return ResourceType.NONE;
		case wood:
			return ResourceType.WOOD;
		case brick:
			return ResourceType.BRICK;
		case sheep:
			return ResourceType.SHEEP;
		case wheat:
			return ResourceType.WHEAT;
		case ore:
			return ResourceType.ORE;
		
		default:
			return ResourceType.NONE;
			
		}
	}
}
