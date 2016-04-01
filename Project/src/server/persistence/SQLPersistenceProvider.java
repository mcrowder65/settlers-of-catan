package server.persistence;

import java.util.List;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.util.GameCombo;
import shared.communication.request.MoveCommand;
/**
 * 
 * @author Brennen
 *
 */
public class SQLPersistenceProvider extends PersistenceProvider{
	
	/**
	 * constructor for SQL Persistence Provider.
	 * @param commandCount
	 *  Sets how many commands need to be executed before we write to disk
	 */
	public SQLPersistenceProvider(int commandCount){
		super(commandCount);
	}
	
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
	public void endTransaction(boolean commit) {
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
	 * loads a game into memory via the gameID
	 * @param int gameID
	 */
	@Override
	public List<GameCombo> loadGames() {
		return null;
	}

	@Override
	protected void flushGame(int gameID) {
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

	@Override
	public IGameDAO createGameDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
