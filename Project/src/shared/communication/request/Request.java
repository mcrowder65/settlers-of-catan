package shared.communication.request;

import client.utils.Translator;

public abstract class Request {

	@Override
	public String toString() {
		return Translator.objectToJson(this);
	}
}
