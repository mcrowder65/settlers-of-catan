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
		File[] fileList = directory.listFiles();
		List<PlayerInfo>players = new ArrayList<PlayerInfo>();
		
		for(File file : fileList){
			if(file.isFile() && !file.getName().equals(".DS_Store")){
				System.out.println(file.getPath());
				XStream xStream = new XStream(new DomDriver());
				InputStream inFile = new BufferedInputStream(new FileInputStream(file));
				GameParams game = (GameParams)xStream.fromXML(inFile);
				inFile.close();
				
				File joinDirectory = new File("xml/joinTable"+game.getId());
				if(joinDirectory.exists() == false){
					return null; //no games exist
				}
				File[] gameFiles = joinDirectory.listFiles();
				for(File f : gameFiles){
					if(f.isFile()){
						XStream xStream2 = new XStream(new DomDriver());
						InputStream inFile2 = new BufferedInputStream(new FileInputStream(f));
						JoinUserParams user = (JoinUserParams)xStream2.fromXML(inFile2);
						int userId = user.getUserID();
						int playerIndex = user.getPlayerIndex();
						CatanColor color = user.getColor();
						
						XMLUserDAO userDAO = new XMLUserDAO();
						List<RegisteredPersonInfo> allPlayers = userDAO.getUsers();
						for(RegisteredPersonInfo p : allPlayers){
							int id = p.getId();
							String name = p.getUsername();
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
				GameInfo info = new GameInfo();
				info.setId(gameId);
				info.setTitle(gameName);
				for(PlayerInfo pi : players){
					info.addPlayer(pi);
				}
				GameCombo combo = new GameCombo();
				combo.model = game.getModel();
				combo.info = info;
				allGames.add(combo);
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
		String destination = "xml/commands"+ gameID + ".xml";
		File f = new File(destination);
		if(f.exists()){
			XStream xStream = new XStream(new DomDriver());
			InputStream inFile = new BufferedInputStream(new FileInputStream(f));
			CommandParams params = (CommandParams)xStream.fromXML(inFile);
			inFile.close();
			f.delete();
			
			params.allCommands.add(command);
			XStream xStream2 = new XStream(new DomDriver()); //new xStream
			OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
			xStream2.toXML(params,outFile);
			outFile.close();
			
		}
		else{
			CommandParams params = new CommandParams();
			params.allCommands.add(command);
			params.gameID = gameID;
			XStream xStream = new XStream(new DomDriver()); //new xStream
			OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
			xStream.toXML(params,outFile);
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
		String destination = "xml/allGames/"+ gameID + ".xml";
		File f = new File(destination);
		String title ="";
		if(f.exists()){
			XStream xStream = new XStream(new DomDriver());
			InputStream inFile = new BufferedInputStream(new FileInputStream(f));
			GameParams game = (GameParams)xStream.fromXML(inFile);
			title = game.getTitle();
			inFile.close();
			f.delete();
		}
		GameParams game = new GameParams(gameID,model,title);
		XStream xStream = new XStream(new DomDriver()); //new xStream
		OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
		xStream.toXML(game,outFile);
		outFile.close();
	}
	/**
	 * deletes commands from the XML file
	 * @param gameID int
	 * @throws IOException 
	 */ 
	@Override
	public void deleteCommands(int gameID) throws IOException {
		String directory = "xml/commands"+ gameID +".xml";
		File f = new File(directory);
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
		String destination = "xml/joinTable"+ gameID + "/" + userID +".xml";
		(new File("xml/joinTable"+gameID)).mkdirs();
		
			File f = new File(destination);
			if(f.exists()){
				f.delete();
			}
			XStream xStream = new XStream(new DomDriver()); //new xStream
			OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
			JoinUserParams joinUser = new JoinUserParams(userID,gameID,color,playerIndex);
			xStream.toXML(joinUser,outFile);
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
		GameParams game = new GameParams(id, model, title);
		String destination = "xml/allGames/"+ id + ".xml";
		(new File("xml/allGames")).mkdirs();
		
		File f = new File(destination);
		if(f.exists()){
			f.delete();
		}
		XStream xStream = new XStream(new DomDriver()); //new xStream
		OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
		xStream.toXML(game,outFile);
		outFile.close();
		
		
	}

	@Override
	public List<MoveCommand> getCommands(int gameID) throws IOException {
		String destination = "xml/commands"+gameID+".xml";
		File f = new File(destination);
		if(f.exists()){
			XStream xStream = new XStream(new DomDriver());
			InputStream inFile = new BufferedInputStream(new FileInputStream(f));
			CommandParams params = (CommandParams)xStream.fromXML(inFile);
			inFile.close();
			return params.allCommands;
		}
		return null;
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
	 */
	@Override
	public void setConnection(Connection conn) {
		
		
	}

}
