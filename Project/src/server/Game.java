package server;

import java.util.ArrayList;
import java.util.HashMap;

import client.data.GameInfo;
import client.data.PlayerInfo;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameModel;
import server.util.ServerPlayer;

public class Game {
	
	private ArrayList<GameCombo> arrayGames = new ArrayList<GameCombo>();
	private ArrayList<RegisteredPersonInfo> registeredUsers = new ArrayList<RegisteredPersonInfo>();
	private static Game _instance;
	
	
	private Game() {
		
	}
	
	public static Game instance() {
		if(_instance == null) {
			_instance = new Game();
		}
		
		return _instance;
	}
	
	
	public int addGame(GameInfo info, ServerGameModel model) {
	
		GameCombo combo = new GameCombo();
		combo.info = info;
		combo.model = model;
		_instance.arrayGames.add(combo);
		
		return _instance.arrayGames.size() - 1;
	}
	
	public ServerGameModel getGameId(int index) {
		return _instance.arrayGames.get(index).model;
	}
	
	public boolean verifyPassword(String username, String password) {
		for (RegisteredPersonInfo person : registeredUsers) {
			if (person.getUsername().equals(username) && person.getPassword().equals(password))
				return true;
		}
		return false;
	}
	public boolean userExists(String username) {
		for (RegisteredPersonInfo person : registeredUsers) {
			if (person.getUsername().equals(username))
				return true;
		}
		return false;
	}
	
	public int getUserId(String username) {
		for (RegisteredPersonInfo person : registeredUsers) {
			if (person.getUsername().equals(username))
				return person.getId();
		}
		return -1;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return returns the person's new playerId
	 */
	public int addUser(String username, String password) {
		RegisteredPersonInfo person = new RegisteredPersonInfo();
		person.setUsername(username);
		person.setPassword(password);
		person.setId(registeredUsers.size());
		registeredUsers.add(person);
		return person.getId();
	
	}
	
	public ArrayList<GameInfo> getGamesList() {
		ArrayList<GameInfo> games = new ArrayList<GameInfo>();
		for (GameCombo game : arrayGames) {
			games.add(game.info);
		}
		return games;
	}
}
