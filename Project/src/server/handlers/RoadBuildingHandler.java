package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
/**
 * Handler for any roadBuilding requests made by the user
 * @author Brennen
 *
 */
public class RoadBuildingHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * Constructor the RoadBuildingHandler
	 * @param facade
	 */
	public RoadBuildingHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * calls RoadBuilding on the IMovesFacade
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
	}
	

}
