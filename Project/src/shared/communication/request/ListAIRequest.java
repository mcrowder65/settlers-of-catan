package shared.communication.request;

import shared.communication.response.ListAIResponse;

public class ListAIRequest extends Request {

	public ListAIRequest() throws IllegalArgumentException {
		
	}
	
	public ListAIResponse listAITypes() {
		return new ListAIResponse(0,null);
	}
}
