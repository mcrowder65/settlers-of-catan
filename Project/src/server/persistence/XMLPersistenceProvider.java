package server.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.dao.XMLGameDAO;
import server.dao.XMLUserDAO;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameModel;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * Stores the delta between keys
 * Uses the DAO's to write to the XML files
 * @author Brennen
 *
 */
public class XMLPersistenceProvider extends PersistenceProvider{
	
	
	/**
	 * constructor for XMLPersistenceProvider
	 * @param commandCount
	 */
	public XMLPersistenceProvider(int commandCount){
		super(commandCount);
		
		this.gameDAO = this.createGameDAO();
		this.userDAO = this.createUserDAO();
		
	}
	

	/**
	 * adds a command to the array of commands 
	 * calls add command on the XML DAO
	 * @param command MoveCommand
	 */
	@Override
	public void addCommand(MoveCommand command) {
		try {
			gameDAO.addCommand(command, command.getGameCookie());
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * loads a game into memory via the gameID
	 */
	@Override
	public List<GameCombo> loadGames() {
		return null;
	}

	@Override
	protected void flushGame(int gameID, ServerGameModel model) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * creates an new UserDAO for an xml file 
	 * @return a IUserDAO
	 */
	@Override
	public IUserDAO createUserDAO() {
		return new XMLUserDAO();
	}

	/**
	 * creates the gameDAO
	 * @return a IGameDAO
	 */
	@Override
	public IGameDAO createGameDAO() {
		return new XMLGameDAO();
	}
	
	/**
	 * calls the userDAO to add a user to the xml file
	 * @param userID int
	 * @param gameID int
	 * @param color CatanColor 
	 * @param playerIndex int
	 */
	@Override
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex){
		
	}
	
	/**
	 * calls addUser on the XMLUserDAO
	 * person RegisteredPersonInfo
	 */
	@Override
	public void addUser(RegisteredPersonInfo person){
		
	}

	/**
	 * is not implemented because we are writing to an XML file
	 */
	@Override
	public void startTransaction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * is not implemented because we are writing to an XML file
	 * @param commit boolean
	 */
	@Override
	public void endTransaction(boolean commit) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<MoveCommand> getCommands(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void dropTables() {
		// TODO Auto-generated method stub
		
	}
	
}
