package persistence;

import dao.IUserDAO;
import shared.communication.request.MoveCommand;
/**
 * 
 * @author Brennen
 *
 */
public class SQLPersistenceProvider implements IPersistenceProvider{
	private MoveCommand[] commands;
	private int max;
	
	/**
	 * constructor for SQL Persistence Provider
	 */
	public SQLPersistenceProvider(){}
	
	/**
	 * Starts an SQL transaction 
	 */
	@Override
	public void startTransaction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Ends an SQL Transaction
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
	 * creates an new UserDAO for the SQL database
	 * @return IUserDAO
	 */
	@Override
	public IUserDAO createUserDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
