package client.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import shared.definitions.CatanColor;
import shared.definitions.GameModel;
import shared.definitions.MessageLine;
import shared.definitions.MessageList;
import shared.definitions.Player;
import shared.definitions.ResourceType;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, Observer {

	private Facade facade;
	private int currentNumberOfMessages;
	private Object lockObject = new Object();
	
	public ChatController(IChatView view, Facade facade) {
		
		super(view);
		this.facade = facade;
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	/**
	 * sends the message to the facade
	 * @param message
	 */
	@Override
	public void sendMessage(String message) {
		synchronized (lockObject) {
		
			boolean success = facade.sendChat(message);
			//updates view if successful
			if (success) {
				currentNumberOfMessages++;
				GameModel model = facade.fetchModel();
				MessageList messages = model.getChat();
				updateMessageView(messages, model);
			}
		}
	}

	/**
	 * updates the messages whenever the poller runs we we recieve a new pmde;
	 */
	@Override
	public void update(Observable o, Object arg) {
		synchronized (lockObject) {
			GameModel model = (GameModel)arg;
			MessageList messages = model.getChat();
			if (messages.getLines().length > currentNumberOfMessages) {
				currentNumberOfMessages = messages.getLines().length;
				updateMessageView(messages, model); //updating the message view
			}	
		}
	}
	
	/**
	 * goes through all the messages and sets them in the view 
	 * @param messages
	 * @param model
	 */
	private void updateMessageView(MessageList messages, GameModel model) {
		MessageLine[] lines = messages.getLines();
		List<LogEntry> entries = new ArrayList<LogEntry>();
		//looping through all the messages
		for(int i =0; i<lines.length; i++){
			MessageLine line = lines[i];
			String message = line.getMessage();
			String source = line.getSource();
			Player sourcePlayer = model.findPlayerByName(source);
			LogEntry entry = new LogEntry(sourcePlayer.getColor(), message);
			entries.add(entry); //adding logEntries
		}
		
		//setting the view
		getView().setEntries(entries);
	}

	public void enterGame() {
		this.facade.addObserver(this);
		
	}
	public void leaveGame() {
		this.facade.deleteObserver(this);
		currentNumberOfMessages = 0;
		getView().setEntries(new ArrayList<LogEntry>()); //Empty list
		
	}
}

