package client.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import client.main.Catan;
import client.utils.Translator;
import shared.communication.request.*;
import shared.communication.response.*;
import shared.definitions.AIType;
import shared.definitions.CatanColor;
import shared.definitions.LogLevel;
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
		
		URL url = new URL("http://" + hostName + ":" + portNumber + urlPath);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.connect();
		
		String result = null;
		 if (conn.getResponseCode() < 400) {
			 BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			 result = in.readLine();
		 	 in.close();
		 } 
		 else {
		 	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		 	result = in.readLine();
		 	in.close();
		 }
		response.setResponseCode(conn.getResponseCode());
		response.setResponseBody(result);
		return response;
	}
	@SuppressWarnings("static-access")
	public String decodeCookie(String encodedCookie) throws UnsupportedEncodingException{
		return new URLDecoder().decode(encodedCookie, "UTF-8");
	}
	public void setCookies(HttpURLConnection conn){
		if(this.userCookie != null) {
			conn.setRequestProperty("Cookie", "catan.user=" + this.userCookie);
		}
		if(this.gameCookie != null && this.userCookie != null){
			conn.setRequestProperty("Cookie", "catan.user=" + this.userCookie + 
					"; catan.game=" + this.gameCookie);
		}
	}
	public void evaluateCookies(HttpURLConnection conn, String urlPath){
		if(conn.getHeaderField("Set-cookie") != null && this.userCookie == null){
			StringBuilder bigCookie = new StringBuilder(conn.getHeaderField("Set-cookie"));
			StringBuilder smallerCookie = new StringBuilder(bigCookie.substring(11, bigCookie.length()));
			String encodedCookie = smallerCookie.substring(0, smallerCookie.length()-8);
			setUserCookie(encodedCookie);
		}
		if(urlPath.equals("/games/join")){
			StringBuilder bigCookie = new StringBuilder(conn.getHeaderField("Set-cookie"));
			StringBuilder smallerCookie = new StringBuilder(bigCookie.substring(11, bigCookie.length()));
			String encodedCookie = smallerCookie.substring(0, smallerCookie.length()-8);
			setGameCookie(encodedCookie);
		}
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
		setCookies(conn); //sets user and/or game cookies on HTTP headers as needed.
		
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.connect();
		String json = Translator.objectToJson(requestBody);
		
		conn.getOutputStream().write(json.getBytes());
		conn.getOutputStream().close();
		evaluateCookies(conn, urlPath); //evaluates whether to set "this"'s cookies.
		
		String result = null;
		 if (conn.getResponseCode() < 400) {
			 BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			 result = in.readLine();
		 	 in.close();
		 } 
		 else {
		 	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		 	result = in.readLine();
		 	in.close();
		 }
		response.setResponseCode(conn.getResponseCode());
		response.setResponseBody(result);
		return response;
	}

	/**
	 * 
	 * @param cookie
	 * @throws IllegalArgumentException
	 * Throws this exception if the cookie is invalid.
	 */
	private void setUserCookie(String cookie) throws IllegalArgumentException {
		this.userCookie = cookie;
	}
	/**
	 * 
	 * @param cookie
	 * @throws IllegalArgumentException
	 * Throws this exception if the cookie is invalid.
	 */
	private void setGameCookie(String cookie) throws IllegalArgumentException {
		this.gameCookie = cookie;
	}
	
	
	public String getServerURL() {
		
		return "http://" + hostName + ":" + portNumber + "/";
	}	
	private GetModelResponse sendCommand(MoveCommand command)  {
		HTTPJsonResponse httpResponse = new HTTPJsonResponse();
		try {
			httpResponse = doPost("/moves/" + command.getMoveType(), command);
		} 
		catch (IOException e) 
		{
			httpResponse.setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST);
			httpResponse.setResponseBody(e.getMessage());
		}
		return new GetModelResponse(httpResponse.getResponseCode(), httpResponse.getResponseBody());
	}
	private HTTPJsonResponse sendRequest(String path, Request request) {
		HTTPJsonResponse httpResponse = new HTTPJsonResponse();
		try {
			httpResponse = request == null ? doGet(path) : doPost(path, request);
		} 
		catch (IOException e) 
		{
			httpResponse.setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST);
			httpResponse.setResponseBody(e.getMessage());
		}
		return httpResponse;
	}
	@Override
	public GetModelResponse sendChat(String content) {
		SendChatCommand command = new SendChatCommand(playerIndex, content);
		 return sendCommand(command);
		
	}
	@Override
	public Response login(String username, String password) throws IllegalArgumentException {
		HTTPJsonResponse response = sendRequest("/user/login", new LoginRequest(username, password));
		return new Response(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public Response register(String username, String password) throws IllegalArgumentException {
		HTTPJsonResponse response = sendRequest("/user/register", new RegisterRequest(username, password));
		return new Response(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public ListGamesResponse listGames() {
		HTTPJsonResponse response = sendRequest("/games/list", null);
		return new ListGamesResponse(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public CreateGameResponse createGame(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) throws IllegalArgumentException {
		HTTPJsonResponse response = sendRequest("/games/create", new CreateGameRequest(name, randomTiles, randomNumbers, randomPorts));
		return new CreateGameResponse(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public Response joinGame(int id, CatanColor color) throws IllegalArgumentException {
		HTTPJsonResponse response = sendRequest("/games/join", new JoinGameRequest(id, color));
		return new Response(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public Response loadGame(String name) throws IllegalArgumentException {
		// TODO some other phase?
		return null;
	}
	@Override
	public Response saveGame(int id, String filename) throws IllegalArgumentException {
		// TODO some other phase?
		//Response response = sendCommand(new )
		//Response response = sendCommand("/games/save", new SaveGameRequest(id, filename));
		return null;
	}
	@Override
	public GetModelResponse reset() {
		// TODO some other phase?
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
		HTTPJsonResponse response = sendRequest("/game/addAI", new AddAIRequest(getAIType(aiType)));
		
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

	public AIType getAIType(String type){
		switch(type){
		case "LARGEST_ARMY":
			return AIType.LARGEST_ARMY;
		default:
			return null;
		}
	}
	
}