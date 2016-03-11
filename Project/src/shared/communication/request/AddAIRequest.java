package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.Response;
import shared.definitions.AIType;

public class AddAIRequest extends Request {

	AIType AIType;
	public AddAIRequest(AIType AIType) throws IllegalArgumentException {
		if (AIType == null)
			throw new IllegalArgumentException("aitype cannot be null");
		this.AIType = AIType;
	}
	
	public Response addAI() {
		return new Response();
	}
	public AddAIRequest(HttpExchange exchange){
		super(exchange);
	}
}
