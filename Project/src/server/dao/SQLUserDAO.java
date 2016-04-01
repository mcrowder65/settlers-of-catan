package server.dao;

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
		// TODO Auto-generated method stub
		return null;
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
