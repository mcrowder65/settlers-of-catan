package client.communication;

import shared.definitions.CatanColor;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.definitions.TradeOffer;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * A Proxy that returns hardcoded responses,
 * used for testing the client.
 *
 */
public class MockProxy implements IProxy {

	/**
	 * A list of hardcoded server responses to return.
	 */
	private String[] responses;
	
	/**
	 * Loads the hardcoded server responses from the file.
	 * @param filename
	 * The name of the file.
	 */
	private void loadResponses(String filename) {
		
	
	}
	/**
	 * Constructs a new MockProxy instance.
	 * @param filename
	 * The name of the file to load hardcoded server responses from.
	 */
	public MockProxy(String filename) {
		
		
	}
	@Override
	public String login(String username, String password) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String register(String username, String password) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String listGames() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String joinGame(int gameId, CatanColor playerColor) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String createGame(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String loadGame(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String saveGame(String fileName) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String reset() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String listAI() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String addAI(String aiType) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String checkVersion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getCommands() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String sendChat(String content) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String rollNumber(int number) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String robPlayer(int victimIndex, HexLocation location) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String finishTurn() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String buyDevCard() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String Year_Of_Plenty(ResourceType resource1, ResourceType resource2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String Road_Building(EdgeLocation spot1, EdgeLocation spot2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String Soldier(int victimIndex, HexLocation location) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String Monopoly(ResourceType resource) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String Monument() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String buildRoad(EdgeLocation roadLocation, boolean free) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String buildSettlement(VertexLocation vertexLocation, boolean free) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String buildCity(VertexLocation vertexLocation) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String offerTrade(TradeOffer offer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String discardCards(ResourceList discardedCards) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String changeLogLevel(String loggingLevel) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
