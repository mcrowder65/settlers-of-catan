package shared.communication.request;

public class FinishTurnCommand extends MoveCommand {

	public FinishTurnCommand(int playerIndex)
			throws IllegalArgumentException {
		super(playerIndex);
	}

	@Override
	public String getMoveType() {
		return "finishTurn";
	}

}
