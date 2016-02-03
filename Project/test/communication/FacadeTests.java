package communication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import client.communication.HTTPProxy;
import client.controller.Facade;
import client.data.GameInfo;
import client.data.GameManager;
import shared.definitions.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FacadeTests {
	static GameManager game;
	static Facade facade;
	static GameModel gameModel;
	static HTTPProxy proxy;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		game = new GameManager();
		gameModel = new GameModel();
		proxy = new HTTPProxy(2, "localhost", 8081);
		game.updateModel(gameModel);
		game.setProxy(proxy);
		facade = new Facade(proxy, 3);
		
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
		boolean register = facade.register("matt", "crowder");
		boolean login = facade.login("matt", "crowder");
		if(register == false && login == false) fail();
		
	}
	@Test
	public void btestLogin() {
		boolean login = facade.login("matt", "crowder");
		if(login == true) fail();
		login = facade.login("quinn", "snell");
		if(login == true) fail();
	}
	@Test
	public void ctestListGames(){
		GameInfo[] games = facade.listGames();
		if(games.length == 0) fail();
	}
	@Test
	public void dtestCreateGame(){
		boolean create = facade.createGame("matt", false, false, false);
		if(create == false) fail();
	}
	@Test
	public void etestJoinGame(){
		boolean join = facade.joinGame(3, CatanColor.red);
		if(join == false) fail();
	}
	@Test
	public void ftestRollNumber(){
		int result = facade.rollNumber();
		if(result > 12 || result < 2) fail();
	}
	
}	
