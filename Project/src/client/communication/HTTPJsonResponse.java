package client.communication;

import java.util.List;
import java.util.Map;

public class HTTPJsonResponse {

	private String responseBody;
	private int responseCode;
	private String responseCookie;
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getResponseCookie() {
		return responseCookie;
	}
	public void setResponseCookie(String responseCookie) {
		this.responseCookie = responseCookie;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
}
