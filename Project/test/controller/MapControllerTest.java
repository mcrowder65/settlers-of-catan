package controller;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.definitions.GameMap;
import shared.definitions.Hex;
import shared.definitions.HexType;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

public class MapControllerTest {

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

	public HexType getHexType(String resource){
		return resource.equals("DESERT") ? HexType.DESERT : resource.equals("WOOD") ? HexType.WOOD : 
				resource.equals("BRICK") ? HexType.BRICK : resource.equals("SHEEP") ? HexType.SHEEP : 
				resource.equals("WHEAT") ? HexType.WHEAT : resource.equals("ORE") ? HexType.ORE : 
				resource.equals("WATER") ? HexType.WATER : null;
	}
	@Test
	public void initMap(){
		
//		Random rand = new Random();
//		Hex[] hexes = map.getHexes();
		Hex[] hexes = new Hex[6];
		
		hexes[0] = new Hex(new HexLocation(0,0),  ResourceType.WOOD, 0);
		hexes[1] = new Hex(new HexLocation(1,1),  ResourceType.BRICK, 0);
		hexes[2] = new Hex(new HexLocation(2,2),  ResourceType.SHEEP, 0);
		hexes[3] = new Hex(new HexLocation(3,3),  ResourceType.WHEAT, 0);
		hexes[4] = new Hex(new HexLocation(4,4),  ResourceType.ORE, 0);
		hexes[5] = new Hex(new HexLocation(5,5),  ResourceType.NONE, 0);
		
		for(int i = 0; i < hexes.length; i++){
			System.out.println(hexes[i].getLocation() + " " +  hexes[i].getNumber());
			HexType hexType = hexes[i].getResource().name().equals("NONE") ? HexType.DESERT : getHexType(hexes[i].getResource().name());
			System.out.println(hexType);
		}
//			for(int i = 0; i < hexes.length; i++){
//				getView().addNumber(hexes[i].getLocation(), hexes[i].getNumber());
//				HexType hexType = hexes[i].getResource().name().equals("NONE") ? HexType.DESERT : getHexType(hexes[i].getResource().name());
//				getView().addHex(hexes[i].getLocation(), hexType);
//			}
	}

}
