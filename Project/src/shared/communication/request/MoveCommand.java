package shared.communication.request;


public abstract class MoveCommand extends Request {

	private int playerIndex;
	protected MoveCommand(int playerIndex) throws IllegalArgumentException {
		
		if (playerIndex < 0 || playerIndex > 3)
			throw new IllegalArgumentException("playerIndex must be between 0 and 3");
		this.playerIndex = playerIndex;
	}
	
	public abstract String getMoveType();
	
	
}
