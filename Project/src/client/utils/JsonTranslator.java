package client.utils;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import shared.definitions.*;

public class JsonTranslator {
	public JsonTranslator(){}
	
	public Object makeObject(String json){
		JsonParser parser = new JsonParser();
		JsonObject jsonObj = (JsonObject) parser.parse(json);
		ResourceList bank = getResourceList((JsonObject)jsonObj.get("bank"));
		makeChat((JsonObject)jsonObj.get("chat"));
		makeLog((JsonObject)jsonObj.get("log"));
		makeMap((JsonObject)jsonObj.get("map"));
		makePlayers(jsonObj.get("players").getAsJsonArray());
		makeTradeOffer((JsonObject)jsonObj.get("tradeOffer"));
		makeTurnTracker((JsonObject)jsonObj.get("turnTracker"));
		int version = jsonObj.get("version").getAsInt();
		int winner = jsonObj.get("winner").getAsInt();
		return new Object();
	}
	public void makeChat(JsonObject jsChat){
		System.out.println("************** CHAT **************");
		//MessageList messageList = new MessageList();
		JsonArray messages = jsChat.get("lines").getAsJsonArray();
		for(int i = 0; i < messages.size(); i++){
			JsonObject temp = (JsonObject)messages.get(i);
			System.out.println("message: " + temp.get("message").getAsString());
			System.out.println("source: " + temp.get("source").getAsString());
		}
	}
	public void makeLog(JsonObject jsLog){
	//	MessageList messageList = new MessageList();
		System.out.println("************** LOG **************");
		JsonArray messages = jsLog.get("lines").getAsJsonArray();
		for(int i = 0; i < messages.size(); i++){
			JsonObject temp = (JsonObject)messages.get(i);
			System.out.println("message: " + temp.get("message").getAsString());
			System.out.println("source: " + temp.get("source").getAsString());
		}
	}
	
	public void makeMap(JsonObject jsMap){
		System.out.println("************** MAP **************");
		int radius = jsMap.get("radius").getAsInt();
		System.out.println("radius: " + radius);
		JsonObject jsRobber = (JsonObject)jsMap.get("robber");
		int rX = jsRobber.get("x").getAsInt();
		int rY = jsRobber.get("y").getAsInt();
		System.out.println("robberX: " + rX);
		System.out.println("robberY: " + rY);
		makeHexes(jsMap.get("hexes").getAsJsonArray());
		makePorts(jsMap.get("ports").getAsJsonArray());
		makeRoads(jsMap.get("roads").getAsJsonArray());
		makeSettlements(jsMap.get("settlements").getAsJsonArray());
		makeCities(jsMap.get("cities").getAsJsonArray());
		
	}
	public void makeHexes(JsonArray jsHexes){
		System.out.println("**** HEXES ****");
		for(int i = 0; i < jsHexes.size(); i++){
			JsonObject temp = (JsonObject)jsHexes.get(i);
			JsonObject location = (JsonObject)temp.get("location");
			int x = location.get("x").getAsInt();
			int y = location.get("y").getAsInt();
			System.out.println("x: " + x);
			System.out.println("y: " + y);
			
			String resource = temp.get("resource").getAsString();
			int number = temp.get("number").getAsInt();
			System.out.println("resource: " + resource);
			System.out.println("number: " + number);
		}
	}
	public void makePorts(JsonArray jsPorts){
		System.out.println("**** PORTS ****");
		for(int i = 0; i < jsPorts.size(); i++){
			JsonObject temp = (JsonObject)jsPorts.get(i);
			JsonObject location = (JsonObject)temp.get("location");
			int x = location.get("x").getAsInt();
			int y = location.get("y").getAsInt();
			System.out.println("x: " + x);
			System.out.println("y: " + y);
			
			String direction = temp.get("direction").getAsString();
			String resource = temp.get("resource").getAsString();
			String ratio = temp.get("ratio").getAsString();
			
			System.out.println("direction: " + direction);
			System.out.println("resource: " + resource);
			System.out.println("ratio: " + ratio);
		}
	}
	public void makeRoads(JsonArray jsRoads){
		System.out.println("**** ROADS ****");
		for(int i = 0; i < jsRoads.size(); i++){
			JsonObject temp = (JsonObject)jsRoads.get(i);
			JsonObject location = (JsonObject)temp.get("location");
			int x = location.get("x").getAsInt();
			int y = location.get("y").getAsInt();
			System.out.println("x: " + x);
			System.out.println("y: " + y);
			String direction = location.get("direction").getAsString();
			System.out.println("direction: " + direction);
			
			
			int owner = temp.get("owner").getAsInt();
			System.out.println("owner: " + owner);			
		}
	}
	public void makeSettlements(JsonArray jsSettlements){
		System.out.println("**** Settlements ****");
		for(int i = 0; i < jsSettlements.size(); i++){
			JsonObject temp = (JsonObject)jsSettlements.get(i);
			JsonObject location = (JsonObject)temp.get("location");
			int x = location.get("x").getAsInt();
			int y = location.get("y").getAsInt();
			System.out.println("x: " + x);
			System.out.println("y: " + y);
			String direction = location.get("direction").getAsString();
			System.out.println("direction: " + direction);
			int owner = temp.get("owner").getAsInt();
			System.out.println("owner: " + owner);
		}
	}
	public void makeCities(JsonArray jsCities){
		System.out.println("**** Cities ****");
		for(int i = 0; i < jsCities.size(); i++){
			JsonObject temp = (JsonObject)jsCities.get(i);
			JsonObject location = (JsonObject)temp.get("location");
			int x = location.get("x").getAsInt();
			int y = location.get("y").getAsInt();
			System.out.println("x: " + x);
			System.out.println("y: " + y);
			String direction = location.get("direction").getAsString();
			System.out.println("direction: " + direction);
			int owner = temp.get("owner").getAsInt();
			System.out.println("owner: " + owner);
		}
	}
	public void makePlayers(JsonArray jsPlayers){
		System.out.println("**** PLAYERS ****");
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
	public void makeTradeOffer(JsonObject jsTradeOffer){
		int sender = jsTradeOffer.get("sender").getAsInt();
		int receiver = jsTradeOffer.get("receiver").getAsInt();
		ResourceList offer = getResourceList((JsonObject)jsTradeOffer.get("offer"));
		
	}
	public void makeTurnTracker(JsonObject jsTurnTracker){
		int currentTurn = jsTurnTracker.get("currentTurn").getAsInt();
		String status = jsTurnTracker.get("status").getAsString();
		int longestRoad = jsTurnTracker.get("longestRoad").getAsInt();
		int largestArmy = jsTurnTracker.get("largestArmy").getAsInt();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
