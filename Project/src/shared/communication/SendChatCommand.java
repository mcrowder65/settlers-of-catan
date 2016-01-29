package shared.communication;

public class SendChatCommand extends MoveCommand {

	private String content;
	protected SendChatCommand(int playerIndex, String content) throws IllegalArgumentException {
		super(playerIndex);
		this.content = content;
	}

	@Override
	public String getMoveType() {
		return "sendChat";
	}
	
	public String getContent() {
		return content;
	}

}
