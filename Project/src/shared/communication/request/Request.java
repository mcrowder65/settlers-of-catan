package shared.communication.request;

import client.utils.Translator;
/**
 * Request class.
 * @author mcrowder65
 *
 */
public abstract class Request {

	@Override
	public String toString() {
		return Translator.objectToJson(this);
	}
}
