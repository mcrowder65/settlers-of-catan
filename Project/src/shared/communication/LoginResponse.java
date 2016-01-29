package shared.communication;

import client.utils.Translator;

public class LoginResponse extends Response {
	private String name;
	private String password;
	private int playerID;
	public LoginResponse(int responseCode, String json) {
		super(responseCode);
		LoginResponse response = Translator.jsonToObject(json);
		this.name = response.name;
		
	}
}
