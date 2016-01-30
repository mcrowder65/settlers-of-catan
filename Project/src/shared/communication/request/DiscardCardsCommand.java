package shared.communication.request;

import shared.definitions.ResourceList;

public class DiscardCardsCommand extends MoveCommand {
	public DiscardCardsCommand(int playerIndex, ResourceList discardedCards) throws IllegalArgumentException {
		super(playerIndex);
		this.discardedCards = discardedCards;
	}


	ResourceList discardedCards;
	
	
	public ResourceList getDiscardedCards() {
		return discardedCards;
	}


	@Override
	public String getMoveType() {
		return "discardCards";
	}

}
