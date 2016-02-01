package communication;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import client.communication.*;
import client.utils.Translator;
import shared.communication.response.*;
public class HTTPProxyTest {
	HTTPProxy httpProxy;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	httpProxy = new HTTPProxy(2, "localhost", 8081);
	}

	@After
	public void tearDown() throws Exception {
	}
	//@Test
	public void testCreateGame(){
		CreateGameResponse response = httpProxy.createGame("matt", false, false, false);
		System.out.println("creategame: " + response.getResponseCode());
	}
	//@Test
	public void testLogin(){
		LoginResponse response = httpProxy.login("matt", "crowder");
		System.out.println("login test");
	}
//	@Test
	public void testRegister(){
		
		RegisterResponse response = httpProxy.register("matt", "crowder");
		System.out.println("register test");
	}
//	@Test
	public void testListGames(){
		ListGamesResponse response = httpProxy.listGames();
		System.out.println("list games response");
	}
	
	@Test
	public void testJoinGame(){
		JoinGameResponse response = httpProxy.joinGame(3, Translator.getJsonTranslator().getCatanColor("RED"));
		System.out.println("test join game");
	}
}
