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
import shared.definitions.MirrorResourceType;
import shared.definitions.Port;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
/**
 * Maritime trade command, extends MoveCommand
 * @author mcrowder65
 */
public class MaritimeTradeCommand extends MoveCommand {

	int ratio;
	MirrorResourceType inputResource;
	MirrorResourceType outputResource;
	/**
	 * Constructor
	 * @param playerIndex index of player
	 * @param ratio ratio of resource
	 * @param input going in
	 * @param output going out
	 * @throws IllegalArgumentException
	 */
	public MaritimeTradeCommand(int playerIndex, int ratio, ResourceType input, ResourceType output)
			throws IllegalArgumentException {
		super(playerIndex);
		
		if(ratio < 2 || ratio > 4) throw new IllegalArgumentException("Ratio can't be a number other than 2,3, or 4");
		if(input == null) throw new IllegalArgumentException("Input Resource can't be NULL");
		if(output == null) throw new IllegalArgumentException("Output Resurce can't be NULL");
		if(input == ResourceType.NONE) throw new IllegalArgumentException("Input Resource can't be None");
		if(output == ResourceType.NONE) throw new IllegalArgumentException("Output Resurce can't be None");
		
		this.ratio = ratio;
		this.inputResource = MirrorResourceType.getResource(input);
		this.outputResource = MirrorResourceType.getResource(output);
		this.type = "maritimeTrade";
	}
	
	public MaritimeTradeCommand(HttpExchange exchange) {
		super(exchange);
		MaritimeTradeCommand tmp = (MaritimeTradeCommand)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.ratio = tmp.ratio;
		this.playerIndex = tmp.playerIndex;
		this.inputResource = tmp.inputResource;
		this.outputResource = tmp.outputResource; 
		
	}
	public MaritimeTradeCommand(String json) {
		MaritimeTradeCommand tmp = (MaritimeTradeCommand)Translator.jsonToObject(json, this.getClass());
		this.ratio = tmp.ratio;
		this.playerIndex = tmp.playerIndex;
		this.inputResource = tmp.inputResource;
		this.outputResource = tmp.outputResource; 
		
	}
	
	//For Testing
	public MaritimeTradeCommand(int playerIndex, int gameIndex, int ratio, ResourceType input, ResourceType output) {
		super(playerIndex);
		this.gameIDCookie = gameIndex;
		this.ratio = ratio;
		this.inputResource = MirrorResourceType.getResource(input);
		this.outputResource = MirrorResourceType.getResource(output);
	}
	
	/**
	 * This executes the MaritimeTrade.
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange

			int playerIndex=this.getPlayerIndex();			
	 		int index =this.gameIDCookie;		
	 		GetModelResponse response = new GetModelResponse();	
	 		Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(index);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];		
	 		int ratio = this.ratio;		
	 		ResourceType input = this.getInput();
	 		ResourceType output = this.getOutput();
	 		
	 		//setting the response header
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
			
			String status = turnTracker.getStatus();		
			//making sure its the right status		
			if(!status.equals("Playing")){
				response.setSuccess(false);
				response.setErrorMessage("Wrong status");
				return response; //Need to throw some error here		
			}	
			//checking to see if the player has the resources 
			if(player.getResources().hasResourceCertainNumber(input,ratio) == false){
				response.setSuccess(false);
				response.setErrorMessage("Doesnt have resource");
				return response;
			}
			//checking to see if the bank has the resources
			if(model.getBank().hasResource(output) == false){
				response.setSuccess(false);
				response.setErrorMessage("Bank is all out of that resource");
				return response;
			}
			
			//checks to see if the player has the necessary ports
			if(ratio <4){
				if(checkPorts(map,input) == false){
					response.setSuccess(false);
					response.setErrorMessage("Player does not have necessary port");
					return response;
				}
			}
			
			//makes the trade
			player.addResource(output);
			player.getResources().removeResource(input, ratio);
			model.getBank().removeResource(output,1);
			model.setVersion(model.getVersion() + 1); //updating the version
			response.setSuccess(true);
			response.setJson(model.toString());
			
			return response;
		}
	}
	
	@Override
	public GetModelResponse reExecute(int gameID, int playerIndex) {
		synchronized(Game.instance().lock){
			//getting all the info needed to execute the command from the cookies and http exchange

			//int playerIndex=playerID;			
	 		int index =gameID;		
	 		GetModelResponse response = new GetModelResponse();	
	 		Game game = Game.instance();		
	 		ServerGameModel model = game.getGameId(index);		
	 		ServerGameMap map = model.getServerMap();		
	 		ServerTurnTracker turnTracker = model.getServerTurnTracker();		
	 		ServerPlayer player = model.getServerPlayers()[playerIndex];		
	 		int ratio = this.ratio;		
	 		ResourceType input = this.getInput();
	 		ResourceType output = this.getOutput();
	 		
	 		//setting the response header
	 		try {
				response.setCookie("Set-cookie", "catan.user=" +
						URLEncoder.encode("{" +
					       "\"authentication\":\"" + "1142128101" + "\"," +
				           "\"name\":\"" + player.getName() + "\"," +
						   "\"password\":\"" + game.getPassword(player.getName()) + "\"," + 
				           "\"playerID\":" + player.getPlayerID() + "}", "UTF-8" ) + ";catan.game=" + gameID);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	 		
			//making sure its the players turn		
			if(checkTurn(turnTracker,playerIndex) == false){
				response.setSuccess(false);
				response.setErrorMessage("Wrong turn");
				return response; //Need to throw some error here		
			}
			
			String status = turnTracker.getStatus();		
			//making sure its the right status		
			if(!status.equals("Playing")){
				response.setSuccess(false);
				response.setErrorMessage("Wrong status");
				return response; //Need to throw some error here		
			}	
			//checking to see if the player has the resources 
			if(player.getResources().hasResourceCertainNumber(input,ratio) == false){
				response.setSuccess(false);
				response.setErrorMessage("Doesnt have resource");
				return response;
			}
			//checking to see if the bank has the resources
			if(model.getBank().hasResource(output) == false){
				response.setSuccess(false);
				response.setErrorMessage("Bank is all out of that resource");
				return response;
			}
			
			//checks to see if the player has the necessary ports
			if(ratio <4){
				if(checkPorts(map,input) == false){
					response.setSuccess(false);
					response.setErrorMessage("Player does not have necessary port");
					return response;
				}
			}
			
			//makes the trade
			player.addResource(output);
			player.getResources().removeResource(input, ratio);
			model.getBank().removeResource(output,1);
			model.setVersion(model.getVersion() + 1); //updating the version
			response.setSuccess(true);
			response.setJson(model.toString());
			
			return response;
		}
	}
	
	/**
	 * checks to see if the player has the proper ports 
	 * @param map
	 * @param input
	 * @return
	 */
	public boolean checkPorts(ServerGameMap map, ResourceType input){
		List<Port> ports = map.getPersonalPorts(playerIndex);
		boolean correct = false;
		//goes through the players ports seeing if they have a port with a ratio of 3
		if(ratio == 3){
			for(int i=0; i<ports.size(); i++){
				Port port = ports.get(i);
				if(port.getRatio() == ratio){
					correct = true;
				}
			}
		}
		//going though the players ports seeing if they have a port with the ratio of 2 and the proper resource
		else if(ratio == 2){
			for(int i=0; i<ports.size(); i++){
				Port port = ports.get(i);
				if(port.getRatio() == ratio && port.getResource() == input){
					correct = true;
				}
			}
		}
		
		return correct;
	}


	public ResourceType getInput() {
		return MirrorResourceType.getOriginal(inputResource);
	}
	public ResourceType getOutput() {
		return MirrorResourceType.getOriginal(outputResource);
	}
}
