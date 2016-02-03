package shared.communication.request;

public class SendChatCommand extends MoveCommand {

	private String content;
	private String type;
	public SendChatCommand(int playerIndex, String content) throws IllegalArgumentException {
		super(playerIndex);
		this.content = content;
		this.type = "sendChat";
		//TODO ask eric if it's okay to have a type in sendchatcommand
	}

	@Override
	public String getMoveType() {
		return "sendChat";
	}
	
	public String getContent() {
		return content;
	}

}
