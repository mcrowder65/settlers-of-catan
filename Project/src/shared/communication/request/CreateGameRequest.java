package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import shared.communication.response.CreateGameResponse;
import shared.communication.response.Response;

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
		CreateGameResponse response = new CreateGameResponse(name, gameIDCookie);
		response.setErrorMessage("Success");
		response.setSuccess(true);
		String username = Game.instance().getPersonById(playerIDCookie).getUsername();
		String password = Game.instance().getPersonById(playerIDCookie).getPassword();
		try {
			response.setCookie("Set-cookie", "catan.user=" +
					URLEncoder.encode("{" +
			           "\"name\":\"" + username + "\", " +
					   "\"password\":\"" + password + "\", " + 
			           "\"playerID\":" + playerIDCookie + "}", "UTF-8" ) 
						+ "; catan.game=\"" + gameIDCookie + "\";");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return (CreateGameResponse) response;
	}
	public CreateGameRequest(HttpExchange exchange){
		super(exchange);
		CreateGameRequest tmp = (CreateGameRequest)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		setVariables(tmp.name, tmp.randomTiles, tmp.randomNumbers, tmp.randomPorts);
	}
}
