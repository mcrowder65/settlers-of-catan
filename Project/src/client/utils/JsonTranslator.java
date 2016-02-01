package client.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import shared.communication.response.LoginResponse;
import shared.definitions.*;
import shared.locations.*;
import client.data.GameInfo;
import client.data.PlayerInfo;
public class JsonTranslator {
	public JsonTranslator(){}
	public MirrorGameModel makeMirrorObject(GameModel gameModel){
		return new MirrorGameModel(gameModel);
	}
	public Object makeGenericObject(String json, Object object){
		return new Gson().fromJson(json, object.getClass());
	}
	public List<GameInfo> makeListOfGames(String json){
		JsonParser parser = new JsonParser();
		JsonArray gameArray = (JsonArray) parser.parse(json);
		List<GameInfo> games = new ArrayList<GameInfo>();
		for(int i = 0; i < gameArray.size(); i++){
			GameInfo gameInfo = new GameInfo();
			JsonObject temp = gameArray.get(i).getAsJsonObject();
			String title = temp.get("title").getAsString();
			int id = temp.get("id").getAsInt();
			List<Player> playerArray = new ArrayList<Player>();
			JsonArray jsPlayerArray = temp.get("players").getAsJsonArray();
			for(int x = 0; x < jsPlayerArray.size(); x++){
				JsonObject jsPlayer = jsPlayerArray.get(i).getAsJsonObject();
				
				CatanColor color = getCatanColor(jsPlayer.get("color").getAsString());
				String name = jsPlayer.get("name").getAsString();
				int playerID = jsPlayer.get("id").getAsInt();
				gameInfo.addPlayer(new PlayerInfo(playerID, name, color));
			}
			gameInfo.setTitle(title);
			gameInfo.setId(id);
			games.add(gameInfo);
		}
		return games;
	}
	public GameModel makeObject(String json){
		JsonParser parser = new JsonParser();
		JsonObject jsonObj = (JsonObject) parser.parse(json);
		
		GameModel gameModel = new GameModel();
		
		ResourceList bank = getResourceList((JsonObject)jsonObj.get("bank"));
		gameModel.setBank(bank);
		
		MessageList chat = makeMessageList((JsonObject)jsonObj.get("chat"));
		gameModel.setChat(chat);
		
		MessageList log = makeMessageList((JsonObject)jsonObj.get("log"));
		gameModel.setLog(log);
		
		GameMap map = makeMap((JsonObject)jsonObj.get("map"));
		gameModel.setMap(map);
		
		Player[] players =  makePlayers(jsonObj.get("players").getAsJsonArray());
		gameModel.setPlayers(players);
		
		TradeOffer tradeOffer = makeTradeOffer((JsonObject)jsonObj.get("tradeOffer"));
		gameModel.setTradeOffer(tradeOffer);
		
		TurnTracker turnTracker = makeTurnTracker((JsonObject)jsonObj.get("turnTracker"));
		gameModel.setTurnTracker(turnTracker);
		
		int version = jsonObj.get("version").getAsInt();
		gameModel.setVersion(version);
		
		int winner = jsonObj.get("winner").getAsInt();
		gameModel.setWinner(winner);
		
		return gameModel;
	}
	public MessageList makeMessageList(JsonObject jsMessageList){
		MessageList messageList = new MessageList();
		JsonArray messages = jsMessageList.get("lines").getAsJsonArray();
		MessageLine[] lines = new MessageLine[messages.size()];
		for(int i = 0; i < messages.size(); i++){
			JsonObject temp = (JsonObject)messages.get(i);
			String message = temp.get("message").getAsString();
			String source = temp.get("source").getAsString();
			lines[i] = new MessageLine(message, source);
		}
		messageList.setLines(lines);
		return messageList;
	}
	
	
	public GameMap makeMap(JsonObject jsMap){
		GameMap map = new GameMap();
		
		int radius = jsMap.get("radius").getAsInt();
		map.setRadius(radius);
		
		JsonObject jsRobber = (JsonObject)jsMap.get("robber");
		int rX = jsRobber.get("x").getAsInt();
		int rY = jsRobber.get("y").getAsInt();
		HexLocation robber = new HexLocation(rX, rY);
		map.setRobber(robber);

		Hex[] hexes = makeHexes(jsMap.get("hexes").getAsJsonArray());
		map.setHexes(hexes);
		
		Port[] ports = makePorts(jsMap.get("ports").getAsJsonArray());
		map.setPorts(ports);
		
		EdgeValue[] roads = makeRoads(jsMap.get("roads").getAsJsonArray());
		map.setRoads(roads);
		
		VertexObject[] settlements = makeSettlements(jsMap.get("settlements").getAsJsonArray());
		map.setSettlements(settlements);
		
		VertexObject[] cities = makeCities(jsMap.get("cities").getAsJsonArray());
		map.setCities(cities);
		
		return map;
	}
	public Hex[] makeHexes(JsonArray jsHexes){
		ArrayList<Hex> hexes = new ArrayList<Hex>();
		for(int i = 0; i < jsHexes.size(); i++){
			JsonObject temp = (JsonObject)jsHexes.get(i);
			JsonObject location = (JsonObject)temp.get("location");
			int x = location.get("x").getAsInt();
			int y = location.get("y").getAsInt();
			
			String resource = temp.get("resource").getAsString();
			int number = temp.get("number").getAsInt();
			Hex hex = new Hex(new HexLocation(x,y), getResourceType(resource), number);
			hexes.add(hex);
		}
		return hexes.toArray(new Hex[hexes.size()]);
	}
	public ResourceType getResourceType(String resource){
		//NONE, WOOD, BRICK, SHEEP, WHEAT, ORE
		switch (resource){
			default:
				return ResourceType.NONE;
			case "WOOD":
				return ResourceType.WOOD;
			case "BRICK":
				return ResourceType.BRICK;
			case "SHEEP":
				return ResourceType.SHEEP;
			case "WHEAT":
				return ResourceType.WHEAT;
			case "ORE":
				return ResourceType.ORE;	
		}
	}
	public Port[] makePorts(JsonArray jsPorts){
		ArrayList<Port> ports = new ArrayList<Port>();
		for(int i = 0; i < jsPorts.size(); i++){
			
			JsonObject temp = (JsonObject)jsPorts.get(i);
			JsonObject location = (JsonObject)temp.get("location");
			int x = location.get("x").getAsInt();
			int y = location.get("y").getAsInt();
			HexLocation hex = new HexLocation(x,y);
			
			String direction = temp.get("direction").getAsString();
			EdgeDirection edgeDirection = getEdgeDirection(direction);
			
			String resource = temp.get("resource").getAsString();
			ResourceType resourceType = getResourceType(resource);
			int ratio = temp.get("ratio").getAsInt();
			 
			Port port = new Port(resourceType, hex, edgeDirection, ratio);
			ports.add(port);
		}
		return ports.toArray(new Port[ports.size()]);
	}
	public EdgeDirection getEdgeDirection(String direction){
		//NorthWest, North, NorthEast, SouthEast, South, SouthWest;
		switch (direction){
			default:
				return EdgeDirection.NorthWest;
			case "North":
				return EdgeDirection.North;
			case "NorthEast":
				return EdgeDirection.NorthEast;
			case "SouthEast":
				return EdgeDirection.SouthEast;
			case "South":
				return EdgeDirection.South;
			case "SouthWest":
				return EdgeDirection.SouthWest;
		}
	}
	public VertexDirection getVertexDirection(String direction){
		//West, NorthWest, NorthEast, East, SouthEast, SouthWest;
		switch (direction){
			default:
				return VertexDirection.West;
			case "NorthWest":
				return VertexDirection.NorthWest;
			case "NorthEast":
				return VertexDirection.NorthEast;
			case "East":
				return VertexDirection.East;
			case "SouthEast":
				return VertexDirection.SouthEast;
			case "SouthWest":
				return VertexDirection.SouthWest;	
		}	
		
	}
	public EdgeValue[] makeRoads(JsonArray jsRoads){
		ArrayList<EdgeValue> roads = new ArrayList<EdgeValue>();
		for(int i = 0; i < jsRoads.size(); i++){
			JsonObject temp = (JsonObject)jsRoads.get(i);
			JsonObject location = (JsonObject)temp.get("location");
			int x = location.get("x").getAsInt();
			int y = location.get("y").getAsInt();
			HexLocation hex = new HexLocation(x,y);
			
			String direction = location.get("direction").getAsString();
			EdgeDirection edgeDirection = getEdgeDirection(direction);
			
			EdgeLocation edgeLocation = new EdgeLocation(hex, edgeDirection);
			
			int owner = temp.get("owner").getAsInt();
			roads.add(new EdgeValue(owner, edgeLocation));
		}
		return roads.toArray(new EdgeValue[roads.size()]);
	}
	public VertexObject[] makeSettlements(JsonArray jsSettlements){
		ArrayList<VertexObject> settlements = new ArrayList<VertexObject>();
		for(int i = 0; i < jsSettlements.size(); i++){
			JsonObject temp = (JsonObject)jsSettlements.get(i);
			JsonObject location = (JsonObject)temp.get("location");
			int x = location.get("x").getAsInt();
			int y = location.get("y").getAsInt();
			
			String direction = location.get("direction").getAsString();
			VertexLocation vertexLocation = 
					new VertexLocation(new HexLocation(x,y), 
					getVertexDirection(direction));
			int owner = temp.get("owner").getAsInt();
			settlements.add(new VertexObject(owner, vertexLocation));
		}
		return settlements.toArray(new VertexObject[settlements.size()]);
	}
	public VertexObject[] makeCities(JsonArray jsCities){
		ArrayList<VertexObject> cities = new ArrayList<VertexObject>();
		for(int i = 0; i < jsCities.size(); i++){
			JsonObject temp = (JsonObject)jsCities.get(i);
			JsonObject location = (JsonObject)temp.get("location");
			int x = location.get("x").getAsInt();
			int y = location.get("y").getAsInt();
			//hexlocation
			//vertexdirection
			
			
			String direction = location.get("direction").getAsString();
			VertexLocation vertexLocation = new VertexLocation(
					new HexLocation(x,y), getVertexDirection(direction));
			
			int owner = temp.get("owner").getAsInt();
			VertexObject vertexObject = new VertexObject(owner, vertexLocation);
			cities.add(vertexObject);
		}
		return cities.toArray(new VertexObject[cities.size()]);
	}
	public Player[] makePlayers(JsonArray jsPlayers){
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < jsPlayers.size(); i++){
			JsonObject player = (JsonObject)jsPlayers.get(i);
			int cities = player.get("cities").getAsInt();
			String color = player.get("color").getAsString();
			boolean discarded = player.get("discarded").getAsBoolean();
			int monuments = player.get("monuments").getAsInt();
			int playerIndex = player.get("playerIndex").getAsInt();
			boolean playedDevCard = player.get("playedDevCard").getAsBoolean();
			int playerID = player.get("playerID").getAsInt();
			int roads = player.get("roads").getAsInt();
			int settlements = player.get("settlements").getAsInt();
			int soldiers = player.get("soldiers").getAsInt();
			int victoryPoints = player.get("victoryPoints").getAsInt();
			
			String name = player.get("name").getAsString();
			DevCardList newDevCards = getDevCards((JsonObject)player.get("newDevCards"));
			DevCardList oldDevCards = getDevCards((JsonObject)player.get("oldDevCards"));
			ResourceList resources = getResourceList((JsonObject)player.get("resources"));
			
			Player playerObj = new Player(cities,  getCatanColor(color), discarded, monuments,
					name, newDevCards, oldDevCards,
					playerIndex, playedDevCard, playerID,
				    resources, roads, settlements, soldiers,
					victoryPoints);
			players.add(playerObj);
		}
		return players.toArray(new Player[players.size()]);
	}
	public CatanColor getCatanColor(String color){
		//RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BROWN;
		switch(color){
			default:
				return CatanColor.RED;
			case "ORANGE":
				return CatanColor.ORANGE;
			case "YELLOW":
				return CatanColor.YELLOW;
			case "BLUE":
				return CatanColor.BLUE;
			case "GREEN":
				return CatanColor.GREEN;
			case "PURPLE":
				return CatanColor.PURPLE;
			case "PUCE":
				return CatanColor.PUCE;
			case "WHITE":
				return CatanColor.WHITE;
			case "BROWN":
				return CatanColor.BROWN;
		}	
	}
	public ResourceList getResourceList(JsonObject jsObject){
		int brick = jsObject.get("brick").getAsInt();
		int ore = jsObject.get("ore").getAsInt();
		int sheep = jsObject.get("sheep").getAsInt();
		int wheat = jsObject.get("wheat").getAsInt();
		int wood = jsObject.get("wood").getAsInt();
		return new ResourceList(brick, ore, sheep, wheat, wood);
	}
	public DevCardList getDevCards(JsonObject devCards){
		int monopoly = devCards.get("monopoly").getAsInt();
		int monument = devCards.get("monument").getAsInt();
		int roadBuilding = devCards.get("roadBuilding").getAsInt();
		int soldier = devCards.get("soldier").getAsInt();
		int yearOfPlenty = devCards.get("yearOfPlenty").getAsInt();
		
		return new DevCardList(monopoly, monument, 
				roadBuilding, soldier, yearOfPlenty);
	}
	public TradeOffer makeTradeOffer(JsonObject jsTradeOffer){
		int sender = jsTradeOffer.get("sender").getAsInt();
		int receiver = jsTradeOffer.get("receiver").getAsInt();
		ResourceList offer = getResourceList((JsonObject)jsTradeOffer.get("offer"));
		return new TradeOffer(sender, receiver, offer);
	}
	public TurnTracker makeTurnTracker(JsonObject jsTurnTracker){
		int currentTurn = jsTurnTracker.get("currentTurn").getAsInt();
		String status = jsTurnTracker.get("status").getAsString();
		int longestRoad = jsTurnTracker.get("longestRoad").getAsInt();
		int largestArmy = jsTurnTracker.get("largestArmy").getAsInt();
		return new TurnTracker(currentTurn, status, longestRoad, largestArmy);
	}
	
	
	
	
	
	
	
	
	
	
	
}