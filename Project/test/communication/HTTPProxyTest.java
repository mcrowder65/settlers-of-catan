package communication;

import org.junit.runners.MethodSorters;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import client.communication.*;
import client.utils.Translator;
import shared.communication.response.*;

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
	
	@Test
	public void atestRegister(){
		
		RegisterResponse response = httpProxy.register("matt", "crowder");
		System.out.println("register test: " + response.getJson());
	}
	@Test 
	public void btestLogin(){
		LoginResponse response = httpProxy.login("matt", "crowder");
		System.out.println("login test: " + response.getJson());
	}
	@Test
	public void ctestCreateGame(){
		CreateGameResponse response = httpProxy.createGame("matt", false, false, false);
		System.out.println("creategame: " + response.getJson());
	}
	@Test
	public void dtestListGames(){
		ListGamesResponse response = httpProxy.listGames();
		System.out.println("list games response: " + response.getJson());
	}
	
	@Test
	public void etestJoinGame(){
		System.out.println("before test join game");
		JoinGameResponse response = httpProxy.joinGame(3, Translator.getJsonTranslator().getCatanColor("red"));
		System.out.println("test join game");
	}
}
