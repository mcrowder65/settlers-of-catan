package server.handlers;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.facade.IMovesFacade;
import shared.communication.response.GetModelResponse;
/**
 * This class is a buy dev card handle. It handles accepting buying dev cards
 * @author mcrowder65
 *
 */
public class BuyDevCardHandler implements HttpHandler {

	IMovesFacade facade;
	
	/**
	 * Constructor
	 * @param facade IMovesFacade type
	 */
	public BuyDevCardHandler(IMovesFacade facade) {
		this.facade = facade;
	}
	
	/**
	 * Handles... overrides HttpHandler handle method
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		GetModelResponse response = facade.buyDevCard(exchange);
		exchange.getResponseBody().write(response.toString().getBytes());
	}

}
