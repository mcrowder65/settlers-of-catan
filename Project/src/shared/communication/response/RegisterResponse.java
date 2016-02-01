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
	private int responseCode;
	private String json;
	
	public RegisterResponse(int responseCode, String json) {
		super(responseCode, json);
		if (success) 
		{
			this.responseCode = responseCode;
			this.json = json;
			RegisterResponse response = new RegisterResponse(json, responseCode);//(RegisterResponse) Translator.makeGenericObject(json, new RegisterResponse());
			this.name = response.name;
			
		}
		
	}
	public RegisterResponse(String json, int responseCode) {
		this.json = json;
		this.responseCode = responseCode;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
}
