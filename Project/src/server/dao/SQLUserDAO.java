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
	private Connection conn;
	/**
	 * constructor for the SQLUserDAO
	 */
	public SQLUserDAO(Connection conn){
		this.conn = conn;
	}
	
	/**
	 * gets all users from the User table in the SQL database
	 * @return List<RegisteredPersonInfo>
	 */
	@Override
	public List<RegisteredPersonInfo> getUsers() {
		 ArrayList<RegisteredPersonInfo> users = null;
        try {
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/340server", "root", "root");
			PreparedStatement stmt = null;
			String mysqlstring="Select * from users;";
			stmt = conn.prepareStatement(mysqlstring);
			ResultSet set = stmt.executeQuery();
			users = new ArrayList<RegisteredPersonInfo>();
			while(set.next()) { //id, name, password
				
				int id = set.getInt(1);
				String username = set.getString(2);
				String password = set.getString(3);
				users.add(new RegisteredPersonInfo(id, username, password));
			}
			stmt.close();
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
        
		return users;
	}

	/**
	 * adds a user to the SQL user table 
	 * @param RegeristeredPersonInfo
	 */
	@Override
	public void addUser(RegisteredPersonInfo person) {
		// TODO Auto-generated method stub
		
	}

}
