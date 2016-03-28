package shared.definitions;

import shared.locations.*;
/**
 * Class to wrap messages and their source
 * @author Brennen
 *
 */
public class MessageLine {
	
	@Override
	public String toString() {
		return message;
	}

	/**
	 * Message to be sent
	 */
	private String message;
	/**
	 * The source of the message
	 */
	private String source;
	
	/**
	 * Constructor for MessageLine
	 * sets message and source of the object
	 * @param message
	 * @param source
	 */
	public MessageLine(String message, String source) {
		
		this.message = message;
		this.source = source;
	}

	/**
	 * retrieves the message
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * retrieves the source
	 * @return source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * sets the message variable
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Sets the source variable
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	
	
	
}
