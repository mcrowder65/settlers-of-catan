package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import client.data.GameInfo;
import client.data.PlayerInfo;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import shared.definitions.AIType;
import shared.definitions.CatanColor;
import shared.definitions.Player;

public class Game {
	
	private ArrayList<GameCombo> arrayGames = new ArrayList<GameCombo>();
	private ArrayList<RegisteredPersonInfo> registeredUsers = new ArrayList<RegisteredPersonInfo>();
	private static Game _instance;
	
	
	private Game() {
		addUser("matt", "matt");
		addUser("brennen", "0000");
		addUser("eric", "0000");
		addUser("manuel","0000");
	}
	
	public static Game instance() {
		if(_instance == null) {
			_instance = new Game();
		}
		
		return _instance;
	}
	
	private static List<AIType> aiTypes;
	private static HashSet<String> aiNames;
	static {
		aiTypes = new ArrayList<AIType>();
		aiTypes.add(AIType.LARGEST_ARMY);
		
		aiNames = new HashSet<String>();
		aiNames.add("Steve");
		aiNames.add("Bentz");
		aiNames.add("Snell");
		aiNames.add("Andrew");
		aiNames.add("Trent");
		aiNames.add("Bandana");
		aiNames.add("Sideburns");
	}
	public static List<AIType> getAiTypes() {
		return aiTypes;
	}
	
	
	public  String getUnusedAiName(int gameId) {
		boolean success = false;
		for (String name : aiNames) {
			success = true;
			for (Player player : _instance.getGameId(gameId).getPlayers()) {
				if (name.equals(player.getName())) {
					success = false;
					break;
				}
			}
			if (success) {
				return name;
			}
		}
		return null;
	}
	
	public CatanColor getUnusedColor(int gameId) {
		boolean success = false;
		for (CatanColor color : CatanColor.values()) {
			success = true;
			for (Player player : _instance.getGameId(gameId).getPlayers()) {
				if (color.equals(player.getColor())) {
					success = false;
					break;
				}
			}
			if (success) {
				return color;
			}
		}
		return null;
	}
	public void setGame(int gameID, ServerGameModel model){
		GameCombo combo = new GameCombo();
		//TODO info?
		combo.model = model;
		_instance.arrayGames.set(gameID, combo);
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
	public RegisteredPersonInfo getPersonById(int id){
		for(RegisteredPersonInfo person : registeredUsers){
			if(person.getId() == id){
				return person;
			}
		}
		return null;
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
