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
import shared.definitions.ResourceList;
import shared.definitions.TradeOffer;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;
/**
 * This offers a trade, extends the MoveCommand class
 * @author mcrowder65
 *
 */
public class OfferTradeCommand extends MoveCommand {
	private int receiver;
	private ResourceList offer;
	 
	public int getReceiver() {
		return receiver;
	}

	public ResourceList getOffer() {
		return offer;
	}

	public OfferTradeCommand(int playerIndex, int receiver, ResourceList offer) throws IllegalArgumentException {
		super(playerIndex);
		if (receiver < 0 || receiver > 3) 
			throw new IllegalArgumentException("receiver must be between 0 -3");
		if (offer == null)
			throw new IllegalArgumentException("offer cannot be null.");
		this.receiver = receiver;
		this.offer = offer;
		this.type = "offerTrade";
	}
	
	public OfferTradeCommand(HttpExchange exchange) {
		super(exchange);
		OfferTradeCommand tmp = (OfferTradeCommand)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.playerIndex = tmp.playerIndex;
		this.type = tmp.type;
		this.offer = tmp.offer;
		this.receiver = tmp.receiver;
		
	}

	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();	
			ResourceList offer = this.getOffer();	
	 		Game game = Game.instance();	
	 		GetModelResponse response = new GetModelResponse();
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		String status = turnTracker.getStatus();
	 		
	 		//if pos player is giving if neg player is recieving
	 		ResourceList normalizedList = model.getRecievingResourceList(offer);
	 		if(player.canMakeTrade(normalizedList) == false){
	 			response.setSuccess(false);
				response.setErrorMessage("Not enough resources");
				return response;
	 		}
	 		//checking the status
	 		if(status != "Playing"){
	 			response.setSuccess(false);
				response.setErrorMessage("Wrong Status");
				return response;
	 		}
	 		//checking the turn
	 		if(checkTurn(turnTracker,playerIndex) == false){		
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response; 		
			}
	 		//creating the trade offer
	 		TradeOffer trade = new TradeOffer(playerIndex,getReceiver(),offer);
	 		model.setTradeOffer(trade);
	 		
	 		//AIs can't make trades
	 		if (Game.instance().getGameId(gameIDCookie).getServerPlayers()[receiver].getPlayerID() < 0) {
	 			AcceptTradeCommand rejectTrade = new AcceptTradeCommand(receiver, false);
	 			rejectTrade.setGameCookie(gameIDCookie);
	 			rejectTrade.execute();
	 		} 
	 		
	 		model.setVersion(model.getVersion() + 1);//setting the version
	 		response.setSuccess(true);
	 		response.setJson(model.toString());
	 		
	 		return response; 
		}
	}

}
