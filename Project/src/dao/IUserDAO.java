package dao;

import java.util.List;

import server.util.RegisteredPersonInfo;

public interface IUserDAO {
	public List<RegisteredPersonInfo> getUsers();
	public void addUser(RegisteredPersonInfo person);
}
