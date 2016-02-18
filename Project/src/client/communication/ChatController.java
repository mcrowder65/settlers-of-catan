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


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, Observer {

	private Facade facade;
	public ChatController(IChatView view, Facade facade) {
		
		super(view);
		this.facade = facade;
		this.facade.addObserver(this);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		facade.sendChat(message);
	}

	@Override
	public void update(Observable o, Object arg) {
		/*GameModel model = (GameModel)arg;
		MessageList messages = model.getChat();
		MessageLine[] lines = messages.getLines();
		List<LogEntry> entries = new ArrayList();
		Player[] allPlayers = model.getPlayers();
		for(int i =0; i<lines.length; i++){
			MessageLine line = lines[i];
			String message = line.getMessage();
			String source = line.getSource();
			for(int j=0; j<allPlayers.length; j++){
				Player player = allPlayers[j];
				String name = player.getName();
				if(name.equals(source)){
					CatanColor color = player.getColor();
					LogEntry entry = new LogEntry(color,message);
					entries.add(entry);
				}
			}	
		}
		
		this.getView().setEntries(entries);*/
		
	}

}

