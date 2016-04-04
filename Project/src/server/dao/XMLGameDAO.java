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
	 * @return List<GameCombo>
	 * @return null if there are no games
	 * @throws IOException 
	 */
	@Override
	public List<GameCombo> getGames() throws IOException {
		List<GameCombo> allGames = new ArrayList<GameCombo>();
		File directory = new File("./allGames"); //getting the directory

		if(!directory.exists()){
			return null; //no directory therefore no games exist
		}
		File[] fileList = directory.listFiles();
		List<PlayerInfo>players = new ArrayList<PlayerInfo>();
		
		for(File file : fileList){
			if(file.isFile()){
				XStream xStream = new XStream(new DomDriver());
				InputStream inFile = new BufferedInputStream(new FileInputStream(file));
				GameParams game = (GameParams)xStream.fromXML(inFile);
				
				File joinDirectory = new File("./joinTable/"+game.getId());
				if(joinDirectory.exists() == false){
					return null; //no games exist
				}
				File[] gameFiles = joinDirectory.listFiles();
				for(File f : gameFiles){
					if(f.isFile()){
						XStream xStream2 = new XStream(new DomDriver());
						InputStream inFile2 = new BufferedInputStream(new FileInputStream(f));
						JoinUserParams user = (JoinUserParams)xStream.fromXML(f);
						int userId = user.getUserID();
						int gameId = user.getGameID();
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
	 * @param MoveCommand - the move that should be added
	 * @param gameID int the id of the game.
	 * @throws IOException 
	 */
	@Override
	public void addCommand(MoveCommand command, int gameID) throws IOException {
		String destination = "./commands/"+ gameID + "/command.xml";
		boolean success = (new File("./joinTable/"+gameID)).mkdirs();
		if(success){
			File f = new File(destination);
			int x = 0;
			while(f.exists()){
				x++;
				destination = "./commands/"+ gameID + "/command"+x+".xml";
				f = new File(destination);
			}
			
			CommandParams params = new CommandParams();
			params.command = command;
			params.gameID = gameID;
			XStream xStream = new XStream(new DomDriver()); //new xStream
			OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
			xStream.toXML(params,outFile);
			outFile.close();
		}
		
	}
	/**
	 * updates a game in the XML file
	 *@param int gameID
	 *@param ServerGameModel model
	 * @throws IOException 
	 */
	@Override
	public void updateGame(int gameID, ServerGameModel model) throws IOException {
		String destination = "./allGames/"+ gameID + ".xml";
		File f = new File(destination);
		String title ="";
		if(f.exists()){
			XStream xStream = new XStream(new DomDriver());
			InputStream inFile = new BufferedInputStream(new FileInputStream(f));
			GameParams game = (GameParams)xStream.fromXML(f);
			title = game.getTitle();
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
	 * @param int gameID
	 */
	@Override
	public void deleteCommands(int gameID) {
		String directory = "./commands/"+ gameID;
		File f = new File(directory);
		f.delete();
	}
	
	/**
	 * Recursively defeats files from a directory and then deletes the directory
	 * @param f
	 * @throws FileNotFoundException
	 */
	public void delete(File f) throws FileNotFoundException{
		if(f.isDirectory()){
			for(File ff : f.listFiles()){
				ff.delete();
			}
		}
		if(f.delete() == false){
			throw new FileNotFoundException("File Not Found");
		}

	}
	/**
	 * 
	 * @param int UserID
	 * @param int gameID
	 * @param CatanColor color
	 * @throws IOException 
	 */
	@Override
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex) throws IOException {
		String destination = "./joinTable/"+ gameID + "/" + userID +".xml";
		boolean success = (new File("./joinTable"+gameID)).mkdirs();
		if(success){
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
		
	}

	/**
	 * adds a game to the XML games file
	 * @param int id
	 * @param ServerGameModel model
	 * @param String title
	 * @throws IOException 
	 */
	@Override
	public void addGame(int id, ServerGameModel model, String title) throws IOException {
		GameParams game = new GameParams(id, model, title);
		String destination = "./allGames/"+ id + ".xml";
		boolean success = (new File("./allGames")).mkdirs();
		if(success){
			File f = new File(destination);
			if(f.exists()){
				f.delete();
			}
			XStream xStream = new XStream(new DomDriver()); //new xStream
			OutputStream outFile = new BufferedOutputStream(new FileOutputStream(destination));
			xStream.toXML(game,outFile);
			outFile.close();
		}
		
	}

}
