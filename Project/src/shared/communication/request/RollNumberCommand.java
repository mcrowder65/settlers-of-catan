package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.MessageLine;
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
	  		ServerPlayer player = model.getServerPlayers()[playerIndex];
	  		/*
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
			*/
	  		
	  		
	  		
	  		if(numRolled == 7){
	  			turnTracker.setStatus("Discarding");
	  			for (int n = 0; n < 4; n++) {
		  			model.getServerPlayers()[n].setDiscarded(
		  		    model.getServerPlayers()[n].getNumOfCards() > 6 ? false : true
		  					);
		  		}
	  			
	  		//Need this for ais to discard
		  		turnTracker.handleAITurn(gameIDCookie);
		  		boolean doneDiscarding = false;
		  		for (int n = 0; n < 4; n++) {
		  			if (model.getServerPlayers()[n].getDiscarded())  {
		  				doneDiscarding = true;
		  				break;
		  			}
		  		}
		  		if (doneDiscarding) {
		  			turnTracker.setStatus("Playing");
		  		}
		  		
		  		
		  		
	  		}
	  		else{
	  			turnTracker.setStatus("Playing");
	  		}
	  		
	  		
	  		
	  		addGameLog(player,model,numRolled);
	  		model.issueResourcesNormalPlay(numRolled);
	  		model.setVersion(model.getVersion() + 1);
	        response.setJson(model.toString());
	  		response.setSuccess(true);
	 		return response;
		}
	}
	
	public void addGameLog(ServerPlayer player, ServerGameModel model, int numRolled){
		String message = player.getName() + " rolled a " + numRolled +".";
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
	}

	public int getNumber() {
		return number;
	}

	
}
