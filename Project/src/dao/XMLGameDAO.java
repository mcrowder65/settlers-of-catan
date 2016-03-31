package dao;

import java.util.List;

import server.util.GameCombo;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;

public class XMLGameDAO implements IGameDAO{
	public XMLGameDAO(){}
	@Override
	public List<GameCombo> getGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCommand(MoveCommand command) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGame(int gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCommands(int gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinUser(int userID, int gameID, CatanColor color) {
		// TODO Auto-generated method stub
		
	}

}
