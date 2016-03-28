package communication;


import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import client.communication.*;
import client.utils.Translator;
import shared.communication.response.*;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HTTPProxyTest {
	static HTTPProxy httpProxy;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		httpProxy = new HTTPProxy("localhost", 8081);
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
	@Test
	public void atestRegister(){
		Response register = httpProxy.register("dipstick", "crowder");
		Response login = httpProxy.login("dipstick", "crowder");
		if(!register.isSuccess() && !login.isSuccess()) fail();
		
	}
	@Test
	public void btestLogin(){
		Response response = httpProxy.login("matt", "crowder");
		if(!response.isSuccess()) fail();
	}
	@Test
	public void ctestCreateGame(){
		CreateGameResponse response = httpProxy.createGame("matt", false, false, false);
		if(!response.isSuccess()) fail();
	}
	@Test
	public void dtestJoinGame(){
		Response response = httpProxy.joinGame(0, Translator.getCatanColor("puce"));
		if(!response.isSuccess()) fail();
	}

	@Test
	public void etestListGames(){
		ListGamesResponse response = httpProxy.listGames();
		if(!response.isSuccess()) fail();
	}
	@Test
	public void ftestAddAI(){
		Response response = httpProxy.addAI("LARGEST_ARMY");
		if(!response.isSuccess()) fail();
	}
	@Test
	public void gtestListAI(){
		ListAIResponse response = httpProxy.listAI();
		if(!response.isSuccess()) fail();
	}

	
}
