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
		 ArrayList<GameCombo> games = null;
	        try {
				Class.forName("com.mysql.jdbc.Driver");
				PreparedStatement pstmt = null;
				String mysqlstring="Select id from join;";
				pstmt = conn.prepareStatement(mysqlstring);
				ResultSet set = pstmt.executeQuery();
				games = new ArrayList<GameCombo>();
				while(set.next()) { //id, name, password
					
					int id = set.getInt(1);
					String data = set.getString(2);
					String title = set.getString(3);
					
				}
				pstmt.close();
			} catch (ClassNotFoundException|SQLException e) {
				e.printStackTrace();
			}
	        
			return games;
	}

	
	@Override
	public void addCommand(MoveCommand command) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * updates a game in the SQL database
	 *@param int gameID
	 *@param ServerGameModel model
	 */
	@Override
	public void updateGame(int gameID, ServerGameModel model) {
		String json = Translator.modelToJson(model);
		
		
		
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
			String mysqlstring="delete from commands where game_id=1;";
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
		// TODO Auto-generated method stub
		
	}

	/**
	 * adds a game
	 * @param int id
	 * @param ServerGameModel model
	 * @param String title
	 */
	@Override
	public void addGame(int id, ServerGameModel model, String title) {
		// TODO Auto-generated method stub
		
	}

}
