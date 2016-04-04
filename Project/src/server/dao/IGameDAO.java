package server.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
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
	 * @return a list of GameCombos 
	 * @throws FileNotFoundException if the file isn't found
	 * @throws IOException if an error occurs
	 */
	public List<GameCombo> getGames() throws FileNotFoundException, IOException, SQLException;
	
	/**
	 * adds a command to the command table or file
	 * @param command MoveCommand
	 * @param gameID int
	 * @throws FileNotFoundException if file isn't found
	 * @throws IOException if error occurs
	 */
	public void addCommand(MoveCommand command, int gameID) throws FileNotFoundException, IOException, SQLException;
	/**
	 * adds a game to the game table or file
	 * @param id - int (of game)
	 * @param model ServerGameModel
	 * @param title String - title of game
	 * @throws IOException if error occurs
	 */
	public void addGame(int id, ServerGameModel model, String title) throws IOException, SQLException;
		
	/**
	 * updates the game in game table or xml file
	 * @param gameID - int
	 * @param model ServerGameModel
	 * @throws FileNotFoundException if the file isn't found
	 * @throws IOException if error occurs
	 */
	public void updateGame(int gameID, ServerGameModel model) throws FileNotFoundException, IOException, Exception;
	/**
	 * gets the commands for a certain game
	 * @param gameID int
	 */
	public List<MoveCommand> getCommands(int gameID) throws Exception;
	/**
	 * deletes the commands from the database
	 * @param gameID int
	 * @throws IOException 
	 */
	public void deleteCommands(int gameID) throws SQLException, IOException;
	
	/**
	 * Adds to the join user section of the database
	 * @param userID int
	 * @param gameID int
	 * @param color CatanColor
	 * @param playerIndex int 
	 * @throws FileNotFoundException if the file isn't found
	 * @throws IOException if an error
	 */
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex) throws FileNotFoundException, IOException, SQLException;
	
	/**
	 * drops the tables in the sqlite db or xml
	 * @throws SQLException 
	 */
	public void dropTables() throws SQLException;

}
