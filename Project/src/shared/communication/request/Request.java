package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

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
//		System.out.println("***********************************************");
//		for(String i : headers.keySet()){
//			System.out.println(i);
//		}
		if(headers.containsKey("Cookie")){
			System.out.println("found a cookie!");
			String cookie = headers.get("Cookie").toString();
			//System.out.println("cookie: " + cookie);
			playerIDCookie = Translator.getPlayerId(headers.get("Cookie").toString());
			
			userCookie = Translator.getPlayerName(headers.get("Cookie").toString());
			passCookie = Translator.getPlayerPassword(headers.get("Cookie").toString());
			gameIDCookie = Translator.getGameId(headers.get("Cookie").toString());
//			
//			System.out.println("playerIDCookie: " + playerIDCookie);
//			System.out.println("userCookie: " + userCookie);
//			System.out.println("passCookie: " + passCookie);
//			System.out.println("gameIDCookie: " + gameIDCookie);
		}
	}
	protected static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

}
