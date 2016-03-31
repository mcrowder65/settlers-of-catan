package dao;

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
	public List<GameCombo> getGames();
	public void addCommand(MoveCommand command);
	public void updateGame(int gameID);
	public void deleteCommands(int gameID);
	public void joinUser(int userID, int gameID, CatanColor color);
}
