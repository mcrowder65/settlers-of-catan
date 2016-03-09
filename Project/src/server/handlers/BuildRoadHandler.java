package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
/**
 * This class is a build road handler. it handles building roads.
 * @author mcrowder65
 *
 */
public class BuildRoadHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * Constructor
	 * @param facade IMovesFacade type
	 */
	public BuildRoadHandler(IMovesFacade facade) {
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
