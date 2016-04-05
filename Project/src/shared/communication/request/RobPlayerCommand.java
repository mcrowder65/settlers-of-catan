package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;
import com.sun.scenario.effect.Blend.Mode;

import client.utils.Translator;
import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.MessageLine;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
/**
 * This is the rob player command. Extends MoveCommand
 * @author mcrowder65
 *
 */
public class RobPlayerCommand extends MoveCommand {

	HexLocation location;
	int victimIndex;
	private ResourceType resourceGenerated;
	public RobPlayerCommand(int playerIndex, HexLocation location, int victimIndex) throws IllegalArgumentException {
		super(playerIndex);
		if (victimIndex < -1 || victimIndex > 3) 
			throw new IllegalArgumentException("victimIndex must be between -1 -3");
		if(location == null) throw new IllegalArgumentException("Location can't be NULL");
		
		this.location = location;
		this.victimIndex = victimIndex;
		this.type = "robPlayer";
	}

	public RobPlayerCommand(HttpExchange exchange) {
		super(exchange);
		RobPlayerCommand tmp = (RobPlayerCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.location = tmp.location;
		this.victimIndex = tmp.victimIndex;
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
		
	}
	/**
	 * This executes the rob player.
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();	
			int victimIndex = this.getVictimIndex();
			HexLocation location = this.getLocation();
			Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		GetModelResponse response = new GetModelResponse();
	 		String status = turnTracker.getStatus();
	 		
	 		//making sure its the players turn		
			if(checkTurn(turnTracker,playerIndex) == false){		
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response; 	
			}	
			
			//check status
			if(!status.equals("Robbing")){
				response.setSuccess(false);
				response.setErrorMessage("Wrong status");
				return response; 
			}
			
			turnTracker.setStatus("Playing");
			
			map.setRobber(location); //placing the robber
			model.setVersion(model.getVersion() + 1); //updating the version
			
			//not robbing anyone
			if (victimIndex == -1) {
				addGameLog(player, model, null);
				response.setSuccess(true);
				response.setJson(model.toString());
				
				return response;
			}

			ServerPlayer victim = model.getServerPlayers()[victimIndex]; //getting the victim

			//victim has no resources
			if(victim.getResources().isEmpty()){
				addGameLog(player, model, null);
				
				response.setSuccess(true);
				response.setJson(model.toString());
				
				return response; 
			}
			
			ResourceType resource = model.generateRandomResource(); //getting the random resource
			
			//keep generating resources if the player doesn't have that resource
			while(victim.getResources().hasResource(resource) == false){
				resource = model.generateRandomResource();
			}
			this.resourceGenerated = resource;
			victim.removeResource(resource);//remove resource from the victim
			player.addResource(resource);
			model.setVersion(model.getVersion() + 1);
			addGameLog(player,model,victim); //updating game log
			response.setSuccess(true);
			response.setJson(model.toString());
			
			return response; 
		}
	}
	
	public GetModelResponse reExecute() {
		int gameIndex = this.gameIDCookie;
		int playerIndex = this.getPlayerIndex();	
		int victimIndex = this.getVictimIndex();
		HexLocation location = this.getLocation();
		Game game = Game.instance();		
 		ServerGameModel model = game.getGameId(gameIndex);		
 		ServerGameMap map = model.getServerMap();		
 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
 		ServerPlayer player = model.getServerPlayers()[playerIndex];
 		GetModelResponse response = new GetModelResponse();
 		String status = turnTracker.getStatus();
 		
 		turnTracker.setStatus("Playing");
		
		map.setRobber(location); //placing the robber
		model.setVersion(model.getVersion() + 1); //updating the version
		
		//not robbing anyone
		if (victimIndex == -1) {
			addGameLog(player, model, null);
			response.setSuccess(true);
			response.setJson(model.toString());
			return response;
		}

		ServerPlayer victim = model.getServerPlayers()[victimIndex]; //getting the victim

		//victim has no resources
		if(victim.getResources().isEmpty()){
			addGameLog(player, model, null);
			
			response.setSuccess(true);
			response.setJson(model.toString());
			return response; 
		}
		

		victim.removeResource(resourceGenerated);//remove resource from the victim
		player.addResource(resourceGenerated);
		model.setVersion(model.getVersion() + 1);
		addGameLog(player,model,victim); //updating game log
		response.setSuccess(true);
		response.setJson(model.toString());
		return response; 
	}
	
	/**
	 * updating the gamelog
	 * @param player
	 * @param model
	 * @param player2
	 */
	public void addGameLog(ServerPlayer player, ServerGameModel model, ServerPlayer player2){
		String message;
		if (player2 == null)
			message = player.getName() + " moved the robber but couldn't rob anyone!";
		else
		    message = player.getName() + " moved the robber and robbed "+player2.getName();
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
	}
	
	public HexLocation getLocation() {
		return location;
	}

	public int getVictimIndex() {
		return victimIndex;
	}

	
}
