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
import shared.definitions.MirrorResourceType;
import shared.definitions.ResourceType;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;
/**
 * This class is the Monopoly command. It extends MoveCommand
 * @author mcrowder65
 *
 */
public class MonopolyCommand extends MoveCommand {

	MirrorResourceType resource;
	public MonopolyCommand(int playerIndex, ResourceType resource) throws IllegalArgumentException {
		super(playerIndex);

		if(resource == null) throw new IllegalArgumentException("Resource can't be NULL");
		if(resource == ResourceType.NONE) throw new IllegalArgumentException("Resource can't be None");
		this.resource = MirrorResourceType.getResource(resource);
		this.type = "Monopoly";
	}

	public MonopolyCommand(HttpExchange exchange) {
		super(exchange);
		MonopolyCommand tmp = (MonopolyCommand)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.playerIndex = tmp.playerIndex;
		this.resource = tmp.resource;
		this.type = tmp.type; 
		
	}
	public MonopolyCommand(String json) {
		MonopolyCommand tmp = (MonopolyCommand)Translator.jsonToObject(json, this.getClass());
		this.playerIndex = tmp.playerIndex;
		this.resource = tmp.resource;
		this.type = tmp.type; 
		
	}
	/**
	 * this executes the Monopoly command. returns GetModelResponse
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();		
	 		Game game = Game.instance();	
	 		GetModelResponse response = new GetModelResponse();
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		ServerPlayer[] allPlayers = model.getServerPlayers();
	 		String status = turnTracker.getStatus();
	 		ResourceType resource = getResource();
	 		
	 		//setting the headers
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
			
			//check status
			if(!status.equals("Playing")){
				response.setSuccess(false);
				response.setErrorMessage("Wrong status");
				return response;
			}
			
			//checks to make sure the player has a monopoly card and hasnt already played a dev card
			if(!player.canPlayMonopolyCard()){
				response.setSuccess(false);
				response.setErrorMessage("Player cannot play monopoly card");
				return response;
			}
			//excuting the monopoly command
			for(int i=0; i<allPlayers.length; i++){
				if(i != playerIndex){
					ServerPlayer player2 = allPlayers[i];
					if(player2.getResources().hasResource(resource)){
						int num = player2.getResources().numResource(resource);
						player.getResources().addResource(resource,num);
						player2.getResources().removeResource(resource,num);
					}
				}
			}
			player.playMonopolyCard(); //charging the player for the monopoly card
			player.setPlayedDevCard(true);
			model.setVersion(model.getVersion()  + 1);//updating the version
			addGameLog(player,model);
			response.setSuccess(true);
			response.setJson(model.toString());
			
			return response;
		}
	}
	/**
	 * updates the game log
	 * @param player
	 * @param model
	 */
	public void addGameLog(ServerPlayer player, ServerGameModel model){
		String message = player.getName() + " stole everyones " + resource.toString();
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
	}
	
	public ResourceType getResource() {
		return MirrorResourceType.getOriginal(resource);
	}
}
