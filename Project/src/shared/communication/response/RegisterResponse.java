package shared.communication.response;

import client.utils.Translator;

public class RegisterResponse extends Response {
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
	public RegisterResponse(int responseCode, String json) {
		super(responseCode, json);
		if (success) 
		{
			RegisterResponse response = (RegisterResponse) Translator.makeGenericObject(json, new RegisterResponse());
			this.name = response.name;
		}
		
	}
	public RegisterResponse() {
		// TODO Auto-generated constructor stub
	}
}
