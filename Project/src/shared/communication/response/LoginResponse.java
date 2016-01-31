package shared.communication.response;

import client.utils.Translator;

public class LoginResponse extends Response {
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public int getPlayerID() {
		return playerID;
	}
	private String name;
	private String password;
	private int playerID;
	public LoginResponse(int responseCode, String json) {
		super(responseCode, json);
		if (success) 
		{
			LoginResponse response = (LoginResponse) Translator.makeGenericObject(json, new LoginResponse());
			this.name = response.name;
		}
		
	}
	public LoginResponse() {
		// TODO Auto-generated constructor stub
	}
}
