package server.dao;

import java.util.List;

import server.util.GameCombo;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * adds/retrieves game data from the SQL database
 * inherits from IGameDAO
 * @author Brennen
 *
 */
public class SQLGameDAO implements IGameDAO{
	/**
	 * constructor for SQLGameDAO
	 */
	public SQLGameDAO(){}
	
	/**
	 * gets all the games in the game table of the SQL database
	 * @return List<GameCombo>
	 * @return null if there are no games
	 */
	@Override
	public List<GameCombo> getGames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * inserts the command to the command table in the SQL database
	 * @param MoveCommand
	 */
	@Override
	public void addCommand(MoveCommand command) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * updates a game in the SQL database
	 *@param int gameID
	 */
	@Override
	public void updateGame(int gameID) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * deletes commands from the command SQL table 
	 * @param int gameID
	 */
	@Override
	public void deleteCommands(int gameID) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * adds to the joinUserTable
	 * @param int UserID
	 * @param int gameID
	 * @param CatanColor color
	 */
	@Override
	public void joinUser(int userID, int gameID, CatanColor color) {
		// TODO Auto-generated method stub
		
	}

}
