package server.facade;

import shared.definitions.*;
import shared.locations.*;

public interface IAIFacade {

	 void rollNumber(int number);
	
	 void discardCards(ResourceList resources);
	 void buildRoad(EdgeLocation location, boolean free);
	 void buildSettlement(VertexLocation location, boolean free);
	 void buildCity(VertexLocation location);
	 void buyDevCard();
	 void monopoly(ResourceType resource);
	 void soldier(int victimIndex, HexLocation location);
	 void monument();
	 void yearOfPlenty(ResourceType resource1, ResourceType resource2);
	 void roadBuilding(EdgeLocation spot1, EdgeLocation spot2);
	
	 void robPlayer(int victimIndex, HexLocation location);
	 void acceptTrade(boolean willAccept);
	 void maritimeTrade(int ratio, ResourceType input, ResourceType output);
	 
	 void finishTurn(); 
	
	 int getGameId();
}
