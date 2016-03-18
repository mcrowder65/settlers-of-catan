package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import server.util.ServerGameModel;
import shared.communication.response.GetModelResponse;
import sun.net.www.protocol.http.HttpURLConnection;

public class GetModelRequest extends Request {

	//Nullable because this is optional
	Integer version;
	String model;
	public GetModelRequest() {
		
	}
	public GetModelRequest(int version) {
		this.version = version;
	}
	
	public GetModelResponse getModel() {
		GetModelResponse response = new GetModelResponse(200, model);
		return response;
	}
	public GetModelRequest(HttpExchange exchange){
		super(exchange);
		ServerGameModel sgm = Game.instance().getGameId(gameIDCookie);
		if(sgm.isAiTurn() != null)
			sgm.doAiStuff();
		model = sgm.toString();
		
	}
}
