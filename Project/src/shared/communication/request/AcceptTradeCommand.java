package shared.communication.request;

public class AcceptTradeCommand extends MoveCommand {

	private boolean willAccept;
	public AcceptTradeCommand(int playerIndex, boolean willAccept) throws IllegalArgumentException {
		super(playerIndex);
		this.willAccept = willAccept;
		this.type = "acceptTrade";
	}

	

	public boolean getWillAccept() {
		return willAccept;
	}
}
