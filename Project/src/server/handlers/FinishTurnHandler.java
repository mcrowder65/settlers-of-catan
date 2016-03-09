package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facade.IMovesFacade;
/**
 * finish turn handler. It handles accepting trades
 * @author mcrowder65
 *
 */
public class FinishTurnHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * Constructor
	 * @param facade IMovesFacade type
	 */
	public FinishTurnHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * Handles... overrides HttpHandler handle method
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
