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
import shared.locations.EdgeLocation;
import shared.locations.MirrorVertexLocation;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;

/**
 * This class executes the build city command,
 * extends MoveCommand
 * @author Manuel
 *
 */

public class BuildCityCommand extends MoveCommand {
	
	public BuildCityCommand(int playerIndex, VertexLocation vertexLocation) throws IllegalArgumentException {
		super(playerIndex);
		if (vertexLocation == null)
			throw new IllegalArgumentException("vertexLocation cannot be null.");

		this.vertexLocation = new MirrorVertexLocation(vertexLocation);
		this.type = "buildCity";
	}
	
	/**
	 * creating the build city command from the httpexchange
	 * @param exchange
	 */
	public BuildCityCommand(HttpExchange exchange) {
		super(exchange);
		BuildCityCommand tmp = (BuildCityCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
		this.vertexLocation = tmp.vertexLocation;
		
	}

	/**
	 * Executes the logic to process the build city command
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			
			//getting all the info needed to execute the command from the cookies and http exchange
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();	
			VertexLocation loc = this.getLocation().getOriginal();		
	 		Game game = Game.instance();	
	 		GetModelResponse response = new GetModelResponse();
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		String status = turnTracker.getStatus();
	 		VertexObject vertex = new VertexObject(playerIndex,loc);
	 		
	 		//setting the cookie headers
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
	 		
	 		//making sure its the players turn		
			if(checkTurn(turnTracker,playerIndex) == false){		
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response; //Need to throw some error here		
			}
			
			//making sure that the status is correct
			if(status.equals("Playing")){
				if(!player.canBuildCity()){ //checks to see if a player can build the city
					//return that there was an error
					response.setSuccess(false);
					response.setErrorMessage("Wrong status");
					return response;
				}
				if(!map.canBuildCity(vertex)){ //checks to see if the city can be placed on the map
					response.setSuccess(false);
					response.setErrorMessage("Bad Location");
					return response;
				}
				
				map.layCity(vertex); //lays the city on the map
				player.layCity(); //updates the player
				
				//sets the winner if needed
				if(player.getVictoryPoints() > 9){
					model.setWinner(playerIndex);
				}
				
				//return that there was a success
				addGameLog(player,model);
				model.setVersion(model.getVersion() + 1);//updating the version
				//creating the response
				response.setJson(model.toString());
				response.setSuccess(true);
				
				this.modelResponse = response;
				return response;
			}
			
			//command was unsucesful
			response.setSuccess(false);
			response.setErrorMessage("Unreachable");
			return response;
		}
	}
	
	/**
	 * adds the message to the gamelog
	 * @param player
	 * @param model
	 */
	public void addGameLog(ServerPlayer player, ServerGameModel model){
		String message = player.getName() + " built a city";
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
	}

	/**
	 * gets the mirror vertex loc
	 * @return
	 */
	public MirrorVertexLocation getLocation() {
		return vertexLocation;
	}
	private MirrorVertexLocation vertexLocation;

}
