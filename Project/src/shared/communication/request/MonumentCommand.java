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
		MonumentCommand tmp = (MonumentCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.playerIndex = tmp.playerIndex;
		this.type = tmp.type; 
		
	}
	/**
	 * This executes the monument command. returns GetModelResponse
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();	
	 					
	 		Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		GetModelResponse response = new GetModelResponse();
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
			//making sure its the right status		
			if(status.equals("Playing")){
				if(!player.canPlayMonumentCard()){
					response.setSuccess(false);
					response.setErrorMessage("Player cannot play monument");
					return response;
				}
				player.playMonument();
				model.setVersion(model.getVersion() + 1);
				response.setSuccess(true);
				response.setJson(model.toString());
				return response;
			}
			response.setSuccess(false);
			response.setErrorMessage("Wrong status");
			return response; //need to return some error here
		}
	}


}
