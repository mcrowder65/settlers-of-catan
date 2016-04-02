package server.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import server.util.RegisteredPersonInfo;
/**
 * interface for the UserDAO
 * both the SQLUserDao and the XMLUserDAO inherit from this interface
 * @author Brennen
 *
 */
public interface IUserDAO {
	/**
	 * gets all users from the persistence storage
	 * @return List<RegisteredPersonInfo>
	 */
	public List<RegisteredPersonInfo> getUsers();
	/**
	 * adds a user to the persistance storage
	 * @param person
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public void addUser(RegisteredPersonInfo person) throws FileNotFoundException, IOException;
}
