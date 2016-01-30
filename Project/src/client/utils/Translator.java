package client.utils;

import java.io.BufferedReader;

import com.google.gson.Gson;


import shared.definitions.*;

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
	/**
	 * Converts the object to its JSON representation.
	 * @param object
	 * The object to convert
	 * @return
	 * Returns the json string of the given object
	 */
	public static String objectToJson(Object object) throws IllegalArgumentException  {
		return new Gson().toJson(jsonTranslator.makeMirrorObject((GameModel)object));
	}
}
