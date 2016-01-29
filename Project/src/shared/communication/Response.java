package shared.communication;

import client.utils.Translator;
import sun.net.www.protocol.http.HttpURLConnection;

public class Response {
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public Response(int responseCode) throws IllegalArgumentException {
		if (responseCode == HttpURLConnection.HTTP_OK)
			setSuccess(true);
		else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST)
			setSuccess(false);
		else
			throw new IllegalArgumentException("Unrecognized status code " + responseCode);
	}
}
