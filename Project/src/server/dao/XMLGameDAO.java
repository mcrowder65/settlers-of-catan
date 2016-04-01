package server.dao;

import java.util.List;

import server.util.GameCombo;
import server.util.ServerGameModel;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * adds updates games in an XML file
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
	/**
	 * adds a command to the XML file
	 * @param MoveCommand - the move that should be added
	 * @param gameID int the id of the game.
	 */
	@Override
	public void addCommand(MoveCommand command, int gameID) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * updates a game in the XML file
	 *@param int gameID
	 *@param ServerGameModel model
	 */
	@Override
	public void updateGame(int gameID, ServerGameModel model) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * deletes commands from the XML file
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
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * this adds a game
	 * @param int id
	 * @param ServerGameModel model
	 * @param String title
	 */
	@Override
	public void addGame(int id, ServerGameModel model, String title) {
		// TODO Auto-generated method stub
		
	}

}
