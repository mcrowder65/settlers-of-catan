package shared.communication;

public class LoginRequest extends Request {
	
     private String username;
     private String password;
     public LoginRequest(String username, String password) throws IllegalArgumentException {
	   	 if (username == null) throw new IllegalArgumentException("username cannot be null.");
	   	 if (password == null) throw new IllegalArgumentException("password cannot be null.");
    	 this.username = username;
    	 this.password = password;
    	  
     }
   
}
