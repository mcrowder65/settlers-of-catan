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
import server.util.ServerPlayer;
import shared.communication.response.Response;
import shared.definitions.CatanColor;
import shared.definitions.Player;

public class JoinGameRequest extends Request {
	int id;
	CatanColor color;
	public int getId() {
		return id;
	}

	public CatanColor getColor() {
		return color;
	}

	public int getPlayerID() {
		return playerIDCookie;
	}
	public int getPlayerIndex() {
		return Game.instance().getGameId(id).getLocalIndex(playerIDCookie);
	}
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
		
		synchronized(Game.instance().lock){
			Response response = new Response();
			Game game = Game.instance();
			
			ServerGameModel model = game.getGameId(id);
			//String name, CatanColor color, int playerID, int playerIndex
			int playerIndex = model.getLocalIndex(playerIDCookie);
			ServerPlayer serverPlayer = new ServerPlayer(userCookie, color, playerIDCookie, playerIndex);
			if(playerIndex != -1){
				serverPlayer = model.getPlayerByIndex(playerIndex);
				serverPlayer.setColor(color); //TODO pointer issues
				Game.instance().setPlayer(id, serverPlayer);
				

			}
			//otherwise they're not in the game, find the index to use to add them to the game.
			else{
				playerIndex = model.getLocalIndexJoinGame(playerIDCookie);
				serverPlayer.setPlayerIndex(playerIndex);
				Game.instance().addPlayer(id, serverPlayer);
				Game.instance().getPersistenceProvider().update(id, model);
				
			}
			response.setCookie("Set-cookie", "catan.game=" + id + ";Path=/;");
			response.setErrorMessage("Success");
			response.setSuccess(true);
			
			return response;
		}
	}
	public JoinGameRequest(HttpExchange exchange){
		super(exchange);
		JoinGameRequest tmp = (JoinGameRequest)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		this.id = tmp.id;
		this.color = tmp.color;
	}
}