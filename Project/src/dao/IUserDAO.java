package dao;

import java.util.List;

import server.util.RegisteredPersonInfo;
/**
 * interface for the UserDAO
 * both the SQLUserDao and the XMLUserDAO inherit from this interface
 * @author Brennen
 *
 */
public interface IUserDAO {
	public List<RegisteredPersonInfo> getUsers();
	public void addUser(RegisteredPersonInfo person);
}
