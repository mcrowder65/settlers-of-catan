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
	public ListGamesResponse(List<GameInfo> games) {
		this.games = games;
		this.setSuccess(true);
		
	}
	public GameInfo[] getGames() {
		return  games.toArray(new GameInfo[games.size()]);
	}
	
	@Override
	public String toString() {
		return Translator.objectToJson(games);
	}

}
