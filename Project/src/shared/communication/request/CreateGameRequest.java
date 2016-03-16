package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

import com.sun.net.httpserver.HttpExchange;

import client.data.GameInfo;
import client.data.PlayerInfo;
import client.utils.Translator;
import server.Game;
import server.util.ServerGameModel;
import shared.communication.response.CreateGameResponse;
import shared.communication.response.Response;
import shared.definitions.*;
import shared.locations.EdgeValue;
import shared.locations.HexLocation;
import shared.locations.VertexObject;
import sun.security.util.Resources;

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
		//Hex[] hexes, Port[] ports, EdgeValue[] roads,
		//VertexObject[] settlements, VertexObject[] cities, int radius,
		//HexLocation robber
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
	
	private void generateHexes(){
		ArrayList<Integer> nums = initNumbers();
		ArrayList<ResourceType> resources = initResources();

		if(randomNumbers)
			Collections.shuffle(nums);
		if(randomTiles)
			Collections.shuffle(resources);
		setHexes(nums,resources);
	}
	private void setHexes(ArrayList<Integer> nums, ArrayList<ResourceType> resources){
		Hex[] hexes = {
				new Hex(new HexLocation(0,-2),  resources.get(0), nums.get(0)),
				new Hex(new HexLocation(-1,-1),  resources.get(1), nums.get(1)),
				new Hex(new HexLocation(1,-2),  resources.get(2), nums.get(2)),
				new Hex(new HexLocation(-2,0),  resources.get(3), nums.get(3)),
				new Hex(new HexLocation(0, -1),  resources.get(4), nums.get(4)),
				new Hex(new HexLocation(2,-2),  resources.get(5), nums.get(5)),
				new Hex(new HexLocation(-1,0),  resources.get(6), nums.get(6)),
				new Hex(new HexLocation(1,-1),  resources.get(7), nums.get(7)),
				new Hex(new HexLocation(-2,1),  resources.get(8), nums.get(8)),
				new Hex(new HexLocation(0,0),  resources.get(9), nums.get(9)),
				new Hex(new HexLocation(2,-1),  resources.get(10), nums.get(10)),
				new Hex(new HexLocation(-1,1),  resources.get(11), nums.get(11)),
				new Hex(new HexLocation(1,0),  resources.get(12), nums.get(12)),
				new Hex(new HexLocation(-2,2), resources.get(13), nums.get(13)),
				new Hex(new HexLocation(0,1),  resources.get(14), nums.get(14)),
				new Hex(new HexLocation(2,0),  resources.get(15), nums.get(15)),
				new Hex(new HexLocation(-1,2),  resources.get(16), nums.get(16)),
				new Hex(new HexLocation(1,1),  resources.get(17), nums.get(17)),
				new Hex(new HexLocation(0,2),  resources.get(18), nums.get(18))
		};
		this.hexes = hexes;
	}
	private ArrayList<Integer> initNumbers(){
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(0);
		nums.add(8);
		nums.add(4);
		nums.add(5);
		nums.add(3);
		nums.add(11);
		nums.add(10);
		nums.add(9);
		nums.add(2);
		nums.add(11);
		nums.add(11);
		nums.add(9);
		nums.add(5);
		nums.add(6);
		nums.add(4);
		nums.add(6);
		nums.add(3);
		nums.add(10);
		nums.add(8);
		return nums;
	}
	private ArrayList<ResourceType> initResources(){
		ArrayList<ResourceType> resources = new ArrayList<ResourceType>();
		resources.add(ResourceType.NONE);
		resources.add(ResourceType.BRICK);
		resources.add(ResourceType.BRICK);
		resources.add(ResourceType.ORE);
		resources.add(ResourceType.WOOD);
		resources.add(ResourceType.WOOD);
		resources.add(ResourceType.SHEEP);
		resources.add(ResourceType.ORE);
		resources.add(ResourceType.WHEAT);
		resources.add(ResourceType.WHEAT);
		resources.add(ResourceType.SHEEP);
		resources.add(ResourceType.SHEEP);
		resources.add(ResourceType.BRICK);
		resources.add(ResourceType.WOOD);
		resources.add(ResourceType.WOOD);
		resources.add(ResourceType.WHEAT);
		resources.add(ResourceType.ORE);
		resources.add(ResourceType.SHEEP);
		resources.add(ResourceType.WHEAT);
		return resources;
	}
}