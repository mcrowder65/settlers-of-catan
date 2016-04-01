package server.persistence;

import java.util.List;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * Interface for the two different persistance providers - XML and SQL
 * @author Brennen
 */
public abstract class PersistenceProvider {
	
	protected int max;
	protected MoveCommand[] commands;
	
	public PersistenceProvider(int commandCount) {
		max = commandCount;
		commands = new MoveCommand[max];
	}
	
	public abstract void startTransaction();
	public abstract void endTransaction(boolean commit);
	public abstract void addCommand(MoveCommand command);
	
    public abstract List<GameCombo> loadGames();
    protected abstract void flushGame(int gameID);
	public abstract IUserDAO createUserDAO();
	public abstract IGameDAO createGameDAO();
	public abstract void addUser(RegisteredPersonInfo person);
	public abstract void joinUser(int userID, int gameID, CatanColor color);
	
}
