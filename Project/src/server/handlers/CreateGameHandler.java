package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facade.IInnerGameFacade;
import server.facade.IMovesFacade;
import server.facade.IOuterGameFacade;
import server.facade.IUserFacade;
/**
 * Create game handler. It handles creating games.
 * @author mcrowder65
 *
 */
public class CreateGameHandler implements HttpHandler {

	IOuterGameFacade facade;
	
	/**
	 * Constructor
	 * @param facade IOuterGameFacade type
	 */
	public CreateGameHandler(IOuterGameFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * Handles... overrides HttpHandler handle method
	 */
	@Override
	public void handle(HttpExchange aexchangerg0) throws IOException {
		// TODO Auto-generated method stub

	}

}
