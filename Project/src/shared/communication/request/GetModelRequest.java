package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.GetModelResponse;
import sun.net.www.protocol.http.HttpURLConnection;

public class GetModelRequest extends Request {

	//Nullable because this is optional
	Integer version;
	public GetModelRequest() {
		
	}
	public GetModelRequest(int version) {
		this.version = version;
	}
	
	public GetModelResponse getModel() {
		return new GetModelResponse(0, null);
	}
	public GetModelRequest(HttpExchange exchange){
		super(exchange);
	}
}
