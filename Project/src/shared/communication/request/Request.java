package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;

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
	protected int gameID;
	protected int playerID;
	protected void setCookies(Headers headers){
		//response.setResponseCookie(conn.getHeaderField("Set-cookie"));
		String cookie = headers.get("Cookie").get(0); //TODO this probably aint right
		playerID = Translator.getPlayerId(cookie);
		gameID = Translator.getGameId(cookie);
	}
//	public String parseCookie(String cookie) {
//		StringBuilder smallerCookie = new StringBuilder(cookie.substring(11, cookie.length()));
//		String encodedCookie = smallerCookie.substring(0, smallerCookie.length()-8);
//		return encodedCookie;
//	}
//	public void setCookies(HttpURLConnection conn){
//		if(this.userCookie != null) {
//			conn.setRequestProperty("Cookie", "catan.user=" + this.userCookie);
//		}
//		if(this.gameCookie != null && this.userCookie != null){
//			conn.setRequestProperty("Cookie", "catan.user=" + this.userCookie + 
//					"; catan.game=" + this.gameCookie);
//		}
//	}
}
