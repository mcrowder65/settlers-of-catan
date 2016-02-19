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
	public GameHistoryController(IGameHistoryView view, Facade facade) {
		
		super(view);
		
		//initFromModel();
		this.facade = facade;
		
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
		
		//<temp>
		
		/*List<LogEntry> entries = new ArrayList<LogEntry>();
		entries.add(new LogEntry(CatanColor.brown, "This is a brown message"));
		entries.add(new LogEntry(CatanColor.orange, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
		entries.add(new LogEntry(CatanColor.brown, "This is a brown message"));
		entries.add(new LogEntry(CatanColor.orange, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
		entries.add(new LogEntry(CatanColor.brown, "This is a brown message"));
		entries.add(new LogEntry(CatanColor.orange, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
		entries.add(new LogEntry(CatanColor.brown, "This is a brown message"));
		entries.add(new LogEntry(CatanColor.orange, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
		
		getView().setEntries(entries);
	
		//</temp>*/
		
	}

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		MessageList messages = model.getLog();
		if (messages.getLines().length > currentNumberOfMessages) {
			currentNumberOfMessages = messages.getLines().length;
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
			
			
			getView().setEntries(entries);
		}	
	}
	
	public void enterGame() {
		this.facade.addObserver(this);
		
	}
	public void leaveGame() {
		this.facade.deleteObserver(this);
		
	}
	
}

