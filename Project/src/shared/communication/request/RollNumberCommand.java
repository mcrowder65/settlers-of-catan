package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.ServerGameModel;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
/**
 * This is the class the executes a roll number.
 * @author mcrowder65
 *
 */
public class RollNumberCommand extends MoveCommand {
	private int number;
	public RollNumberCommand(int playerIndex, int number) throws IllegalArgumentException {
		super(playerIndex);
		if (number < 2 || number > 12) 
			throw new IllegalArgumentException("number must be between 2 - 12");
		this.number = number;
		type = "rollDice";
	}
	
	public RollNumberCommand(HttpExchange exchange) {
		super(exchange);
		RollNumberCommand tmp = (RollNumberCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.number = tmp.number;
		this.type = tmp.type;
		
	}
	/**
	 * The function which executes rolling a number
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			int gameIndex = this.gameIDCookie;	
	 		int numRolled = this.getNumber();
	 		Game game = Game.instance();		
	  		ServerGameModel model = game.getGameId(gameIndex);			
	  		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	  		GetModelResponse response = new GetModelResponse();
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
	  		if(numRolled == 7){
	  			turnTracker.setStatus("Discarding");
	  		}
	  		else{
	  			turnTracker.setStatus("Playing");
	  		}
	  		model.issueResourcesNormalPlay(numRolled);
	  		model.setVersion(model.getVersion() + 1);
	        response.setJson(model.toString());
	  		response.setSuccess(true);
	 		return response;
		}
	}

	public int getNumber() {
		return number;
	}

	
}
