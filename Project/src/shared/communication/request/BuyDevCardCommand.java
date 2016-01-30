package shared.communication.request;

public class BuyDevCardCommand extends MoveCommand {

	public BuyDevCardCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
	
	}

	@Override
	public String getMoveType() {
		return "buyDevCard";
	}

}
