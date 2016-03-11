package server.handlers;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facade.IInnerGameFacade;
import server.facade.IMovesFacade;
import server.facade.IOuterGameFacade;
import server.facade.IUserFacade;
import shared.communication.response.GetModelResponse;
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
		// TODO Auto-generated method stub
		GetModelResponse response = (GetModelResponse) facade.joinGame(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
