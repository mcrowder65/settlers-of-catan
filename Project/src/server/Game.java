package server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import client.data.GameInfo;
import client.data.PlayerInfo;
import client.utils.Translator;
import server.persistence.PersistenceProvider;
import server.plugin.ClassLoaderTool;
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
	public Object lock = new Object();
	private PersistenceProvider persistenceProvider;
	public PersistenceProvider getPersistenceProvider() {
		return persistenceProvider;
	}
	
	private Game() {
		addUser("matt", "crowder");
		addUser("brennen", "0000");
		addUser("eric", "0000");
		addUser("manuel","0000");
	
	}
	
	public static void Reset() {
		_instance = null;
	}
	
	public static Game instance() {
		if(_instance == null) {
			_instance = new Game();
		}
		
		return _instance;
	}
	
	/**
	 * initializes the persistence provider
	 * @param max int
	 * @param location String
	 */
	public void initPersistanceProvider(int max, String location){
		Class c =ClassLoaderTool.getClass(location);
		Constructor constructor;
		PersistenceProvider p;
		try {
			constructor = c.getConstructor(int.class);
			p = (PersistenceProvider)constructor.newInstance(max);
			persistenceProvider = p;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PersistenceProvider getProvider() {
		return persistenceProvider;
	}
	
	
	public int getNumGames() {
		return arrayGames.size();
	}
	private static List<AIType> aiTypes;
	private static HashSet<String> aiNames;
	
	static {
		aiTypes = new ArrayList<AIType>();
		aiTypes.add(AIType.LARGEST_ARMY);
		aiTypes.add(AIType.LONGEST_ROAD);
		aiTypes.add(AIType.SETTLEMENT_BUILDER);
		aiTypes.add(AIType.CITY_BUILDER);
		aiTypes.add(AIType.NORMAL);
		
		aiNames = new HashSet<String>();
		aiNames.add("Steve");
		aiNames.add("Bentz");
		aiNames.add("Snell");
		aiNames.add("Andrew");
		aiNames.add("Trent");
		aiNames.add("MrBurns");
	}
	public static List<AIType> getAiTypes() {
		return aiTypes;
	}
	
	
	public  String getUnusedAiName(int gameId) {
		//_instance.arrayGames.get(gameId)
		return _instance.arrayGames.get(gameId).model.chooseAIName();
	}
	
	public CatanColor getUnusedColor(int gameId) {
		return _instance.arrayGames.get(gameId).model.chooseAIColor();
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
	public void addPlayer(int gameId, ServerPlayer player){
		arrayGames.get(gameId).model.addPlayer(player);
		PlayerInfo p = new PlayerInfo(player);
		if(!arrayGames.get(gameId).info.hasPlayer(p.getName())){
			arrayGames.get(gameId).info.addPlayer(p);
		}
	}
	public void setPlayer(int gameId, ServerPlayer player){
		arrayGames.get(gameId).model.setPlayer(player);
		arrayGames.get(gameId).info.setPlayer(player.getPlayerIndex(),player.getPlayerID(), player.getColor());
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
