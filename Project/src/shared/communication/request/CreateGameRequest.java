package shared.communication.request;


import java.util.ArrayList;
import java.util.Collections;

import com.sun.net.httpserver.HttpExchange;

import client.data.GameInfo;
import client.data.PlayerInfo;
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
	private transient int radius = 2;
	private transient HexLocation robber = new HexLocation(0, -2);

	
	
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
		synchronized(Game.instance().lock){
			GameInfo info = new GameInfo();
			info.setTitle(name);
			info.setId(Game.instance().getNumGames());
			info.addPlayer(new PlayerInfo(playerIDCookie, userCookie, CatanColor.red));
			CreateGameResponse response = new CreateGameResponse(info);
			response.setErrorMessage("Success");
			response.setSuccess(true);
			
			
			ServerGameModel sgm = new ServerGameModel();
			gameIDCookie = Game.instance().getAddableGameCookie();
			sgm.setGameId(gameIDCookie);
		
			generatePorts();
			generateHexes();
			
			sgm.setServerGameMap(new ServerGameMap(hexes, ports, roads, settlements, cities, radius, robber));
			sgm.setServerPlayers(new ServerPlayer[4]);
	
			//int brick, int ore, int sheep, int wheat, int wood
			sgm.setBank(new ResourceList(19, 19, 19, 19, 19));
			sgm.setDeck(new DevCardList(2, 5, 2, 14, 2));
			//int monopoly, int monument, int roadBuilding, int soldier, int yearOfPlenty)
			sgm.setChat(new MessageList());
			sgm.setLog(new MessageList());
			sgm.setLocalPlayer(Game.instance().getLocalPlayer(playerIDCookie));
			sgm.initServerPlayers();
			sgm.setServerTurnTracker(new ServerTurnTracker(0, "FirstRound", -1, -1)); //int currentTurn, String status, int longestRoad, int largestArmy
			sgm.setVersion(0);
			//sgm.setTradeOffer(new TradeOffer(10,10, new ResourceList(0,0,0,0,0)));
			sgm.setTradeOffer(null);
			sgm.setWinner(-1);
			sgm.initAINames();
			sgm.initAIColors();
			Game.instance().addGame(info, sgm);
			response.setGameId(gameIDCookie);
	    	response.setCookie("Set-cookie", "catan.game=" + gameIDCookie + ";");
	
			return response;
		}
	}
	public void setRobber(){
		for(int i = 0; i < hexes.length; i++){
			if(hexes[i].getResource() == ResourceType.NONE)
				robber = new HexLocation(hexes[i].getLocation().getX(), hexes[i].getLocation().getY());
		}
	}
	public CreateGameRequest(HttpExchange exchange){
		super(exchange);
		CreateGameRequest tmp = (CreateGameRequest)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
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
		portTypes.add(new PortTypeAndRatio(ResourceType.SHEEP, 2));
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
		ArrayList<HexLocation> locations = initHexLocs();
		if(randomNumbers)
			Collections.shuffle(nums);
		if(randomTiles){
			Collections.shuffle(locations);
		}
		if(randomTiles && randomNumbers){
			Collections.shuffle(locations);
			Collections.shuffle(resources);
			Collections.shuffle(nums);
		}

		setHexes(nums,resources, locations);
		setRobber();
	}
	private int getZero(ArrayList<Integer> nums){
		for(int i = 0; i < nums.size(); i++){
			if(nums.get(i) == 0)
				return i;
		}
		return -1;
	}
	private int getDesert(ArrayList<ResourceType> resources){
		for(int i = 0; i < resources.size(); i++){
			if(resources.get(i) == ResourceType.NONE)
				return i;
		}
		return -1;
	}
	/**
	 * this function takes the arrayLists and adds the desert hex first, then adds all the others.
	 * @param nums
	 * @param resources
	 * @param locations
	 */
	private void setHexes(ArrayList<Integer> nums, ArrayList<ResourceType> resources, ArrayList<HexLocation>locations){
		
		int desertHex = getDesert(resources);
		int zero = getZero(nums);
		hexes[0] = new Hex(locations.get(0), resources.get(desertHex), 0);
		resources.remove(desertHex);
		nums.remove(zero);
		locations.remove(0);
		int i = 1;
		while(nums.size() != 0 && resources.size() != 0 && locations.size() != 0){
			
			hexes[i] = new Hex(locations.get(0), resources.get(0), nums.get(0));
			nums.remove(0);
			
			locations.remove(0);
			resources.remove(0);
			i++;
		}
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
	private ArrayList<HexLocation> initHexLocs(){
		ArrayList<HexLocation> hexLocs = new ArrayList<HexLocation>();
		hexLocs.add(new HexLocation(0,-2));
		hexLocs.add(new HexLocation(-1,-1));
		hexLocs.add(new HexLocation(1,-2));
		hexLocs.add(new HexLocation(-2,0));
		hexLocs.add(new HexLocation(0, -1));
		hexLocs.add(new HexLocation(2,-2));
		hexLocs.add(new HexLocation(-1,0));
		hexLocs.add(new HexLocation(1,-1));
		hexLocs.add(new HexLocation(-2,1));
		hexLocs.add(new HexLocation(0,0));
		hexLocs.add(new HexLocation(2,-1));
		hexLocs.add(new HexLocation(-1,1));
		hexLocs.add(new HexLocation(1,0));
		hexLocs.add(new HexLocation(-2,2));
		hexLocs.add(new HexLocation(0,1));
		hexLocs.add(new HexLocation(2,0));
		hexLocs.add(new HexLocation(-1,2));
		hexLocs.add(new HexLocation(1,1));
		hexLocs.add(new HexLocation(0,2));
		return hexLocs;
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

