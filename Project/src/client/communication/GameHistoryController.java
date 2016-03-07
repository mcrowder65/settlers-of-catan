package client.communication;

import java.util.*;

import client.base.*;
import client.controller.Facade;
import shared.definitions.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, Observer {

	private Facade facade;
	private int currentNumberOfMessages = 0;
	final int maxNumOfMessages = 30;
	public GameHistoryController(IGameHistoryView view, Facade facade) {
		
		super(view);
		this.facade = facade;
		
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		MessageList messages = model.getLog();
		if (messages.getLines().length > currentNumberOfMessages || messages.getLines().length == maxNumOfMessages) {
			currentNumberOfMessages = messages.getLines().length;
			MessageLine[] lines = messages.getLines();
			List<LogEntry> entries = new ArrayList<LogEntry>();
			for(int i =0; i<lines.length; i++){
				MessageLine line = lines[i];
				String message = line.getMessage();
				String source = line.getSource();
				Player sourcePlayer = model.findPlayerByName(source);
				LogEntry entry = new LogEntry(sourcePlayer.getColor(), message);
				entries.add(entry);
			}
			
			
			getView().setEntries(entries);
		}	
	}
	
	public void enterGame() {
		this.facade.addObserver(this);
		
	}
	public void leaveGame() {
		this.facade.deleteObserver(this);
		getView().setEntries(new ArrayList<LogEntry>()); //Empty list
		currentNumberOfMessages = 0;
		
	}
	
}

