package shared.definitions;

import java.util.ArrayList;
import java.util.List;

import client.data.PlayerInfo;
import client.data.RobPlayerInfo;
import shared.locations.*;


/**
 * The GameModel object holds a bank, which is of type ResourceList, 
	 * which contains a list of resources to give to players
 * @author Brennen
 *
 */
public class GameModel {

	public GameModel(){
		
	}
	
	/**
	 * a list of all resources cards to give to players
	 */
	private ResourceList bank;
	
	/**
	 * a list of all dev cards to give to players.
	 */
	private DevCardList deck;
	
	/**
	 * The chat object of type MessageList holds the messages sent back and forth
	 * between users
	 */
	private MessageList chat;
	
	/**
	 * All the log messages
	 */
	private MessageList log;
	private GameMap map;
	private Player[] players;
	
	/**
	 * The current trade offer, if there is one
	 */
	private TradeOffer tradeOffer;
	
	/**
	 * This tracks who's turn it is and what action's being done
	 */
	private TurnTracker turnTracker;
	
	/**
	 * The version of the model. This is incremented 
	 * whenever anyone makes a move
	 */
	private int version;
	
	/**
	 *  This is -1 when nobody's won yet. 
	 *  When they have, it's their order 
	 *  index [0-3]
	 */
	private int winner;
	
	/**
	 * contains username, password, playerID
	 */
	private String userCookie;
	/**
	 * unique identifier of the game that the player is in 
	 */
	private String gameCookie;

	
	public ResourceList getBank() throws IllegalArgumentException {
		return bank;
	}
	public void setBank(ResourceList bank) throws IllegalArgumentException  {
		this.bank = bank;
	}
	public DevCardList getDeck() throws IllegalArgumentException{
		return deck;
	}
	public void setDeck(DevCardList deck) throws IllegalArgumentException{
		this.deck = deck;
	}
	public MessageList getChat() throws IllegalArgumentException{
		return chat;
	}
	public void setChat(MessageList chat)  throws IllegalArgumentException {
		this.chat = chat;
	}
	public MessageList getLog() throws IllegalArgumentException{
		return log;
	}
	public void setLog(MessageList log) throws IllegalArgumentException  {
		this.log = log;
	}
	public GameMap getMap() throws IllegalArgumentException{
		return map;
	}
	public void setMap(GameMap map) throws IllegalArgumentException {
		this.map = map;
	}
	public Player[] getPlayers() throws IllegalArgumentException{
		return players;
	}
	public PlayerInfo[] getPlayersLite() {
		int playerCount = 0;
		for (playerCount = 0; playerCount < players.length; playerCount++) {
			if (players[playerCount] == null) break;
		}
		PlayerInfo[] playersLite = new PlayerInfo[playerCount];
		for (int n = 0; n < playerCount; n++) {
			playersLite[n] = new PlayerInfo(players[n]);
		}
		return playersLite;
	}
	public PlayerInfo[] getOtherPlayers(int playerID){
		ArrayList<PlayerInfo> oP = new ArrayList<PlayerInfo>();
		for(int i = 0; i < 4; i++){
			if(players[i].getPlayerID() != playerID)
				oP.add(new PlayerInfo(players[i]));
		}
		
		PlayerInfo[] otherPlayers = new PlayerInfo[3];
		for(int i = 0; i < 3; i++)
			otherPlayers[i] = oP.get(i);
		return otherPlayers;
	}
	public Player findPlayerByName(String name) {
		for (int n = 0; n < players.length; n++) {
			if (players[n] == null) break;
			if (players[n].getName().equals(name)) 
				return players[n];
		}
		return null;
	}
	
	public void setPlayers(Player[] players)  throws IllegalArgumentException {
		this.players = players;
	}
	public TradeOffer getTradeOffer() throws IllegalArgumentException{
		return tradeOffer;
	}
	public void setTradeOffer(TradeOffer tradeOffer)  throws IllegalArgumentException {
		this.tradeOffer = tradeOffer;
	}
	public TurnTracker getTurnTracker() throws IllegalArgumentException{
		return turnTracker;
	}
	public void setTurnTracker(TurnTracker turnTracker)  throws IllegalArgumentException  {
		this.turnTracker = turnTracker;
	}
	public int getVersion() throws IllegalArgumentException{
		return version;
	}
	public void setVersion(int version)  throws IllegalArgumentException {
		this.version = version;
	}
	public int getWinner() throws IllegalArgumentException{
		return winner;
	}
	public void setWinner(int winner)  throws IllegalArgumentException {
		this.winner = winner;
	}
	public String getUserCookie() throws IllegalArgumentException{
		return userCookie;
	}
	public void setUserCookie(String userCookie)  throws IllegalArgumentException {
		this.userCookie = userCookie;
	}
	public String getGameCookie() throws IllegalArgumentException{
		return gameCookie;
	}
	public void setGameCookie(String gameCookie)  throws IllegalArgumentException {
		this.gameCookie = gameCookie;
	}
	public Player getLocalPlayer(int playerId) {
		for (int n = 0; n < players.length; n++) {
			if (players[n] != null && players[n].getPlayerID() == playerId) 
				return players[n];
		}
		return null;
	}
	/**
	 * WARNING!! FOR TESTING ONLY!!!!!!!!!!!
	 * @param player
	 */
	public void setLocalPlayer(Player player) {
		if (players == null)
			players = new Player[4];
		players[0] = player;
	}
	public int getLocalIndex(int playerId) {
		for (int n = 0; n < players.length; n++) {
			if (players[n] != null && players[n].getPlayerID() == playerId) 
				return n;
		}
		return -1;
	}
	

	
}