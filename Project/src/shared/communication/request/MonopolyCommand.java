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
		MonopolyCommand tmp = (MonopolyCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.playerIndex = tmp.playerIndex;
		this.resource = tmp.resource;
		this.type = tmp.type; 
		
	}
	/**
	 * this executes the Monopoly command. returns GetModelResponse
	 */
	@Override
	public GetModelResponse execute() {
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
		
		response.setSuccess(true);
		return response;
	}
	
	public ResourceType getResource() {
		return MirrorResourceType.getOriginal(resource);
	}
}
