package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import client.utils.Translator;
/**
 * Request class.
 * @author mcrowder65
 *
 */
public abstract class Request {
	public Request(){}
	public Request(HttpExchange exchange) {
		setCookies(exchange.getRequestHeaders());
	}
	@Override
	public String toString() {
		return Translator.objectToJson(this);
	}
    protected transient int gameIDCookie = -1;
	protected transient int playerIDCookie = -1;
	protected transient String userCookie;
	protected transient String passCookie;
	protected void setCookies(Headers headers){
		
		if(headers.containsKey("Cookie") && !headers.get("Cookie").isEmpty()){
			List<String> cookie = headers.get("Cookie"); //TODO this probably aint right
			String k = cookie.get(0);
			for (String s : cookie)
				System.out.println(s);
			//playerID = Translator.getPlayerId(cookie);
			//gameID = Translator.getGameId(cookie);
		}
	}
	protected static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

}
