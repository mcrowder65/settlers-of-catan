package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;

import client.data.GameInfo;
import client.data.PlayerInfo;
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
		
		response.setErrorMessage("Success");
		response.setSuccess(true);
		games.get(id).addPlayer(new PlayerInfo(playerIDCookie, userCookie, color == null ? CatanColor.red : color));
		try {
			response.setCookie("Set-cookie", "catan.user=" +
					URLEncoder.encode("{" +
			           "\"name\":\"" + userCookie + "\", " +
					   "\"password\":\"" + passCookie + "\", " + 
			           "\"playerID\":" + playerIDCookie + "}", "UTF-8" ) + "; catan.game=" + gameIDCookie + ";");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return response;
	}
	public JoinGameRequest(HttpExchange exchange){
		super(exchange);
	}
}
