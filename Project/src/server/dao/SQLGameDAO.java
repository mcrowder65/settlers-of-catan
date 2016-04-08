package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import client.data.GameInfo;
import client.data.PlayerInfo;
import client.utils.Translator;
import server.Game;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameMap;
import server.util.ServerGameModel;
import server.util.ServerPlayer;
import server.util.ServerTurnTracker;
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
	private final String driver = "org.sqlite.JDBC";
	/**
	 * constructor for SQLGameDAO
	 */
	public SQLGameDAO(Connection conn){
		this.conn = conn;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	/**
	 * gets all the games in the game table of the SQL database
	 * @return a list of GameCombos
	 * @return null if there are no games
	 */
	@Override
	public List<GameCombo> getGames() throws SQLException{
		//GameCombo - ServerGameModel, GameInfo
		//ServerGameModel from games list data
		//GameInfo from game membership and games title
		 ArrayList<ServerGameModel> serverGameModels = null;
		 ArrayList<String> titles = null; //the id's of the games are -1 of what they are 
		 								  //in the database, since arraylists are 0 indexed
	        try {
				PreparedStatement pstmt = null;
				String mysqlstring="Select * from games;";
				pstmt = conn.prepareStatement(mysqlstring);
				ResultSet set = pstmt.executeQuery();
				serverGameModels = new ArrayList<ServerGameModel>();
				titles = new ArrayList<String>();
				while(set.next()) {
					
					int id = set.getInt(1);
					String serverGameModelString = set.getString(2);
					ServerGameModel serverGameModelObject = 
							Translator.jsonToServerGameModel(serverGameModelString);
					
					String title = set.getString(3);
					serverGameModels.add(serverGameModelObject);
					titles.add(title);
				}
				set.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException();
			}
	        
	        ArrayList<ArrayList<PlayerInfo>> players = new ArrayList<ArrayList<PlayerInfo>>();
	        for(int i = 0; i < serverGameModels.size(); i++){
	        	players.add(new ArrayList<PlayerInfo>()); //initialize i amount of games with 4 players(ish)
	        }
	        for(int i = 0; i < titles.size(); i++){
	        	try {
					PreparedStatement pstmt = null;
					String mysqlstring="Select * from game_membership where game_id = " + (i) + " order by id ASC;";
					pstmt = conn.prepareStatement(mysqlstring);
					ResultSet set = pstmt.executeQuery();
					
					while(set.next()) { 
						
						int id = set.getInt(1);
						int user_id = set.getInt(2);
						int game_id = set.getInt(3);
						CatanColor color = Translator.getCatanColor(set.getString(4));
						int playerIndex = set.getInt(5);
						try {
							
							PreparedStatement pst = null;
							String sql="Select * from users where id = " + user_id + ";";
							pst = conn.prepareStatement(sql);
							ResultSet rSet = pst.executeQuery();
							boolean found = false;
							while(rSet.next()) { 
								
								int pId = rSet.getInt(1);
								String user = rSet.getString(2);
								String pass = rSet.getString(3);
								players.get(i).add(new PlayerInfo(user_id, user, color, playerIndex));
								found = true;
							}
							if (!found) //No player found, add Ai player
								players.get(i).add(new PlayerInfo(user_id, "Temp", color, playerIndex));
							rSet.close();
							pst.close();
						} catch (SQLException e) {
							e.printStackTrace();
							throw new SQLException();
						}
					}
					set.close();
					pstmt.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SQLException();
				}
	        }
	        
	    ArrayList<GameCombo> games = new ArrayList<GameCombo>();
	    for(int i = 0; i < titles.size(); i++){
	    	GameCombo temp = new GameCombo();
	    	temp.info = new GameInfo(i, titles.get(i), players.get(i));
	    	temp.model = serverGameModels.get(i);
	    	
	    	if (temp.model.getServerPlayers() == null) //players haven't been written yet
	    		temp.model.setServerPlayers(new ServerPlayer[4]);
	    	
	    	for (int n = 0; n < temp.info.getPlayers().size(); n++) {
	    		if (temp.model.getServerPlayers()[n] == null)
	    			temp.model.getServerPlayers()[n] = new ServerPlayer();
	    		if (temp.info.getPlayers().size() > n)
	    			temp.model.getServerPlayers()[n].setName(temp.info.getPlayers().get(n));
	    		

	    	}
	    
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
	public void addCommand(MoveCommand command, int gameID) throws SQLException{
		try {
			PreparedStatement pstmt = null;
			String mysqlstring="insert into commands (data, game_id) "
					+ "values (?, ?);";
			pstmt = conn.prepareStatement(mysqlstring);
			pstmt.setString(1, Translator.objectToJson(command));
			pstmt.setInt(2, gameID);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}
	
	/**
	 * updates a game in the SQL database
	 *@param gameID int
	 *@param model ServerGameModel
	 */
	@Override
	public void updateGame(int gameID, ServerGameModel model) throws SQLException{
		String json = Translator.modelToJson(model);
		try {
			PreparedStatement pstmt = null;
			String mysqlstring="update games set data = ? where games.id =  " + gameID + ";";
			pstmt = conn.prepareStatement(mysqlstring);
			pstmt.setString(1, json);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}
	
	/**
	 * deletes commands from command SQL table
	 * @param gameID int
	 */
	@Override
	public void deleteCommands(int gameID) throws SQLException{
		try {
			PreparedStatement pstmt = null;
			String mysqlstring="delete from commands where game_id=" + gameID + ";";
			pstmt = conn.prepareStatement(mysqlstring);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
		
	}
	
	/**
	 * adds to the joinUserTable
	 * @param userID int
	 * @param gameID int
	 * @param color CatanColor
	 */
	@Override
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex) throws SQLException{

		int id = -1;
		try {
			PreparedStatement pst = null;
			String sql="Select * from game_membership where game_id = " + gameID + " and user_id = " + userID + ";";
			pst = conn.prepareStatement(sql);
			ResultSet rSet = pst.executeQuery();
			
			while(rSet.next()) { 
				id = rSet.getInt(1);
			}
			rSet.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
		
		
		String sColor = color.toString();
		
		if(id != -1){
			try {
				PreparedStatement pstmt = null;
				String mysqlstring="update game_membership set color = ? where id = " + id + ";";
				pstmt = conn.prepareStatement(mysqlstring);
				pstmt.setString(1, sColor);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException();
			}
		}
		else{
			try {
				PreparedStatement pstmt = null;
				String mysqlstring="insert into 'game_membership' (user_id, game_id, color, playerIndex) "
						+ "values (?, ?, ?, ?);";
				pstmt = conn.prepareStatement(mysqlstring);
				pstmt.setInt(1, userID);
				pstmt.setInt(2, gameID);
				pstmt.setString(3, sColor);
				pstmt.setInt(4, playerIndex);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException();
			}
		}
	}

	/**
	 * adds a game to the SQL games table
	 * @param id int
	 * @param model ServerGameModel
	 * @param title String
	 */
	@Override
	public void addGame(int id, ServerGameModel model, String title) throws SQLException{
		String json = Translator.modelToJson(model);
		try {
			PreparedStatement pstmt = null;
			String mysqlstring="insert into games (id, data, title) values (?, ?, ?);";
			pstmt = conn.prepareStatement(mysqlstring);
			pstmt.setInt(1, id);
			pstmt.setString(2, json);
			pstmt.setString(3, title);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
		
	}

	/**
	 * @param gameID int
	 * @throws SQLException 
	 */
	@Override
	public List<MoveCommand> getCommands(int gameID) throws SQLException{
		ArrayList<MoveCommand> commands = new ArrayList<MoveCommand>();
		try {
			PreparedStatement pst = null;
			String sql="Select * from commands where game_id = " + gameID + ";";
			pst = conn.prepareStatement(sql);
			ResultSet rSet = pst.executeQuery();
			
			while(rSet.next()) { 
				
				String data = rSet.getString(2);
				MoveCommand command = (MoveCommand) Translator.jsonToObject(data, MoveCommand.class);
				command.setGameCookie(gameID);
				commands.add(command);
			}
			rSet.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
		
		return commands;
	}
	
	@Override
	public void resetPersistence() throws SQLException {
		Statement stmnt = null;
		
		try{
			stmnt = conn.createStatement();
		} 
		catch (SQLException e1){
			e1.printStackTrace();
			throw new SQLException();
		}
		try{
			String sql = "DROP TABLE IF EXISTS commands;";
			sql = sql + "DROP TABLE IF EXISTS game_membership;";
			sql = sql + "DROP TABLE IF EXISTS games;";
			sql = sql + "DROP TABLE IF EXISTS users;";
			stmnt.executeUpdate(sql);
			
			sql = "CREATE TABLE games ("
					+"id integer not null primary key autoincrement ,"
					+"data longtext not null,"
					+"title varchar(255) not null"
				    +");";
			stmnt.executeUpdate(sql);
			
			
			sql = " CREATE TABLE users ("
					+"id integer not null primary key autoincrement,"
					+"user varchar(255) not null,"
					+"pass varchar(255) not null"
					+");";
			stmnt.executeUpdate(sql);
			
			sql = "CREATE TABLE commands ("
				+"id integer not null primary key autoincrement,"
				+"data longtext NOT NULL,"
				+"game_id integer NOT NULL"
				+");";
			stmnt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE game_membership ("
					+"id integer NOT NULL primary key autoincrement,"
					+"user_id integer unsigned not null,"
					+"game_id integer unsigned not null,"
					+"color varchar(255) not null DEFAULT '',"
					+"playerIndex integer not null"
					+");";
			stmnt.executeUpdate(sql);
			
			stmnt.close();
		
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new SQLException();
		}	
	}

	

}
