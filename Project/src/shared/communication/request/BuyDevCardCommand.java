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
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;

/**
 * This class executes the Buy Dev Card Command,
 * extends MoveCommand
 * @author Manuel
 *
 */

public class BuyDevCardCommand extends MoveCommand {

	public BuyDevCardCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
		this.type = "buyDevCard";
	}

	public BuyDevCardCommand(HttpExchange exchange) {
		super(exchange);
		BuyDevCardCommand tmp = (BuyDevCardCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
		
	}

	/**
	 * Executes the logic for the buy dev card command
	 */
	@Override
	public GetModelResponse execute() {
		
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
			return response;	//Need to throw some error here		
		}		
				
		String status = turnTracker.getStatus();		
		//making sure its the right status				
		if(status.equals("Playing")){		
			if(!player.canBuyDevCard()){
				response.setSuccess(false);
				response.setErrorMessage("Not enough resources to purchase");
				return response;	
			}
			if(model.isDeckEmpty() == true){
				response.setSuccess(false);
				response.setErrorMessage("Empty Deck");
				return response;	
			}
			DevCardType card = model.generateRandomDevCard();
			while(card == DevCardType.NONE){
				card = model.generateRandomDevCard();
			}
			player.buyDevCard(card);
			model.buyFromDeck(card);
			response.setSuccess(true);
			return response;	
			
		}
		response.setSuccess(false);
		response.setErrorMessage("Wrong status");
		return response;	
	}
}
