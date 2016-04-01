package server.persistence;

import java.util.List;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;

public class XMLPersistenceProvider extends PersistenceProvider{
	
	
	public XMLPersistenceProvider(int commandCount){
		super(commandCount);
		
	}
	

	/**
	 * Starts a transaction to write to an XML file
	 */
	@Override
	public void startTransaction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ends a transaction when writing to an XML file
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
	 * creates an new UserDAO for an xml file 
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
	
	/**
	 * calls the userDAO to add a user to the xml file
	 * @param int userID, int gameID, CatanColor color
	 */
	@Override
	public void joinUser(int userID, int gameID, CatanColor color){
		
	}
	
	/**
	 * calls addUser on the XMLUserDAO
	 * @param userID
	 * @param gameID
	 * @param color
	 */
	@Override
	public void addUser(RegisteredPersonInfo person){
		
	}
	
}
