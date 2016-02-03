package shared.communication.response;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.AIType;

public class ListAIResponse extends Response {

	private List<AIType> aiTypes;
	public ListAIResponse(int responseCode, String json) throws IllegalArgumentException {
		super(responseCode, json);
		if (success) 
		{
			aiTypes = new ArrayList<AIType>();
			json = new String(json.replace("[", ""));
			json = new String(json.replace("]", ""));
			json = new String(json.replace(" ", ""));
			
			String[] ary = json.split(",");
			for(int i = 0; i < ary.length; i++){
				aiTypes.add(getAIType(ary[i]));
				System.out.println("1");
			}
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
