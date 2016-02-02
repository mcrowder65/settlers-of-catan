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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response saveGame(int id, String fileName) throws IllegalArgumentException {
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
