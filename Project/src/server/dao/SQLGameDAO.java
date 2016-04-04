package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.data.GameInfo;
import client.data.PlayerInfo;
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
	/**
	 * connection to the SQL database
	 */
	private Connection conn;
	/**
	 * constructor for SQLGameDAO
	 */
	public SQLGameDAO(Connection conn){
		this.conn = conn;
	}
	
	/**
	 * gets all the games in the game table of the SQL database
	 * @return a list of GameCombos
	 * @return null if there are no games
	 */
	@Override
	public List<GameCombo> getGames() {
		//GameCombo - ServerGameModel, GameInfo
		//ServerGameModel from games list data
		//GameInfo from game membership and games title
		 ArrayList<ServerGameModel> serverGameModels = null;
		 ArrayList<String> titles = null; //the id's of the games are -1 of what they are 
		 								  //in the database, since arraylists are 0 indexed
	        try {
				Class.forName("com.mysql.jdbc.Driver");
				PreparedStatement pstmt = null;
				String mysqlstring="Select * from games;";
				pstmt = conn.prepareStatement(mysqlstring);
				ResultSet set = pstmt.executeQuery();
				serverGameModels = new ArrayList<ServerGameModel>();
				titles = new ArrayList<String>();
				while(set.next()) {
					
					int id = set.getInt(1);
					String serverGameModelString = set.getString(2);
					ServerGameModel serverGameModelObject = (ServerGameModel) 
							Translator.makeGenericObject(serverGameModelString, ServerGameModel.class);
					String title = set.getString(3);
					serverGameModels.add(serverGameModelObject);
					titles.add(title);
				}
				pstmt.close();
			} catch (ClassNotFoundException|SQLException e) {
				e.printStackTrace();
			}
	        
	        ArrayList<ArrayList<PlayerInfo>> players = new ArrayList<ArrayList<PlayerInfo>>();
	        for(int i = 0; i < serverGameModels.size(); i++){
	        	players.add(new ArrayList<PlayerInfo>()); //initialize i amount of games with 4 players(ish)
	        }
	        for(int i = 0; i < titles.size(); i++){
	        	try {
					Class.forName("com.mysql.jdbc.Driver");
					PreparedStatement pstmt = null;
					String mysqlstring="Select * from game_membership where game_id = " + (i+1) + ";";
					pstmt = conn.prepareStatement(mysqlstring);
					ResultSet set = pstmt.executeQuery();
					
					while(set.next()) { 
						
						int id = set.getInt(1);
						int user_id = set.getInt(2);
						int game_id = set.getInt(3);
						CatanColor color = Translator.getCatanColor(set.getString(4));
						int playerIndex = set.getInt(5);
						try {
							Class.forName("com.mysql.jdbc.Driver");
							PreparedStatement pst = null;
							String sql="Select * from users where id = " + user_id + ";";
							pst = conn.prepareStatement(sql);
							ResultSet rSet = pst.executeQuery();
							
							while(rSet.next()) { 
								
								int pId = rSet.getInt(1);
								String user = rSet.getString(2);
								String pass = rSet.getString(3);
								players.get(i).add(new PlayerInfo(user_id, user, color, playerIndex));
							}
							pst.close();
						} catch (ClassNotFoundException|SQLException e) {
							e.printStackTrace();
						}
					}
					pstmt.close();
					
				} catch (ClassNotFoundException|SQLException e) {
					e.printStackTrace();
				}
	        }
	        
	    ArrayList<GameCombo> games = new ArrayList<GameCombo>();
	    for(int i = 0; i < titles.size(); i++){
	    	GameCombo temp = new GameCombo();
	    	temp.info = new GameInfo(i+1, titles.get(i), players.get(i));
	    	temp.model = serverGameModels.get(i);
	    	games.add(temp);
	    }
		return games;
	}

	/**
	 * adds a command to the SQL command table
	 * @param command MoveCommand
	 * @param gameID int
	 */
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
	 *@param gameID int
	 *@param model ServerGameModel
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
	 * deletes commands from command SQL table
	 * @param gameID int
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
	 * @param userID int
	 * @param gameID int
	 * @param color CatanColor
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
	 * adds a game to the SQL games table
	 * @param id int
	 * @param model ServerGameModel
	 * @param title String
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

	@Override
	public void getCommands(int gameID) {
		// TODO Auto-generated method stub
		
	}

}
