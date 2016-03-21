package client.utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import shared.definitions.*;
import shared.locations.*;
import client.data.GameInfo;
import client.data.PlayerInfo;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
public class JsonTranslator {
	public JsonTranslator(){}
	public MirrorGameModel makeMirrorObject(GameModel gameModel){
		return new MirrorGameModel(gameModel);
	}
	public Object makeGenericObject(String json, Object object){
		return new Gson().fromJson(json, object.getClass());
	}
	public Object makeGenericObject(String json, Class klass) {
		return new Gson().fromJson(json, klass);
	}
	public HashMap<String, String> makeKeyValuePairs(String json) {
		return (HashMap<String,String>)(new Gson().fromJson(json, HashMap.class));
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
				JsonObject jsPlayer = jsPlayerArray.get(x).getAsJsonObject();

				CatanColor color = (jsPlayer.has("color")) ? Translator.getCatanColor(jsPlayer.get("color").getAsString()) : null;
				String name = (jsPlayer.has("name")) ? jsPlayer.get("name").getAsString() : null;
				int playerID = (jsPlayer.has("id")) ? jsPlayer.get("id").getAsInt() : -1;
				if(color != null)
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
		//		StringReader strReader = new StringReader(json);
		//		JsonReader reader = new JsonReader(strReader);
		//		reader.setLenient(true);
		JsonObject jsonObj = (JsonObject) parser.parse(json);
		//JsonObject jsonObj = (JsonObject) parser.parse(reader);

		GameModel gameModel = new GameModel();

		DevCardList deck = makeDeck((JsonObject)jsonObj.get("deck"));
		gameModel.setDeck(deck);

		ResourceList bank = getResourceList((JsonObject)jsonObj.get("bank"));
		gameModel.setBank(bank);

		MessageList chat = makeMessageList((JsonObject)jsonObj.get("chat"));
		gameModel.setChat(chat);

		MessageList log = makeMessageList((JsonObject)jsonObj.get("log"));
		gameModel.setLog(log);

		GameMap map = makeMap(jsonObj.getAsJsonObject("map"));
		gameModel.setMap(map);

		Player[] players =  makePlayers(jsonObj.getAsJsonArray("players"));
		gameModel.setPlayers(players);

		if (jsonObj.has("tradeOffer")) {
			TradeOffer tradeOffer = makeTradeOffer((JsonObject)jsonObj.get("tradeOffer"));
			gameModel.setTradeOffer(tradeOffer);
		}
		else
		{
			gameModel.setTradeOffer(null);
		}

		TurnTracker turnTracker = makeTurnTracker((JsonObject)jsonObj.get("turnTracker"));
		gameModel.setTurnTracker(turnTracker);

		int version = jsonObj.get("version").getAsInt();
		gameModel.setVersion(version);

		int winner = jsonObj.get("winner").getAsInt();
		gameModel.setWinner(winner);

		return gameModel;
	}
	public DevCardList makeDeck(JsonObject jsDeck){
		DevCardList deck = new DevCardList();
		deck.setYearOfPlenty(jsDeck.get("yearOfPlenty").getAsInt());
		deck.setMonopoly(jsDeck.get("monopoly").getAsInt());
		deck.setSoldier(jsDeck.get("soldier").getAsInt());
		deck.setRoadBuilding(jsDeck.get("roadBuilding").getAsInt());
		deck.setMonument(jsDeck.get("monument").getAsInt());
		return deck;
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

		Hex[] hexes = makeHexes(jsMap.getAsJsonArray("hexes"));
		map.setHexes(hexes);

		Port[] ports = makePorts(jsMap.getAsJsonArray("ports"));
		map.setPorts(ports);

		EdgeValue[] roads = makeRoads(jsMap.getAsJsonArray("roads"));
		map.setRoads(roads);

		VertexObject[] settlements = makeSettlements(jsMap.getAsJsonArray("settlements"));
		map.setSettlements(settlements);

		VertexObject[] cities = makeCities(jsMap.getAsJsonArray("cities"));
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

			String resource = "None";
			int number = 0;
			if (temp.has("resource")) {
				resource = temp.get("resource").toString().replace("\"", "");
				number = temp.get("number").getAsInt();
			}
			Hex hex = new Hex(new HexLocation(x,y), Translator.getResourceType(resource), number);
			hexes.add(hex);
		}
		return hexes.toArray(new Hex[hexes.size()]);
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
			EdgeDirection edgeDirection = Translator.getEdgeDirection(direction);

			String resource = "None";
			if (temp.has("resource")) {
				resource = temp.get("resource").getAsString();
			}
			ResourceType resourceType = Translator.getResourceType(resource);
			int ratio = temp.get("ratio").getAsInt();

			Port port = new Port(resourceType, hex, edgeDirection, ratio);
			ports.add(port);
		}
		return ports.toArray(new Port[ports.size()]);
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
			EdgeDirection edgeDirection = Translator.getEdgeDirection(direction);

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
							Translator.getVertexDirection(direction));
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
					new HexLocation(x,y), Translator.getVertexDirection(direction));

			int owner = temp.get("owner").getAsInt();
			VertexObject vertexObject = new VertexObject(owner, vertexLocation);
			cities.add(vertexObject);
		}
		return cities.toArray(new VertexObject[cities.size()]);
	}
	public Player[] makePlayers(JsonArray jsPlayers){
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < jsPlayers.size(); i++){
			if (jsPlayers.get(i).isJsonNull()) {
				players.add(null);
				continue;
			}
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

			Player playerObj = new Player(cities,  Translator.getCatanColor(color), discarded, monuments,
					name, newDevCards, oldDevCards,
					playerIndex, playedDevCard, playerID,
					resources, roads, settlements, soldiers,
					victoryPoints);
			players.add(playerObj);
		}
		return players.toArray(new Player[players.size()]);
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


	//**********************************************************************************************************************************
	//**********************************************************************************************************************************
	//************************             *********************************************************************************************
	//************************MODEL TO JSON*********************************************************************************************
	//************************             *********************************************************************************************
	//**********************************************************************************************************************************
	//**********************************************************************************************************************************

	public String modelToJson(ServerGameModel model) {

		//Extracts all of the different parts from the model
		DevCardList deck = model.getDeck();
		ServerGameMap map = model.getServerMap();
		ServerPlayer[] players = model.getServerPlayers();
		MessageList log = model.getLog();
		MessageList chat = model.getChat();
		ResourceList bank = model.getBank();
		ServerTurnTracker turnTracker = model.getServerTurnTracker();
		TradeOffer offer = model.getTradeOffer();
		int winner = model.getWinner();
		int version = model.getVersion();


		//Creates the inner objects of the Json from the different 
		//parts of the model extracted above
		JsonObject innerDeck = this.makeJsonDevCardListObject(deck);
		JsonObject innerMap = this.makeJsonGameMapObject(map);
		JsonArray innerPlayers = this.makeJsonPlayersObject(players);
		JsonObject innerLog = this.makeJsonMessageListObject(log);
		JsonObject innerChat = this.makeJsonMessageListObject(chat);
		JsonObject innerBank = this.makeJsonResourceListObject(bank);
		JsonObject innerTurnTracker = this.makeJsonTurnTrackerObject(turnTracker);
		JsonObject innerTradeOffer = null;
		if(offer!=null){
			innerTradeOffer = this.makeJsonTradeOfferObject(offer);
		}

		//The inner objects are inserted in a "Shell" which is the structure
		//that holds the entire model
		JsonObject shell = new JsonObject();
		shell.add("deck", innerDeck);
		shell.add("map", innerMap);
		shell.add("players", innerPlayers);
		shell.add("log", innerLog);
		shell.add("chat", innerChat);
		shell.add("bank", innerBank);
		shell.add("turnTracker", innerTurnTracker);
		shell.addProperty("winner", winner);
		shell.addProperty("version", version);
		if(offer!=null){
			shell.add("tradeOffer", innerTradeOffer);
		}

		return shell.toString();
	}

	public JsonObject makeJsonDevCardListObject(DevCardList list) {
		JsonObject jList = new JsonObject();
		jList.addProperty("yearOfPlenty", list.getYearOfPlenty());
		jList.addProperty("monopoly", list.getMonopoly());
		jList.addProperty("soldier", list.getSoldier());
		jList.addProperty("roadBuilding", list.getRoadBuilding());
		jList.addProperty("monument", list.getMonument());

		return jList;
	}

	private JsonObject makeJsonGameMapObject(ServerGameMap map) {
		JsonObject jMap = new JsonObject();

		jMap.add("hexes", makeJsonHexesObject(map.getHexes()));
		jMap.add("roads", makeJsonEdgeValueObject(map.getRoads()));
		jMap.add("cities", makeJsonVertexObject(map.getCities()));
		jMap.add("settlements", makeJsonVertexObject(map.getSettlements()));
		jMap.addProperty("radius", map.getRadius());
		jMap.add("ports", makeJsonPortObject(map.getPorts()));
		jMap.add("robber", makeJsonHexLocationObject(map.getRobber()));

		return jMap;	
	}

	private JsonArray makeJsonHexesObject(Hex[] hexes) {
		JsonArray jHexes = new JsonArray();


		//All of the other hexes
		for(int i = 0; i < hexes.length; i++) {
			JsonObject jHex = new JsonObject();

			//This checks for the Desert 
			if(hexes[i].getResource() == ResourceType.NONE) {
				jHex.add("location", makeJsonHexLocationObject(hexes[i].getLocation()));
			}
			else{
				jHex.addProperty("resource", hexes[i].getResource().toString().toLowerCase());
				jHex.add("location", makeJsonHexLocationObject(hexes[i].getLocation()));
				jHex.addProperty("number", hexes[i].getNumber());
			}

			jHexes.add(jHex);
		}
		return jHexes;
	}

	private JsonArray makeJsonPortObject(Port[] ports) {
		JsonArray jPorts = new JsonArray();

		for(int i = 0; i < ports.length; i++) {
			JsonObject jPort = new JsonObject();

			jPort.addProperty("ratio", ports[i].getRatio());
			if(ports[i].getResource() != ResourceType.NONE){
				jPort.addProperty("resource", ports[i].getResource().toString().toLowerCase());
			}
			jPort.addProperty("direction", MirrorEdgeDirection.getMirrorEdge(ports[i].getDirection()).toString());      
			jPort.add("location", makeJsonHexLocationObject(ports[i].getLocation()));


			jPorts.add(jPort);
		}


		return jPorts;
	}

	private JsonObject makeJsonHexLocationObject(HexLocation hex) {
		JsonObject jHex = new JsonObject();

		jHex.addProperty("x", hex.getX());
		jHex.addProperty("y", hex.getY());

		return jHex;
	}

	private JsonArray makeJsonEdgeValueObject(EdgeValue[] roads) {
		JsonArray jRoads = new JsonArray();

		for(int i = 0; i < roads.length; i++) {
			JsonObject jRoad = new JsonObject();

			jRoad.addProperty("owner", roads[i].getOwner());
			jRoad.add("location", makeJsonEdgeLocationObject(roads[i].getLocation()));

			jRoads.add(jRoad);
		}

		return jRoads;
	}

	private JsonObject makeJsonEdgeLocationObject(EdgeLocation location) {
		JsonObject jLocation = new JsonObject();

		jLocation.addProperty("direction", MirrorEdgeDirection.getMirrorEdge(location.getDir()).toString());
		jLocation.addProperty("x", location.getHexLoc().getX());
		jLocation.addProperty("y", location.getHexLoc().getY());

		return jLocation;
	}

	private JsonArray makeJsonVertexObject(VertexObject[] array) {
		JsonArray jArray = new JsonArray();

		for(int i = 0; i < array.length; i++) {
			JsonObject jObject = new JsonObject();
			if(array[i]!=null){
				jObject.addProperty("owner", array[i].getOwner());
				jObject.add("location", makeJsonVertexLocationObject(array[i].getLocation()));
				jArray.add(jObject);
			}

			
		}


		return jArray;
	}

	private JsonObject makeJsonVertexLocationObject(VertexLocation location) {
		JsonObject jLocation = new JsonObject();

		jLocation.addProperty("direction", MirrorVertexDirection.getMirrorEdge(location.getDir()).toString());
		jLocation.addProperty("x", location.getHexLoc().getX());
		jLocation.addProperty("y", location.getHexLoc().getY());

		return jLocation;
	}


	private JsonArray makeJsonPlayersObject(ServerPlayer[] players) {
		JsonArray jArray = new JsonArray();
		int i = 0;
		for(int x = 0; x < players.length; x++)
			i = players[x] != null ? i+1 : i;
		
		for(int x = 0; x < i; x++) {
			jArray.add(makeJsonPlayerObject(players[x]));
		}
		return jArray;
	}

	private JsonObject makeJsonPlayerObject(ServerPlayer player) {
		JsonObject jPlayer = new JsonObject();

		jPlayer.add("resources", makeJsonResourceListObject(player.getResources()));
		jPlayer.add("oldDevCards", makeJsonDevCardListObject(player.getOldDevCards()));
		jPlayer.add("newDevCards", makeJsonDevCardListObject(player.getNewDevCards()));

		jPlayer.addProperty("roads", player.getRoads());
		jPlayer.addProperty("cities", player.getCities());
		jPlayer.addProperty("settlements", player.getSettlements());
		jPlayer.addProperty("soldiers", player.getSoldiers());
		jPlayer.addProperty("victoryPoints", player.getVictoryPoints());
		jPlayer.addProperty("monuments", player.getMonuments());
		jPlayer.addProperty("playedDevCard", player.getPlayedDevCard());
		jPlayer.addProperty("discarded", player.getDiscarded());
		jPlayer.addProperty("playerID", player.getPlayerID());
		jPlayer.addProperty("playerIndex", player.getPlayerIndex());
		jPlayer.addProperty("name", player.getName());
		jPlayer.addProperty("color", player.getColor().toString());

		return jPlayer;
	}



	private JsonObject makeJsonMessageListObject(MessageList list) {
		JsonObject jList = new JsonObject();


		JsonArray jArray = new JsonArray();
		if(list.getLines() != null){
			for(int i = 0; i < list.getLines().length; i++) {
				JsonObject line = new JsonObject();
	
				line.addProperty("source", list.getLines()[i].getSource());
				line.addProperty("message", list.getLines()[i].getMessage());
	
				jArray.add(line);
			}
		}

		jList.add("lines", jArray);
		return jList;
	}

	private JsonObject makeJsonResourceListObject(ResourceList list) {
		JsonObject jList = new JsonObject();

		jList.addProperty("brick", list.getBrick());
		jList.addProperty("wood", list.getWood());
		jList.addProperty("sheep", list.getSheep());
		jList.addProperty("wheat", list.getWheat());
		jList.addProperty("ore", list.getOre());

		return jList;
	}

	private JsonObject makeJsonTurnTrackerObject(TurnTracker turn) {
		JsonObject jList = new JsonObject();

		jList.addProperty("status", turn.getStatus());
		jList.addProperty("currentTurn", turn.getCurrentTurn());
		jList.addProperty("longestRoad", turn.getLongestRoad());
		jList.addProperty("largestArmy", turn.getlargestArmy());

		return jList;
	}
	
	private JsonObject makeJsonTradeOfferObject(TradeOffer offer) {
		JsonObject jList = new JsonObject();

		jList.addProperty("sender", offer.getSender());
		jList.addProperty("receiver", offer.getReciever());
		jList.add("offer", makeJsonResourceListObject(offer.getOffer()));

		return jList;
	}


	//**************************************************************************
	//**************TESTING METHOD************************************
	public static String translate() {
		JsonTranslator t = new JsonTranslator();

		TurnTracker turn = new TurnTracker();
		turn.setStatus("Playing");
		turn.setCurrentTurn(0);
		turn.setLongestRoad(-1);
		turn.setLargestArmy(-1);


		MessageLine[] lines = { new MessageLine("manuel built a road", "manuel"),
				new MessageLine("manuel buitl a settlement", "manuel"),
				new MessageLine("manuel's turn just ended", "manuel"),
				new MessageLine("Ken built a road", "Ken"),
				new MessageLine("Ken built a settlement", "Ken"),
				new MessageLine("Ken's turn just ended", "Ken"),
				new MessageLine("Steve built a road", "Steve"),
				new MessageLine("Steve built a settlement", "Steve"),
				new MessageLine("Steve's turn just ended", "Steve"),
				new MessageLine("Squall built a road", "Squal"),
				new MessageLine("Squall built a settlement", "Squall"),
				new MessageLine("Squall's turn just ended", "Squall")
		};
		MessageList log = new MessageList(lines);

		MessageList chat = new MessageList(new MessageLine[0]);


		ServerPlayer player1 = new ServerPlayer();
		player1.setResources(new ResourceList(2,0,1,0,1));
		player1.setOldDevCards(new DevCardList(0,0,0,0,0));
		player1.setNewDevCards(new DevCardList(0,0,0,0,0));
		player1.setRoads(13);
		player1.setCities(4);
		player1.setSettlements(3);
		player1.setSoldiers(0);
		player1.setVictoryPoints(2);
		player1.setMonuments(0);
		player1.setPlayedDevCard(false);
		player1.setDiscarded(false);
		player1.setPlayerID(12);
		player1.setPlayerIndex(0);
		player1.setName("manuel");
		player1.setColor(CatanColor.purple);

		ServerPlayer player2 = new ServerPlayer();
		player2.setResources(new ResourceList(0,1,1,2,0));
		player2.setOldDevCards(new DevCardList(0,0,0,0,0));
		player2.setNewDevCards(new DevCardList(0,0,0,0,0));
		player2.setRoads(13);
		player2.setCities(4);
		player2.setSettlements(3);
		player2.setSoldiers(0);
		player2.setVictoryPoints(2);
		player2.setMonuments(0);
		player2.setPlayedDevCard(false);
		player2.setDiscarded(false);
		player2.setPlayerID(-2);
		player2.setPlayerIndex(1);
		player2.setName("Ken");
		player2.setColor(CatanColor.orange);

		ServerPlayer[] players = {player1, player2};


		ServerGameMap map = new ServerGameMap();

		Hex[] hexes = { new Hex(new HexLocation(0,-2),ResourceType.NONE,0),
				new Hex(new HexLocation(1,-2), ResourceType.BRICK, 4),
				new Hex(new HexLocation(2,-2), ResourceType.WOOD,11),
				new Hex(new HexLocation(-1,-1), ResourceType.BRICK,8),
				new Hex(new HexLocation(0,-1), ResourceType.WOOD, 3)
		};
		map.setHexes(hexes);

		EdgeValue[] roads = { new EdgeValue(0, new EdgeLocation(new HexLocation(-1,-1), EdgeDirection.South)),
				new EdgeValue(1, new EdgeLocation(new HexLocation(0,-1), EdgeDirection.SouthEast)),
				new EdgeValue(2, new EdgeLocation(new HexLocation(2,-1), EdgeDirection.SouthWest)),
				new EdgeValue(2, new EdgeLocation(new HexLocation(-1,1), EdgeDirection.SouthWest)),
				new EdgeValue(1, new EdgeLocation(new HexLocation(0,1), EdgeDirection.South))
		};
		map.setRoads(roads);

		VertexObject[] cities = new VertexObject[0];
		map.setCities(cities);

		VertexObject[] settlements = { new VertexObject(1, new VertexLocation(new HexLocation(1,-2), VertexDirection.SouthWest)),
				new VertexObject(0, new VertexLocation(new HexLocation(-1,0), VertexDirection.SouthEast)),
				new VertexObject(3, new VertexLocation(new HexLocation(-1,1), VertexDirection.SouthEast)),
				new VertexObject(2, new VertexLocation(new HexLocation(-2,1), VertexDirection.SouthEast)),
				new VertexObject(2, new VertexLocation(new HexLocation(1,-1), VertexDirection.SouthEast))
		};
		map.setSettlements(settlements);
		map.setRadius(3);

		Port[] ports = { new Port(ResourceType.BRICK,new HexLocation(-2,3), EdgeDirection.NorthEast, 2),
				new Port(ResourceType.NONE, new HexLocation(-3,0), EdgeDirection.SouthEast,3),
				new Port(ResourceType.WOOD, new HexLocation(-3,2), EdgeDirection.NorthEast,2),
				new Port(ResourceType.SHEEP, new HexLocation(3,-1), EdgeDirection.NorthWest,2),
				new Port(ResourceType.NONE, new HexLocation(2,1), EdgeDirection.NorthWest,3)				
		};
		map.setPorts(ports);


		map.setRobber(new HexLocation(0,-2));


		JsonObject shell = new JsonObject();
		shell.add("deck", t.makeJsonDevCardListObject(new DevCardList(2,5,2,14,2)));
		shell.add("map", t.makeJsonGameMapObject(map));
		shell.add("players", t.makeJsonPlayersObject(players));
		shell.add("log", t.makeJsonMessageListObject(log));
		shell.add("chat", t.makeJsonMessageListObject(chat));
		shell.add("bank", t.makeJsonResourceListObject(new ResourceList(22,23,21,20,22)));
		shell.add("turnTracker", t.makeJsonTurnTrackerObject(turn));
		shell.addProperty("winner", -1);
		shell.addProperty("version", 25);

		System.out.println(shell.toString());


		return null;
	}









}