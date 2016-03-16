package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;

import client.data.GameInfo;
import client.data.PlayerInfo;
import client.utils.Translator;
import server.Game;
import server.util.ServerGameModel;
import shared.communication.response.CreateGameResponse;
import shared.communication.response.Response;
import shared.definitions.*;
import shared.locations.HexLocation;

public class CreateGameRequest extends Request {

	private String name;
	private boolean randomTiles;
	private boolean randomNumbers;
	private boolean randomPorts;
	private Hex[] hexes = new Hex[19];
	public CreateGameRequest(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) {
		setVariables(name, randomTiles, randomNumbers, randomPorts);
	}
	void setVariables(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts){
		if (name == null) throw new IllegalArgumentException("name cannot be null.");
		this.name = name;
		this.randomNumbers = randomNumbers;
		this.randomTiles = randomTiles;
		this.randomPorts = randomPorts;
	}
	public CreateGameResponse createGame() {
		CreateGameResponse response = new CreateGameResponse(name);
		response.setErrorMessage("Success");
		response.setSuccess(true);
		ServerGameModel sgm = new ServerGameModel();
		
		gameIDCookie = Game.instance().addGame(response.getGame(), sgm);
		sgm.setGameCookie(Integer.toString(gameIDCookie));
		
		//sgm.setMap(new ServerGameMap());
		Game.instance().setGame(gameIDCookie, sgm);
		response.setGameId(gameIDCookie);

		response.setCookie("Set-cookie", "catan.game=" + gameIDCookie + ";");

		return (CreateGameResponse) response;
	}
	public CreateGameRequest(HttpExchange exchange){
		super(exchange);
		CreateGameRequest tmp = (CreateGameRequest)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		setVariables(tmp.name, tmp.randomTiles, tmp.randomNumbers, tmp.randomPorts);
	}
	public void generateHexes(){
//		Hex[] hexLocs = {
//				new Hex(new HexLocation(0,-2), ResourceType.NONE, 0),
//				new Hex(new HexLocation(-1,-1),  ResourceType.BRICK, 8),
//				new HexLocation(1,-2), 
//				new HexLocation(-2,0), 
//				new HexLocation(0, -1), 
//				new HexLocation(2,-2), 
//				new HexLocation(-1,0), 
//				new HexLocation(1,-1), 
//				new HexLocation(-2,1), 
//				new HexLocation(0,0), 
//				new HexLocation(2,-1), 
//				new HexLocation(-1,1), 
//				new HexLocation(1,0), 
//				new HexLocation(-2,2), 
//				new HexLocation(0,1), 
//				new HexLocation(2,0), 
//				new HexLocation(-1,2), 
//				new HexLocation(1,1),
//				new HexLocation(0,2)
//		};
//		for(int i = 0; i < hexLocs.length; i++){
//			hexes[i] = new Hex(water[i], ResourceType)
//		}	
	}
	
}