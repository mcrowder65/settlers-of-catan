package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.utils.Translator;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameModel;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * adds/retrieves game data from the SQL database
 * inherits from IGameDAO
 * @author Brennen
 *
 */
public class SQLGameDAO implements IGameDAO{
	private Connection conn;
	/**
	 * constructor for SQLGameDAO
	 */
	public SQLGameDAO(Connection conn){
		this.conn = conn;
	}
	
	/**
	 * gets all the games in the game table of the SQL database
	 * @return List<GameCombo>
	 * @return null if there are no games
	 */
	@Override
	public List<GameCombo> getGames() {
		return null;
	}

	@Override
	public void addCommand(MoveCommand command, int gameID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			PreparedStatement pstmt = null;
			String mysqlstring="insert into commands (data, game_id) "
					+ "values (?, ?);";
			pstmt = conn.prepareStatement(mysqlstring);
			pstmt.setString(1, command.getMoveType());
			pstmt.setInt(2, gameID);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * updates a game in the SQL database
	 *@param int gameID
	 *@param ServerGameModel model
	 */
	@Override
	public void updateGame(int gameID, ServerGameModel model) {
		String json = Translator.modelToJson(model);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			PreparedStatement pstmt = null;
			String mysqlstring="update games set data = ? where games.id =  " + gameID + ";";
			pstmt = conn.prepareStatement(mysqlstring);
			pstmt.setString(1, json);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * deletes commands 
	 * @param int gameID
	 */
	@Override
	public void deleteCommands(int gameID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			PreparedStatement pstmt = null;
			String mysqlstring="delete from commands where game_id=" + gameID + ";";
			pstmt = conn.prepareStatement(mysqlstring);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * adds to the joinUserTable
	 * @param int UserID
	 * @param int gameID
	 * @param CatanColor color
	 */
	@Override
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex) {
		String sColor = color.toString();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			PreparedStatement pstmt = null;
			String mysqlstring="insert into 'game membership' (user_id, game_id, color, playerIndex) "
					+ "values (?, ?, ?, ?);";
			pstmt = conn.prepareStatement(mysqlstring);
			pstmt.setInt(2, userID);
			pstmt.setInt(3, gameID);
			pstmt.setString(4, sColor);
			pstmt.setInt(playerIndex, playerIndex);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * adds a game
	 * @param int id
	 * @param ServerGameModel model
	 * @param String title
	 */
	@Override
	public void addGame(int id, ServerGameModel model, String title) {
		String json = Translator.modelToJson(model);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			PreparedStatement pstmt = null;
			String mysqlstring="insert into games (id, data, title) values (?, ?, ?);";
			pstmt = conn.prepareStatement(mysqlstring);
			pstmt.setInt(1, id);
			pstmt.setString(2, json);
			pstmt.setString(3, title);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
	}

}
