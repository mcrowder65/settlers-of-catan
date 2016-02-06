package shared.definitions;

import shared.locations.*;
/**
 * Class to hold all the messages
 * @author Brennen
 *
 */
public class MessageList {
	
	private MessageLine[] lines;

	/**
	 * constructor for MessageList
	 * sets lines in constructor
	 * @param lines
	 */
	public MessageList(MessageLine[] lines) {
		
		this.lines = lines;
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

	/**
	 * Sets the MessageLine Array
	 * @param lines
	 */
	public void setLines(MessageLine[] lines) throws IllegalArgumentException  {
		this.lines = lines;
	}

	public int size() throws IllegalArgumentException{
		return lines.length;
	}
	
}
