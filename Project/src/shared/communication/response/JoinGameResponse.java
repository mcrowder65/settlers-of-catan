package shared.communication.response;

public class JoinGameResponse extends Response {

	int gameId;
	public JoinGameResponse(int responseCode, String json) throws IllegalArgumentException {
		super(responseCode, json);
		if (success) {

		}
	}

}
