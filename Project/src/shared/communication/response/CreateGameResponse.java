package shared.communication.response;

import client.data.GameInfo;
import client.utils.Translator;

public class CreateGameResponse extends Response {

	GameInfo game;
	public CreateGameResponse(int responseCode, String json) throws IllegalArgumentException {
		super(responseCode, json);
		if (success) {
			this.game = (GameInfo) Translator.makeGenericObject(json, new GameInfo());
		}
	}
	public GameInfo getGame() {
		return game;
	}

}
