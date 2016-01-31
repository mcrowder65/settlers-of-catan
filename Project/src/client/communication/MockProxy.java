package client.communication;

import java.util.List;

import shared.communication.response.CreateGameResponse;
import shared.communication.response.GetCommandsResponse;
import shared.communication.response.GetModelResponse;
import shared.communication.response.JoinGameResponse;
import shared.communication.response.ListAIResponse;
import shared.communication.response.ListGamesResponse;
import shared.communication.response.LoginResponse;
import shared.communication.response.RegisterResponse;
import shared.communication.response.Response;
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
	public LoginResponse login(String username, String password) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RegisterResponse register(String username, String password) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListGamesResponse listGames() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JoinGameResponse joinGame(int id, CatanColor color) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response loadGame(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response saveGame(String fileName) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse reset() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse getModel(int version) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListAIResponse listAI() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response addAI(String aiType) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetCommandsResponse getCommands() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse executeCommands(List<String> commands) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response sendChat(String content) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response rollNumber(int number) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response robPlayer(int victimIndex, HexLocation location) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response finishTurn() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response buyDevCard() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response Year_Of_Plenty(ResourceType resource1, ResourceType resource2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response Road_Building(EdgeLocation spot1, EdgeLocation spot2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response Soldier(int victimIndex, HexLocation location) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response Monopoly(ResourceType resource) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response Monument() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response buildRoad(EdgeLocation roadLocation, boolean free) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response buildSettlement(VertexLocation vertexLocation, boolean free) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response buildCity(VertexLocation vertexLocation) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response offerTrade(TradeOffer offer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response discardCards(ResourceList discardedCards) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response changeLogLevel(String loggingLevel) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public CreateGameResponse createGame(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


}
