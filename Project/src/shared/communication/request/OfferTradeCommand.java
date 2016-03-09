package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
/**
 * This 
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
		return null;
	}

}
