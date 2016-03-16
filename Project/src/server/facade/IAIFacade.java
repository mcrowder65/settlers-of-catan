package server.facade;

import shared.definitions.ResourceList;

public interface IAIFacade {

	 void rollNumber();
	
	 void discardCards(ResourceList resources);
	 void tryBuildRoads();
	 void tryBuildSettlements();
	 void tryBuildCities();
	 void tryBuyDevCards();
	 
	 void finishTurn();
	 
	
}
