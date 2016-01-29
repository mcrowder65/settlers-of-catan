package client.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.definitions.*;

public class TranslatorTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test public void testObject2Json(){

		ResourceList rS = new ResourceList(5, 2, 3, 4, 5);
		//5 brick, 2 ore, 3 sheep, 4 wheat, 5 wood
		TradeOffer temp = new TradeOffer(1, 2, rS);
		String json = new Translator().objectToJson(temp);
		//System.out.println("json: " + json);
	}
	@Test
	public void testJson2Object() throws FileNotFoundException{
//		String json = 	"{"
//				+ "\"sender\": \"1\","
//				+ "\"receiver\": \"2\","
//				+ "\"offer\": {"
//				+ "\"brick\": \"5\","
//				+ "\"ore\": \"1\","
//				+ "\"sheep\": \"3\","
//				+ "\"wheat\": \"4\","
//				+ "\"wood\": \"5\"}}";
//		json = "{"
//			+ "\"currentTurn\": \"3\","
//			+ "\"status\": \"Hello world!\","
//			+ "\"longestRoad\": \"4\","
//			+ "\"largestArmy\": \"2\"}";
//	 
		BufferedReader br;

		String line = new String();
		StringBuilder append = new StringBuilder();
		try{
			br = new BufferedReader(new FileReader("json.txt"));
			while((line = br.readLine()) != null){
				append.append(line);
			}
			br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(append.toString());
		Object temp = new Translator().jsonToObject(append.toString());
		//System.out.println(temp.toString());

	}

}



//String json = 
//+ "{"
//+ "\"bank\": {
//	 +"\"brick\": \"integer\","
//	 +"\"ore\": \"integer\","
//	 +"\"sheep\": \"integer\","
//	 +"\"wheat\": \"integer\","
//	 +"\"wood\": \"integer\""
//	 +"},"
//	 +"\"chat\": {"
//	 +"\"lines\": ["
//	 +"{"
//	 +"\"message\": \"string\","
//	 +"\"source\": \"string\""
//	 + "}"
//	 + "]"
//	 +"},"
//	 +"\"log\": {"
//	 +"\"lines\": ["
//	 + "{";
//		+ "\"message\": \"string\",
//	 "source": "string"
//	 }
//	 ]
//	 },
//	 "map": {
//	 "hexes": [
//	 {
//	 "location": {
//	 "x": "integer",
//	 "y": "integer"
//	 },
//	 "resource": "string",
//	 "number": "integer"
//	 }
//	 ],
//	 "ports": [
//	 {
//	 "resource": "string",
//	 "location": {
//	 "x": "integer",
//	 "y": "integer"
//	 },
//	 "direction": "string",
//	 "ratio": "integer"
//	 }
//	 ],
//	 "roads": [
//	 {
//	 "owner": "index",
//	 "location": {
//	 "x": "integer",
//	 "y": "integer",
//	 "direction": "string"
//	 }
//	 }
//	 ],
//	 "settlements": [
//	 {
//	 "owner": "index",
//	 "location": {
//	 "x": "integer",
//	 "y": "integer",
//	 "direction": "string"
//	 }
//	 }
//	 ],
//	 "cities": [
//	 {
//	 "owner": "index",
//	 "location": {
//	 "x": "integer",
//	 "y": "integer",
//	 "direction": "string"
//	 }
//	 }
//	 ],
//	 "radius": "integer",
//	 "robber": {
//	 "x": "integer",
//	 "y": "integer"
//	 }
//	 },
//	 "players": [
//	 {
//	 "cities": "number",
//	 "color": "string",
//	 "discarded": "boolean",
//	 "monuments": "number",
//	 "name": "string",
//	 "newDevCards": {
//	 "monopoly": "number",
//	 "monument": "number",
//	 "roadBuilding": "number",
//	 "soldier": "number",
//	 "yearOfPlenty": "number"
//	 },
//	 "oldDevCards": {
//	 "monopoly": "number",
//	 "monument": "number",
//	 "roadBuilding": "number",
//	 "soldier": "number",
//	 "yearOfPlenty": "number"
//	 },
//	 "playerIndex": "index",
//	 "playedDevCard": "boolean",
//	 "playerID": "integer",
//	 "resources": {
//	 "brick": "integer",
//	 "ore": "integer",
//	 "sheep": "integer",
//	 "wheat": "integer",
//	 "wood": "integer"
//	 },
//	 "roads": "number",
//	 "settlements": "integer",
//	 "soldiers": "integer",
//	 "victoryPoints": "integer"
//	 }
//	 ],
//	 "tradeOffer": {
//	 "sender": "integer",
//	 "receiver": "integer",
//	 "offer": {
//	 "brick": "integer",
//	 "ore": "integer",
//	 "sheep": "integer",
//	 "wheat": "integer",
//	 "wood": "integer"
//	 }
//	 },
//	 "turnTracker": {
//	 "currentTurn": "index",
//	 "status": "string",
//	 "longestRoad": "index",
//	 "largestArmy": "index"
//	 },
//	 "version": "index",
//	 "winner": "index"
//	}