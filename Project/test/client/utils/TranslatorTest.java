package client.utils;

import static org.junit.Assert.fail;

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
import shared.locations.*;

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
//	@Test 
	public void testObject2Json(){

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
		Object temp = Translator.jsonToObject(append.toString());
		String json = Translator.objectToJson(temp);
		System.out.println(json);
	}
	@Test
	public void testJsonToGameModel() throws FileNotFoundException{	 
		BufferedReader br;

		String line = new String();
		StringBuilder append = new StringBuilder();
		try{
			br = new BufferedReader(new FileReader("gameModel.txt"));
			while((line = br.readLine()) != null){
				append.append(line);
			}
			br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		GameModel gameModel = Translator.jsonToObject(append.toString());
		
		DevCardList deck = new DevCardList(2, 2, 2, 14, 2); //monopoly, monument, roadBuilding, soldier, yearOfPlenty
		if(deck.isEqual(gameModel.getDeck()) == true) fail();
		deck = new DevCardList(2, 5, 2, 14, 2);
		if(deck.isEqual(gameModel.getDeck()) == false) fail();
		Port[] ports = new Port[9];
		setPorts(ports);
		for(int i = 0; i < ports.length; i++){
			if(ports[i] == null)
				break;
			if(ports[i].equals(gameModel.getMap().getPorts()[i]) == false)fail();
			
		}
	}
	public void setPorts(Port[] ports){
		//new Port(ResourceType, HexLocation, EdgeDirection, int ratio)
		//ResourceType -> Translator.getResourceType("TYPE");
		//new HexLocation(int x, int y)
		//EdgeDirection -> Translator.getEdgeDirection("TYPE");
		//ports[0] = new Port()
		ports[0] = new Port(Translator.getResourceType("ORE"), new HexLocation(1, -3), Translator.getEdgeDirection("S"), 2);
		ports[1] = new Port(Translator.getResourceType("falskjdf"), new HexLocation(3,-3), Translator.getEdgeDirection("SW"), 3);
		ports[2] = new Port(Translator.getResourceType("lkj"), new HexLocation(2,1), Translator.getEdgeDirection("NW"), 3);
		ports[3] = new Port(Translator.getResourceType("brick"), new HexLocation(-2,3), Translator.getEdgeDirection("NE"), 2);
		ports[4] = new Port(Translator.getResourceType("wheat"), new HexLocation(-1,-2), Translator.getEdgeDirection("S"), 2);
	}
	public void getResourceType(String type){
		
	}

}
