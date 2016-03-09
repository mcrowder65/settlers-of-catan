package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IInnerGameFacade;
import server.facade.IMovesFacade;
import server.facade.IUserFacade;
/**
 * get model handler. It handles getting the model.
 * @author mcrowder65
 *
 */
public class GetModelHandler implements HttpHandler {

	IInnerGameFacade facade;
	
	/**
	 * Constructor
	 * @param facade IInnerGameFacade type
	 */
	public GetModelHandler(IInnerGameFacade facade) {
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
