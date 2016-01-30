package shared.communication.request;

import shared.definitions.AIType;

public class AddAIRequest extends Request {

	AIType aiType;
	public AddAIRequest(AIType aiType) throws IllegalArgumentException {
		if (aiType == null)
			throw new IllegalArgumentException("aitype cannot be null");
		this.aiType = aiType;
	}
}
