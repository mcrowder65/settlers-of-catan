package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.MessageLine;
import shared.locations.HexLocation;
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
		int gameIndex = this.gameIDCookie;
		int playerIndex = this.getPlayerIndex();	
		Game game = Game.instance();		
 		ServerGameModel model = game.getGameId(gameIndex);				
 		ServerPlayer player = model.getServerPlayers()[playerIndex];
 		GetModelResponse response = new GetModelResponse();
 		MessageLine line = new MessageLine(getContent(),player.getName());
 		model.addChatMessage(line);
 			
 		response.setSuccess(true);
		return response; 
	}

	
	public String getContent() {
		return content;
	}

}
