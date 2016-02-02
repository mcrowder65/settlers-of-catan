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
			    getClass().getResourceAsStream("/client/communication/mockresponse/" + relativePath + ".txt");
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
				strBld.append(line);
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
		return new GetModelResponse(200, "true");
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse rollNumber(int number) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse robPlayer(int victimIndex, HexLocation location) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse finishTurn() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse buyDevCard() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse Year_Of_Plenty(ResourceType resource1, ResourceType resource2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse Road_Building(EdgeLocation spot1, EdgeLocation spot2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse Soldier(int victimIndex, HexLocation location) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse Monopoly(ResourceType resource) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse Monument() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse buildRoad(EdgeLocation roadLocation, boolean free) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse buildSettlement(VertexLocation vertexLocation, boolean free) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse buildCity(VertexLocation vertexLocation) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse offerTrade(TradeOffer offer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GetModelResponse discardCards(ResourceList discardedCards) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response changeLogLevel(LogLevel loggingLevel) throws IllegalArgumentException {
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
