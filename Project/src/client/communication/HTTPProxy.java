package client.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import client.utils.Translator;
import shared.communication.request.*;
import shared.communication.response.*;
import shared.definitions.CatanColor;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.definitions.TradeOffer;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * A wrapper for HTTP requests and responses to the server.
 */
public class HTTPProxy implements IProxy{

	/**
	 * The host name of the server.
	 */
	private String hostName;
	/**
	 * The port number to connect to.
	 */
	private int portNumber;
	/**
	 * The user cookie that contains 
	 * the username, password and playerId.
	 */
	private String userCookie;
	/**
	 * The game cookie that contains
	 * the game ID.
	 */
	private String gameCookie;
	/**
	 * The index of the player.
	 */
	private int playerIndex;
	
	/**
	 * Constructs a new instance of HTTPProxy.
	 * @param playerIndex
	 * The index of the player.
	 * @param host
	 * The host name of the server.
	 * @param port
	 * The port to send HTTP requests on.
	 * @throws IllegalArgumentException
	 * Throws this exception if the server cannot be reached,
	 * or playerIndex is invalid.
	 */
	public HTTPProxy(int playerIndex, String host, int port) throws IllegalArgumentException {
		if (playerIndex < 0 || playerIndex > 3) throw new IllegalArgumentException("playerIndex must be between 0 and 3.");
		if (port <= 0 || port > 65535) throw new IllegalArgumentException("port must be non-negative and less than 65535");
		if (host == null || host.equals("")) throw new IllegalArgumentException("Host must be non-null and non-empty.");
		
		this.playerIndex = playerIndex;
		this.portNumber = port;
		this.hostName = host;
		
	}
	/**
	 * Sends a GET request.
	 * @param urlPath
	 * The URL path to send the request on, including the query string
	 * @return
	 * Returns the server's response.
	 */
	private HTTPJsonResponse doGet(String urlPath) throws IOException {
		HTTPJsonResponse response = new HTTPJsonResponse();
		XStream xstream = new XStream(new DomDriver());
		URL url = new URL("http", hostName, portNumber,  urlPath);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		
		response.setResponseCode(conn.getResponseCode());
		Object result = xstream.fromXML(conn.getInputStream());
		response.setResponseBody(result.toString());
		return response;
	}
	/**
	 * Sends a POST request.
	 * @param urlPath
	 * The URL to send the request on
	 * @param requestBody
	 * The data to send to the server.
	 * @return
	 * Returns the server's response.
	 */
	private HTTPJsonResponse doPost(String urlPath, Object requestBody) throws IOException {
		HTTPJsonResponse response = new HTTPJsonResponse();
		
		URL url = new URL("http://" + hostName + ":" + portNumber + urlPath);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		
		conn.connect();
		String json = Translator.objectToJson(requestBody);
		
		conn.getOutputStream().write(json.getBytes());
		conn.getOutputStream().close();
		response.setResponseCode(conn.getResponseCode());
		response.setResponseBody(json);
		return response;
	}

	/**
	 * 
	 * @param cookie
	 * @throws IllegalArgumentException
	 * Throws this exception if the cookie is invalid.
	 */
	private void setUserCookie(String cookie) throws IllegalArgumentException {
	}
	/**
	 * 
	 * @param cookie
	 * @throws IllegalArgumentException
	 * Throws this exception if the cookie is invalid.
	 */
	private void setGameCookie(String cookie) throws IllegalArgumentException {
	}
	
	
	public String getServerURL() {
		
		return "http://" + hostName + ":" + portNumber + "/";
	}	
	private Response sendCommand(String method, Object command)  {
		HTTPJsonResponse httpResponse = new HTTPJsonResponse();
		try {
			httpResponse =  doPost(method, command);
		} 
		catch (IOException e) 
		{
			httpResponse.setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST);
			httpResponse.setResponseBody(e.getMessage());
		}
		return new Response(httpResponse.getResponseCode(), httpResponse.getResponseBody());
	}
	@Override
	public Response sendChat(String content) {
		//SendChatCommand command = new SendChatCommand(playerIndex, content);
		//return sendCommand(command);
		return null;
	}
	@Override
	public LoginResponse login(String username, String password) throws IllegalArgumentException {
		Response response = sendCommand("/user/login", new LoginRequest(username, password));
		return new LoginResponse(response.getResponseCode(), response.getJson());

	}
	
	@Override
	public RegisterResponse register(String username, String password) throws IllegalArgumentException {
		Response response = sendCommand("/user/register", new RegisterRequest(username, password));
		return new RegisterResponse(response.getResponseCode(), response.getJson());
	}
	@Override
	public ListGamesResponse listGames() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CreateGameResponse createGame(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) throws IllegalArgumentException {
		Response response = sendCommand("/games/create", new CreateGameRequest(name, randomTiles, randomNumbers, randomPorts));
		return new CreateGameResponse(response.getResponseCode(), response.getJson());
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


	
}