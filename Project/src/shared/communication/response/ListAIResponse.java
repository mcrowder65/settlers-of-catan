package shared.communication.response;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.AIType;

public class ListAIResponse extends Response {

	@Override
	public String toString() {
		StringBuilder ais = new StringBuilder("[");
		for(int i = 0; i < aiTypes.size(); i++){
			ais.append("\"");
			ais.append(aiTypes.get(i).toString());
			ais.append("\"");
			if(i != aiTypes.size() - 1){
				ais.append(",");
			}
		}
		ais.append("]");
		return ais.toString();
	}

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
			}
		}
	}
	
	public ListAIResponse(List<AIType> aiTypes) {
		this.aiTypes = aiTypes;
	}
	public AIType getAIType(String type){
		switch(type){
		case "\"LARGEST_ARMY\"":
			return AIType.LARGEST_ARMY;
		case "\"LONGEST_ROAD\"":
			return AIType.LONGEST_ROAD;
		default:
			return null;
		}
	}
	
	public List<AIType> getAITypes() {
		return aiTypes;
	}

}
