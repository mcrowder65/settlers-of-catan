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
import shared.definitions.MessageLine;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;

/**
 * This class executes the Buy Dev Card Command,
 * extends MoveCommand
 * @author Manuel
 *
 */

public class BuyDevCardCommand extends MoveCommand {

	private DevCardType purchasedCard;
	
	public BuyDevCardCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
		this.type = "buyDevCard";
	}

	public BuyDevCardCommand(HttpExchange exchange) {
		super(exchange);
		BuyDevCardCommand tmp = (BuyDevCardCommand)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
		
	}
	public BuyDevCardCommand(String json) {
		BuyDevCardCommand tmp = (BuyDevCardCommand)Translator.jsonToObject(json, this.getClass());
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
		
	}
	//For testing
	public BuyDevCardCommand(int gameIndex, int playerIndex){
		super(playerIndex);
		this.gameIDCookie = gameIndex;		
	}

	/**
	 * Executes the logic for the buy dev card command
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
	 		
	 		GetModelResponse response = new GetModelResponse(); //creating a response
	 		
	 		//setting response headers
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
				//checking to see if the deck is empty
				if(model.isDeckEmpty() == true){
					response.setSuccess(false);
					response.setErrorMessage("Empty Deck");
					return response;	
				}
				DevCardType card = model.generateRandomDevCard(); //generates a random card
				while(card == DevCardType.NONE){
					card = model.generateRandomDevCard();
				}
				this.purchasedCard = card;
				//buys the dev card for player and model
				player.buyDevCard(card);
				model.buyFromDeck(card);
				model.setVersion(model.getVersion() + 1);
				addGameLog(player,model);
				response.setSuccess(true);
				response.setJson(model.toString());
				
				return response;	
				
			}
			response.setSuccess(false);
			response.setErrorMessage("Wrong status");
			return response;	
		}
	}
	
	/**
	 * updates the gamelog
	 * @param player
	 * @param model
	 */
	public void addGameLog(ServerPlayer player, ServerGameModel model){
		String message = player.getName() + " bought a development card.";
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
	}
	
	public GetModelResponse reExecute() {
		int gameIndex = this.gameIDCookie;
		int playerIndex = this.getPlayerIndex();			
 		Game game = Game.instance();		
 		ServerGameModel model = game.getGameId(gameIndex);			
 		ServerPlayer player = model.getServerPlayers()[playerIndex];
 		
 		GetModelResponse response = new GetModelResponse(); //creating a response
		
		player.buyDevCard(purchasedCard);
		model.buyFromDeck(purchasedCard);
		model.setVersion(model.getVersion() + 1);
		addGameLog(player,model);
		response.setSuccess(true);
		response.setJson(model.toString());
		
		return response;
	}
}
