package shared.communication.request;
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
   
}
