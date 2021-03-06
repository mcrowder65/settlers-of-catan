package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.MessageLine;
import shared.locations.EdgeLocation;
/**
 * This executes the monument action. Extends MoveCommand
 * @author mcrowder65
 */
public class MonumentCommand extends MoveCommand {
	public MonumentCommand(int playerIndex) throws IllegalArgumentException {
		super(playerIndex);
		this.type = "Monument";
	}
	
	public MonumentCommand(HttpExchange exchange) {
		super(exchange);
		MonumentCommand tmp = (MonumentCommand)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.playerIndex = tmp.playerIndex;
		this.type = tmp.type; 
		
	}
	public MonumentCommand(String json) {
		MonumentCommand tmp = (MonumentCommand)Translator.jsonToObject(json, this.getClass());
		this.playerIndex = tmp.playerIndex;
		this.type = tmp.type; 
		
	}
	//For testing
	public MonumentCommand(int playerIndex, int gameIndex) {
		super(playerIndex);
		this.gameIDCookie = gameIndex;
	}
	/**
	 * This executes the monument command. returns GetModelResponse
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();		
	 		Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		GetModelResponse response = new GetModelResponse();
	 		//adds response headers
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
	 				
			//making sure its the players turn		
			if(checkTurn(turnTracker,playerIndex) == false){		
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response; //Need to throw some error here		
			}		
					
			String status = turnTracker.getStatus();
			
			//checking to see if the playr can play the monument card
			if(!player.canPlayMonumentCard()){
				response.setSuccess(false);
				response.setErrorMessage("Player cannot play monument card");
				return response; 
			}
			
			//making sure its the right status		
			if(status.equals("Playing")){
				player.playMonument(); //playing the monument card
				//checking the winner
				if(player.getVictoryPoints() > 9){
					model.setWinner(playerIndex);
				}
				model.setVersion(model.getVersion() + 1);
				addGameLog(player,model);
				response.setSuccess(true);
				response.setJson(model.toString());
				
				return response;
			}
			response.setSuccess(false);
			response.setErrorMessage("Wrong status");
			return response; //need to return some error here
		}
	}
	
	/**
	 * reexecutes the command after it has been reloaded from the database
	 * @param gameID int
	 * @param playerIndex int
	 * @return GetModelResponse
	 */
	@Override
	public GetModelResponse reExecute(int gameID, int playerIndex) {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
			int gameIndex = gameID;		
	 		Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		GetModelResponse response = new GetModelResponse();
	 		//adds response headers
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
	 				
			//making sure its the players turn		
			if(checkTurn(turnTracker,playerIndex) == false){		
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response; //Need to throw some error here		
			}		
					
			String status = turnTracker.getStatus();
			
			//checking to see if the playr can play the monument card
			if(!player.canPlayMonumentCard()){
				response.setSuccess(false);
				response.setErrorMessage("Player cannot play monument card");
				return response; 
			}
			
			//making sure its the right status		
			if(status.equals("Playing")){
				player.playMonument(); //playing the monument card
				//checking the winner
				if(player.getVictoryPoints() > 9){
					model.setWinner(playerIndex);
				}
				model.setVersion(model.getVersion() + 1);
				addGameLog(player,model);
				response.setSuccess(true);
				response.setJson(model.toString());
				
				return response;
			}
			response.setSuccess(false);
			response.setErrorMessage("Wrong status");
			return response; //need to return some error here
		}
	}
	/**
	 * updating the gameLog
	 * @param player
	 * @param model
	 */
	public void addGameLog(ServerPlayer player, ServerGameModel model){
		String message = player.getName() + " built a monument and gained a victory point";
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
	}


}
