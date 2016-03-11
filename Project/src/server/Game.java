package server;

import java.util.ArrayList;
import java.util.HashMap;

import server.util.ServerGameModel;
import server.util.ServerPlayer;

public class Game {
	
	private ArrayList<ServerGameModel> arrayGames;
	private HashMap<String, String> usernameToPassword = new HashMap<String, String>();
	
	private static Game _instance;
	
	
	private Game() {
		
	}
	
	public static Game instance() {
		if(_instance == null) {
			_instance = new Game();
		}
		
		return _instance;
	}
	
	
	public int addGame(ServerGameModel game) {
	
		_instance.arrayGames.add(game);
		
		return _instance.arrayGames.size() - 1;
	}
	
	public ServerGameModel getGameId(int index) {
		return _instance.arrayGames.get(index);
	}
	
	public boolean verifyPassword(String username, String password) {
		return usernameToPassword.containsKey(username) &&
				usernameToPassword.get(username).equals(password);
	}
	public boolean userExists(String username) {
		return usernameToPassword.containsKey(username);
	}
	public void addUser(String username, String password) {
		usernameToPassword.put(username, password);
	}
}
