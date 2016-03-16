package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

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
		
	}

	@Override
	public GetModelResponse execute() {
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

 		ResourceList normalizedList = model.normalizeResourceList(offer);
 		if(player.canMakeTrade(normalizedList) == false){
 			response.setSuccess(false);
			response.setErrorMessage("Not enough resources");
			return response;
 		}
 		if(status != "Playing"){
 			response.setSuccess(false);
			response.setErrorMessage("Wrong Status");
			return response;
 		}
 		
 		if(checkTurn(turnTracker,playerIndex) == false){		
			response.setSuccess(false);
			response.setErrorMessage("Wrong turn");
			return response; 		
		}
 		
 		TradeOffer trade = new TradeOffer(playerIndex,getReceiver(),offer);
 		response.setSuccess(true);
		return response; 
	}

}
