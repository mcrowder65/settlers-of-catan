package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.ai.*;
import server.facade.*;
import server.util.ServerPlayer;
import shared.communication.response.Response;
import shared.definitions.*;

public class AddAIRequest extends Request {

	AIType AIType;
	public AddAIRequest(AIType AIType) throws IllegalArgumentException {
		if (AIType == null)
			throw new IllegalArgumentException("aitype cannot be null");
		this.AIType = AIType;
	}
	
	public Response addAI() {
		Response response = new Response();
		
		IAIFacade facade = new ServerAIFacade();
		int playerIndex = Game.instance().getGameId(this.gameIDCookie).getPlayers().length;
		String name = Game.instance().getUnusedAiName(gameIDCookie);
		CatanColor color = Game.instance().getUnusedColor(gameIDCookie);
		int playerId = (playerIndex + 1) * -1;
		
		AIBase ai;
		
		switch (AIType) {
		case LARGEST_ARMY:
			ai = new LargestArmyAI(name, color, playerId, playerIndex, facade);
			break;
		default:
			response.setSuccess(false);
			response.setErrorMessage("Server error: unhandled type");
			return response;
		}
		try {
			Game.instance().getGameId(this.gameIDCookie).addPlayer(ai);
		}catch (IllegalStateException ex) {
			response.setSuccess(false);
			response.setErrorMessage(ex.getMessage());
		}
		
		return response;
	}
	public AddAIRequest(HttpExchange exchange){
		super(exchange);
		AddAIRequest tmp = (AddAIRequest)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
		AIType = tmp.AIType;
	}
}
