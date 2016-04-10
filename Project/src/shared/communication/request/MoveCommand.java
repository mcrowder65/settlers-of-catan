package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
/**
 * This executes a move, extends request. Abunch of other
 * classes extend this abstract class.
 * @author mcrowder65
 */
public class MoveCommand extends Request {
	public MoveCommand(){}
	protected int playerIndex;
	protected String type;
	protected  MoveCommand(int playerIndex) throws IllegalArgumentException {
		
		if (playerIndex < 0 || playerIndex > 3)
			throw new IllegalArgumentException("playerIndex must be between 0 and 3");
		this.playerIndex = playerIndex;
	}
	/**
	 * THIS INITIALIZER IS ONLY FOR AI'S DO NOT USE IT
	 * IF YOU DON'T KNOW WHAT YOU ARE DOING WITH IT
	 * @param playerIndex index of player
	 * @param gameID index of game
	 */
	protected MoveCommand(int playerIndex, int gameID){
		this.playerIndex = playerIndex;
		this.gameIDCookie = gameID;
	}
	protected MoveCommand(HttpExchange exchange) {
		super(exchange);
	}
	public MoveCommand(String json) {
		MoveCommand tmp = (MoveCommand)Translator.jsonToObject(json, this.getClass());
		this.playerIndex = tmp.playerIndex;
		this.type = tmp.type;
	}
	
	public  GetModelResponse execute() {return null;}
	


	public String getMoveType() {
		return type;
	}
	public int getPlayerIndex() {
		return playerIndex;
	}
	public int getGameCookie() {
		return gameIDCookie;
	}
	/**
	 * checks to see if its a players turn
	 * @param turnTracker
	 * @param playerIndex
	 * @return boolean
	 */
	public boolean checkTurn(ServerTurnTracker turnTracker, int playerIndex){		
		if(turnTracker.getCurrentTurn() == playerIndex){		
		 	return true;		
		 }		
		 return false;		
	}		
	
	/**
	 * DO NOT USE THIS SETTER EXCEPT FOR AI!!!!!!!!!!!!!!
	 * @param id
	 */
	public void setGameCookie(int id) {
		this.gameIDCookie = id;
	}
	
	public static MoveCommand interpretCommand(String json) throws IllegalArgumentException {
		MoveCommand base = new MoveCommand(json);
		switch (base.type) {
		case "Year_Of_Plenty":
			return new YearOfPlentyCommand(json);
		case "Soldier":
			return new SoldierCommand(json);
		case "sendChat":
			return new SendChatCommand(json);
		case "rollNumber":
			return new RollNumberCommand(json);
		case "robPlayer":
			return new RobPlayerCommand(json);
		case "Road_Building":
			return new RoadBuildingCommand(json);
		case "Monument":
			return new MonumentCommand(json);
		case "Monopoly":
			return new MonopolyCommand(json);
		case "maritimeTrade":
			return new MaritimeTradeCommand(json);
		case "finishTurn":
			return new FinishTurnCommand(json);
		case "discardCards":
			return new DiscardCardsCommand(json);
		case "buyDevCard":
			return new BuyDevCardCommand(json);
		case "buildSettlement":
			return new BuildSettlementCommand(json);
		case "buildRoad":
			return new BuildRoadCommand(json);
		case "buildCity":
			return new BuildCityCommand(json);
		case "acceptTrade":
			return new AcceptTradeCommand(json);
		case "offerTrade":
			return new OfferTradeCommand(json);
		default:
			throw new IllegalArgumentException("Command type '" + base.type + "' was not accounted for.");
		}
		
	}
	
	public GetModelResponse reExecute(int gameID, int playerID) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
