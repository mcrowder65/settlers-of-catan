package server.persistence;

import java.util.List;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
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
	 * @param commandCount
	 */
	public PersistenceProvider(int commandCount) {
		max = commandCount;
		commands = new MoveCommand[max];
	}
	
	/**
	 * starts an SQL transaction
	 */
	public abstract void startTransaction();
	/**
	 * ends a sql transaction
	 * @param boolean commit
	 */
	public abstract void endTransaction(boolean commit);
	/**
	 * adds a command to the command array 
	 * calls the add a command to the DAOs
	 * @param MoveCommand command
	 */
	public abstract void addCommand(MoveCommand command);
	/**
	 * gets all of the games stored 
	 * @return List<GameCombo>
	 */
    public abstract List<GameCombo> loadGames();
    /**
     * writes game to disk
     * @param int gameID
     */
    protected abstract void flushGame(int gameID);
    /**
     * creates the UserDAO
     * @return IUserDAO
     */
	public abstract IUserDAO createUserDAO();
	/**
	 * creates the gameDAO
	 * @return IGameDAO
	 */
	public abstract IGameDAO createGameDAO();
	/**
	 * adds a user to disk
	 * @param RegisteredPersonInfo person
	 */
	public abstract void addUser(RegisteredPersonInfo person);
	/**
	 * adds a user to the joinUser table/file
	 * @param userID
	 * @param gameID
	 * @param color
	 */
	public abstract void joinUser(int userID, int gameID, CatanColor color);
	
}
