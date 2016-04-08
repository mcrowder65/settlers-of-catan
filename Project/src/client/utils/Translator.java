package client.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import client.data.GameInfo;
import server.util.ServerGameModel;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.VertexDirection;

/**
 * Provides static methods for converting to and from 
 * JSON and Java
 */
public class Translator {

	public Translator(){}
	private static JsonTranslator jsonTranslator = new JsonTranslator();
	public static ServerGameModel jsonToServerGameModel(String json){
		return jsonTranslator.jsonToServerGameModel(json);
	}
	/**
	 * Converts the JSON string to its java object representation
	 * @param json The JSON string
	 * @return Returns an object of the given type, constructed from the json.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToGameModel(String json) throws IllegalArgumentException  {
		return (T) jsonTranslator.jsonToGameModel(json);
	}
	public static Object jsonToObject(String json, Object object) throws IllegalArgumentException {
		return jsonTranslator.jsonToObject(json,  object);
	}
	public static Object jsonToObject(String json, @SuppressWarnings("rawtypes") Class klass) throws IllegalArgumentException {
		return jsonTranslator.jsonToObject(json,  klass);
	}
	
	public static List<GameInfo> makeListOfGames(String json){
		return jsonTranslator.makeListOfGames(json);
	}
	
	public static HashMap<String,String> makeKeyValuePairs(String json) {
		return jsonTranslator.makeKeyValuePairs(json);
	}
	
	/**
	 * Converts the object to its JSON representation.
	 * @param object
	 * The object to convert
	 * @return
	 * Returns the json string of the given object
	 */
	public static String objectToJson(Object object) throws IllegalArgumentException  {
		return new Gson().toJson(object);
	}
	public static JsonTranslator getJsonTranslator() {
		return jsonTranslator;
	}
	public static void setJsonTranslator(JsonTranslator jsonTranslator) {
		Translator.jsonTranslator = jsonTranslator;
	}
	public static String modelToJson(ServerGameModel model) {
        return Translator.jsonTranslator.modelToJson(model);
    }
	public static ResourceType getResourceType(String resource){
		//NONE, WOOD, BRICK, SHEEP, WHEAT, ORE
		int switchType = 5; //SHEEP, sheep
		if(resource.equals("sheep") || resource.equals("SHEEP"))
			switchType = 0;
		if(resource.equals("wood") || resource.equals("WOOD"))
			switchType = 1;
		else if(resource.equals("brick") || resource.equals("BRICK"))
			switchType = 2;
		else if (resource.equals("wheat") || resource.equals("WHEAT"))
			switchType = 3;
		else if(resource.equals("ore") || resource.equals("ORE"))
			switchType = 4;
		switch (switchType){
			case 0:
				return ResourceType.SHEEP;
			case 1:
				return ResourceType.WOOD;
			case 2:
				return ResourceType.BRICK;
			case 3:
				return ResourceType.WHEAT;
			case 4:
				return ResourceType.ORE;
			default:
				return ResourceType.NONE;
		}
	}
	public static EdgeDirection getEdgeDirection(String direction){
		//NorthWest, North, NorthEast, SouthEast, South, SouthWest;
		switch (direction){
			default:
				return null;
			case "N":
				return EdgeDirection.North;
			case "NE":
				return EdgeDirection.NorthEast;
			case "SE":
				return EdgeDirection.SouthEast;
			case "S":
				return EdgeDirection.South;
			case "SW":
				return EdgeDirection.SouthWest;
			case "NW":
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
			case "NorthWest":
				return EdgeDirection.NorthWest;
		}
	}
	public static VertexDirection getVertexDirection(String direction){
		//West, NorthWest, NorthEast, East, SouthEast, SouthWest;
		switch (direction){
			default:
				return null;
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
			case "West":
				return VertexDirection.West;
			case "NW":
				return VertexDirection.NorthWest;
			case "NE":
				return VertexDirection.NorthEast;
			case "E":
				return VertexDirection.East;
			case "SE":
				return VertexDirection.SouthEast;
			case "SW":
				return VertexDirection.SouthWest;	
			case "W":
				return VertexDirection.West;
		}	
		
	}
	public static CatanColor getCatanColor(String color){
		//RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BROWN;
		switch(color){
			default:
				return null;
			case "orange":
				return CatanColor.orange;
			case "yellow":
				return CatanColor.yellow;
			case "blue":
				return CatanColor.blue;
			case "green":
				return CatanColor.green;
			case "purple":
				return CatanColor.purple;
			case "puce":
				return CatanColor.puce;
			case "white":
				return CatanColor.white;
			case "brown":
				return CatanColor.brown;
			case "red":
				return CatanColor.red;
			case "ORANGE":
				return CatanColor.orange;
			case "YELLOW":
				return CatanColor.yellow;
			case "BLUE":
				return CatanColor.blue;
			case "GREEN":
				return CatanColor.green;
			case "PURPLE":
				return CatanColor.purple;
			case "PUCE":
				return CatanColor.puce;
			case "WHITE":
				return CatanColor.white;
			case "BROWN":
				return CatanColor.brown;
			case "RED":
				return CatanColor.red;
		}	
	}
	
	/**
	 * This method parses the cookies and retrieves the game Id
	 * @param cookie - String
	 * @return gameID - int
	 */
	public static int getGameId(String cookie) {
		cookie = decodeCookie(cookie);
		String chopped = cookie.substring(1, cookie.length() - 1);
		String[] cookies = chopped.split(";");
		for (int n = 0; n < cookies.length; n++) {
			if (cookies[n].trim().substring(0, 10).equals("catan.game")) {
				int index = cookies[n].lastIndexOf("=") + 1;
				String temp = cookies[n].substring(index);
				if (temp == null) return -1;
				return Integer.parseInt(temp);
			}
		}
		return -1;
		
		/*
		int index = cookie.lastIndexOf(".game=");
		int lastIndex = cookie.indexOf("]", index);
		if(index + 5 < lastIndex)
			lastIndex = cookie.indexOf(";");
		String temp = index != -1  && lastIndex != -1 ? cookie.substring(index+6, lastIndex) : null;
		if(temp == null) return -1;
		return !temp.equals("null") ? Integer.parseInt(temp) : -1;
		*/
	}
	
	/**
	 * This method parses the cookies and retrieves the player id
	 * @param cookie String
	 * @return playerID - int
	 */
	public static int getPlayerId(String cookie) {
		
		cookie = decodeCookie(cookie);
		int index = cookie.lastIndexOf("ID\":");
		int lastIndex = cookie.lastIndexOf("}");
		String number = index == -1 || lastIndex == -1 ? null : cookie.substring(index+4, lastIndex);
		return number == null ? -1 : Integer.parseInt(number);
	}
	public static String getPlayerName(String cookie){
		cookie = decodeCookie(cookie);
		int index = cookie.indexOf("e\":\"") + 4; //e":"Sam" -> starts at S
		int lastIndex = cookie.indexOf("\"", index); //finds the closing quote
		
		return index == -1 || lastIndex == -1 ?  null : cookie.substring(index, lastIndex);
	}
	public static String getPlayerPassword(String cookie){
		cookie = decodeCookie(cookie);
		int index = cookie.indexOf("d\":\"") + 4; //d":"sam" -> starts at s
		int lastIndex = cookie.indexOf("\"", index); //finds the closing quote
		return index == -1 || lastIndex == -1 ? null : cookie.substring(index, lastIndex);
	}
	private static String decodeCookie(String cookie){
		try {
			cookie = URLDecoder.decode(cookie, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return cookie;
	}
}