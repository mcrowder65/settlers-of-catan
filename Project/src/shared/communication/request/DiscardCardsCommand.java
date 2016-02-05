package shared.communication.request;

import shared.definitions.ResourceList;

public class DiscardCardsCommand extends MoveCommand {
	public DiscardCardsCommand(int playerIndex, ResourceList discardedCards) throws IllegalArgumentException {
		super(playerIndex);
		if (discardedCards == null)
			throw new IllegalArgumentException("discardedCards can't be null.");
		this.discardedCards = discardedCards;
		this.type = "discardCards";
	}


	ResourceList discardedCards;
	
	
	public ResourceList getDiscardedCards() {
		return discardedCards;
	}



}
