package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facade.IMovesFacade;
/**
 * This class is a build city handler. It handles building cities.
 * @author mcrowder65
 *
 */
public class BuildCityHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * Constructor
	 * @param facade
	 */
	public BuildCityHandler(IMovesFacade facade) {
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
