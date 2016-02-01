package shared.communication.response;

import java.util.List;

import client.data.GameInfo;
import client.utils.Translator;

public class ListGamesResponse extends Response {

	private List<GameInfo> games;
	public ListGamesResponse(int responseCode, String json) throws IllegalArgumentException {
		super(responseCode, json);
		if (success) 
		{
			List<GameInfo> games = (List<GameInfo>) Translator.makeListOfGames(json);
			this.games = games;
		}
	}

}
