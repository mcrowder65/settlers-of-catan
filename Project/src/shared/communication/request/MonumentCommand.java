package shared.communication.request;

public class MonumentCommand extends MoveCommand {

	public MonumentCommand(int playerIndex) throws IllegalArgumentException {
		super(playerIndex);
		this.type = "Monument";
	}


}
