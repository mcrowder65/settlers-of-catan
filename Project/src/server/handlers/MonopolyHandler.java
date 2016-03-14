package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;
import shared.communication.response.Response;
import sun.net.www.protocol.http.HttpURLConnection;
/**
 * handler for when the user wants to play the monopoly cards
 * @author Brennen
 *
 */
public class MonopolyHandler implements HttpHandler {

	IMovesFacade facade;
	
	
	public MonopolyHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls monument on the IMovesFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Response response = null;
		try{
		   response = facade.monopoly(exchange);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		if (response.getCookie() != null)
			exchange.getResponseHeaders().add(response.getCookie().getKey(), response.getCookie().getValue());
		exchange.getResponseHeaders().add("Content-type", "application/json");
		exchange.sendResponseHeaders(response.isSuccess() ? HttpURLConnection.HTTP_OK : HttpURLConnection.HTTP_BAD_REQUEST, 0);
		exchange.getResponseBody().write(response.toString().getBytes());
		exchange.getResponseBody().close();
	}

}
