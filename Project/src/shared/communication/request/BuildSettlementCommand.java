package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
import shared.communication.response.GetModelResponse;
import shared.definitions.Hex;
import shared.locations.EdgeLocation;
import shared.locations.MirrorVertexLocation;
import shared.locations.VertexLocation;
import shared.locations.VertexObject;
/**
 * This class executes the build settlement command,
 * extends MoveCommand
 * 
 * @author Manuel
 *
 */

public class BuildSettlementCommand extends MoveCommand {

	
	
	boolean free;
	MirrorVertexLocation vertexLocation;
	
	
	public BuildSettlementCommand(int playerIndex, boolean free, VertexLocation vertexLocation)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(vertexLocation == null) throw new IllegalArgumentException("Location can't be NULL");
		
		
		this.free = free;
		this.vertexLocation = new MirrorVertexLocation(vertexLocation);
		
		this.type = "buildSettlement";
	}
	public BuildSettlementCommand(HttpExchange exchange) {
		super(exchange);
		BuildSettlementCommand tmp = (BuildSettlementCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.free = tmp.free;
		this.vertexLocation = tmp.vertexLocation;
		this.type = tmp.type;
		this.playerIndex = tmp.playerIndex;
	}

	/**
	 * Executes the logic for the build settlement command
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			int gameIndex = this.gameIDCookie;
			int playerIndex = this.getPlayerIndex();	
			VertexLocation loc = this.getLocation().getOriginal();			
	 		Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(gameIndex);		
	 		ServerGameMap map = model.getServerMap();
	 		GetModelResponse response = new GetModelResponse();
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];
	 		String status = turnTracker.getStatus();
	 		VertexObject vertex = new VertexObject(playerIndex,loc);
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
	 		//making sure its the players turn		
			if(checkTurn(turnTracker,playerIndex) == false){		
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response;		
			}
			if(status.equals("FirstRound") || status.equals("SecondRound")){
				if(!map.canBuildSettlementFirstRound(vertex)){
					//need to return some error here
					response.setSuccess(false);
					response.setErrorMessage("bad location");
					return response;
				}
			}
			if(status.equals("SecondRound")){
				map.laySettlement(vertex,true);
				List<VertexObject>settlements = map.getSecondRoundSettlements(playerIndex);
				for(int i=0; i<settlements.size(); i++){
					if(settlements.get(i)!=null){
						issueResource(settlements.get(i),map,player);
					}
				}
				
				player.removeSettlement();
				player.addVictoryPoints();
				response.setSuccess(true);
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				return response;
			}
			if(status.equals("FirstRound")){
				map.laySettlement(vertex,false);
				player.removeSettlement();
				player.addVictoryPoints();
				
				//need to return that it was successful 
				response.setSuccess(true);
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				return response;
			}
			if(status.equals("Playing")){
				if(!player.canBuildSettlement() || !map.canBuildSettlement(vertex)){
					response.setSuccess(false);
					response.setErrorMessage("Bad Location");
					return response;
				}
				map.laySettlement(vertex,false);
				player.laySettlement();
				response.setSuccess(true);
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				return response;
			}
			
			//need to return some error
			response.setSuccess(false);
			response.setErrorMessage("Wrong status");
			return response;
		}
	}
	
	public void issueResource(VertexObject settlement, ServerGameMap map, ServerPlayer player){
		Hex[] allHexes = map.getHexes();
		for(int i=0; i<allHexes.length; i++){
			Hex hex = allHexes[i];
			if(hex.getLocation().equals(settlement.getLocation().getHexLoc())){
				player.addResource(hex.getResource());
			}
		}
	}
	

	public boolean isFree() {
		return free;
	}
	public MirrorVertexLocation getLocation() {
		return vertexLocation;
	}
}
