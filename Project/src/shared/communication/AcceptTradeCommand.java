package shared.communication;

public class AcceptTradeCommand extends MoveCommand {

	private boolean willAccept;
	protected AcceptTradeCommand(int playerIndex, boolean willAccept) throws IllegalArgumentException {
		super(playerIndex);
		this.willAccept = willAccept;
	}

	@Override
	public String getMoveType() {
		return "acceptTrade";
	}

	public boolean getWillAccept() {
		return willAccept;
	}
}
