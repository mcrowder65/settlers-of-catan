package server.facade;

import shared.communication.request.*;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class ServerAIFacade implements IAIFacade {

	int playerIndex;
	int gameId;
	public ServerAIFacade(int playerIndex, int gameId) {
		this.playerIndex = playerIndex;
		this.gameId = gameId;
	}
	
	@Override
	public void rollNumber(int number) {
		RollNumberCommand command = new RollNumberCommand(playerIndex, number);
		command.setGameCookie(gameId);
		command.execute();
	}

	@Override
	public void discardCards(ResourceList resources) {
		DiscardCardsCommand command = new DiscardCardsCommand(playerIndex, resources);
		command.setGameCookie(gameId);
		command.doSuppressAIHandling();
		command.execute();
		
	}

	@Override
	public void buildRoad(EdgeLocation location, boolean free) {
		 BuildRoadCommand command = new BuildRoadCommand(playerIndex, free, location);
		 command.setGameCookie(gameId);
		 command.execute();
	}

	@Override
	public void buildSettlement(VertexLocation location, boolean free) {
		BuildSettlementCommand command = new BuildSettlementCommand(playerIndex, free, location);
		 command.setGameCookie(gameId);
		 command.execute();
		 
	}

	@Override
	public void buildCity(VertexLocation location) {
		BuildCityCommand command = new BuildCityCommand(playerIndex, location);
		 command.setGameCookie(gameId);
		 command.execute();
		 
		
	}

	@Override
	public void buyDevCard() {
		BuyDevCardCommand command = new BuyDevCardCommand(playerIndex);
		 command.setGameCookie(gameId);
		 command.execute();
		 
	}

	@Override
	public void monopoly(ResourceType resource) {
		MonopolyCommand command = new MonopolyCommand(playerIndex, resource);	
		 command.setGameCookie(gameId);
		 command.execute();
		
	}

	@Override
	public void soldier(int victimIndex, HexLocation location) {
		SoldierCommand command = new SoldierCommand(playerIndex, location, victimIndex);
		 command.setGameCookie(gameId);
		 command.execute();
	}

	@Override
	public void monument() {
		MonumentCommand command = new MonumentCommand(playerIndex);
		 command.setGameCookie(gameId);
		 command.execute();
	}

	@Override
	public void yearOfPlenty(ResourceType resource1, ResourceType resource2) {
		YearOfPlentyCommand command = new YearOfPlentyCommand(playerIndex, resource1, resource2);
		 command.setGameCookie(gameId);
		 command.execute();
		 
	}

	@Override
	public void roadBuilding(EdgeLocation spot1, EdgeLocation spot2) {
		RoadBuildingCommand command = new RoadBuildingCommand(playerIndex, spot1, spot2);
		 command.setGameCookie(gameId);
		 command.execute();
		 
	}

	@Override
	public void robPlayer(int victimIndex, HexLocation location) {
		RobPlayerCommand command = new RobPlayerCommand(playerIndex, location, victimIndex);
		 command.setGameCookie(gameId);
		 command.execute();
		 
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		AcceptTradeCommand command = new AcceptTradeCommand(playerIndex, willAccept);
		 command.setGameCookie(gameId);
		 command.execute();
	}

	@Override
	public void maritimeTrade(int ratio, ResourceType input, ResourceType output) {
		MaritimeTradeCommand command = new MaritimeTradeCommand(playerIndex, ratio, input, output);
		 command.setGameCookie(gameId);
		 command.execute();
		 
	}
	@Override
	public void finishTurn(){
		FinishTurnCommand command = new FinishTurnCommand(playerIndex);
		 command.setGameCookie(gameId);
		 command.execute();
		 
	}
	
	@Override
	public int getGameId() {
		return gameId; 
	}
}
