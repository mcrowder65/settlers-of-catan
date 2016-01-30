package shared.communication.request;

public class SaveGameRequest extends Request {

	int gameId;
	public SaveGameRequest(int gameId) {
		this.gameId = gameId;
	}
}
