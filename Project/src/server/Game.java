package server;

import java.util.ArrayList;

import server.util.ServerGameModel;

public class Game {
	
	public ArrayList<ServerGameModel> arrayGames;
	
	private static Game _instance;
	
	
	public Game() {
		
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
	
	public ServerGameModel getGameModel(int index) {
		return _instance.arrayGames.get(index);
	}
	
}
