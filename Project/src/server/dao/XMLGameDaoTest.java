package server.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import shared.communication.request.MoveCommand;
import shared.communication.request.RollNumberCommand;
import shared.definitions.CatanColor;
import shared.definitions.DevCardList;
import shared.definitions.ResourceList;

public class XMLGameDaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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


	public void addCommandTest() throws IOException {
		MoveCommand command = new RollNumberCommand(2,2);
		XMLGameDAO dao = new XMLGameDAO();
		dao.addCommand(command, 1);
		command = new RollNumberCommand(3,3);
		dao.addCommand(command, 1);
		dao.addCommand(command, 2);
	}
	
	public void deleteCommandTest() throws IOException {
		XMLGameDAO dao = new XMLGameDAO();
		dao.deleteCommands(1);
	}
	
	public void getCommandsTest() throws IOException {
		XMLGameDAO dao = new XMLGameDAO();
		List<MoveCommand>commands = new ArrayList<MoveCommand>();
		commands = dao.getCommands(1);
		assertTrue(commands.size() == 2);
		
	}
	
	public void deleteAll() throws IOException {
		XMLGameDAO dao = new XMLGameDAO();
		dao.resetPersistence();
	}
	
	public void addGameTest() throws IOException {
		XMLGameDAO dao = new XMLGameDAO();
		ServerGameModel model = new ServerGameModel();
		ServerGameMap map = new ServerGameMap();
		ServerPlayer p0 = new ServerPlayer();
		ServerPlayer p1 = new ServerPlayer();
		ServerPlayer p2 = new ServerPlayer();
		ServerPlayer p3 = new ServerPlayer();
		ServerPlayer[] players = new ServerPlayer[4];
		players[0] = p0;
		players[1] = p1;
		players[2] = p2;
		players[3] = p3;
		
		model.setServerGameMap(map);
		model.setServerPlayers(players);
		model.setBank(new ResourceList(1,1,1,1,1));
		model.setDeck(new DevCardList(2,2,2,2,2));
		
		ServerGameModel model2 = model;
		
		dao.addGame(1, model, "SnellSucks");
		dao.addGame(2, model2, "DopeSauce");
		dao.addGame(3, model2, "balls");
	}
	
	public void updateGameTest() throws IOException {
		XMLGameDAO dao = new XMLGameDAO();
		ServerGameModel model = new ServerGameModel();
		ServerGameMap map = new ServerGameMap();
		ServerPlayer p0 = new ServerPlayer();
		ServerPlayer p1 = new ServerPlayer();
		ServerPlayer p2 = new ServerPlayer();
		ServerPlayer p3 = new ServerPlayer();
		ServerPlayer[] players = new ServerPlayer[4];
		players[0] = p0;
		players[1] = p1;
		players[2] = p2;
		players[3] = p3;
		
		model.setServerGameMap(map);
		model.setServerPlayers(players);
		model.setBank(new ResourceList(3,0,0,1,1));
		model.setDeck(new DevCardList(7,4,2,0,1));
		dao.updateGame(1, model);
		dao.updateGame(2, model);
	}
	
	
	public void joinUserTest() throws IOException{
		XMLGameDAO dao = new XMLGameDAO();
		dao.joinUser(0, 1, CatanColor.blue, 0);
		dao.joinUser(1, 1, CatanColor.brown, 1);
		dao.joinUser(2, 1, CatanColor.green, 2);
		dao.joinUser(3, 1, CatanColor.purple, 3);
		dao.joinUser(0, 2, CatanColor.blue, 0);
		dao.joinUser(1, 2, CatanColor.brown, 1);
		dao.joinUser(2, 2, CatanColor.green, 2);
		dao.joinUser(3, 2, CatanColor.purple, 3);
		dao.joinUser(0, 3, CatanColor.blue, 0);
		dao.joinUser(1, 3, CatanColor.brown, 1);
		dao.joinUser(2, 3, CatanColor.green, 2);
		dao.joinUser(3, 3, CatanColor.purple, 3);
		
	}
	
	@Test
	public void getGamesTest() throws IOException{
		XMLGameDAO dao = new XMLGameDAO();
		dao.joinUser(0, 1, CatanColor.blue, 0);
		dao.joinUser(1, 1, CatanColor.brown, 1);
		dao.joinUser(2, 1, CatanColor.green, 2);
		dao.joinUser(3, 1, CatanColor.purple, 3);
		dao.joinUser(0, 2, CatanColor.blue, 0);
		dao.joinUser(1, 2, CatanColor.brown, 1);
		dao.joinUser(2, 2, CatanColor.green, 2);
		dao.joinUser(3, 2, CatanColor.purple, 3);
		dao.joinUser(0, 3, CatanColor.blue, 0);
		dao.joinUser(1, 3, CatanColor.brown, 1);
		dao.joinUser(2, 3, CatanColor.green, 2);
		dao.joinUser(3, 3, CatanColor.purple, 3);
		
		
		ServerGameModel model = new ServerGameModel();
		ServerGameMap map = new ServerGameMap();
		ServerPlayer p0 = new ServerPlayer();
		ServerPlayer p1 = new ServerPlayer();
		ServerPlayer p2 = new ServerPlayer();
		ServerPlayer p3 = new ServerPlayer();
		ServerPlayer[] players = new ServerPlayer[4];
		players[0] = p0;
		players[1] = p1;
		players[2] = p2;
		players[3] = p3;
		
		model.setServerGameMap(map);
		model.setServerPlayers(players);
		model.setBank(new ResourceList(1,1,1,1,1));
		model.setDeck(new DevCardList(2,2,2,2,2));
		
		ServerGameModel model2 = model;
		
		dao.addGame(1, model, "SnellSucks");
		dao.addGame(2, model2, "DopeSauce");
		dao.addGame(3, model2, "balls");
		XMLUserDAO userDAO = new XMLUserDAO();
		userDAO.addUser(new RegisteredPersonInfo(0,"brennen","tyler423"));
		userDAO.addUser(new RegisteredPersonInfo(1,"james","tyler423"));
		userDAO.addUser(new RegisteredPersonInfo(2,"john","tyler423"));
		userDAO.addUser(new RegisteredPersonInfo(3,"jim","tyler423"));
		List<GameCombo> combo = dao.getGames();
		System.out.println("Here");
	}
	

}
