package communication;


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
		httpProxy = new HTTPProxy(2, "localhost", 8081);
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
	//@Test
	public void atestRegister(){
		RegisterResponse response = httpProxy.register("matt", "crowder");
		System.out.println("register test");
	}
	@Test
	public void btestLogin(){
		LoginResponse response = httpProxy.login("matt", "crowder");
		System.out.println("login test");
	}
	@Test
	public void ctestCreateGame(){
		CreateGameResponse response = httpProxy.createGame("matt", false, false, false);
		System.out.println("creategame: " + response.getResponseCode());
	}
	@Test
	public void dtestJoinGame(){
		JoinGameResponse response = httpProxy.joinGame(3, Translator.getJsonTranslator().getCatanColor("puce"));
		System.out.println("test join game");
	}

	@Test
	public void etestListGames(){
		ListGamesResponse response = httpProxy.listGames();
		System.out.println("list games response");
	}
	@Test
	public void ftestSaveGame(){
		Response response = httpProxy.saveGame(4, "hello");
		System.out.println("save game test");
	}
	
	
}
