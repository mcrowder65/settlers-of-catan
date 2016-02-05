package shared.communication.request;

public class BuyDevCardCommand extends MoveCommand {

	public BuyDevCardCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
		this.type = "buyDevCard";
	}

	
}
