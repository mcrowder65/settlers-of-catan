package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;

import client.data.GameInfo;
import client.data.PlayerInfo;
import client.utils.Translator;
import server.Game;
import shared.communication.response.Response;
import shared.definitions.CatanColor;

public class JoinGameRequest extends Request {
	int id;
	CatanColor color;
	/**
	 * 
	 * @param id id of the game you want to join
	 * @param color color the player wants to join as
	 * @throws IllegalArgumentException
	 */
	public  JoinGameRequest(int id, CatanColor color) throws IllegalArgumentException {
		if (color == null) throw new IllegalArgumentException("Color cannot be null.");
		this.id = id;
		this.color = color;
		
	}
	
	public Response joinGame() {
		Response response = new Response();
		Game game = Game.instance();
		ArrayList<GameInfo> games = game.getGamesList();
		GameInfo requestedGame = games.get(id);

		if(requestedGame.hasPlayer(userCookie)){
			games.get(id).setPlayer(id, color);
		}
		else
			games.get(id).addPlayer(new PlayerInfo(playerIDCookie, userCookie, color)); 

		response.setCookie("Set-cookie", "catan.game=" + gameIDCookie + ";Path=/;");
		response.setErrorMessage("Success");
		response.setSuccess(true);
		return response;
	}
	public JoinGameRequest(HttpExchange exchange){
		super(exchange);
		JoinGameRequest tmp = (JoinGameRequest)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.id = tmp.id;
		this.color = tmp.color;
	}
}
