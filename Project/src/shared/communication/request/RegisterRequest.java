package shared.communication.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.net.httpserver.HttpExchange;

import client.utils.Translator;
import server.Game;
import shared.communication.response.Response;
import sun.misc.IOUtils;

/**
 * This executes a register request, extends Request
 * @author mcrowder65
 *
 */
public class RegisterRequest extends Request {
    private String username;
    private String password;
    private int ID;
    /**
     * Constructor
     * @param username - String
     * @param password - String
     * @throws IllegalArgumentException
     */
    public RegisterRequest(String username, String password) throws IllegalArgumentException {
	   	 if (username == null) throw new IllegalArgumentException("username cannot be null.");
	   	 if (password == null) throw new IllegalArgumentException("password cannot be null.");
	   	 this.username = username;
	   	 this.password = password;
    }
    
    public String getUsername() {
		return username;
	}
    
    public int getID(){
    	return ID;
    }

	public String getPassword() {
		return password;
	}

	public Response register() {
    	synchronized(Game.instance().lock){
	    	Response response = new Response();
	    	boolean userExists = Game.instance().userExists(username);
	    	if (userExists) {
	    		response.setErrorMessage("This user already exists");
	    		response.setSuccess(false);
	    		return response;
	    	}
	    	int id = Game.instance().addUser(username, password);
	    	this.ID = id;
	    	
	    	response.setErrorMessage("Success");
	    	response.setSuccess(true);
	    	
	    	try {
				response.setCookie("Set-cookie", "catan.user=" +
						URLEncoder.encode("{" +
				           "\"name\":\"" + username + "\"," +
						   "\"password\":\"" + password + "\"," + 
				           "\"playerID\":" + id + "}", "UTF-8" ) + ";Path=/;");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	    	
	    	return response;
    	}
    }
    public RegisterRequest(HttpExchange exchange){
    	super(exchange);
    	RegisterRequest tmp = (RegisterRequest)Translator.jsonToObject(convertStreamToString(exchange.getRequestBody()), this.getClass());
    	this.username = tmp.username;
    	this.password = tmp.password;
    	
    
    }
}
