package shared.communication.request;


import java.util.ArrayList;
import java.util.Collections;

import com.sun.net.httpserver.HttpExchange;

import client.data.GameInfo;
import client.utils.Translator;
import server.Game;
import server.util.*;
import shared.communication.response.*;
import shared.definitions.*;
import shared.locations.*;

public class CreateGameRequest extends Request {

	private String name;
	private boolean randomTiles;
	private boolean randomNumbers;
	private boolean randomPorts;
	
	private transient Hex[] hexes = new Hex[19];
	private transient Port[] ports = new Port[9];
	private transient EdgeValue[] roads = new EdgeValue[0];
	private transient VertexObject[] settlements = new VertexObject[0];
	private transient VertexObject[] cities = new VertexObject[0];
	private transient int radius = 2; //TODO what is radius?
	private transient HexLocation robber = new HexLocation(0, -2); //TODO configure random robber
	
	
	
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
		GameInfo info = new GameInfo();
		info.setTitle(name);
		info.setId(Game.instance().getNumGames());
		
		CreateGameResponse response = new CreateGameResponse(info);
		response.setErrorMessage("Success");
		response.setSuccess(true);
		
		
		ServerGameModel sgm = new ServerGameModel();
		gameIDCookie = Game.instance().addGame(info, sgm);
		sgm.setGameId(gameIDCookie);
		

		//Hex[] hexes, Port[] ports, EdgeValue[] roads,
		//VertexObject[] settlements, VertexObject[] cities, int radius,
		//HexLocation robber
		generatePorts();
		generateHexes();
		sgm.setMap(new ServerGameMap(hexes, ports, roads, settlements, cities, radius, robber));
		Game.instance().setGame(gameIDCookie, sgm);
		
		
		response.setCookie("Set-cookie", "catan.game=" + gameIDCookie + ";");

		return response;
	}
	public CreateGameRequest(HttpExchange exchange){
		super(exchange);
		CreateGameRequest tmp = (CreateGameRequest)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		setVariables(tmp.name, tmp.randomTiles, tmp.randomNumbers, tmp.randomPorts);
	}
	private void generatePorts(){
		ArrayList<PortTypeAndRatio> portTypes = initPortTypes();
		if(randomPorts)
			Collections.shuffle(portTypes);
		setPorts(portTypes);
	}

	private void setPorts(ArrayList<PortTypeAndRatio> portTypes){
		//ResourceType resource, HexLocation location, EdgeDirection direction, int ratio
		ports[0] = new Port(portTypes.get(0).type, new HexLocation(-1, -2), EdgeDirection.South, portTypes.get(0).ratio);
		ports[1] = new Port(portTypes.get(1).type, new HexLocation(1, -3), EdgeDirection.South, portTypes.get(1).ratio);
		ports[2] = new Port(portTypes.get(2).type, new HexLocation(3, -3), EdgeDirection.SouthWest, portTypes.get(2).ratio);
		ports[3] = new Port(portTypes.get(3).type, new HexLocation(3, -1), EdgeDirection.NorthWest, portTypes.get(3).ratio);
		ports[4] = new Port(portTypes.get(4).type, new HexLocation(2,1), EdgeDirection.NorthWest, portTypes.get(4).ratio);
		ports[5] = new Port(portTypes.get(5).type, new HexLocation(0,3), EdgeDirection.North, portTypes.get(5).ratio);
		ports[6] = new Port(portTypes.get(6).type, new HexLocation(-2,3), EdgeDirection.NorthEast, portTypes.get(6).ratio);
		ports[7] = new Port(portTypes.get(7).type, new HexLocation(-3,2), EdgeDirection.NorthEast, portTypes.get(7).ratio);
		ports[8] = new Port(portTypes.get(8).type, new HexLocation(-3,0), EdgeDirection.SouthEast, portTypes.get(8).ratio);
	}
	private ArrayList<PortTypeAndRatio> initPortTypes(){
		ArrayList<PortTypeAndRatio> portTypes = new ArrayList<PortTypeAndRatio>();
		portTypes.add(new PortTypeAndRatio(ResourceType.WHEAT, 2));
		portTypes.add(new PortTypeAndRatio(ResourceType.ORE, 2));
		portTypes.add(new PortTypeAndRatio(ResourceType.NONE, 3));
		portTypes.add(new PortTypeAndRatio(ResourceType.NONE, 2));
		portTypes.add(new PortTypeAndRatio(ResourceType.NONE, 3));
		portTypes.add(new PortTypeAndRatio(ResourceType.NONE, 3));
		portTypes.add(new PortTypeAndRatio(ResourceType.BRICK, 2));
		portTypes.add(new PortTypeAndRatio(ResourceType.WOOD, 2));
		portTypes.add(new PortTypeAndRatio(ResourceType.NONE, 3));
		return portTypes;
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
		nums.add(0); //0
		nums.add(8); //1
		nums.add(4); //2
		nums.add(5); //3
		nums.add(3);//4
		nums.add(11);//5
		nums.add(10);//6
		nums.add(9);//7
		nums.add(2);//8
		nums.add(11);//9
		nums.add(12);//10
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
	class PortTypeAndRatio{
		ResourceType type;
		int ratio;
		public PortTypeAndRatio(ResourceType type, int ratio){
			this.type = type;
			this.ratio = ratio;
		}
		
	};
}

