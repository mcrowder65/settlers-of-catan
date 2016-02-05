package shared.communication.request;

public class FinishTurnCommand extends MoveCommand {

	public FinishTurnCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
		this.type = "finishTurn";
	}


}
