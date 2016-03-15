package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.locations.HexLocation;
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
		type = "rollNumber";
	}
	
	public RollNumberCommand(HttpExchange exchange) {
		super(exchange);
		
	}
	
	/**
	 * The function which executes rolling a number
	 */
	@Override
	public GetModelResponse execute() {
		int gameIndex = this.gameIDCookie;
		int playerIndex = this.getPlayerIndex();	
		int numRolled = this.getNumber();
		Game game = Game.instance();		
 		ServerGameModel model = game.getGameId(gameIndex);			
 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
 		GetModelResponse response = new GetModelResponse();

 		
 		if(numRolled == 7){
 			turnTracker.setStatus("Discarding");
 		}
 		else{
 			turnTracker.setStatus("Playing");
 		}
 		model.issueResourcesNormalPlay(numRolled);
 		response.setSuccess(true);
		return response;
	}

	public int getNumber() {
		return number;
	}

	
}
