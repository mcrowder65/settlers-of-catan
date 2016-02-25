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

	@Override
	public void sendMessage(String message) {
		synchronized (lockObject) {
			if(message.equals("hello")) {
				for(int i = 0; i < 7; i++) {
					facade.playMonument();
				}
			}
			else if(message.equals("hello1")) {
				facade.playMonument();
			}
			else if(message.equals("wood")) {
				for(int i = 0; i < 5; i++){
					facade.playYearOfPlenty(ResourceType.WOOD, ResourceType.WOOD);
				}				                                                            
			}
			else if(message.equals("brick")) {
				for(int i = 0; i < 5; i++){
					facade.playYearOfPlenty(ResourceType.BRICK, ResourceType.BRICK);
				}				                                                            
			}
			else if(message.equals("ore")) {
				for(int i = 0; i < 5; i++){
					facade.playYearOfPlenty(ResourceType.ORE, ResourceType.ORE);
				}				                                                            
			}
			else if(message.equals("sheep")) {
				for(int i = 0; i < 5; i++){
					facade.playYearOfPlenty(ResourceType.SHEEP, ResourceType.SHEEP);
				}				                                                            
			}
			else if(message.equals("wheat")) {
				for(int i = 0; i < 5; i++){
					facade.playYearOfPlenty(ResourceType.WHEAT, ResourceType.WHEAT);
				}				                                                            
			}
			else if(message.equals("dev")) {
				facade.buyDevCard();
			}
			
		
			boolean success = facade.sendChat(message);
			if (success) {
				currentNumberOfMessages++;
				GameModel model = facade.fetchModel();
				MessageList messages = model.getChat();
				updateMessageView(messages, model);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		synchronized (lockObject) {
			GameModel model = (GameModel)arg;
			MessageList messages = model.getChat();
			if (messages.getLines().length > currentNumberOfMessages) {
				currentNumberOfMessages = messages.getLines().length;
				updateMessageView(messages, model);
			}	
		}
	}
	
	private void updateMessageView(MessageList messages, GameModel model) {
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

	public void enterGame() {
		this.facade.addObserver(this);
		
	}
	public void leaveGame() {
		this.facade.deleteObserver(this);
		
	}
}

