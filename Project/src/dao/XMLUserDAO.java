package dao;

import java.util.List;

import server.util.RegisteredPersonInfo;
/**
 * adds and retrievs data from the user xml file
 * @author Brennen
 *
 */
public class XMLUserDAO implements IUserDAO{
	
	/**
	 * constructor for the XMLUserDAO
	 */
	public XMLUserDAO(){}
	

	/**
	 * gets all users from the user xml file
	 * @return List<RegisteredPersonInfo>
	 */
	@Override
	public List<RegisteredPersonInfo> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * adds a user to the user xml file
	 * @param RegeristeredPersonInfo
	 */
	@Override
	public void addUser(RegisteredPersonInfo person) {
		// TODO Auto-generated method stub
		
	}

}
