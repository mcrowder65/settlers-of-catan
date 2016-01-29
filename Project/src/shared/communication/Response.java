package shared.communication;

import client.utils.Translator;
import sun.net.www.protocol.http.HttpURLConnection;

public class Response {
	protected boolean success;
	protected String  errorMessage;

	public boolean isSuccess() {
		return success;
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
			//TODO: Set error message here
			} catch (Exception e) {
				throw new IllegalArgumentException("Server returned a 400 but the response body was not a valid error message.");
			}
		}
		else 
		{
			throw new IllegalArgumentException("Unrecognized status code " + responseCode);
		}
		
	}
	
}
