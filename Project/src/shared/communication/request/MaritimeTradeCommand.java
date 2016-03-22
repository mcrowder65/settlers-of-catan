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
		MaritimeTradeCommand tmp = (MaritimeTradeCommand)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.ratio = tmp.ratio;
		this.playerIndex = tmp.playerIndex;
		this.inputResource = tmp.inputResource;
		this.outputResource = tmp.outputResource; 
		
	}
	/**
	 * This executes the MaritimeTrade.
	 */
	@Override
	public GetModelResponse execute() {
		synchronized(Game.instance().lock){
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
			
			if(player.getResources().hasResourceCertainNumber(input,ratio) == false){
				response.setSuccess(false);
				response.setErrorMessage("Doesnt have resource");
				return response;
			}
			
			if(model.getBank().hasResource(output) == false){
				response.setSuccess(false);
				response.setErrorMessage("Bank is all out of that resource");
				return response;
			}
			
			List<Port> ports = map.getPersonalPorts(playerIndex);
			boolean correct = false;
			if(ratio == 3){
				for(int i=0; i<ports.size(); i++){
					Port port = ports.get(i);
					if(port.getRatio() == ratio){
						correct = true;
					}
				}
			}
			else if(ratio == 2){
				for(int i=0; i<ports.size(); i++){
					Port port = ports.get(i);
					if(port.getRatio() == ratio && port.getResource().equals(output)){
						correct = true;
					}
				}
			}
			if(!correct){
				response.setSuccess(false);
				response.setErrorMessage("Player does not have necessary port");
				return response;
			}
			
			player.addResource(output);
			player.getResources().removeResource(input, ratio);
			model.getBank().removeResource(output,1);
			model.setVersion(model.getVersion() + 1);
			response.setSuccess(true);
			response.setJson(model.toString());
			return response;
		}
	}


	public ResourceType getInput() {
		return MirrorResourceType.getOriginal(inputResource);
	}
	public ResourceType getOutput() {
		return MirrorResourceType.getOriginal(outputResource);
	}
}
