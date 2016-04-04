package server.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import server.util.GameCombo;
import server.util.ServerGameModel;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * Interface for the GameDAO
 * both the SQLUserDao and the XMLUserDAO inherit from this interface
 * @author Brennen
 *
 */
public interface IGameDAO {
	/**
	 * gets all the games from the database/file
	 * should be overridden by the DAOs
	 * @return List<GameCombo>
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public List<GameCombo> getGames() throws FileNotFoundException, IOException;
	
	/**
	 * adds a command to the command table or file
	 * @param command
	 * @param int gameID
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public void addCommand(MoveCommand command, int gameID) throws FileNotFoundException, IOException;
	/**
	 * adds a game to the game table or file
	 * @param id of game
	 * @param model ServerGameModel
	 * @param title String - title of game
	 * @throws IOException 
	 */
	public void addGame(int id, ServerGameModel model, String title) throws IOException;
		
	/**
	 * updates the game in game table or xml file
	 * @param gameID
	 * @param model ServerGameModel
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public void updateGame(int gameID, ServerGameModel model) throws FileNotFoundException, IOException;
	/**
	 * deletes the commands from the database
	 * @param gameID
	 */
	public void deleteCommands(int gameID);
	
	/**
	 * Adds to the join user section of the database
	 * @param int userID
	 * @param int gameID
	 * @param CatanColor color
	 * @param int playerIndex
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex) throws FileNotFoundException, IOException;
}
