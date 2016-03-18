package shared.communication.response;

import client.utils.Translator;
import shared.definitions.GameModel;

public class GetModelResponse extends Response {

	private boolean updated;
	private GameModel model;
	private String json;
	public GetModelResponse(int responseCode, String json) throws IllegalArgumentException {
		super(responseCode, json);
		if (success) {
			if (json.equals("\"true\"")) {
				updated = false;
			} else {
				model = Translator.jsonToObject(json);
				updated = true;
				this.json = json;
			}
		}
	}
	public GetModelResponse(){
		
	}
	public void setJson(String json) {
		this.json = json;
	}
	public boolean isUpdated() {
		return updated;
	}
	public GameModel getModel() {
		return model;
	}
	public String toString(){
		return json;
	}

}
