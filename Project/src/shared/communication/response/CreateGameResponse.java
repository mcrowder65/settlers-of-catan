package shared.communication.response;

import com.sun.net.httpserver.HttpExchange;

import client.data.GameInfo;
import client.utils.Translator;
import shared.communication.request.RegisterRequest;

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
	public void setGameId(int id){
		game.setId(id);
	}
	public CreateGameResponse(String title){
		game = new GameInfo();
		game.setTitle(title);
	}
}
