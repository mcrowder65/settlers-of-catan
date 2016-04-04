package server.persistence;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.dao.SQLGameDAO;
import server.dao.SQLUserDAO;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * Stores the delta between keys
 * Uses the DAO's to write to the SQL database
 * @author Brennen
 *
 */
public class SQLPersistenceProvider extends PersistenceProvider{
	private Connection connection;
	private SQLUserDAO userDAO;
	private SQLGameDAO gameDAO;
	private static final String DATABASE_DIRECTORY = "database";
	

	private static final String DATABASE_FILE = "db.sqlite";
	private static final String DATABASE_URL = "jdbc:sqlite:"  + DATABASE_DIRECTORY + 
												File.separator + DATABASE_FILE;
	/**
	 * constructor for SQL Persistence Provider.
	 *  Sets how many commands need to be executed before we write to disk
	 * @param commandCount int
	 * 
	 */
	public SQLPersistenceProvider(int commandCount){
		super(commandCount);
		//new dao's, new connection
		userDAO = new SQLUserDAO(connection);
		gameDAO = new SQLGameDAO(connection);
	}
	
	/**
	 * Starts an SQL transaction 
	 * @throws DatabaseException 
	 */
	@Override
	public void startTransaction() throws DatabaseException {
		try {	
			connection = DriverManager.getConnection(DATABASE_URL);
			connection.setAutoCommit(false);
		}
		catch (SQLException e) {
			throw new DatabaseException("Could not connect to database. Make sure " + 
				DATABASE_FILE + " is available in ./" + DATABASE_DIRECTORY, e);
		}
		
	}

	/**
	 * Ends an SQL Transaction
	 * @param commit boolean
	 */
	@Override
	public void endTransaction(boolean commit) {
		try {
			if (commit) {
				connection.commit();
			}
			else {
				connection.rollback();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			safeClose(connection);
			connection = null;
		}
		
	}
	/**
	 * 
	 * @param conn
	 */
	public static void safeClose(Connection conn) 
	{
		if (conn != null) 
		{
			try 
			{
				conn.close();
			}
			catch (SQLException e) 
			{
				
			}
		}
	}
	/**
	 * adds a command to the array of commands 
	 * calls the command method on the DAOs
	 * @param command MoveCommand
	 */
	@Override
	public void addCommand(MoveCommand command) {
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return;
		}
		try {
			gameDAO.addCommand(command, command.getGameCookie());
			endTransaction(true);
		} catch (SQLException e) {
			e.printStackTrace();
			endTransaction(false);
		}
		
	}
	
	/**
	 * loads a game into memory via the gameID 
	 * calls the DAOs to do this
	 */
	@Override
	public List<GameCombo> loadGames() {
		return null;
	}

	/**
	 * Writes the entire game model to the database
	 * Clears the commands table
	 * @param gameID int
	 */
	@Override
	protected void flushGame(int gameID) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * creates an new UserDAO for the SQL database
	 * @return a IUserDAO
	 */
	@Override
	public IUserDAO createUserDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates the game DAO 
	 * @return a IGameDAO 
	 */
	@Override
	public IGameDAO createGameDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * calls the userDAO to add a user to the join table
	 * @param userID int
	 * @param gameID int
	 * @param color CatanColor
	 */ 
	@Override
	public void joinUser(int userID, int gameID, CatanColor color){
		
	}
	
	/**
	 * calls addUser on the SQLUserDAO
	 * @param person RegisteredPersonInfo
	 */
	@Override
	public void addUser(RegisteredPersonInfo person){
		
	}

	
}
