package persistence;

import dao.IUserDAO;
import shared.communication.request.MoveCommand;

public class XMLPersistenceProvider implements IPersistenceProvider{
	private MoveCommand[] commands;
	private int max;
	public XMLPersistenceProvider(){}
	

	/**
	 * Starts a transaction to write to an XML file
	 */
	@Override
	public void startTransaction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ends a transaction when writing to an XML file
	 */
	@Override
	public void endTransaction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * adds a command to the array of commands 
	 * @param MoveCommand
	 */
	@Override
	public void addCommand(MoveCommand command) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Sets how many commands need to be executed before we write to disk
	 * @param int commandCount
	 */
	@Override
	public void setup(int commandCount) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * loads a game into memory via the gameID
	 * @param int gameID
	 */
	@Override
	public void loadGame(int gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flushGame(int gameID) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * creates an new UserDAO for an xml file 
	 * @return IUserDAO
	 */
	@Override
	public IUserDAO createUserDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
