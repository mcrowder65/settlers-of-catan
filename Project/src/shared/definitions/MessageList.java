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
	public MessageLine[] getLines() {
		return lines;
	}

	/**
	 * Sets the MessageLine Array
	 * @param lines
	 */
	public void setLines(MessageLine[] lines) throws IllegalArgumentException  {
		this.lines = lines;
	}
	/**
	 * Adds a MessageLine to the array
	 * @param line
	 */
	public void addMessage(MessageLine line) throws IllegalArgumentException {
		
	}
	public int size(){
		return lines.length;
	}
	
}
