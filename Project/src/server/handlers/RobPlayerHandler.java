package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;
import shared.communication.response.Response;
import sun.net.www.protocol.http.HttpURLConnection;
/**
 * Handler for robbing a player
 * @author Brennen
 *
 */
public class RobPlayerHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * constructor for RobPlayerHandler
	 * @param facade
	 */
	public RobPlayerHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls robPlayer on IMovesFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Response response = null;
		try{
		   response = facade.robPlayer(exchange);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		if (response.getCookie() != null)
			exchange.getResponseHeaders().add(response.getCookie().getKey(), response.getCookie().getValue());
		exchange.getResponseHeaders().add("Content-type", "application/json");
		exchange.sendResponseHeaders(response.isSuccess() ? HttpURLConnection.HTTP_OK : HttpURLConnection.HTTP_NOT_FOUND, 0);
		exchange.getResponseBody().write(response.toString().getBytes());
		exchange.getResponseBody().close();
	}

}
