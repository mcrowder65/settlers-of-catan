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
		if(deck.equals(gameModel.getDeck()) == true) fail();
		deck = new DevCardList(2, 5, 2, 14, 2);
		if(deck.equals(gameModel.getDeck()) == false) fail();
		
		ResourceList bank = new ResourceList(24, 24, 24, 24, 24);
		if(gameModel.getBank().equals(bank) == false) fail();
		
		
		
	}
	public void makeHexes(Hex[] hexes){
		//HexLocation location, ResourceType resource, int number
		hexes[0] = new Hex(new HexLocation(0, -2), Translator.getResourceType("asdf"), -1);
		hexes[1] = new Hex(new HexLocation(1, -2), Translator.getResourceType("brick"), 4);
		hexes[2] = new Hex(new HexLocation(2, -2), Translator.getResourceType("wood"), 11);
		
		hexes[3] = new Hex(new HexLocation(-1, -1), Translator.getResourceType("brick"), 8);
		
		hexes[4] = new Hex(new HexLocation(0, -1), Translator.getResourceType("wood"), 3);
		
		hexes[5] = new Hex(new HexLocation(1, -1), Translator.getResourceType("ore"), 9);
		
		hexes[6] = new Hex(new HexLocation(2, -1), Translator.getResourceType("sheep"), 12);
		
		hexes[7] = new Hex(new HexLocation(-2, 0), Translator.getResourceType("ore"), 5);
		
		hexes[8] = new Hex(new HexLocation(-1, 0), Translator.getResourceType("sheep"), 10);
		
		hexes[9] = new Hex(new HexLocation(0, 0), Translator.getResourceType("wheat"), 11);
		
		hexes[10] = new Hex(new HexLocation(1, 0), Translator.getResourceType("brick"), 5);
		
		hexes[11] = new Hex(new HexLocation(2, 0), Translator.getResourceType("wheat"), 6);
		
		hexes[12] = new Hex(new HexLocation(-2, 1), Translator.getResourceType("wheat"), 2);
		
		hexes[13] = new Hex(new HexLocation(-1, 1), Translator.getResourceType("sheep"), 9);
		
		hexes[14] = new Hex(new HexLocation(0, 1), Translator.getResourceType("wood"), 4);
		
		hexes[15] = new Hex(new HexLocation(1, 1), Translator.getResourceType("sheep"), 10);
		
		hexes[16] = new Hex(new HexLocation(-2, 2), Translator.getResourceType("wood"), 6);
		
		hexes[17] = new Hex(new HexLocation(-1, 2), Translator.getResourceType("ore"), 3);
		
		hexes[18] = new Hex(new HexLocation(0, 2), Translator.getResourceType("wheat"), 8);
		
		
	}
	public void testMap(GameModel gameModel){
		Port[] ports = new Port[9];
		setPorts(ports);
		for(int i = 0; i < ports.length; i++){
			if(ports[i].equals(gameModel.getMap().getPorts()[i]) == false) 
				fail();
		}
		HexLocation robber = new HexLocation(0,-2);
		if(robber.equals(gameModel.getMap().getRobber()) == false) fail();
		
		Player[] players = new Player[4];
		setPlayers(players);
		for(int i = 0; i < players.length; i++){
			if(players[i] == null && i > 0)
				continue;
			if(players[i].equals(gameModel.getPlayers()[i]) == false)
				fail();
		}
		if(gameModel.getMap().getRoads().length != 0) fail();
		if(gameModel.getMap().getCities().length != 0) fail();
		if(gameModel.getMap().getSettlements().length != 0) fail();
		if(gameModel.getMap().getRadius() != 3) fail();
		if(gameModel.getLog().size() != 0) fail();
		if(gameModel.getChat().size() != 0) fail();
		
		Hex[] hexes = new Hex[19];
		for(int i = 0; i < hexes.length; i++){
			if(hexes[i].equals(gameModel.getMap().getHexes()[i]) == false) fail();
		}
	}
	public void setPlayers(Player[] players){
		players[0] = new Player("matt", Translator.getCatanColor("red"), 12, 0);
		players[0].setCities(4);
		players[0].setRoads(15);
		players[0].setSettlements(5);
		players[0].setSoldiers(0);
		players[0].setVictoryPoints(0);
		players[0].setMonuments(0);
		players[0].setPlayedDevCard(false);
		players[0].setDiscarded(false);
		players[0].setResources(new ResourceList(0, 0, 0, 0, 0));
		players[0].setOldDevCards(new DevCardList(0, 0, 0, 0, 0));
		players[0].setNewDevCards(new DevCardList(0, 0, 0, 0, 0));
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
		ports[5] = new Port(Translator.getResourceType("wood"), new HexLocation(-3,2), Translator.getEdgeDirection("NE"), 2);
		ports[6] = new Port(Translator.getResourceType("fasdf"), new HexLocation(-3, 0), Translator.getEdgeDirection("SE"), 3);
		ports[7] = new Port(Translator.getResourceType("sheep"), new HexLocation(3, -1), Translator.getEdgeDirection("NW"), 2);
		ports[8] = new Port(Translator.getResourceType("klfjasdf"), new HexLocation(0, 3), Translator.getEdgeDirection("N"), 3);
	}
}
