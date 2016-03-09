package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facade.IMovesFacade;
/**
 * Handles a player making a maritimeTrade 
 * @author Brennen
 *
 */
public class MaritimeTradeHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * constructor for MaritimeTradeHandler
	 * @param facade
	 */
	public MaritimeTradeHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls maritimeTrade on the IMovesFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
	}
	

}
