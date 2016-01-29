package client.communication;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
		
	}
	/**
	 * Sends a GET request.
	 * @param urlPath
	 * The URL path to send the request on, including the query string
	 * @return
	 * Returns the server's response.
	 */
	private Object doGet(String urlPath) throws IOException {
		
		XStream xstream = new XStream(new DomDriver());
		URL url = new URL("http", hostName, portNumber,  urlPath);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			Object result = xstream.fromXML(conn.getInputStream());
			return result;
		}
		return null;
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
	private Object doPost(String urlPath, Object requestBody) throws IOException {
		XStream xstream = new XStream(new DomDriver());
		URL url = new URL("http", hostName, portNumber, urlPath);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		conn.connect();
		xstream.toXML(requestBody, conn.getOutputStream());
		
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			Object result = xstream.fromXML(conn.getInputStream());
			return result;
		}
		return null;
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