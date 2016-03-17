package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import shared.communication.response.ListAIResponse;

public class ListAIRequest extends Request {

	public ListAIRequest() throws IllegalArgumentException {
		
	}
	
	public ListAIResponse listAITypes() {
		ListAIResponse response = new ListAIResponse(Game.getAiTypes());
		response.setSuccess(true);
		response.setErrorMessage("Success");
		return response;
	}
	public ListAIRequest(HttpExchange exchange){
		super(exchange);
	}
}
