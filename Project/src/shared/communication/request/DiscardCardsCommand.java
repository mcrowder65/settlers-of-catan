package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
import shared.definitions.ResourceList;
/**
 * This class executes the Discard Cards command,
 * extends MoveCommand
 * @author Manuel
 *
 */

public class DiscardCardsCommand extends MoveCommand {
	public DiscardCardsCommand(int playerIndex, ResourceList discardedCards) throws IllegalArgumentException {
		super(playerIndex);
		if (discardedCards == null)
			throw new IllegalArgumentException("discardedCards can't be null.");
		this.discardedCards = discardedCards;
		this.type = "discardCards";
	}
	public DiscardCardsCommand(HttpExchange exchange) {
		super(exchange);
		
	}

	/**
	 * Executes the logic for the discard cards command
	 */
	@Override
	public GetModelResponse execute() {
		return null;
	}

	ResourceList discardedCards;
	
	
	public ResourceList getDiscardedCards() {
		return discardedCards;
	}



}
