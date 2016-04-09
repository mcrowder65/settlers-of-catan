package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.*;
import shared.communication.response.GetModelResponse;
import shared.definitions.MessageLine;
import shared.definitions.MirrorResourceType;
import shared.definitions.ResourceType;

/**
 * This executes the year of plenty. Extends MoveCommand.
 * @author mcrowder65
 *
 */
public class YearOfPlentyCommand extends MoveCommand {
	MirrorResourceType resource1;
	MirrorResourceType resource2;
	
	public YearOfPlentyCommand(int playerIndex, ResourceType resource1, ResourceType resource2)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(resource1 == null) throw new IllegalArgumentException("Resource1 can't be NULL");
		if(resource2 == null) throw new IllegalArgumentException("Resource2 can't be NULL");
		
		if(resource1 == ResourceType.NONE) throw new IllegalArgumentException("Resource1 can't be None");
		if(resource2 == ResourceType.NONE) throw new IllegalArgumentException("Resource2 can't be None");
		
		this.resource1 = MirrorResourceType.getResource(resource1);
		this.resource2 = MirrorResourceType.getResource(resource2);
		this.type = "Year_Of_Plenty";
	}
	
	public YearOfPlentyCommand(HttpExchange exchange) {
		super(exchange);
		YearOfPlentyCommand tmp = (YearOfPlentyCommand)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.resource1 = tmp.resource1;
		this.resource2 = tmp.resource2;
		this.playerIndex = tmp.playerIndex;
		
	}
	public YearOfPlentyCommand(String json) {
		YearOfPlentyCommand tmp = (YearOfPlentyCommand)Translator.jsonToObject(json, this.getClass());
		this.resource1 = tmp.resource1;
		this.resource2 = tmp.resource2;
		this.playerIndex = tmp.playerIndex;
	}
	/**
	 * Function that executes the year of plenty.
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();
			ResourceType res1 = this.getResource1();
			ResourceType res2 = this.getResource2();
	 		Game game = Game.instance();	
	 		GetModelResponse response = new GetModelResponse();
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		String status = turnTracker.getStatus();
	 		
	 		//updates cookie header
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
	 		
	 		//checks to make sure the turn is correct
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
	 		//making sure the bank has the first resource
	 		if(model.getBank().hasResource(res1) == false){
	 			response.setSuccess(false);
				response.setErrorMessage("Bank doesn't have resources to fill order");
				return response;
	 		}
	 		//making sure the bank has the second resource
	 		if(model.getBank().hasResource(res2) == false){
	 			response.setSuccess(false);
				response.setErrorMessage("Bank doesn't have resources to fill order");
				return response;
	 		}
	 		//if both the resources are the same
	 		if(res1 == res2){
	 			if(model.getBank().getResource(res1) < 2){
	 				response.setSuccess(false);
					response.setErrorMessage("Bank doesn't have resources to fill order");
					return response;
	 			}
	 		}
	 		//checks to see if the playr can use the card
	 		if(!player.canPlayYearOfPlentyCard()){
	 			response.setSuccess(false);
				response.setErrorMessage("Player cannot play YOP card");
				return response;
	 		}
	 		//plays the card
	 		player.playYearOfPlentyCard();
	 		player.setPlayedDevCard(true);
	 		player.getResources().addResource(res1,1);
	 		player.getResources().addResource(res2,1);
	 		model.getBank().removeResource(res1,1);
	 		model.getBank().removeResource(res2,1);
	 		addGameLog(player,model,res1,res2);
	 		model.setVersion(model.getVersion() + 1); //updates model
			response.setSuccess(true);
			response.setJson(model.toString());
			
			return response;
		}
	}
	
	@Override
	public GetModelResponse reExecute(int gameID, int playerIndex) {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
			int gameIndex = gameID;
			//int playerIndex = this.getPlayerIndex();
			ResourceType res1 = this.getResource1();
			ResourceType res2 = this.getResource2();
	 		Game game = Game.instance();	
	 		GetModelResponse response = new GetModelResponse();
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		String status = turnTracker.getStatus();
	 		
	 		//updates cookie header
	 		try {
				response.setCookie("Set-cookie", "catan.user=" +
						URLEncoder.encode("{" +
					       "\"authentication\":\"" + "1142128101" + "\"," +
				           "\"name\":\"" + player.getName() + "\"," +
						   "\"password\":\"" + game.getPassword(player.getName()) + "\"," + 
				           "\"playerID\":" + player.getPlayerID() + "}", "UTF-8" ) + ";catan.game=" + gameID);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	 		
	 		//checks to make sure the turn is correct
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
	 		//making sure the bank has the first resource
	 		if(model.getBank().hasResource(res1) == false){
	 			response.setSuccess(false);
				response.setErrorMessage("Bank doesn't have resources to fill order");
				return response;
	 		}
	 		//making sure the bank has the second resource
	 		if(model.getBank().hasResource(res2) == false){
	 			response.setSuccess(false);
				response.setErrorMessage("Bank doesn't have resources to fill order");
				return response;
	 		}
	 		//if both the resources are the same
	 		if(res1 == res2){
	 			if(model.getBank().getResource(res1) < 2){
	 				response.setSuccess(false);
					response.setErrorMessage("Bank doesn't have resources to fill order");
					return response;
	 			}
	 		}
	 		//checks to see if the playr can use the card
	 		if(!player.canPlayYearOfPlentyCard()){
	 			response.setSuccess(false);
				response.setErrorMessage("Player cannot play YOP card");
				return response;
	 		}
	 		//plays the card
	 		player.playYearOfPlentyCard();
	 		player.setPlayedDevCard(true);
	 		player.getResources().addResource(res1,1);
	 		player.getResources().addResource(res2,1);
	 		model.getBank().removeResource(res1,1);
	 		model.getBank().removeResource(res2,1);
	 		addGameLog(player,model,res1,res2);
	 		model.setVersion(model.getVersion() + 1); //updates model
			response.setSuccess(true);
			response.setJson(model.toString());
			
			return response;
		}
	}
	/**
	 * updates the gamelog
	 * @param player
	 * @param model
	 * @param res1
	 * @param res2
	 */
	public void addGameLog(ServerPlayer player, ServerGameModel model, ResourceType res1, ResourceType res2){
		String resource1 = resourceToString(res1);
		String resource2 = resourceToString(res2);
		String message = player.getName() + " used a Year of Plenty and got a "+ resource1 + " and a " + resource2;
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
	}
	
	/**
	 * creats a string from the resource
	 * used for the gamelog
	 * @param res
	 * @return
	 */
	public String resourceToString(ResourceType res){
		if(res == ResourceType.SHEEP){
			return "sheep";
		}
		if(res == ResourceType.WHEAT){
			return "wheat";
		}
		if(res == ResourceType.ORE){
			return "ore";
		}
		if(res == ResourceType.BRICK){
			return "brick";
		}
		if(res == ResourceType.WOOD){
			return "wood";
		}
		
		return "Error";
		
	}

	public ResourceType getResource1() {
		return MirrorResourceType.getOriginal(resource1);
	}

	public ResourceType getResource2() {
		return MirrorResourceType.getOriginal(resource2);
	}

	

}
