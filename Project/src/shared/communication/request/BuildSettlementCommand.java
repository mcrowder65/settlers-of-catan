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
import shared.definitions.MessageLine;
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
	 * used specifically for junit testing
	 * @param playerIndex
	 * @param free
	 * @param vertexLocation
	 * @param gameIndex
	 */
	public BuildSettlementCommand(int playerIndex, boolean free, VertexLocation vertexLocation, int gameIndex){
		super(playerIndex, gameIndex);
		this.free = free;
		this.vertexLocation = new MirrorVertexLocation(vertexLocation);
		this.type = "buildSettlement";
	}
	/**
	 * Executes the logic for the build settlement command
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			int gameIndex = this.gameIDCookie; //getting all the info needed to execute the command from the cookies and http exchange
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

	 		//making sure its the players turn		
			if(checkTurn(turnTracker,playerIndex) == false){		
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response;		
			}
			if(status.equals("FirstRound") || status.equals("SecondRound")){
				if(!map.canBuildSettlementFirstRound(vertex)){ //making sure settlement can be built during the first two rounds
					//need to return some error here
					response.setSuccess(false);
					response.setErrorMessage("bad location");
					return response;
				}
			}
			//lays settlement during the second round 
			if(status.equals("SecondRound")){
				map.laySettlement(vertex,true);
				List<VertexObject>settlements = map.getSecondRoundSettlements(playerIndex);
				for(int i=0; i<settlements.size(); i++){
					if(settlements.get(i)!=null){
						issueResource(settlements.get(i),map,player);
					}
				}
				
				player.removeSettlement(); //removes settlement from player count
				player.addVictoryPoints(); //adds victory points
				
				//setting winnder
				if(player.getVictoryPoints() > 9){
					model.setWinner(playerIndex);
				}
				addGameLog(player,model); //adds message to gamelog
				response.setSuccess(true);
				model.setVersion(model.getVersion() + 1); //updates version
	            response.setJson(model.toString());
				return response;
			}
			//lays a road if status is first round
			if(status.equals("FirstRound")){
				map.laySettlement(vertex,false);
				player.removeSettlement();
				player.addVictoryPoints(); //adds victory points
				
				//sets the winner
				if(player.getVictoryPoints() > 9){
					model.setWinner(playerIndex);
				}
				
				//need to return that it was successful 
				addGameLog(player,model);
				response.setSuccess(true);
				model.setVersion(model.getVersion() + 1);
	            response.setJson(model.toString());
				return response;
			}
			//lays a settlement if the for normal play
			if(status.equals("Playing")){
				map.laySettlement(vertex,false);
				player.laySettlement();
				//sets the winner
				if(player.getVictoryPoints() > 9){
					model.setWinner(playerIndex);
				}
				addGameLog(player,model);
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
	
	/**
	 * updates the game log
	 * @param player
	 * @param model
	 */
	public void addGameLog(ServerPlayer player, ServerGameModel model){
		String message = player.getName() + " built a settlement";
		MessageLine line = new MessageLine(message,player.getName());
		model.addGameLogMessage(line);
	}
	
	/**
	 * issues resources for the second round
	 * @param settlement
	 * @param map
	 * @param player
	 */
	public void issueResource(VertexObject settlement, ServerGameMap map, ServerPlayer player){
		Hex[] allHexes = map.getHexes();
		//goes hexes and finds where the player laid the road
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
