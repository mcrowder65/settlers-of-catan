package server.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


import server.util.RegisteredPersonInfo;
/**
 * adds and retrieves data from the user XML file
 * @author Brennen
 *
 */
public class XMLUserDAO implements IUserDAO{
	
	/**
	 * constructor for the XMLUserDAO
	 */
	public XMLUserDAO(){}
	

	/**
	 * gets all users from the user XML file
	 * @return a list of List of RegisteredPersonInfo
	 * @throws IOException if error occurs
	 */
	@Override
	public List<RegisteredPersonInfo> getUsers() throws IOException {
		String destination = "xml/Users.xml"; //creates the destination
		List<RegisteredPersonInfo> users = new ArrayList<RegisteredPersonInfo>();
		XStream xStream = new XStream(new DomDriver());
		File f = new File(destination); //creates a new file
	
		//reads in the users from the XML file
		if(f.exists()){
			InputStream	inFile = new BufferedInputStream(new FileInputStream(destination));
			users = (List<RegisteredPersonInfo>)xStream.fromXML(inFile);
			inFile.close();
			return users;
		}
		
		return users;
	}

	/**
	 * adds a user to the user XML file
	 * @param person RegeristeredPersonInfo
	 * @throws IOException 
	 */
	@Override
	public void addUser(RegisteredPersonInfo person) throws IOException {
		String destination = "xml/Users.xml"; //creates a destination
		List<RegisteredPersonInfo> users = new ArrayList<RegisteredPersonInfo>();
		File f = new File(destination); //creates the new file
		//gets users and then adds the new one
		if(f.exists()){
			XStream xStream = new XStream(new DomDriver());
			InputStream inFile = new BufferedInputStream(new FileInputStream(destination));
			users = (List<RegisteredPersonInfo>)xStream.fromXML(inFile);
			users.add(person);
			inFile.close();
		}
		else{
			users.add(person); //adds a user when its the first user in the file
		}
		
		//writes all the users back to the file
		XStream xStream = new XStream(new DomDriver()); //new xStream
		OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
		xStream.toXML(users,outFile);
		outFile.close();
		
	}

	/**
	 * don't do anything
	 * used for SQL
	 */
	@Override
	public void setConnection(Connection conn) {}

}
