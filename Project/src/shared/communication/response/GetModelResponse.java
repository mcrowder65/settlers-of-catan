package shared.communication.response;

import client.utils.Translator;
import shared.definitions.GameModel;

public class GetModelResponse extends Response {

	private boolean updated;
	private GameModel model;
	public GetModelResponse(int responseCode, String json) throws IllegalArgumentException {
		super(responseCode, json);
		if (success) {
			if (json.equals("\"true\"")) {
				updated = false;
			} else {
				model = Translator.jsonToObject(json);
				updated = true;
			}
		}
	}
	public GetModelResponse(){
		
	}
	public boolean isUpdated() {
		return updated;
	}
	public GameModel getModel() {
		return model;
	}

}
