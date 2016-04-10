package server.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.Game;
import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.dao.SQLGameDAO;
import server.dao.SQLUserDAO;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameModel;
import shared.communication.request.BuyDevCardCommand;
import shared.communication.request.MoveCommand;
import shared.communication.request.RobPlayerCommand;
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
	
	
	public SQLPersistenceProvider() {
		
	}
	
	/**
	 * constructor for SQL Persistence Provider.
	 *  Sets how many commands need to be executed before we write to disk
	 * @param commandCount int
	 * 
	 */
	public SQLPersistenceProvider(int commandCount){
		super(commandCount);
		connection = null;
		userDAO = createUserDAO();
		gameDAO = createGameDAO();
		File directory = new File("database/"); //getting the directory

		if(!directory.exists()){
			//make a new directory
			directory.mkdirs();
			File db = new File("db.sqlite");
			try {
				db.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			resetPersistence(); //inits the db
		}
	}
	
	/**
	 * Starts an SQL transaction 
	 * @throws DatabaseException 
	 */
	@Override
	protected void startTransaction() throws DatabaseException {
		try {	
			System.out.println("start transaction");
			connection = DriverManager.getConnection(DATABASE_URL);
			connection.setAutoCommit(false);
			userDAO.setConnection(connection);
			gameDAO.setConnection(connection);
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
	protected void endTransaction(boolean commit) {
		System.out.println("end transaction");
		try {
			if (commit) {
				connection.commit();
			}
			else {
				connection.rollback();
			}
			
		}
		catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			safeClose();
			connection = null;
		}
		gameDAO.setConnection(connection);
		userDAO.setConnection(connection);
		
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
		System.out.println("addCommand");
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			endTransaction(false);
			return;
		}
		try {
			gameDAO.addCommand(command, command.getGameCookie());
			endTransaction(true);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			endTransaction(false);
			return;
		}
		
		
		commands[currentCommandCount++] = command;
		if (currentCommandCount >= max) {
			currentCommandCount = 0;
			flushGame(command.getGameCookie(), Game.instance().getGameId(command.getGameCookie()));
			for (int n = 0; n < commands.length; n++)
				commands[n] = null;
			
		}
		
	}
	
	/**
	 * loads a game into memory via the gameID 
	 * calls the DAOs to do this
	 * returns null if it fails
	 */
	@Override
	public List<GameCombo> loadGames() {
		System.out.println("loadGames");
		ArrayList<GameCombo> games = null;
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			endTransaction(false);
			return null;
		}
		try {
			games = (ArrayList<GameCombo>) gameDAO.getGames();
			endTransaction(true);
			return games;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			endTransaction(false);
			games = null;
		}
		return null;
		
	}
	@Override
	public void executeCommands(List<GameCombo> games){
		System.out.println("executeCommands");
		try {
			startTransaction();
		} catch (DatabaseException e2) {
			e2.printStackTrace();
			endTransaction(false);
			return;
			
		}
		for(int i = 0; i < games.size(); i++){
			ArrayList<MoveCommand> commands = null;
			try {
				commands = (ArrayList<MoveCommand>) gameDAO.getCommands(i);
			} catch (Exception e1) {
				e1.printStackTrace();
				endTransaction(false);
				return;
			}
			for(int x = 0; x < commands.size(); x++){
				String moveType = commands.get(x).getMoveType();
				if(moveType.equals("robPlayer")){ //check for reexecutes
					RobPlayerCommand robPlayer = (RobPlayerCommand) commands.get(x);
					robPlayer.reExecute();
				}
				else if(moveType.equals("buyDevCard")){ //check for reexecutes
					BuyDevCardCommand devCard = (BuyDevCardCommand) commands.get(x);
					devCard.reExecute();
				}
				else{
					commands.get(x).execute();
				} 	
			}
			try {
				gameDAO.deleteCommands(games.get(i).info.getId());
			} catch (IllegalArgumentException | SQLException | IOException e) {
				e.printStackTrace();
				endTransaction(false);
				return;
			} 
			try {
				gameDAO.updateGame(games.get(i).info.getId(),games.get(i).model);
			} catch (Exception e) {
				e.printStackTrace();
				endTransaction(false);
				return;
			}
		}
		endTransaction(true);
		
	}
	/**
	 * Writes the entire game model to the database and deletes all the commands.
	 * Clears the commands table
	 * @param gameID int
	 * @param serverGameModel ServerGameModel
	 */
	@Override
	protected void flushGame(int gameID, ServerGameModel serverGameModel) {
		System.out.println("flushGame");
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			endTransaction(false);
			return;
		}
		try {	
			gameDAO.updateGame(gameID, serverGameModel);
			gameDAO.deleteCommands(gameID);
			endTransaction(true);
		} catch (Exception e) {
			e.printStackTrace();
			endTransaction(false);
			return;
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
		System.out.println("joinUser");
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			endTransaction(false);
			return;
		}
		try {
			gameDAO.joinUser(userID, gameID, color, playerIndex);
			endTransaction(true);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			endTransaction(false);
			return;
		}
	}
	
	/**
	 * calls addUser on the SQLUserDAO
	 * @param person RegisteredPersonInfo
	 */
	@Override
	public void addUser(RegisteredPersonInfo person){
		System.out.println("addUser");
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			endTransaction(false);
			return;
		}
		try {
			userDAO.addUser(person);
			endTransaction(true);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			endTransaction(false);
			return;
		}
		
	}

	/**
	 * gets the commands associated with a game id, returns null if something broke
	 * @param gameID int
	 */
	@Override
	public List<MoveCommand> getCommands(int gameID) {
		System.out.println("getCommands");
		List<MoveCommand> commands = null;
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			endTransaction(false);
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

	/**
	 * this drops all the tables in the sqlite db
	 */
	@Override
	public void resetPersistence() {
		System.out.println("resetPersistence");
		try {
			startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
			endTransaction(false);
			return;
		}
		try {
			gameDAO.resetPersistence();
			endTransaction(true);
		} catch (SQLException e) {
			e.printStackTrace();
			endTransaction(false);
			return;
		}
	}

	@Override
	public List<RegisteredPersonInfo> getUsers() {
		System.out.println("getUsers");
		try {
			startTransaction();
		} catch (DatabaseException e1) {
			e1.printStackTrace();
			endTransaction(false);
			return null;
		}
		try {
			List<RegisteredPersonInfo> users = userDAO.getUsers();
			endTransaction(true);
			return users == null ? new ArrayList<RegisteredPersonInfo>() : users;
		} catch (IOException|SQLException e) {
			e.printStackTrace();
			endTransaction(false);
			return null;
		}
		
	}

	@Override
	public void addGame(int id, ServerGameModel model, String title) {
		System.out.println("addGame");
		try {
			startTransaction();
		} catch (DatabaseException e) {
			endTransaction(false);
			return;
		}
		try {
			gameDAO.addGame(id, model, title);
			endTransaction(true);
		} catch (IOException|SQLException e) {
			e.printStackTrace();
			endTransaction(false);
			return;
		}
		
	}
	
	@Override
	public void update(int gameID, ServerGameModel model){
		System.out.println("update");
		try {
			startTransaction();
		} catch (DatabaseException ex) {
			ex.printStackTrace();
			endTransaction(false);
			return;
		}
		try
		{
			gameDAO.updateGame(gameID, model);
			gameDAO.deleteCommands(gameID);
			endTransaction(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			endTransaction(false);
			return;
		}
	}
	


	

	
}
