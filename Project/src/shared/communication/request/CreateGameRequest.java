package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;

import client.data.GameInfo;
import client.data.PlayerInfo;
import client.utils.Translator;
import server.Game;
import server.util.ServerGameModel;
import shared.communication.response.CreateGameResponse;
import shared.communication.response.Response;
import shared.definitions.CatanColor;

public class CreateGameRequest extends Request {

	private String name;
	private boolean randomTiles;
	private boolean randomNumbers;
	private boolean randomPorts;
	public CreateGameRequest(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) {
		setVariables(name, randomTiles, randomNumbers, randomPorts);
	}
	void setVariables(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts){
		if (name == null) throw new IllegalArgumentException("name cannot be null.");
		this.name = name;
		this.randomNumbers = randomNumbers;
		this.randomTiles = randomTiles;
		this.randomPorts = randomPorts;
	}
	public CreateGameResponse createGame() {
		CreateGameResponse response = new CreateGameResponse(name);
		response.setErrorMessage("Success");
		response.setSuccess(true);
		ServerGameModel sgm = new ServerGameModel();
		sgm.setGameCookie(gameIDCookie);
		gameIDCookie = Game.instance().addGame(response.getGame(), sgm);
		response.setGameId(gameIDCookie);

		response.setCookie("Set-cookie", "catan.game=" + gameIDCookie + ";");

		return (CreateGameResponse) response;
	}
	public CreateGameRequest(HttpExchange exchange){
		super(exchange);
		CreateGameRequest tmp = (CreateGameRequest)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		setVariables(tmp.name, tmp.randomTiles, tmp.randomNumbers, tmp.randomPorts);
	}
}