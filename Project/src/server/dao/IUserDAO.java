package server.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
	 * @return a list of RegisteredPersonInfo
	 * @throws IOException if error
	 */
	public List<RegisteredPersonInfo> getUsers() throws IOException, SQLException;
	/**
	 * adds a user to the persistance storage
	 * @param person RegisteredPersonInfo
	 * @throws FileNotFoundException if file isn't found
	 * @throws IOException if there's an error
	 */
	public void addUser(RegisteredPersonInfo person) throws FileNotFoundException, IOException, SQLException;
	
	public void setConnection(Connection conn);
}
