package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
/**
 * This sends a chat, extends MoveCommand
 * @author mcrowder65
 *
 */
public class SendChatCommand extends MoveCommand {

	private String content;
	public SendChatCommand(int playerIndex, String content) throws IllegalArgumentException {
		super(playerIndex);
		this.content = content;
		this.type = "sendChat";
		
	}

	public SendChatCommand(HttpExchange exchange) {
		super(exchange);
		
	}
	/**
	 * This executes the send chat command.
	 */
	@Override
	public GetModelResponse execute() {
		return null;
	}

	
	public String getContent() {
		return content;
	}

}
