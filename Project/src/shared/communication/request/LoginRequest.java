package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import shared.communication.response.Response;

/**
 * This class requests a login, extends Request
 * @author baller
 */

public class LoginRequest extends Request {
	
     private String username;
     private String password;
     /**
      * Requests a login.
      * @param username String - the username request
      * @param password String - the password request
      * @throws IllegalArgumentException
      */
     public LoginRequest(String username, String password) throws IllegalArgumentException {
	   	 if (username == null) throw new IllegalArgumentException("username cannot be null.");
	   	 if (password == null) throw new IllegalArgumentException("password cannot be null.");
    	 this.username = username;
    	 this.password = password;
    	  
     }
   
     public Response login() {
    	Response response = new Response();
     	boolean userVerified = Game.instance().verifyPassword(username, password);
     	if (!userVerified) {
     		response.setErrorMessage("Wrong username or password");
     		response.setSuccess(false);
     		return response;
     	}
     	
     	int id = Game.instance().getUserId(username);
     	response.setErrorMessage("Success");
     	response.setSuccess(true);
     	
		try {
			response.setCookie("Set-cookie", "catan.user=" +
					URLEncoder.encode("{" +
				       "\"authentication\":\"" + "1142128101" + "\"," +
			           "\"name\":\"" + username + "\"," +
					   "\"password\":\"" + password + "\"," + 
			           "\"playerID\":" + id + "}", "UTF-8" ) + ";Path=/;");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

    	 return response;
     }
     public LoginRequest(HttpExchange exchange){
    	super(exchange);
    	LoginRequest tmp = (LoginRequest)Translator.makeGenericObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
     	this.username = tmp.username;
     	this.password = tmp.password;
     	
     	
     }
}
