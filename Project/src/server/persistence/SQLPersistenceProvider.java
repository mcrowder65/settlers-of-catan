package server.persistence;

import java.util.List;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * Stores the delta between keys
 * Uses the DAO's to write to the database/files
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
		//new dao's, new connection
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
	 * @param commit
	 */
	@Override
	public void endTransaction(boolean commit) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * adds a command to the array of commands 
	 * calls the command method on the DAOs
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

	/**
	 * Writes the entire game model to the database
	 * Clears the commands table
	 * @param int gameID
	 */
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

	/**
	 * Creates the game DAO 
	 * @return IGameDAO 
	 */
	@Override
	public IGameDAO createGameDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * calls the userDAO to add a user to the join table
	 * @param int userID, int gameID, CatanColor color
	 */
	@Override
	public void joinUser(int userID, int gameID, CatanColor color){
		
	}
	
	/**
	 * calls addUser on the SQLUserDAO
	 * @param userID
	 * @param gameID
	 * @param color
	 */
	@Override
	public void addUser(RegisteredPersonInfo person){
		
	}

	
}
