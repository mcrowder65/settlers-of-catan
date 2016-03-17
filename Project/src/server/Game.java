package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

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
		addUser("matt", "crowder");
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
	
	
	public int getNumGames() {
		return arrayGames.size();
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
		ArrayList<String> unusedAINames = _instance.arrayGames.get(gameId).model.getUnusedAINames();
		Random rand = new Random();
		int max = unusedAINames.size() - 1;
		int min = 0;
		int ai = rand.nextInt(max - min + 1) + min;
		return unusedAINames.get(ai);
	}
	
	public CatanColor getUnusedColor(int gameId) {
		ArrayList<CatanColor> unusedColors = _instance.arrayGames.get(gameId).model.getUnusedColors();
		Random rand = new Random();
		int max = unusedColors.size() - 1;
		int min = 0;
		int color = rand.nextInt(max - min + 1) + min;
		return unusedColors.get(color);
	}
	public void setGame(int gameID, ServerGameModel model){
		
		_instance.arrayGames.get(gameID).model = model;
	}
	/**
	 * this function returns the gamecookie of the game you're about to add.
	 * I use this function to get the future game cookie without setting
	 * the servergamemodel prematurely
	 * set to size instead of size - 1 because we haven't added the game yet.
	 * @return int gameCookie
	 */
	public int getAddableGameCookie(){
		return _instance.arrayGames.size();
	}
	public int addGame(GameInfo info, ServerGameModel model) {
	
		GameCombo combo = new GameCombo();
		combo.info = info;
		combo.model = model;
		_instance.arrayGames.add(combo);
		
		return _instance.arrayGames.size() - 1;
	}
	/**
	 * only use this if you are creating a game!!!!!
	 * @param playerID id of player, int
	 * @return ServerPlayer
	 */
	public ServerPlayer getLocalPlayer(int playerID){
		for(RegisteredPersonInfo p : registeredUsers){
			if(p.getId() == playerID){
				return new ServerPlayer(p.getUsername(), CatanColor.red,playerID, 0); 
			}
		}
		return null;
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
