package shared.communication.response;

import java.util.List;

import shared.definitions.AIType;

public class ListAIResponse extends Response {

	private List<AIType> aiTypes;
	public ListAIResponse(int responseCode, String json) throws IllegalArgumentException {
		super(responseCode, json);
		if (success) 
		{
			//TODO: parse AITypes
			aiTypes.add(getAIType(json));
		}
	}
	public AIType getAIType(String type){
		switch(type){
		case "LARGEST_ARMY":
			return AIType.LARGEST_ARMY;
		default:
			return null;
		}
	}

}
