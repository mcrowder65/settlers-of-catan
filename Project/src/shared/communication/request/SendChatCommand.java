package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
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
	private Object sendChatLock = new Object();
	private String content;
	public SendChatCommand(int playerIndex, String content) throws IllegalArgumentException {
		super(playerIndex);
		this.content = content;
		this.type = "sendChat";
		
	}

	public SendChatCommand(HttpExchange exchange) {
		super(exchange);
		SendChatCommand tmp = (SendChatCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.content = tmp.content;
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
		
	}
	/**
	 * This executes the send chat command.
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(sendChatLock){
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();	
			Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(gameIndex);				
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		GetModelResponse response = new GetModelResponse();
	 		MessageLine line = new MessageLine(getContent(),player.getName());
	 		model.addChatMessage(line);
	 		try {
				response.setCookie("Set-cookie", "catan.user=" +
						URLEncoder.encode("{" +
					       "\"authentication\":\"" + "1142128101" + "\"," +
				           "\"name\":\"" + userCookie + "\"," +
						   "\"password\":\"" + passCookie + "\"," + 
				           "\"playerID\":" + playerIDCookie + "}", "UTF-8" ) + ";catan.game=" + gameIDCookie);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	 		
	 		model.setVersion(model.getVersion() + 1);
	 		response.setSuccess(true);
	 		response.setJson(model.toString());
			return response; 
		}
	}

	
	public String getContent() {
		return content;
	}

}
