package shared.communication.request;

public class LoadGameRequest extends Request {

	String name;
	public LoadGameRequest(String name) {
		this.name = name;
	}
}
