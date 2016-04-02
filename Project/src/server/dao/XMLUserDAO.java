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
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


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
		String destination = "./Users.xml";
		List<RegisteredPersonInfo> users;
		XStream xStream = new XStream(new DomDriver());
		InputStream inFile;
		try {
			inFile = new BufferedInputStream(new FileInputStream(destination));
		} catch (FileNotFoundException e1) {
			return null;
		}
		users = (List<RegisteredPersonInfo>)xStream.fromXML(inFile);
		try {
			inFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
		
	}

	/**
	 * adds a user to the user xml file
	 * @param RegeristeredPersonInfo
	 * @throws IOException 
	 */
	@Override
	public void addUser(RegisteredPersonInfo person) throws IOException {
		String destination = "./Users.xml";
		List<RegisteredPersonInfo> users = new ArrayList<RegisteredPersonInfo>();
		File f = new File(destination); //creates the new file
		if(f.exists()){
			XStream xStream = new XStream(new DomDriver());
			InputStream inFile = new BufferedInputStream(new FileInputStream(destination));
			users = (List<RegisteredPersonInfo>)xStream.fromXML(inFile);
			for(int i=0; i<users.size(); i++){
				users.add(person);
			}
			inFile.close();
		}
		else{
			users.add(person);
		}
		
		XStream xStream = new XStream(new DomDriver()); //new xStream
		OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
		xStream.toXML(users,outFile);
		outFile.close();
		
	}

}
