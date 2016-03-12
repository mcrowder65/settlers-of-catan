package shared.communication.response;

import java.util.AbstractMap;
import java.util.Map;

import client.utils.Translator;
import sun.net.www.protocol.http.HttpURLConnection;

public class Response {
	
	@Override
	public String toString() {
		if (success)
			return "Success";
		else
			return errorMessage;
	}
	
	
	protected transient boolean success;
	protected String  errorMessage;
	
	protected transient AbstractMap.SimpleEntry<String, String> cookie;

	public AbstractMap.SimpleEntry<String, String> getCookie() {
		return cookie;
	}
	public void setCookie(String key, String val) {
		cookie = new AbstractMap.SimpleEntry<String, String>(key, val);
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String message) {
		errorMessage = message;
	}
	
	
	public Response(int responseCode, String json) throws IllegalArgumentException {
		if (responseCode == HttpURLConnection.HTTP_OK) 
		{
			success = true;
		
		}
		else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST)
		{
			success = false;
			try {
				errorMessage = json;
			} catch (Exception e) {
				throw new IllegalArgumentException("Server returned a 400 but the response body was not a valid error message.");
			}
		}
		else 
		{
			throw new IllegalArgumentException("Unrecognized status code " + responseCode);
		}
		
	}
	public Response(){}
	
}
