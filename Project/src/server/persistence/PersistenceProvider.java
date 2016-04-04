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
	
	/**
	 * num of commands before we write to disk
	 */
	protected int max;
	/**
	 * array of the move commands before we write to disk
	 */
	protected MoveCommand[] commands;
	
	/**
	 * constructor for PersistenceProvider
	 * @param commandCount int
	 */
	public PersistenceProvider(int commandCount) {
		max = commandCount;
		commands = new MoveCommand[max];
	}
	
	/**
	 * starts an SQL transaction
	 */
	public abstract void startTransaction() throws DatabaseException;
	/**
	 * ends a sql transaction
	 * @param commit boolean
	 */
	public abstract void endTransaction(boolean commit);
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
	 */
	public abstract void joinUser(int userID, int gameID, CatanColor color);
	
}
