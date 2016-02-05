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

<<<<<<< HEAD
=======
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

>>>>>>> branch 'master' of https://github.com/mcrowder65/cs340.git
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
<<<<<<< HEAD
	
=======
>>>>>>> branch 'master' of https://github.com/mcrowder65/cs340.git
	@Test
	public void atestRegister(){
<<<<<<< HEAD
		
		RegisterResponse response = httpProxy.register("matt", "crowder");
		System.out.println("register test: " + response.getJson());
=======
		Response response = httpProxy.register("matt", "crowder");
		System.out.println("register test");
>>>>>>> branch 'master' of https://github.com/mcrowder65/cs340.git
	}
<<<<<<< HEAD
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
=======
	@Test
	public void btestLogin(){
		Response response = httpProxy.login("matt", "crowder");
		System.out.println("login test");
	}
	@Test
	public void ctestCreateGame(){
		CreateGameResponse response = httpProxy.createGame("matt", false, false, false);
		System.out.println("creategame: " + response.getErrorMessage());
	}
	@Test
	public void dtestJoinGame(){
		Response response = httpProxy.joinGame(3, Translator.getCatanColor("puce"));
		System.out.println("test join game");
	}

	@Test
	public void etestListGames(){
>>>>>>> branch 'master' of https://github.com/mcrowder65/cs340.git
		ListGamesResponse response = httpProxy.listGames();
		System.out.println("list games response: " + response.getJson());
	}
	@Test
<<<<<<< HEAD
	public void etestJoinGame(){
		System.out.println("before test join game");
		JoinGameResponse response = httpProxy.joinGame(3, Translator.getJsonTranslator().getCatanColor("red"));
		System.out.println("test join game");
=======
	public void ftestAddAI(){
		Response response = httpProxy.addAI("LARGEST_ARMY");
		System.out.println("testing add ai");
>>>>>>> branch 'master' of https://github.com/mcrowder65/cs340.git
	}
	@Test
	public void gtestListAI(){
		ListAIResponse response = httpProxy.listAI();
		System.out.println("testing list ai");
	}
	@Test
	public void htestSendChat(){
		GetModelResponse response = httpProxy.sendChat("hello!");
		System.out.println("testing send chat");
	}
	@Test
	public void itestRollNumber(){
		GetModelResponse response = httpProxy.rollNumber(8);
		System.out.println("rolling number");
	}
	
}
