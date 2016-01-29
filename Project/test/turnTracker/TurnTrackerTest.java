package turnTracker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.TurnTracker;

public class TurnTrackerTest {

	private TurnTracker tracker;
	
	@Before
	public void setUp() throws Exception {
		tracker = new TurnTracker(0,null,0,0);
	}

	@Test
	public void test() {
		//Invalid info
		assertNotEquals(2,tracker.getCurrentTurn());
		assertNotEquals(1, tracker.getCurrentTurn());
		assertNotEquals(-1,tracker.getCurrentTurn());
		
		
		//AdvanceTurn
		tracker.advanceTurn();
		assertEquals(1,tracker.getCurrentTurn());
		
		tracker.advanceTurn();
		assertEquals(2,tracker.getCurrentTurn());
		
		tracker.advanceTurn();
		assertEquals(3,tracker.getCurrentTurn());
		
		//Wrap around
		tracker.advanceTurn();
		assertEquals(0,tracker.getCurrentTurn());
		
	}

}
