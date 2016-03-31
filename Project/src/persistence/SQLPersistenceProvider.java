package persistence;

import dao.IUserDAO;
import shared.communication.request.MoveCommand;

public class SQLPersistenceProvider implements IPersistenceProvider{
	private MoveCommand[] commands;
	private int max;
	public SQLPersistenceProvider(){}
	
	@Override
	public void startTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCommand(MoveCommand command) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setup(int commandCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadGame(int gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flushGame(int gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IUserDAO createUserDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
