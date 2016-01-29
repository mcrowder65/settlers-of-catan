package shared.definitions;

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
	 * a list of resources to give to players
	 */
	private ResourceList bank;
	
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
	/**
	 * the local player who has this instance running
	 */
	private Player localPlayer;
	public ResourceList getBank() {
		return bank;
	}
	public void setBank(ResourceList bank) throws IllegalArgumentException  {
		this.bank = bank;
	}
	public MessageList getChat() {
		return chat;
	}
	public void setChat(MessageList chat)  throws IllegalArgumentException {
		this.chat = chat;
	}
	public MessageList getLog() {
		return log;
	}
	public void setLog(MessageList log) throws IllegalArgumentException  {
		this.log = log;
	}
	public GameMap getMap() {
		return map;
	}
	public void setMap(GameMap map) throws IllegalArgumentException {
		this.map = map;
	}
	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player[] players)  throws IllegalArgumentException {
		this.players = players;
	}
	public TradeOffer getTradeOffer() {
		return tradeOffer;
	}
	public void setTradeOffer(TradeOffer tradeOffer)  throws IllegalArgumentException {
		this.tradeOffer = tradeOffer;
	}
	public TurnTracker getTurnTracker() {
		return turnTracker;
	}
	public void setTurnTracker(TurnTracker turnTracker)  throws IllegalArgumentException  {
		this.turnTracker = turnTracker;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version)  throws IllegalArgumentException {
		this.version = version;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner)  throws IllegalArgumentException {
		this.winner = winner;
	}
	public String getUserCookie() {
		return userCookie;
	}
	public void setUserCookie(String userCookie)  throws IllegalArgumentException {
		this.userCookie = userCookie;
	}
	public String getGameCookie() {
		return gameCookie;
	}
	public void setGameCookie(String gameCookie)  throws IllegalArgumentException {
		this.gameCookie = gameCookie;
	}
	public Player getLocalPlayer() {
		return localPlayer;
	}
	public void setLocalPlayer(Player localPlayer)  throws IllegalArgumentException {
		this.localPlayer = localPlayer;
	}
	
	
	
}