package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.*;
import shared.communication.response.GetModelResponse;
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
		this.type = "Year_of_Plenty";
	}
	
	public YearOfPlentyCommand(HttpExchange exchange) {
		super(exchange);
		YearOfPlentyCommand tmp = (YearOfPlentyCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.resource1 = tmp.resource1;
		this.resource2 = tmp.resource2;
		this.playerIndex = tmp.playerIndex;
		
	}
	/**
	 * Function that executes the year of plenty.
	 */
	@Override
	public GetModelResponse execute() {
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
 		
 		if(checkTurn(turnTracker,playerIndex) == false){		
			response.setSuccess(false);
			response.setErrorMessage("Wrong turn");
			return response; 		
		}
 		
 		if(!status.equals("Playing")){
 			response.setSuccess(false);
			response.setErrorMessage("Wrong status");
			return response;
 		}
 		
 		if(model.getBank().hasResource(res1) == false){
 			response.setSuccess(false);
			response.setErrorMessage("Bank doesn't have resources to fill order");
			return response;
 		}
 		
 		if(model.getBank().hasResource(res2) == false){
 			response.setSuccess(false);
			response.setErrorMessage("Bank doesn't have resources to fill order");
			return response;
 		}
 		
 		if(player.getOldDevCards().getYearOfPlenty() < 1){
 			response.setSuccess(false);
			response.setErrorMessage("Player doesn't have YOP card");
			return response;
 		}
 		
 		player.playYearOfPlentyCard();
 		player.getResources().addResource(res1,1);
 		player.getResources().addResource(res2,1);
 		model.getBank().removeResource(res1,1);
 		model.getBank().removeResource(res2,1);
 		
		response.setSuccess(true);
		return response;
	}

	public ResourceType getResource1() {
		return MirrorResourceType.getOriginal(resource1);
	}

	public ResourceType getResource2() {
		return MirrorResourceType.getOriginal(resource2);
	}

	

}
