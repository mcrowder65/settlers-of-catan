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
import java.util.HashMap;
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

	
	private int playerId;
	
	/**
	 * Constructs a new instance of HTTPProxy.
	 * @param playerIndex
	 * The index of the player.
	 * @param host
	 * The host name of the server.
	 * @param port
	 * The port to send HTTP requests on.
	 * @throws IllegalArgumentException Throws this exception if the server cannot be reached, or playerIndex is invalid.
	 * 
	 * 
	 */
	public HTTPProxy(String host, int port) throws IllegalArgumentException {
		if (port <= 0 || port > 65535) throw new IllegalArgumentException("port must be non-negative and less than 65535");
		if (host == null || host.equals("")) throw new IllegalArgumentException("Host must be non-null and non-empty.");

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
		
		response.setResponseCookie(conn.getHeaderField("Set-cookie"));
		
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
	public String parseCookie(String cookie) {
		StringBuilder smallerCookie = new StringBuilder(cookie.substring(11, cookie.length()));
		String encodedCookie = smallerCookie.substring(0, smallerCookie.length()-8);
		return encodedCookie;
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
		
		response.setResponseCookie(conn.getHeaderField("Set-cookie"));
		
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
	 */
	private void setUserCookie(String cookie) {
		this.userCookie = cookie;
	}
	/**
	 * 
	 * @param cookie
	 * @throws IllegalArgumentException
	 */
	private void setGameCookie(String cookie)  {
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
	public GetModelResponse sendChat(int playerIndex, String content) {
		SendChatCommand command = new SendChatCommand(playerIndex, content);
		return sendCommand(command);	
	}
	@Override
	public Response login(String username, String password) throws IllegalArgumentException {
		HTTPJsonResponse response = sendRequest("/user/login", new LoginRequest(username, password));
		if (response.getResponseCookie() != null)
			setUserCookie(parseCookie(response.getResponseCookie()));
		
		return new Response(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public Response register(String username, String password) throws IllegalArgumentException {
		HTTPJsonResponse response = sendRequest("/user/register", new RegisterRequest(username, password));
		if (response.getResponseCookie() != null)
			setUserCookie(parseCookie(response.getResponseCookie()));
		
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
		if (response.getResponseCookie() != null)
			setGameCookie(parseCookie(response.getResponseCookie()));
		
		return new Response(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public Response loadGame(String name) throws IllegalArgumentException {
		return null;
	}
	@Override
	public Response saveGame(int id, String filename) throws IllegalArgumentException {

		return null;
	}
	@Override
	public GetModelResponse reset() {
		return null;
	}
	@Override
	public GetModelResponse getModel() {
		HTTPJsonResponse response = sendRequest("/game/model", new GetModelRequest());
		return new GetModelResponse(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public GetModelResponse getModel(int version) {
		HTTPJsonResponse response = sendRequest("/game/model?version=" + version, new GetModelRequest(version));
		return new GetModelResponse(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public ListAIResponse listAI() {
		HTTPJsonResponse response = sendRequest("/game/listAI", null);
		return new ListAIResponse(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public Response addAI(String aiType) throws IllegalArgumentException {
		HTTPJsonResponse response = sendRequest("/game/addAI", new AddAIRequest(getAIType(aiType)));
		
		return new Response(response.getResponseCode(), response.getResponseBody());
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
	public GetModelResponse rollNumber(int playerIndex, int number) throws IllegalArgumentException {
		RollNumberCommand command = new RollNumberCommand(playerIndex, number);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse robPlayer(int playerIndex, int victimIndex, HexLocation location) throws IllegalArgumentException {
		RobPlayerCommand command = new RobPlayerCommand(playerIndex, location, victimIndex);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse finishTurn(int playerIndex) {
		FinishTurnCommand command = new FinishTurnCommand(playerIndex);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse buyDevCard(int playerIndex) {
		BuyDevCardCommand command = new BuyDevCardCommand(playerIndex);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse Year_Of_Plenty(int playerIndex, ResourceType resource1, ResourceType resource2) throws IllegalArgumentException {
		YearOfPlentyCommand command = new YearOfPlentyCommand(playerIndex, resource1, resource2);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse Road_Building(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) throws IllegalArgumentException {
		RoadBuildingCommand command = new RoadBuildingCommand(playerIndex, spot1, spot2);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse Soldier(int playerIndex, int victimIndex, HexLocation location) throws IllegalArgumentException {
		SoldierCommand command = new SoldierCommand(playerIndex, location, victimIndex);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse Monopoly(int playerIndex, ResourceType resource) {
		MonopolyCommand command = new MonopolyCommand(playerIndex, resource);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse Monument(int playerIndex) {
		MonumentCommand command = new MonumentCommand(playerIndex);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse buildRoad(int playerIndex, EdgeLocation roadLocation, boolean free) throws IllegalArgumentException {
		BuildRoadCommand command = new BuildRoadCommand(playerIndex, free, roadLocation);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse buildSettlement(int playerIndex, VertexLocation vertexLocation, boolean free) throws IllegalArgumentException {
		BuildSettlementCommand command = new BuildSettlementCommand(playerIndex, free, vertexLocation);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse buildCity(int playerIndex, VertexLocation vertexLocation) throws IllegalArgumentException {
		BuildCityCommand command = new BuildCityCommand(playerIndex, vertexLocation);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse offerTrade(int playerIndex, TradeOffer offer) throws IllegalArgumentException {
		OfferTradeCommand command = new OfferTradeCommand(offer.getSender(), offer.getReciever(), offer.getOffer());
		return sendCommand(command);
	}
	@Override
	public GetModelResponse maritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource)
			throws IllegalArgumentException {
		MaritimeTradeCommand command = new MaritimeTradeCommand(playerIndex, ratio, inputResource, outputResource);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse acceptTrade(int playerIndex, boolean willAccept) {
		AcceptTradeCommand command = new AcceptTradeCommand(playerIndex, willAccept);
		return sendCommand(command);
	}
	@Override
	public GetModelResponse discardCards(int playerIndex, ResourceList discardedCards) throws IllegalArgumentException {
		DiscardCardsCommand command = new DiscardCardsCommand(playerIndex, discardedCards);
		return sendCommand(command);
	}
	@Override
	public Response changeLogLevel(LogLevel loggingLevel) throws IllegalArgumentException {
		HTTPJsonResponse response =  sendRequest("/util/changeLogLevel", new ChangeLogLevelRequest(loggingLevel));
		return new Response(response.getResponseCode(), response.getResponseBody());
	}
	@Override
	public int getPlayerId() {
		try {
		String decoded = URLDecoder.decode(userCookie);
		HashMap<String,String> kvPairs = Translator.makeKeyValuePairs(decoded);
		
		if (!kvPairs.containsKey("playerID")) 
			return -1;
	
			Object o = kvPairs.get("playerID");
			Double d = (Double)o;
			
			int playerId = d.intValue();
			return playerId;
		} catch (Exception ex) {
			ex.printStackTrace(); return -1;
		}
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