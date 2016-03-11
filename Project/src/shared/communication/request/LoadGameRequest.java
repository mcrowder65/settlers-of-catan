package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

/**
 * This class requests loading a game, extends Request
 * @author baller
 */
public class LoadGameRequest extends Request {

	String name;
	/**
	 * Constructor
	 * @param name String - name of the game
	 */
	public LoadGameRequest(String name) {
		this.name = name;
	}
	public LoadGameRequest(HttpExchange exchange){
		super(exchange);
	}
}
