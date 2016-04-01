package server.dao;

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
	 */
	public List<GameCombo> getGames();
	
	/**
	 * adds a command to the command table or file
	 * @param command
	 * @param int gameID
	 */
	public void addCommand(MoveCommand command, int gameID);
	/**
	 * adds a game to the game table
	 * @param id of game
	 * @param model ServerGameModel
	 * @param title String - title of game
	 */
	public void addGame(int id, ServerGameModel model, String title);
		
	/**
	 * updates the game in game table or xml file
	 * @param gameID
	 * @param model ServerGameModel
	 */
	public void updateGame(int gameID, ServerGameModel model);
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
	 */
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex);
}
