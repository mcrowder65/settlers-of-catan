package shared.communication.request;

public class MonumentCommand extends MoveCommand {

	public MonumentCommand(int playerIndex) throws IllegalArgumentException {
		super(playerIndex);
		
	}

	@Override
	public String getMoveType() {
		return "Monument";
	}

}
