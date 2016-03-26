package communication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.util.ServerPlayer;
import shared.definitions.CatanColor;
import shared.definitions.DevCardList;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;

public class ServerPlayerTest {

	ServerPlayer player = new ServerPlayer();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Testing ServerPlayer");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Testing ServerPlayer completed");
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void layRoadTest() {
		System.out.println("Testing chargeBasicRoad in ServerPlayer");
		//setting it up
		player.setResources(new ResourceList(1,0,0,0,1));
		player.setRoads(1);
		player.layRoad();
		
		//check road number
		assertTrue(player.getRoads() == 0);
		
		//check Resources
		assertTrue(player.getResources().getBrick() == 0);
		assertTrue(player.getResources().getWood() == 0);

	}
	
	@Test
	public void layRoadBuilderTest() {
		System.out.println("Testing layRoadBuilder in ServerPlayer");
		//setting it up
		player.setOldDevCards(new DevCardList(0,0,1,0,0));
		player.setRoads(2);
		player.layRoadBuilder();
		
		//check road number
		assertTrue(player.getRoads() == 0);
		
		//check DevCards
		assertTrue(player.getOldDevCards().getRoadBuilding() == 0);

	}
	
	@Test
	public void laySettlementTest() {
		System.out.println("Testing layRoadBuilder in ServerPlayer");
		//setting it up
		player.setResources(new ResourceList(1,1,1,1,1));
		player.setSettlements(1);
		player.setVictoryPoints(0);
		player.laySettlement();
		
		//check road number
		assertTrue(player.getSettlements() == 0);
		
		//check Resources
		assertTrue(player.getResources().getBrick() == 0);
		assertTrue(player.getResources().getWood() == 0);
		assertTrue(player.getResources().getSheep() == 0);
		assertTrue(player.getResources().getWheat() == 0);
		assertTrue(player.getResources().getOre() == 1);
		
		//check victorypoints
		assertTrue(player.getVictoryPoints() == 1);
		
	}
	
	@Test
	public void canMakeTradeTest(){
		System.out.println("Testing canMakeTrade in ServerPlayer");
		player.setResources(new ResourceList(1,1,1,1,1));
		ResourceList offer = new ResourceList(1,1,1,1,1);
		
		//exact amount for trade
		assertTrue(player.canMakeTrade(offer));
		
		//one resource off
		offer = new ResourceList(2,1,1,1,1);
		assertFalse(player.canMakeTrade(offer));
		
		//no offer
		offer = new ResourceList(0,0,0,0,0);
		assertTrue(player.canMakeTrade(offer));
	}
	
	@Test
	public void discardCardsTest(){
		System.out.println("Testing discardCards in ServerPlayer");
		player.setResources(new ResourceList(1,1,1,1,1));
		ResourceList discard = new ResourceList(0,0,0,0,0);
		
		//discard none
		player.discardCards(discard);
		assertTrue(player.getResources().equals(new ResourceList(1,1,1,1,1)));
		
		//discard one card
		discard = new ResourceList(1,0,0,0,0);
		player.discardCards(discard);
		assertTrue(player.getResources().equals(new ResourceList(0,1,1,1,1)));
		
		//discard the rest 
		discard = new ResourceList(0,1,1,1,1);
		player.discardCards(discard);
		assertTrue(player.getResources().equals(new ResourceList(0,0,0,0,0)));
	}
	
	@Test
	public void canAcceptTradeTest(){
		System.out.println("Testing canAcceptTrade in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		ResourceList offer = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		
		//all resources equal
		assertTrue(player.canAcceptTrade(offer));
		
		//cant accept trade - one card too many
		offer = new ResourceList(2,1,1,1,1);
		assertFalse(player.canAcceptTrade(offer));
		
		//can accept not equal
		offer = new ResourceList(0,1,0,1,1);
		assertTrue(player.canAcceptTrade(offer));
		
	}
	
	@Test
	public void layCity(){
		System.out.println("Testing layCity in ServerPlayer");
		ResourceList resources = new ResourceList(0,3,0,2,0);
		player.setResources(resources);
		player.setCities(1);
		player.setSettlements(1);
		player.setVictoryPoints(1);
		
		//lay city
		player.layCity();
		assertTrue(player.getResources().getOre() == 0);
		assertTrue(player.getResources().getWheat() == 0);
		assertTrue(player.getSettlements() == 2);
		assertTrue(player.getCities() == 0);
		assertTrue(player.getVictoryPoints() == 2);

	}
	
	@Test
	public void addResource(){
		System.out.println("Testing addResource in ServerPlayer");
		ResourceList resources = new ResourceList(0,0,0,0,0);
		player.setResources(resources);
		
		player.addResource(ResourceType.ORE);
		assertTrue(player.getResources().getOre() == 1);
		assertTrue(player.getResources().getWood() ==0);
		
		player.addResource(ResourceType.WOOD);
		assertTrue(player.getResources().getWood() == 1);
	}
	
	@Test
	public void removeResource(){
		System.out.println("Testing removeResource in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		
		player.removeResource(ResourceType.ORE);
		assertTrue(player.getResources().getOre() == 0);
		assertTrue(player.getResources().getWood() ==1);
		
		player.removeResource(ResourceType.WOOD);
		assertTrue(player.getResources().getWood() == 0);
	}
	
	@Test
	public void addBrick(){
		System.out.println("Testing addBrick in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		player.addBrick();
		assertTrue(player.getResources().getBrick()==2);
		
	}
	
	@Test
	public void addWood(){
		System.out.println("Testing addWood in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		player.addWood();
		assertTrue(player.getResources().getWood()==2);
		
	}
	@Test
	public void addOre(){
		System.out.println("Testing addOre in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		player.addOre();
		assertTrue(player.getResources().getOre()==2);
		
	}
	
	@Test
	public void addSheep(){
		System.out.println("Testing addSheep in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		player.addSheep();
		assertTrue(player.getResources().getSheep()==2);
		
	}
	
	@Test
	public void addWheat(){
		System.out.println("Testing addWheat in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		player.addWheat();
		assertTrue(player.getResources().getWheat()==2);
		
	}
	
	@Test
	public void removeWheat(){
		System.out.println("Testing removeWheat in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		player.removeWheat();
		assertTrue(player.getResources().getWheat()==0);
		
	}
	@Test
	public void removeWood(){
		System.out.println("Testing removeWood in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		player.removeWood();
		assertTrue(player.getResources().getWood()==0);
		
	}
	
	@Test
	public void removeOre(){
		System.out.println("Testing removeOre in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		player.removeOre();
		assertTrue(player.getResources().getOre()==0);
		
	}
	
	@Test
	public void removeBrick(){
		System.out.println("Testing removeBrick in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		player.removeBrick();
		assertTrue(player.getResources().getBrick()==0);
		
	}
	
	@Test
	public void removeSheep(){
		System.out.println("Testing removeOre in ServerPlayer");
		ResourceList resources = new ResourceList(1,1,1,1,1);
		player.setResources(resources);
		player.removeSheep();
		assertTrue(player.getResources().getSheep()==0);
	}
	
	@Test
	public void addMonopolyTest(){
		System.out.println("Testing addMonopoly in ServerPlayer");
		DevCardList cards = new DevCardList(0,0,0,0,0);
		player.setNewDevCards(cards);
		assertTrue(player.getNewDevCards().getMonopoly()==0);
		player.addMonopoly();
		assertTrue(player.getNewDevCards().getMonopoly()==1);

	}
	
	@Test
	public void addRoadBuilderTest(){
		System.out.println("Testing addRoadBuilder in ServerPlayer");
		DevCardList cards = new DevCardList(0,0,0,0,0);
		player.setNewDevCards(cards);
		assertTrue(player.getNewDevCards().getRoadBuilding()==0);
		player.addRoadBuilder();
		assertTrue(player.getNewDevCards().getRoadBuilding()==1);

	}
	@Test
	public void addMonumentTest(){
		System.out.println("Testing addMonument in ServerPlayer");
		DevCardList cards = new DevCardList(0,0,0,0,0);
		player.setOldDevCards(cards);
		player.setNewDevCards(cards);
		assertTrue(player.getOldDevCards().getMonument()==0);
		player.addMonument();
		assertTrue(player.getOldDevCards().getMonument()==1);
	}
	@Test
	public void addYOP(){
		System.out.println("Testing addYOP in ServerPlayer");
		DevCardList cards = new DevCardList(0,0,0,0,0);
		player.setNewDevCards(cards);
		assertTrue(player.getNewDevCards().getYearOfPlenty()==0);
		player.addYearOfPlenty();
		assertTrue(player.getNewDevCards().getYearOfPlenty()==1);
	}
	@Test
	public void addSoldier(){
		System.out.println("Testing addSoldier in ServerPlayer");
		DevCardList cards = new DevCardList(0,0,0,0,0);
		player.setNewDevCards(cards);
		assertTrue(player.getNewDevCards().getSoldier()==0);
		player.addSoldierCard();
		assertTrue(player.getNewDevCards().getSoldier()==1);
	}
	@Test
	public void playMonumentTest(){
		System.out.println("Testing playMonumentTest in ServerPlayer");
		DevCardList cards = new DevCardList(1,1,1,1,1);
		player.setOldDevCards(cards);
		player.setVictoryPoints(0);
		player.playMonument();
		assertTrue(player.getOldDevCards().getMonument() == 0);
		assertTrue(player.getVictoryPoints() == 1);
		
	}
	
	

}
