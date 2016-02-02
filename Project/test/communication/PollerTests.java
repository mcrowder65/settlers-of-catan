package communication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.communication.HTTPProxy;
import client.communication.MockProxy;
import client.communication.Poller;
import client.data.GameManager;

public class PollerTests {

	GameManager gameManager;
	//HTTPProxy httpProxy;
	MockProxy mockProxy;
	@Before
	public void setUp() throws Exception {
		//httpProxy = new HTTPProxy(3, "localhost", 8001);
		mockProxy = new MockProxy();
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void intervalTest() {
		try
		{
			gameManager = new GameManager(mockProxy, 0);
			fail("Should have thrown an exception.");
		}
		catch (IllegalArgumentException ex) {}
		
		gameManager = new GameManager(mockProxy, 1);
	}
	@Test
	public void startStopTest() {
		gameManager = new GameManager(mockProxy, 1);
		assertFalse(gameManager.getPoller().isPolling());
		
		gameManager.getPoller().stopPolling();
		assertFalse(gameManager.getPoller().isPolling());
		
		gameManager.getPoller().startPolling();
		assertTrue(gameManager.getPoller().isPolling());
		
		gameManager.getPoller().startPolling();
		assertTrue(gameManager.getPoller().isPolling());
		
		gameManager.getPoller().stopPolling();
		assertFalse(gameManager.getPoller().isPolling());
		
		gameManager.getPoller().startPolling();
		assertTrue(gameManager.getPoller().isPolling());
		
	}
	
	@Test
	public void updateModelTest() {
		final int INTERVAL = 2;
		gameManager = new GameManager(mockProxy, INTERVAL);
		
		assertTrue(gameManager.getModel() == null);
		
		gameManager.getPoller().startPolling();
		try {
			Thread.sleep((INTERVAL *2) * 1000);
			assertTrue(gameManager.getModel() != null);
			
		} catch (InterruptedException e) {
			fail("Shouldn't have thrown an exception.");
		}
		
	}
	
	
}
