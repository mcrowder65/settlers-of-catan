package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;
/**
 * Handler for sending a chat
 * @author Brennen
 *
 */
public class SendChatHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * constructor for SendChatHandler
	 * @param facade
	 */
	public SendChatHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls the sendChat on IMovesFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		GetModelResponse response = facade.sendChat(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());

	}

}
