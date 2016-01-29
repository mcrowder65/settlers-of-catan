package shared.communication.response;

import java.util.List;

import client.data.GameInfo;

public class ListGamesResponse extends Response {

	private List<GameInfo> games;
	public ListGamesResponse(int responseCode, String json) throws IllegalArgumentException {
		super(responseCode, json);
		if (success) 
		{
			//TODO: Parse GameInfo
		
		}
	}

}
