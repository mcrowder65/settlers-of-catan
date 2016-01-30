package shared.definitions;

import shared.locations.*;


/**
 * The GameModel object holds a bank, which is of type ResourceList, 
	 * which contains a list of resources to give to players
 * @author Brennen
 *
 */
public class MirrorGameModel {

	public MirrorGameModel(){
		
	}
	public MirrorGameModel(GameModel gameModel){
		bank = gameModel.getBank();
		chat = gameModel.getChat();
		log = gameModel.getLog();
		map = new MirrorGameMap();
		map.setHexes(gameModel.getMap().getHexes());
		map.setPorts(gameModel.getMap().getPorts());
		makeRoads(gameModel.getMap().getRoads());
		map.setCities(makeVertexObjectArray(gameModel.getMap().getCities()));
		map.setSettlements(makeVertexObjectArray(gameModel.getMap().getSettlements()));
		map.setRadius(gameModel.getMap().getRadius());
		map.setRobber(gameModel.getMap().getRobber());
		players = gameModel.getPlayers();
		tradeOffer = gameModel.getTradeOffer();
		turnTracker = gameModel.getTurnTracker();
		version = gameModel.getVersion();
		winner = gameModel.getWinner();
	}
	public void makeRoads(EdgeValue[] oldRoads){
		MirrorEdgeValue[] thisRoads = new MirrorEdgeValue[oldRoads.length];
		
		for(int i = 0; i < thisRoads.length; i++){
			thisRoads[i] = new MirrorEdgeValue();
			thisRoads[i].setOwner(oldRoads[i].getOwner());
			int x = oldRoads[i].getLocation().getHexLoc().getX();
			int y = oldRoads[i].getLocation().getHexLoc().getY();
			EdgeDirection direction = oldRoads[i].getLocation().getDir();
			MirrorEdgeLocation edgeLoc = new MirrorEdgeLocation(x, y, direction);
			thisRoads[i].setLocation(edgeLoc);
		}
		this.map.setRoads(thisRoads);
	}
	public MirrorVertexObject[] makeVertexObjectArray(VertexObject[] old){
		MirrorVertexObject[] newArray = new MirrorVertexObject[old.length];
		for(int i = 0; i < newArray.length; i++){
			newArray[i] = new MirrorVertexObject();
			newArray[i].setOwner(old[i].getOwner());
			int x = old[i].getLocation().getHexLoc().getX();
			int y = old[i].getLocation().getHexLoc().getY();
			VertexDirection direction = old[i].getLocation().getDir();
			MirrorVertexLocation vertexLoc = new MirrorVertexLocation(x, y, direction);
			newArray[i].setLocation(vertexLoc);
		}
		return newArray;
	}
	public void makeSettlements(VertexObject[] oldSettlements){
		MirrorVertexObject[] newSettlements = new MirrorVertexObject[oldSettlements.length];
		for(int i = 0; i < newSettlements.length; i++){
			newSettlements[i] = new MirrorVertexObject();
			newSettlements[i].setOwner(oldSettlements[i].getOwner());
			int x = oldSettlements[i].getLocation().getHexLoc().getX();
			int y = oldSettlements[i].getLocation().getHexLoc().getY();
			VertexDirection direction = oldSettlements[i].getLocation().getDir();
			MirrorVertexLocation vertexLoc = new MirrorVertexLocation(x, y, direction);
			newSettlements[i].setLocation(vertexLoc);
		}
		this.map.setSettlements(newSettlements);
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
	private MirrorGameMap map;
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
	public MirrorGameMap getMap() {
		return map;
	}
	public void setMap(MirrorGameMap map) throws IllegalArgumentException {
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