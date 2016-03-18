package server.facade;

import shared.communication.request.FinishTurnCommand;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class ServerAIFacade implements IAIFacade {

	@Override
	public void rollNumber(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void discardCards(ResourceList resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildRoad(EdgeLocation location, boolean free) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildSettlement(VertexLocation location, boolean free) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildCity(VertexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buyDevCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void monopoly(ResourceType resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void soldier(int victimIndex, HexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void monument() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void yearOfPlenty(ResourceType resource1, ResourceType resource2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void roadBuilding(EdgeLocation spot1, EdgeLocation spot2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robPlayer(int victimIndex, HexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maritimeTrade(int ratio, ResourceType input, ResourceType output) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void finishTurn(){
		// TODO auto-generated method stub
	}
	@Override
	public void finishTurn(int aiIndex, int gameID) {
		FinishTurnCommand command = new FinishTurnCommand(aiIndex, gameID);
		command.execute();
	}


}
