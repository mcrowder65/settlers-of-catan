package communication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.communication.request.*;
import shared.definitions.*;
import shared.locations.*;

public class CommandTests {

	int goodIndex1 = 0;
	int goodIndex2 = 3;
	int badIndex1 = -1;
	int badIndex2 = 4;
	@Test
	public void acceptTradeTest() {
		
		AcceptTradeCommand goodCommand1 = new AcceptTradeCommand(goodIndex1, true);
		AcceptTradeCommand goodCommand2 = new AcceptTradeCommand(goodIndex1, false);
		
		assertTrue(goodCommand1.getWillAccept() == true);
		assertTrue(goodCommand2.getWillAccept() == false);
		
	}

	@Test
	public void buildRoadTest() {
		EdgeLocation goodLocation = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.North);
		BuildRoadCommand goodCommand1 = new BuildRoadCommand(goodIndex1, true, goodLocation);
		BuildRoadCommand goodCommand2 = new BuildRoadCommand(goodIndex1, false, goodLocation);
		
		assertTrue(goodCommand1.isFree() == true);
		assertTrue(goodCommand2.isFree() == false);

		try
		{
			BuildRoadCommand badCommand1 = new BuildRoadCommand(goodIndex1, true, null);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
	}
	@Test
	public void buildSettlementTest() {
		VertexLocation goodLocation = new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast);
		BuildSettlementCommand goodCommand1 = new BuildSettlementCommand(goodIndex1, true, goodLocation);
		BuildSettlementCommand goodCommand2 = new BuildSettlementCommand(goodIndex1, false, goodLocation);
		
		assertTrue(goodCommand1.isFree() == true);
		assertTrue(goodCommand2.isFree() == false);

		try
		{
			BuildSettlementCommand badCommand1 = new BuildSettlementCommand(goodIndex1, true, null);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
	}
	@Test
	public void buildCityTest() {
		VertexLocation goodLocation = new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast);
		BuildCityCommand goodCommand1 = new BuildCityCommand(goodIndex1, true, goodLocation);
		BuildCityCommand goodCommand2 = new BuildCityCommand(goodIndex1, false, goodLocation);
		
		assertTrue(goodCommand1.isFree() == true);
		assertTrue(goodCommand2.isFree() == false);
		assertTrue(goodCommand1.getLocation() == goodLocation);

		try
		{
			BuildCityCommand badCommand1 = new BuildCityCommand(goodIndex1, true, null);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
	}
	@Test
	public void discardCardsTest() {
		ResourceList goodResources = new ResourceList(1,1,1,1,1);
		DiscardCardsCommand goodCommand1 = new DiscardCardsCommand(goodIndex1,goodResources);
		
		assertTrue(goodCommand1.getDiscardedCards() == goodResources);
		
		try
		{
			DiscardCardsCommand badCommand1 = new DiscardCardsCommand(goodIndex1, null);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		
	}
	@Test
	public void maritimeTradeTest() {
		int goodRatio1 = 2;
		int goodRatio2 = 4;
		int badRatio1 = 1;
		int badRatio2 = 5;
		MaritimeTradeCommand goodCommand1 = new MaritimeTradeCommand(goodIndex1, goodRatio1, ResourceType.BRICK, ResourceType.WOOD);
		MaritimeTradeCommand goodCommand2 = new MaritimeTradeCommand(goodIndex1, goodRatio2, ResourceType.WHEAT, ResourceType.SHEEP);
		MaritimeTradeCommand goodCommand3 = new MaritimeTradeCommand(goodIndex1, goodRatio1, ResourceType.BRICK, ResourceType.ORE);
		assertTrue(goodCommand1.getInput() == ResourceType.BRICK);
		assertTrue(goodCommand1.getOutput() == ResourceType.WOOD);
		
		try
		{
			MaritimeTradeCommand badCommand1 = new MaritimeTradeCommand(goodIndex1, badRatio1, ResourceType.BRICK, ResourceType.WOOD);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			MaritimeTradeCommand badCommand2 = new MaritimeTradeCommand(goodIndex1, badRatio2, ResourceType.BRICK, ResourceType.WOOD);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			MaritimeTradeCommand badCommand3 = new MaritimeTradeCommand(goodIndex1, goodRatio1, ResourceType.NONE, ResourceType.WOOD);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			MaritimeTradeCommand badCommand4 = new MaritimeTradeCommand(goodIndex1, goodRatio1, ResourceType.BRICK, ResourceType.NONE);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			MaritimeTradeCommand badCommand5 = new MaritimeTradeCommand(goodIndex1, goodRatio1, null, ResourceType.WOOD);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			MaritimeTradeCommand badCommand6 = new MaritimeTradeCommand(goodIndex1, goodRatio1, ResourceType.BRICK, null);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
	}
	
	@Test
	public void monopolyTest() {
		MonopolyCommand goodCommand1 = new MonopolyCommand(goodIndex1, ResourceType.BRICK);
		assertTrue(goodCommand1.getResource() == ResourceType.BRICK);
		try
		{
			MonopolyCommand badCommand1 = new MonopolyCommand(goodIndex1, ResourceType.NONE);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			MonopolyCommand badCommand2 = new MonopolyCommand(goodIndex1, null);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
	}
	@Test
	public void movementTest() {
		MonopolyCommand goodCommand1 = new MonopolyCommand(goodIndex1, ResourceType.BRICK);
		MonopolyCommand goodCommand2 = new MonopolyCommand(goodIndex2, ResourceType.BRICK);
		try
		{
			MonopolyCommand badCommand1 = new MonopolyCommand(badIndex1, ResourceType.BRICK);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			MonopolyCommand badCommand2 = new MonopolyCommand(badIndex2,  ResourceType.BRICK);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
	}
	@Test
	public void offerTradeTest() {
		int goodReceiver1 = 0;
		int goodReceiver2 = 3;
		int badReceiver1 = -1;
		int badReceiver2 = 4;
		ResourceList goodResources = new ResourceList(1,1,1,1,1);
		OfferTradeCommand goodCommand1 = new OfferTradeCommand(goodIndex1, goodReceiver1, goodResources);
		OfferTradeCommand goodCommand2 = new OfferTradeCommand(goodIndex1, goodReceiver2, goodResources);
		
		assertTrue(goodCommand1.getOffer() == goodResources);
		
		try
		{
			OfferTradeCommand badCommand1 = new OfferTradeCommand(goodIndex1, badReceiver1, goodResources);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			OfferTradeCommand badCommand2 = new OfferTradeCommand(goodIndex1, badReceiver2, goodResources);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			OfferTradeCommand badCommand3 = new OfferTradeCommand(goodIndex1, goodReceiver1, null);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}

	}
	
	@Test
	public void roadBuildingTest() {
		EdgeLocation goodLocation1 = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.North);
		EdgeLocation goodLocation2 = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.South);
		RoadBuildingCommand goodCommand1 = new RoadBuildingCommand(goodIndex1, goodLocation1, goodLocation2);
		
		
		assertTrue(goodCommand1.getSpot1() == goodLocation1);
		assertTrue(goodCommand1.getSpot2() == goodLocation2);

		try
		{
			RoadBuildingCommand badCommand1 = new RoadBuildingCommand(goodIndex1, null, goodLocation2);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			RoadBuildingCommand badCommand2 = new RoadBuildingCommand(goodIndex1, goodLocation1, null);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
	}
	@Test
	public void robPlayerTest() {
		HexLocation goodLocation = new HexLocation(0, 0);
		int goodReceiver1 = 0;
		int goodReceiver2 = 3;
		int badReceiver1 = -1;
		int badReceiver2 = 4;
		ResourceList goodResources = new ResourceList(1,1,1,1,1);
		RobPlayerCommand goodCommand1 = new RobPlayerCommand(goodIndex1, goodLocation, goodReceiver1);
		RobPlayerCommand goodCommand2 = new RobPlayerCommand(goodIndex1, goodLocation, goodReceiver2);
		
		assertTrue(goodCommand1.getLocation() == goodLocation);
		assertTrue(goodCommand1.getVictimIndex() == goodReceiver1);
		assertTrue(goodCommand2.getVictimIndex() == goodReceiver2);
		
		
		try
		{
			RobPlayerCommand badCommand1 = new RobPlayerCommand(goodIndex1, goodLocation, badReceiver1);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			RobPlayerCommand badCommand2 = new RobPlayerCommand(goodIndex1, goodLocation, badReceiver2);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			RobPlayerCommand badCommand3 = new RobPlayerCommand(goodIndex1, null, goodReceiver1);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}

	}
	
	@Test
	public void rollNumberTest() {
		int goodNumber1 = 2;
		int goodNumber2 = 12;
		int badNumber1 = 1;
		int badNumber2 = 13;
		RollNumberCommand goodCommand1 = new RollNumberCommand(goodIndex1, goodNumber1);
		RollNumberCommand goodCommand2 = new RollNumberCommand(goodIndex1, goodNumber2);
		
		assertTrue(goodCommand1.getNumber() == goodNumber1);
		assertTrue(goodCommand2.getNumber() == goodNumber2);
		
		try
		{
			RollNumberCommand badCommand1 = new RollNumberCommand(goodIndex1, badNumber1);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		
		try
		{
			RollNumberCommand badCommand2 = new RollNumberCommand(goodIndex1, badNumber2);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		
	
	}
	
	@Test
	public void sendChatTest() {
		String goodMessage1 = "Message";
		SendChatCommand goodCommand1 = new SendChatCommand(goodIndex1, goodMessage1);
		assertTrue(goodCommand1.getContent().equals(goodMessage1));	
	}
	
	
	@Test
	public void soldierTest() {
		HexLocation goodLocation = new HexLocation(0, 0);
		int goodReceiver1 = 0;
		int goodReceiver2 = 3;
		int badReceiver1 = -1;
		int badReceiver2 = 4;
		ResourceList goodResources = new ResourceList(1,1,1,1,1);
		SoldierCommand goodCommand1 = new SoldierCommand(goodIndex1, goodLocation, goodReceiver1);
		SoldierCommand goodCommand2 = new SoldierCommand(goodIndex1, goodLocation, goodReceiver2);
		
		assertTrue(goodCommand1.getLocation() == goodLocation);
		assertTrue(goodCommand1.getVictimIndex() == goodReceiver1);
		assertTrue(goodCommand2.getVictimIndex() == goodReceiver2);
		
		
		try
		{
			RobPlayerCommand badCommand1 = new RobPlayerCommand(goodIndex1, goodLocation, badReceiver1);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			RobPlayerCommand badCommand2 = new RobPlayerCommand(goodIndex1, goodLocation, badReceiver2);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			RobPlayerCommand badCommand3 = new RobPlayerCommand(goodIndex1, null, goodReceiver1);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}

	}
	
	
	@Test
	public void yearOfPlentyTest() {

		YearOfPlentyCommand goodCommand1 = new YearOfPlentyCommand(goodIndex1, ResourceType.BRICK, ResourceType.WOOD);
		
		assertTrue(goodCommand1.getResource1() == ResourceType.BRICK);
		assertTrue(goodCommand1.getResource2() == ResourceType.WOOD);
		
		try
		{
			YearOfPlentyCommand badCommand1 = new YearOfPlentyCommand(goodIndex1, ResourceType.NONE, ResourceType.WOOD);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			YearOfPlentyCommand badCommand2 = new YearOfPlentyCommand(goodIndex1, ResourceType.BRICK, ResourceType.NONE);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			YearOfPlentyCommand badCommand3 = new YearOfPlentyCommand(goodIndex1, null, ResourceType.WOOD);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
		try
		{
			YearOfPlentyCommand badCommand4 = new YearOfPlentyCommand(goodIndex1, ResourceType.BRICK, null);
			fail("Should have thrown an exception.");
		} catch (Exception e) {}
	}
	
	
	@Before
	public void setUp() {
		
	}
	@After
	public void tearDown() {
		
	}
}
