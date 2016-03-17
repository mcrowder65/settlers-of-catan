package shared.definitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shared.locations.*;
/**
 * Class to hold all the messages
 * @author Brennen
 *
 */
public class MessageList {
	
	private MessageLine[] lines;
	private List<MessageLine>allMessages = new ArrayList<MessageLine>();

	/**
	 * constructor for MessageList
	 * sets lines in constructor
	 * @param lines
	 */
	public MessageList(MessageLine[] lines) {
		
		this.lines = lines;
		this.allMessages.addAll(Arrays.asList(lines));
	}

	public MessageList() {

		
	}

	/**
	 * retrieves the array of messages
	 * @return lines
	 */
	public MessageLine[] getLines() throws IllegalArgumentException{
		return lines;
	}
	
	public void addMessage(MessageLine line){
		allMessages.add(line);
		lines = allMessages.toArray(lines);
	}

	/**
	 * Sets the MessageLine Array
	 * @param lines
	 */
	public void setLines(MessageLine[] lines) throws IllegalArgumentException  {
		this.lines = lines;
		this.allMessages.addAll(Arrays.asList(lines));
	}

	public int size() throws IllegalArgumentException{
		return lines.length;
	}
	
}
