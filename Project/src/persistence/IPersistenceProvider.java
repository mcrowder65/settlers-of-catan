package persistence;

import dao.IUserDAO;
import shared.communication.request.MoveCommand;

public interface IPersistenceProvider {
	public void startTransaction();
	public void endTransaction();
	public void addCommand(MoveCommand command);
	public void setup(int commandCount);
	public void loadGame(int gameID);
	public void flushGame(int gameID);
	public IUserDAO createUserDAO();
}
