package server.dao;

import java.util.ArrayList;
import java.util.List;

import shared.communication.request.MoveCommand;
/**
 * wrapper class to wite out all commands to a single file
 * @author Brennen
 *
 */
public class CommandParams {

	public List<MoveCommand> allCommands = new ArrayList<MoveCommand>(); //list to hold all the commands
	public int gameID; //game id for those commands 
}
