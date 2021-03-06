package server.persistence;

import java.util.List;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameModel;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * Abstract Class for the two different persistence providers - XML and SQL
 * @author Brennen
 */
public abstract class PersistenceProvider {
	protected IUserDAO userDAO;
	protected IGameDAO gameDAO;
	/**
	 * num of commands before we write to disk
	 */
	protected int max;
	
	
	protected int currentCommandCount = 0;
	/**
	 * array of the move commands before we write to disk
	 */
	protected MoveCommand[] commands;
	
	
	public PersistenceProvider() {
		
	}
	/**
	 * constructor for PersistenceProvider
	 * @param commandCount int
	 */
	public PersistenceProvider(int commandCount) {
		setup(commandCount);
	}
	
	public void setup(int commandCount) {
		max = commandCount;
		commands = new MoveCommand[max];
	}
	
	/**
	 * starts an SQL transaction
	 */
	protected abstract void startTransaction() throws DatabaseException;
	/**
	 * ends a sql transaction
	 * @param commit boolean
	 */
	protected abstract void endTransaction(boolean commit);
	/**
	 * adds a command to the command array 
	 * calls the add a command to the DAOs
	 * @param command MoveCommand
	 */
	public abstract void addCommand(MoveCommand command);
	/**
	 * gets all of the games stored 
	 * @return a list of GameCombo
	 */
    public abstract List<GameCombo> loadGames();
    
    public abstract void executeCommands(List<GameCombo> games);
    
    public abstract void addGame(int id, ServerGameModel model, String title);
    /**
     * writes game to disk
     * @param gameID int
     */
    protected abstract void flushGame(int gameID, ServerGameModel serverGameModel);
    /**
     * creates the UserDAO
     * @return a IUserDAO
     */
	public abstract IUserDAO createUserDAO();
	/**
	 * creates the gameDAO
	 * @return a IGameDAO
	 */
	public abstract IGameDAO createGameDAO();
	/**
	 * adds a user to disk
	 * @param person RegisteredPersonInfo 
	 */
	public abstract void addUser(RegisteredPersonInfo person);
	/**
	 * adds a user to the joinUser table/file
	 * @param userID int
	 * @param gameID int
	 * @param color CatanColor
	 * @param playerIndex int
	 */
	public abstract void joinUser(int userID, int gameID, CatanColor color, int playerIndex);
	
	
	
	public abstract List<RegisteredPersonInfo> getUsers();
	/**
	 * gets the commands associated with a gameID
	 * @param gameID int
	 */
	public abstract List<MoveCommand> getCommands(int gameID);
	
	/**
	 * this deletes the xml stuff and/or drops the sql tables
	 */
	public abstract void resetPersistence();
	public void update(int gameID, ServerGameModel model) {
		// TODO Auto-generated method stub
		
	}
	

}
