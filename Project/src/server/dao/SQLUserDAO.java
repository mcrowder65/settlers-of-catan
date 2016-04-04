package server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import server.util.RegisteredPersonInfo;
import java.sql.*;
/**
 * add users to the user table in the SQL database
 * gets users from the user table in the SQL database
 * @author Brennen
 *
 */
public class SQLUserDAO implements IUserDAO{
	/**
	 * connection to the SQL database
	 */
	private Connection conn;
	/**
	 * constructor for the SQLUserDAO
	 */
	public SQLUserDAO(Connection conn){
		this.conn = conn;
	}
	
	/**
	 * gets all users from the User table in the SQL database
	 * @return a list of RegisteredPersonInfo
	 */
	@Override
	public List<RegisteredPersonInfo> getUsers() throws SQLException{
		 ArrayList<RegisteredPersonInfo> users = null;
        try {
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/340server", "root", "root");
			PreparedStatement pstmt = null;
			String mysqlstring="Select * from users;";
			pstmt = conn.prepareStatement(mysqlstring);
			ResultSet set = pstmt.executeQuery();
			users = new ArrayList<RegisteredPersonInfo>();
			while(set.next()) { //id, name, password
				
				int id = set.getInt(1);
				String username = set.getString(2);
				String password = set.getString(3);
				users.add(new RegisteredPersonInfo(id, username, password));
			}
			pstmt.close();
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
        
		return users;
	}

	/**
	 * adds a user to the SQL user table 
	 * @param person RegeristeredPersonInfo
	 */
	@Override
	public void addUser(RegisteredPersonInfo person) throws SQLException {
	        try {
				Class.forName("com.mysql.jdbc.Driver");
				PreparedStatement pstmt = null;
				String mysqlstring="insert into users (id, user, pass) values (?, ?, ?);";
				pstmt = conn.prepareStatement(mysqlstring);
				pstmt.setInt(1, person.getId());
				pstmt.setString(2, person.getUsername());
				pstmt.setString(3, person.getPassword());
				pstmt.executeUpdate();
				pstmt.close();
			} catch (ClassNotFoundException|SQLException e) {
				e.printStackTrace();
				throw new SQLException();
			}
	}

}
