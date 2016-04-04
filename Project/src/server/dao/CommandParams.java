package server.dao;

import java.util.ArrayList;
import java.util.List;

import shared.communication.request.MoveCommand;

public class CommandParams {

	public List<MoveCommand> allCommands = new ArrayList<MoveCommand>();
	public int gameID;
}
