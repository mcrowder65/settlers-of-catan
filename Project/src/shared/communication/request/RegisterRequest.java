package shared.communication.request;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import shared.communication.response.Response;

/**
 * This executes a register request, extends Request
 * @author mcrowder65
 *
 */
public class RegisterRequest extends Request {
	
    private String username;
    private String password;
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
    
    public Response register() {
    	Response response = new Response();
    	boolean userExists = Game.instance().userExists(username);
    	if (userExists) {
    		response.setErrorMessage("This user already exists");
    		response.setSuccess(false);
    		return response;
    	}
    	Game.instance().addUser(username, password);
    	
    	response.setErrorMessage("Success");
    	response.setSuccess(true);
    	
    	return response;
    }
    public RegisterRequest(HttpExchange exchange){
    	super(exchange);
    	
    	
    }
}
