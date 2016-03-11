package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.ListAIResponse;

public class ListAIRequest extends Request {

	public ListAIRequest() throws IllegalArgumentException {
		
	}
	
	public ListAIResponse listAITypes() {
		return new ListAIResponse(0,null);
	}
	public ListAIRequest(HttpExchange exchange){
		super(exchange);
	}
}
