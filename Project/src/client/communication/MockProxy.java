package client.communication;

import java.io.*;
import java.util.List;

import shared.communication.response.*;
import shared.definitions.CatanColor;
import shared.definitions.LogLevel;
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
	 * Constructs a new MockProxy instance.
	 */
	public MockProxy() {
	}
	
	private String readResponse(String relativePath) {
		InputStream in = 
			    getClass().getResourceAsStream(relativePath);
				Reader fr;
				try {
					fr = new InputStreamReader(in, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return e.toString();
				}
				BufferedReader br = new BufferedReader(fr);
				
		try {
			
	
			StringBuilder strBld = new StringBuilder();
			String line;
			line = br.readLine();
			
			while (line != null) {
				strBld.append(line + System.lineSeparator());
				line = br.readLine();
			}
			return strBld.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				return e.toString();
			}
		}
	
	}
	
	@Override
	public Response login(String username, String password) throws IllegalArgumentException {
		return new Response(200, "Success");
	}
	@Override
	public Response register(String username, String password) throws IllegalArgumentException {
		return new Response(200, "Success");
	}
	@Override
	public ListGamesResponse listGames() {
		return new ListGamesResponse(200,readResponse("sample_game_info.txt"));
	}
	@Override
	public Response joinGame(int id, CatanColor color) throws IllegalArgumentException {
		return new Response(200, "Success");
	}
	@Override
	public Response loadGame(String name) throws IllegalArgumentException {
		return new Response(200, "Success");
	}
	@Override
	public Response saveGame(int id, String fileName) throws IllegalArgumentException {
		return new Response(200, "Success");
	}
	@Override
	public GetModelResponse reset() {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse getModel() {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse getModel(int version) {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public ListAIResponse listAI() {
		return new ListAIResponse(200, readResponse("sample_ailist.txt"));
	}
	@Override
	public Response addAI(String aiType) throws IllegalArgumentException {
		return new Response(200, "Success");
	}
	@Override
	public GetCommandsResponse getCommands() {
		return null;
	}
	@Override
	public GetModelResponse executeCommands(List<String> commands) throws IllegalArgumentException {
		return null;
	}
	@Override
	public GetModelResponse sendChat(String content) {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse rollNumber(int number) throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse robPlayer(int victimIndex, HexLocation location) throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse finishTurn() {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse buyDevCard() {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse Year_Of_Plenty(ResourceType resource1, ResourceType resource2) throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse Road_Building(EdgeLocation spot1, EdgeLocation spot2) throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse Soldier(int victimIndex, HexLocation location) throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse Monopoly(ResourceType resource) {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse Monument() {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse buildRoad(EdgeLocation roadLocation, boolean free) throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse buildSettlement(VertexLocation vertexLocation, boolean free) throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse buildCity(VertexLocation vertexLocation) throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse offerTrade(TradeOffer offer) throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource)
			throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse acceptTrade(boolean willAccept) {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public GetModelResponse discardCards(ResourceList discardedCards) throws IllegalArgumentException {
		return new GetModelResponse(200, readResponse("sample_model.txt"));
	}
	@Override
	public Response changeLogLevel(LogLevel loggingLevel) throws IllegalArgumentException {
		return new Response(200, "Success");
	}




	@Override
	public CreateGameResponse createGame(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts)
			throws IllegalArgumentException {
		return new CreateGameResponse(200, readResponse("sample_new_game.txt"));
	}


}
