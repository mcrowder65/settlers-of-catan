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

import client.data.GameInfo;
import client.data.PlayerInfo;
import server.swagger.FileUtils;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameModel;
import shared.communication.request.MoveCommand;
import shared.definitions.CatanColor;
/**
 * adds updates games in an XML file
 * inherits from IGameDAO
 * @author Brennen
 *
 */
public class XMLGameDAO implements IGameDAO{
	/**
	 * constructor for XMLGameDAO
	 */
	public XMLGameDAO(){}
	
	/**
	 * gets all the games in the XML file
	 * @return a list of GameCombos
	 * @return null if there are no games
	 * @throws IOException  if an error occurs
	 */
	@Override
	public List<GameCombo> getGames() throws IOException {
		List<GameCombo> allGames = new ArrayList<GameCombo>();
		File directory = new File("xml/allGames"); //getting the directory

		if(!directory.exists()){
			return null; //no directory therefore no games exist
		}
		File[] fileList = directory.listFiles(); //gets a list of all the games
		List<PlayerInfo>players = new ArrayList<PlayerInfo>();
		
		//goes through all the games to create the list of gameCombos
		for(File file : fileList){
			if(file.isFile() && !file.getName().equals(".DS_Store")){
				players = new ArrayList<PlayerInfo>();
				System.out.println(file.getPath());
				XStream xStream = new XStream(new DomDriver());
				InputStream inFile = new BufferedInputStream(new FileInputStream(file));
				GameParams game = (GameParams)xStream.fromXML(inFile);
				inFile.close();
				
				File joinDirectory = new File("xml/joinTable"+game.getId()); //gets the join file
				if(joinDirectory.exists() == false){
					return null; //no games exist
				}
				File[] gameFiles = joinDirectory.listFiles();
				//goes through the join table to make make the game combos
				for(File f : gameFiles){
					if(f.isFile() && !f.getName().equals(".DS_Store")){
						XStream xStream2 = new XStream(new DomDriver());
						InputStream inFile2 = new BufferedInputStream(new FileInputStream(f));
						JoinUserParams user = (JoinUserParams)xStream2.fromXML(inFile2); //getting the joing user info
						int userId = user.getUserID();
						int playerIndex = user.getPlayerIndex();
						CatanColor color = user.getColor();
						
						XMLUserDAO userDAO = new XMLUserDAO();
						List<RegisteredPersonInfo> allPlayers = userDAO.getUsers();
						for(RegisteredPersonInfo p : allPlayers){
							int id = p.getId();
							String name = p.getUsername();
							//setting all the playerInfo
							if(userId == id){
								PlayerInfo pInfo = new PlayerInfo();
								pInfo.setColor(color);
								pInfo.setId(userId);
								pInfo.setName(name);
								pInfo.setPlayerIndex(playerIndex);
								players.add(pInfo);
							}
						}
						
					}
				}
				int gameId = game.getId();
				String gameName = game.getTitle();
				GameInfo info = new GameInfo(); //creating the game info
				info.setId(gameId);
				info.setTitle(gameName);
				for(PlayerInfo pi : players){
					info.addPlayer(pi);
				}
				GameCombo combo = new GameCombo(); //creating the gameCombo
				combo.model = game.getModel();
				combo.info = info;
				allGames.add(combo); //adding to the game combo
			}
		}

		return allGames;
	}
	/**
	 * adds a command to the XML file
	 * @param command MoveCommand - the move that should be added
	 * @param gameID int the id of the game.
	 * @throws IOException  if an error occurs
	 */
	@Override
	public void addCommand(MoveCommand command, int gameID) throws IOException {
		String destination = "xml/commands"+ gameID + ".xml"; //destination of the command file
		File f = new File(destination);
		//adds commands to the command file if it already exists
		if(f.exists()){
			//gets the onld commands
			XStream xStream = new XStream(new DomDriver());
			InputStream inFile = new BufferedInputStream(new FileInputStream(f));
			CommandParams params = (CommandParams)xStream.fromXML(inFile);
			inFile.close();
			f.delete();
			
			params.allCommands.add(command); //adds command tot he array
			XStream xStream2 = new XStream(new DomDriver()); //new xStream
			OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination)); 
			xStream2.toXML(params,outFile); //write the new commands
			outFile.close();
			
		}
		//creating the file if it doesn't already exist
		else{
			CommandParams params = new CommandParams();
			params.allCommands.add(command); //adding the command to the array
			params.gameID = gameID;
			XStream xStream = new XStream(new DomDriver()); //new xStream
			OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
			xStream.toXML(params,outFile); //writes the file
			outFile.close();
		}
		
		
	}
	
	/**
	 * updates a game in the XML file
	 *@param gameID int
	 *@param model ServerGameModel
	 * @throws IOException  if an error occurs
	 */
	@Override
	public void updateGame(int gameID, ServerGameModel model) throws IOException {
		String destination = "xml/allGames/"+ gameID + ".xml"; //creates the destination
		File f = new File(destination);
		String title ="";
		//if the file exists - get the game and the title and delete the file
		if(f.exists()){
			XStream xStream = new XStream(new DomDriver());
			InputStream inFile = new BufferedInputStream(new FileInputStream(f));
			GameParams game = (GameParams)xStream.fromXML(inFile); //accessing the file
			title = game.getTitle(); //getting the title
			inFile.close();
			f.delete(); //delete the file
		}
		GameParams game = new GameParams(gameID,model,title); //creating the new object to write out
		XStream xStream = new XStream(new DomDriver()); //new xStream
		OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
		xStream.toXML(game,outFile); //writes the XML file
		outFile.close();
	}
	/**
	 * deletes commands from the XML file
	 * @param gameID int
	 * @throws IOException 
	 */ 
	@Override
	public void deleteCommands(int gameID) throws IOException {
		String directory = "xml/commands"+ gameID +".xml"; //creates the destination
		File f = new File(directory);
		//if the file exists delete it
		if(f.exists()){
			XStream xStream = new XStream(new DomDriver());
			InputStream inFile = new BufferedInputStream(new FileInputStream(f));
			CommandParams params = (CommandParams)xStream.fromXML(inFile);
			params.allCommands = new ArrayList<MoveCommand>();
			inFile.close();
			f.delete();
		}
	}
	

	/**
	 * 
	 * @param userID int 
	 * @param gameID int
	 * @param color CatanColor
	 * @throws IOException if an error occurs
	 */ 
	@Override
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex) throws IOException {
		String destination = "xml/joinTable"+ gameID + "/" + userID +".xml"; //creates the desitnation of the file
		(new File("xml/joinTable"+gameID)).mkdirs(); //makes the necessary directory/files
	
		//if the file exists delete it
		File f = new File(destination);
		if(f.exists()){
			f.delete();
		}
		XStream xStream = new XStream(new DomDriver()); //new xStream
		OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
		JoinUserParams joinUser = new JoinUserParams(userID,gameID,color,playerIndex);
		xStream.toXML(joinUser,outFile); //writes the onject holding the join user data out to file
		outFile.close();
	}

	/**
	 * adds a game to the XML games file
	 * @param id int
	 * @param model ServerGameModel
	 * @param title String 
	 * @throws IOException if an error occurs
	 */
	@Override
	public void addGame(int id, ServerGameModel model, String title) throws IOException {
		GameParams game = new GameParams(id, model, title); //creates an object to hold all the game info
		String destination = "xml/allGames/"+ id + ".xml";
		(new File("xml/allGames")).mkdirs(); //makes the necessary directory/files
		
		File f = new File(destination);
		//if the file exists, delete it
		if(f.exists()){
			f.delete();
		}
		XStream xStream = new XStream(new DomDriver()); //new xStream
		OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
		xStream.toXML(game,outFile); //writes out the new file containing the model
		outFile.close();
		
		
	}

	/**
	 * gets all of the commands for a particular game to load them
	 * @param gameID int
	 * @return List<MoveCommand>
	 * @return null - if no commands
	 */
	@Override
	public List<MoveCommand> getCommands(int gameID) throws IOException {
		String destination = "xml/commands"+gameID+".xml"; //creates the destination
		File f = new File(destination);
		if(f.exists()){
			XStream xStream = new XStream(new DomDriver());
			InputStream inFile = new BufferedInputStream(new FileInputStream(f));
			CommandParams params = (CommandParams)xStream.fromXML(inFile); //gets all the commands from the command file
			inFile.close();
			return params.allCommands;
		}
		return null; //returns null if there were no commands to load
	}

	/**
	 * drops all of the XML files
	 */
	@Override
	public void resetPersistence() {
		File f = new File("xml");
		this.deleteAll(f);
	}
	
	/**
	 * Recursively deletes XML files
	 * @param f File
	 */
	public void deleteAll(File f){
		File[] files = f.listFiles();
		if(files != null){
			for(File ff : files){
				if(ff.isDirectory()){
					deleteAll(ff);
				}
				
				ff.delete();
			}
		}
	}


	/**
	 * don't do anything
	 * used in SQL only
	 */
	@Override
	public void setConnection(Connection conn) {}

}
