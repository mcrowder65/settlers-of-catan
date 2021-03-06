package server.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.Game;
import server.dao.IGameDAO;
import server.dao.IUserDAO;
import server.dao.XMLGameDAO;
import server.dao.XMLUserDAO;
import server.util.GameCombo;
import server.util.RegisteredPersonInfo;
import server.util.ServerGameModel;
import shared.communication.request.BuildRoadCommand;
import shared.communication.request.BuyDevCardCommand;
import shared.communication.request.MoveCommand;
import shared.communication.request.RobPlayerCommand;
import shared.definitions.CatanColor;
/**
 * Stores the delta between keys
 * Uses the DAO's to write to the XML files
 * @author Brennen
 *
 */
public class XMLPersistenceProvider extends PersistenceProvider{
	
	
	/**
	 * constructor for XMLPersistenceProvider
	 * @param commandCount
	 */
	public XMLPersistenceProvider(int commandCount){
		super(commandCount);
		
		this.gameDAO = this.createGameDAO();
		this.userDAO = this.createUserDAO();
	}
	

	/**
	 * adds a command to the array of commands 
	 * calls add command on the XML DAO
	 * @param command MoveCommand
	 */
	@Override
	public void addCommand(MoveCommand command) {
		try {
			gameDAO.addCommand(command, command.getGameCookie());
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		

		commands[currentCommandCount++] = command;
		if (currentCommandCount >= max) {
			currentCommandCount = 0;
			flushGame(command.getGameCookie(), Game.instance().getGameId(command.getGameCookie()));
			for (int n = 0; n < commands.length; n++)
				commands[n] = null;
			
		}
		
	}
	
	
	/**
	 * loads a game into memory via the gameID
	 */
	@Override
	public List<GameCombo> loadGames() {
		ArrayList<GameCombo> games = null;
		
		try {
			games = (ArrayList<GameCombo>) gameDAO.getGames();
			if (games == null) return new ArrayList<GameCombo>();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} 
		
		return games;
	}
	@Override
	public void update(int gameID, ServerGameModel model){
		try {
			gameDAO.updateGame(gameID, model);
			gameDAO.deleteCommands(gameID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reexecute the commands from the command file
	 * @param games
	 */
	public void executeCommands(List<GameCombo> games){
		//loops through all the games
		for(int i = 0; i < games.size(); i++){
			ArrayList<MoveCommand> commands = (ArrayList<MoveCommand>) getCommands(i); //gets all the commands for that game
			ServerGameModel model1 = games.get(i).model;
			if(commands != null){ //checks to see if the commands are null
				//loops through the commands calling reexecute on them
				for(int x = 0; x < commands.size(); x++){
					String moveType = commands.get(x).getMoveType();
					if(moveType == null){
						commands.get(x).reExecute(games.get(i).info.getId(),commands.get(x).getPlayerIndex());
					}
					else if(moveType.equals("robPlayer")){ //check for reexecutes
						RobPlayerCommand robPlayer = (RobPlayerCommand) commands.get(x);
						robPlayer.reExecute(games.get(i).info.getId(),robPlayer.getPlayerIndex());
					}
					else if(moveType.equals("buyDevCard")){ //check for reexecutes
						BuyDevCardCommand devCard = (BuyDevCardCommand) commands.get(x);
						devCard.reExecute(games.get(i).info.getId(),devCard.getPlayerIndex());
					}
					else{
						commands.get(x).reExecute(games.get(i).info.getId(),commands.get(x).getPlayerIndex()); //calls reexecute
					}
				}
				try {
					gameDAO.deleteCommands(games.get(i).model.getGameId()); //deletes the command file
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
	
				} catch (SQLException e) {
					e.printStackTrace();
					
				} catch (IOException e) {
					e.printStackTrace();
					
				}
			}
			flushGame(games.get(i).model.getGameId(),games.get(i).model); //flush the new updated model to the file
		}
	}

	@Override
	protected void flushGame(int gameID, ServerGameModel model) {
		try {
			gameDAO.updateGame(gameID, model);
			gameDAO.deleteCommands(gameID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * creates an new UserDAO for an xml file 
	 * @return a IUserDAO
	 */
	@Override
	public IUserDAO createUserDAO() {
		return new XMLUserDAO();
	}

	/**
	 * creates the gameDAO
	 * @return a IGameDAO
	 */
	@Override
	public IGameDAO createGameDAO() {
		return new XMLGameDAO();
	}
	
	/**
	 * calls the userDAO to add a user to the xml file
	 * @param userID int
	 * @param gameID int
	 * @param color CatanColor 
	 * @param playerIndex int
	 */
	@Override
	public void joinUser(int userID, int gameID, CatanColor color, int playerIndex){
		try {
			gameDAO.joinUser(userID, gameID, color, playerIndex);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * calls addUser on the XMLUserDAO
	 * person RegisteredPersonInfo
	 */
	@Override
	public void addUser(RegisteredPersonInfo person){
		try {
			userDAO.addUser(person);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * is not implemented because we are writing to an XML file
	 */
	@Override
	protected void startTransaction() {
		// NULL FOR XML
	}

	/**
	 * is not implemented because we are writing to an XML file
	 * @param commit boolean
	 */
	@Override
	protected void endTransaction(boolean commit) {
		// NULL FOR XML
	}


	@Override
	public List<MoveCommand> getCommands(int gameID) {
		List<MoveCommand> commands = null;
		
		try {
			commands = gameDAO.getCommands(gameID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return commands;
	}


	@Override
	public void resetPersistence() {
		try {
			gameDAO.resetPersistence();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<RegisteredPersonInfo> getUsers() {
		try {
			List<RegisteredPersonInfo> users = userDAO.getUsers();
			return users == null ? new ArrayList<RegisteredPersonInfo>() : users;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public void addGame(int id, ServerGameModel model, String title) {
		try {
			gameDAO.addGame(id, model, title);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
	}

}
