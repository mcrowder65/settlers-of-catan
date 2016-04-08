package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.*;
import shared.communication.response.GetModelResponse;
import shared.definitions.MessageLine;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
/**
 * This class executes the soldier moves. Extends MoveCommand
 * @author mcrowder65
 *
 */
public class SoldierCommand extends MoveCommand {

	private HexLocation location;
	private int victimIndex;
	public SoldierCommand(int playerIndex, HexLocation location, int victimIndex) throws IllegalArgumentException {
		super(playerIndex);
		if (victimIndex < -1 || victimIndex > 3) 
			throw new IllegalArgumentException("victimIndex must be between -1 - 3");
		if (location == null)
			throw new IllegalArgumentException("location can't be null.");

		this.location = location;
		this.victimIndex = victimIndex;
		this.type = "Soldier";
	}
	public SoldierCommand(HttpExchange exchange) {
		super(exchange);
		SoldierCommand tmp = (SoldierCommand)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
		this.location = tmp.location;
		this.victimIndex = tmp.victimIndex;
		
	}
	/**
	 * Executes the Soldier - returns a GetModelResponse
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();	
			HexLocation robberLoc = this.getLocation();		
	 		Game game = Game.instance();	
	 		GetModelResponse response = new GetModelResponse();
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		String status = turnTracker.getStatus();
	 		//updating header
	 		try {
				response.setCookie("Set-cookie", "catan.user=" +
						URLEncoder.encode("{" +
					       "\"authentication\":\"" + "1142128101" + "\"," +
				           "\"name\":\"" + userCookie + "\"," +
						   "\"password\":\"" + passCookie + "\"," + 
				           "\"playerID\":" + playerIDCookie + "}", "UTF-8" ) + ";catan.game=" + gameIDCookie);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	 		//checking the turn
	 		if(checkTurn(turnTracker,playerIndex) == false){		
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response; 		
			}
	 		//checking the status
	 		if(!status.equals("Playing")){
	 			response.setSuccess(false);
				response.setErrorMessage("Wrong status");
				return response;
	 		}
	 		
	 		//seeing if the player can playt the card
	 		if(!player.canPlaySoldierCard()){
	 			response.setSuccess(false);
				response.setErrorMessage("Player cannot play dev card");
				return response;
	 		}
	 		
	 		//checking to make sure the robber loc is valid
	 		if(map.isLand(robberLoc) == false){
	 			response.setSuccess(false);
				response.setErrorMessage("Invalid Hex Location");
				return response;
	 		}
	 		//checking to see if there is a victim
	 		if(victimIndex == -1){
	 			player.playSoldierCard(); 
	 			player.setPlayedDevCard(true);
				map.setRobber(robberLoc);
	 			model.setVersion(model.getVersion() + 1); //updates version
	 			Game.instance().getGameId(gameIDCookie).findLargestArmy(); //updates largest army
	 			response.setSuccess(true);
	 			addGameLog(player,model,null);
	 			response.setJson(model.toString());
	 			
	 			return response;
	 		}
	 		ServerPlayer victim = model.getServerPlayers()[victimIndex]; //getting the victim
	 		
	 		//if the victim has no resources
	 		if(victim.getResources().isEmpty()){
	 			player.playSoldierCard();
	 			player.setPlayedDevCard(true);
				map.setRobber(robberLoc);
	 			model.setVersion(model.getVersion() + 1);
	 			Game.instance().getGameId(gameIDCookie).findLargestArmy(); //updates largest army
	 			response.setSuccess(true);
	 			addGameLog(player,model,victim);
	 			response.setJson(model.toString());
	 			
	 			return response;
			}
	 		
	 		ResourceType resource = model.generateRandomResource(); //gets random resource
			
	 		//keeps generating random resources if the victim doesn't have one
			while(victim.getResources().hasResource(resource) == false){
				resource = model.generateRandomResource();
			}
			
			//executing the rob
			victim.removeResource(resource);
			player.addResource(resource);
			model.setVersion(model.getVersion() +1);
			addGameLog(player,model,victim);
			player.playSoldierCard();
			Game.instance().getGameId(gameIDCookie).findLargestArmy();
			player.setPlayedDevCard(true);
			map.setRobber(robberLoc);
			response.setSuccess(true);
			response.setJson(model.toString());
			
		    return response; 
		}
	
	}
	
	/**
	 * updates the game log
	 * @param player
	 * @param model
	 * @param player2
	 */
	public void addGameLog(ServerPlayer player, ServerGameModel model, ServerPlayer player2){
		String message = player.getName() + " used a soldier";
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
		
		if(player2 == null){
			String message2 = player.getName() + " moved the robber but couldn't rob anyone!";
			MessageLine line2 = new MessageLine(message2,player.getName());
			model.addGameLogMessage(line2);
		}
		else{
			String message2 = player.getName() + " moved the robber and robbed "+player2.getName();
			MessageLine line2 = new MessageLine(message2,player.getName());
			model.addGameLogMessage(line2);
		}
	}

	public HexLocation getLocation() {
		return location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}


}
