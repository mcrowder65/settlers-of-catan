package client.utils;

import java.util.List;

import com.google.gson.Gson;

import client.data.GameInfo;
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
	/**
	 * Converts the JSON string to its java object representation
	 * @param json
	 * The JSON string
	 * @return
	 * Returns an object of the given type, constructed from the json.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObject(String json) throws IllegalArgumentException  {
		return (T) jsonTranslator.makeObject(json);
	}
	public static Object makeGenericObject(String json, Object object) throws IllegalArgumentException {
		return jsonTranslator.makeGenericObject(json,  object);
	}
	
	public static List<GameInfo> makeListOfGames(String json){
		return jsonTranslator.makeListOfGames(json);
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
}
