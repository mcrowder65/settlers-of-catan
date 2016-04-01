package server.dao;

import java.util.List;

import server.util.GameCombo;
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
	 */
	public void addCommand(MoveCommand command);
	
	/**
	 * updates the game in game table or xml file
	 * @param gameID
	 */
	public void updateGame(int gameID);
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
	 */
	public void joinUser(int userID, int gameID, CatanColor color);
}