package shared.communication.request;

import shared.definitions.AIType;

public class AddAIRequest extends Request {

	AIType AIType;
	public AddAIRequest(AIType AIType) throws IllegalArgumentException {
		if (AIType == null)
			throw new IllegalArgumentException("aitype cannot be null");
		this.AIType = AIType;
	}
}
