package server.handlers;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facade.IInnerGameFacade;
import server.facade.IMovesFacade;
import server.facade.IOuterGameFacade;
import server.facade.IUserFacade;
import shared.communication.response.GetModelResponse;
import shared.communication.response.Response;
import sun.net.www.protocol.http.HttpURLConnection;
/**
 * join game handler. It handles accepting trades
 * @author mcrowder65
 *
 */
public class JoinGameHandler implements HttpHandler {

	IOuterGameFacade facade;
	
	/**
	 * Constructor
	 * @param facade IOuterGameFacade type
	 */
	public JoinGameHandler(IOuterGameFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * Handles... overrides HttpHandler handle method
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Response response = null;
		try{
		   response = facade.joinGame(exchange);
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
