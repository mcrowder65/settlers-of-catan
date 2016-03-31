package dao;

import java.util.List;

import server.util.GameCombo;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * adds games to an XML file
 * inherits from IGameDAO
 * @author Brennen
 *
 */
public class XMLGameDAO implements IGameDAO{
	/**
	 * constructor for XMLGameDAO
	 */
	public XMLGameDAO(){}
	
	/**
	 * gets all the games in the XML file
	 * @return List<GameCombo>
	 * @return null if there are no games
	 */
	@Override
	public List<GameCombo> getGames() {
		// TODO Auto-generated method stub
		return null;
	}

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
	 * deletes commands 
	 * @param int gameID
	 */
	@Override
	public void deleteCommands(int gameID) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * @param int UserID
	 * @param int gameID
	 * @param CatanColor color
	 */
	@Override
	public void joinUser(int userID, int gameID, CatanColor color) {
		// TODO Auto-generated method stub
		
	}

}
