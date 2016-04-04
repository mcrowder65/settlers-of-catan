package server.persistence;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.dao.SQLGameDAO;
import server.dao.SQLUserDAO;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameModel;
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
		connection = null;
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
			safeClose();
			connection = null;
		}
		
	}
	/**
	 * safely closes a connection, making sure it isn't null
	 */
	private void safeClose() 
	{
		if (connection != null) {
			try {
				connection.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
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
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			endTransaction(false);
		}
		
	}
	
	/**
	 * loads a game into memory via the gameID 
	 * calls the DAOs to do this
	 * returns null if it fails
	 */
	@Override
	public List<GameCombo> loadGames() {
		ArrayList<GameCombo> games = null;
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return null;
		}
		try {
			games = (ArrayList<GameCombo>) gameDAO.getGames();
			endTransaction(true);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			endTransaction(false);
			games = null;
		}
		
		return games;
	}

	/**
	 * Writes the entire game model to the database
	 * Clears the commands table
	 * @param gameID int
	 * @param serverGameModel ServerGameModel
	 */
	@Override
	protected void flushGame(int gameID, ServerGameModel serverGameModel) {
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return;
		}
		try {	
			gameDAO.updateGame(gameID, serverGameModel);
			endTransaction(true);
		} catch (Exception e) {
			e.printStackTrace();
			endTransaction(false);
		}
		
	}
	
	/**
	 * creates an new UserDAO for the SQL database
	 * @return a IUserDAO
	 */
	@Override
	public IUserDAO createUserDAO() {
		return new SQLUserDAO(connection);
	}

	/**
	 * Creates the game DAO 
	 * @return a IGameDAO 
	 */
	@Override
	public IGameDAO createGameDAO() {
		return new SQLGameDAO(connection);
	}
	
	/**
	 * calls the userDAO to add a user to the join table
	 * @param userID int
	 * @param gameID int
	 * @param color CatanColor
	 */ 
	@Override
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex){
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return;
		}
		try {
			gameDAO.joinUser(userID, gameID, color, playerIndex);
			endTransaction(true);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			endTransaction(false);
		}
	}
	
	/**
	 * calls addUser on the SQLUserDAO
	 * @param person RegisteredPersonInfo
	 */
	@Override
	public void addUser(RegisteredPersonInfo person){
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return;
		}
		try {
			userDAO.addUser(person);
			endTransaction(true);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			endTransaction(false);
		}
		
	}

	/**
	 * gets the commands associated with a game id, returns null if something broke
	 * @param gameID int
	 */
	@Override
	public List<MoveCommand> getCommands(int gameID) {
		List<MoveCommand> commands = null;
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return commands;
		}
		try {
			commands = gameDAO.getCommands(gameID);
			endTransaction(true);
		} catch (Exception e) {
			e.printStackTrace();
			endTransaction(false);
			return null;
		}
		return commands;
	}

	@Override
	public void dropTables() {
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return;
		}
		try {
			gameDAO.dropTables();
			endTransaction(true);
		} catch (SQLException e) {
			e.printStackTrace();
			endTransaction(false);
		}
	}

	
}
