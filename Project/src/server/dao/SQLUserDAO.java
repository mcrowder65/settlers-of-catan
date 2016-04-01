package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import server.util.RegisteredPersonInfo;
/**
 * add users to the user table in the SQL database
 * gets users from the user table in the SQL database
 * @author Brennen
 *
 */
public class SQLUserDAO implements IUserDAO{
	
	/**
	 * constructor for the SQLUserDAO
	 */
	public SQLUserDAO(){}
	
	/**
	 * gets all users from the User table in the SQL database
	 * @return List<RegisteredPersonInfo>
	 */
	@Override
	public List<RegisteredPersonInfo> getUsers() {
		Connection conn = dm.getConnection(); //where do we get this connection?

		PreparedStatement stmt = null;
		String mysqlstring="Select * from users;";
		stmt = conn.prepareStatement(mysqlstring);
		ResultSet set = stmt.executeQuery();
		ArrayList<RegisteredPersonInfo> users = new ArrayList<RegisteredPersonInfo>();
		while(set.next()) { //id, name, password
			int id = set.getInt(0);
			String username = set.getString(1);
			String password = set.getString(2);
			users.add(new RegisteredPersonInfo(id, username, password));
		}
		stmt.close();
		conn.close();
		return users;
	}

	/**
	 * adds a user to the user table
	 * @param RegeristeredPersonInfo
	 */
	@Override
	public void addUser(RegisteredPersonInfo person) {
		// TODO Auto-generated method stub
		
	}

}
