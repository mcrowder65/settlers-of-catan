package server.facade;

import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.CreateGameResponse;
import shared.communication.response.ListGamesResponse;
import shared.communication.response.Response;
/**
 * facde to talk execute commands on the outergame server
 * @author Brennen
 *
 */
public class MockOuterGameFacade implements IOuterGameFacade {
	/**
	 * gets the reponse based on the key
	 * @param key
	 * @return
	 */
	private String readResponse(String key) {
		return responses.get(key);
	}
	/**
	 * gets a the list of games from mock server
	 * @param exchange HttpExchange
	 */
	@Override
	public ListGamesResponse listGames(HttpExchange exchange) {
		return new ListGamesResponse(200, readResponse("game_info"));
	}
	/**
	 * calls createGame on mock server
	 * @param exchange HttpExchange
	 */
	@Override
	public CreateGameResponse createGame(HttpExchange exchange) {
		return new CreateGameResponse(200, readResponse("new_game"));
	}
	/**
	 * gets join game response from mock server
	 * @param exchange HttpExchange
	 */
	@Override
	public Response joinGame(HttpExchange exchange) {
		return new Response(200, "Success");
	}
	private static Map<String, String> responses;
	static {
		responses = new HashMap<String, String>();
		responses.put("ailist", "[\r\n  \"LARGEST_ARMY\"\r\n]");
		responses.put("model", "{\r\n  \"deck\": {\r\n    \"yearOfPlenty\": 2,\r\n    \"monopoly\": 2,\r\n    \"soldier\": 14,\r\n    \"roadBuilding\": 2,\r\n    \"monument\": 5\r\n  },\r\n  \"map\": {\r\n    \"hexes\": [\r\n      {\r\n        \"location\": {\r\n          \"x\": 0,\r\n          \"y\": -2\r\n        }\r\n      },\r\n      {\r\n        \"resource\": \"brick\",\r\n        \"location\": {\r\n          \"x\": 1,\r\n          \"y\": -2\r\n        },\r\n        \"number\": 4\r\n      },\r\n      {\r\n        \"resource\": \"wood\",\r\n        \"location\": {\r\n          \"x\": 2,\r\n          \"y\": -2\r\n        },\r\n        \"number\": 11\r\n      },\r\n      {\r\n        \"resource\": \"brick\",\r\n        \"location\": {\r\n          \"x\": -1,\r\n          \"y\": -1\r\n        },\r\n        \"number\": 8\r\n      },\r\n      {\r\n        \"resource\": \"wood\",\r\n        \"location\": {\r\n          \"x\": 0,\r\n          \"y\": -1\r\n        },\r\n        \"number\": 3\r\n      },\r\n      {\r\n        \"resource\": \"ore\",\r\n        \"location\": {\r\n          \"x\": 1,\r\n          \"y\": -1\r\n        },\r\n        \"number\": 9\r\n      },\r\n      {\r\n        \"resource\": \"sheep\",\r\n        \"location\": {\r\n          \"x\": 2,\r\n          \"y\": -1\r\n        },\r\n        \"number\": 12\r\n      },\r\n      {\r\n        \"resource\": \"ore\",\r\n        \"location\": {\r\n          \"x\": -2,\r\n          \"y\": 0\r\n        },\r\n        \"number\": 5\r\n      },\r\n      {\r\n        \"resource\": \"sheep\",\r\n        \"location\": {\r\n          \"x\": -1,\r\n          \"y\": 0\r\n        },\r\n        \"number\": 10\r\n      },\r\n      {\r\n        \"resource\": \"wheat\",\r\n        \"location\": {\r\n          \"x\": 0,\r\n          \"y\": 0\r\n        },\r\n        \"number\": 11\r\n      },\r\n      {\r\n        \"resource\": \"brick\",\r\n        \"location\": {\r\n          \"x\": 1,\r\n          \"y\": 0\r\n        },\r\n        \"number\": 5\r\n      },\r\n      {\r\n        \"resource\": \"wheat\",\r\n        \"location\": {\r\n          \"x\": 2,\r\n          \"y\": 0\r\n        },\r\n        \"number\": 6\r\n      },\r\n      {\r\n        \"resource\": \"wheat\",\r\n        \"location\": {\r\n          \"x\": -2,\r\n          \"y\": 1\r\n        },\r\n        \"number\": 2\r\n      },\r\n      {\r\n        \"resource\": \"sheep\",\r\n        \"location\": {\r\n          \"x\": -1,\r\n          \"y\": 1\r\n        },\r\n        \"number\": 9\r\n      },\r\n      {\r\n        \"resource\": \"wood\",\r\n        \"location\": {\r\n          \"x\": 0,\r\n          \"y\": 1\r\n        },\r\n        \"number\": 4\r\n      },\r\n      {\r\n        \"resource\": \"sheep\",\r\n        \"location\": {\r\n          \"x\": 1,\r\n          \"y\": 1\r\n        },\r\n        \"number\": 10\r\n      },\r\n      {\r\n        \"resource\": \"wood\",\r\n        \"location\": {\r\n          \"x\": -2,\r\n          \"y\": 2\r\n        },\r\n        \"number\": 6\r\n      },\r\n      {\r\n        \"resource\": \"ore\",\r\n        \"location\": {\r\n          \"x\": -1,\r\n          \"y\": 2\r\n        },\r\n        \"number\": 3\r\n      },\r\n      {\r\n        \"resource\": \"wheat\",\r\n        \"location\": {\r\n          \"x\": 0,\r\n          \"y\": 2\r\n        },\r\n        \"number\": 8\r\n      }\r\n    ],\r\n    \"roads\": [],\r\n    \"cities\": [],\r\n    \"settlements\": [],\r\n    \"radius\": 3,\r\n    \"ports\": [\r\n      {\r\n        \"ratio\": 2,\r\n        \"resource\": \"brick\",\r\n        \"direction\": \"NE\",\r\n        \"location\": {\r\n          \"x\": -2,\r\n          \"y\": 3\r\n        }\r\n      },\r\n      {\r\n        \"ratio\": 3,\r\n        \"direction\": \"NW\",\r\n        \"location\": {\r\n          \"x\": 2,\r\n          \"y\": 1\r\n        }\r\n      },\r\n      {\r\n        \"ratio\": 2,\r\n        \"resource\": \"sheep\",\r\n        \"direction\": \"NW\",\r\n        \"location\": {\r\n          \"x\": 3,\r\n          \"y\": -1\r\n        }\r\n      },\r\n      {\r\n        \"ratio\": 3,\r\n        \"direction\": \"SW\",\r\n        \"location\": {\r\n          \"x\": 3,\r\n          \"y\": -3\r\n        }\r\n      },\r\n      {\r\n        \"ratio\": 2,\r\n        \"resource\": \"wood\",\r\n        \"direction\": \"NE\",\r\n        \"location\": {\r\n          \"x\": -3,\r\n          \"y\": 2\r\n        }\r\n      },\r\n      {\r\n        \"ratio\": 2,\r\n        \"resource\": \"ore\",\r\n        \"direction\": \"S\",\r\n        \"location\": {\r\n          \"x\": 1,\r\n          \"y\": -3\r\n        }\r\n      },\r\n      {\r\n        \"ratio\": 3,\r\n        \"direction\": \"SE\",\r\n        \"location\": {\r\n          \"x\": -3,\r\n          \"y\": 0\r\n        }\r\n      },\r\n      {\r\n        \"ratio\": 3,\r\n        \"direction\": \"N\",\r\n        \"location\": {\r\n          \"x\": 0,\r\n          \"y\": 3\r\n        }\r\n      },\r\n      {\r\n        \"ratio\": 2,\r\n        \"resource\": \"wheat\",\r\n        \"direction\": \"S\",\r\n        \"location\": {\r\n          \"x\": -1,\r\n          \"y\": -2\r\n        }\r\n      }\r\n    ],\r\n    \"robber\": {\r\n      \"x\": 0,\r\n      \"y\": -2\r\n    }\r\n  },\r\n  \"players\": [\r\n    {\r\n      \"resources\": {\r\n        \"brick\": 0,\r\n        \"wood\": 0,\r\n        \"sheep\": 0,\r\n        \"wheat\": 0,\r\n        \"ore\": 0\r\n      },\r\n      \"oldDevCards\": {\r\n        \"yearOfPlenty\": 0,\r\n        \"monopoly\": 0,\r\n        \"soldier\": 0,\r\n        \"roadBuilding\": 0,\r\n        \"monument\": 0\r\n      },\r\n      \"newDevCards\": {\r\n        \"yearOfPlenty\": 0,\r\n        \"monopoly\": 0,\r\n        \"soldier\": 0,\r\n        \"roadBuilding\": 0,\r\n        \"monument\": 0\r\n      },\r\n      \"roads\": 15,\r\n      \"cities\": 4,\r\n      \"settlements\": 5,\r\n      \"soldiers\": 0,\r\n      \"victoryPoints\": 0,\r\n      \"monuments\": 0,\r\n      \"playedDevCard\": false,\r\n      \"discarded\": false,\r\n      \"playerID\": 12,\r\n      \"playerIndex\": 0,\r\n      \"name\": \"Eric\",\r\n      \"color\": \"puce\"\r\n    },\r\n    null,\r\n    null,\r\n    null\r\n  ],\r\n  \"log\": {\r\n    \"lines\": []\r\n  },\r\n  \"chat\": {\r\n    \"lines\": []\r\n  },\r\n  \"bank\": {\r\n    \"brick\": 24,\r\n    \"wood\": 24,\r\n    \"sheep\": 24,\r\n    \"wheat\": 24,\r\n    \"ore\": 24\r\n  },\r\n  \"turnTracker\": {\r\n    \"status\": \"FirstRound\",\r\n    \"currentTurn\": 0,\r\n    \"longestRoad\": -1,\r\n    \"largestArmy\": -1\r\n  },\r\n  \"winner\": -1,\r\n  \"version\": 0\r\n}");
		responses.put("game_info", "[\r\n  {\r\n    \"title\": \"Default Game\",\r\n    \"id\": 0,\r\n    \"players\": [\r\n      {\r\n        \"color\": \"orange\",\r\n        \"name\": \"Sam\",\r\n        \"id\": 0\r\n      },\r\n      {\r\n        \"color\": \"blue\",\r\n        \"name\": \"Brooke\",\r\n        \"id\": 1\r\n      },\r\n      {\r\n        \"color\": \"red\",\r\n        \"name\": \"Pete\",\r\n        \"id\": 10\r\n      },\r\n      {\r\n        \"color\": \"green\",\r\n        \"name\": \"Mark\",\r\n        \"id\": 11\r\n      }\r\n    ]\r\n  },\r\n  {\r\n    \"title\": \"AI Game\",\r\n    \"id\": 1,\r\n    \"players\": [\r\n      {\r\n        \"color\": \"orange\",\r\n        \"name\": \"Pete\",\r\n        \"id\": 10\r\n      },\r\n      {\r\n        \"color\": \"blue\",\r\n        \"name\": \"Squall\",\r\n        \"id\": -2\r\n      },\r\n      {\r\n        \"color\": \"purple\",\r\n        \"name\": \"Ken\",\r\n        \"id\": -3\r\n      },\r\n      {\r\n        \"color\": \"red\",\r\n        \"name\": \"Steve\",\r\n        \"id\": -4\r\n      }\r\n    ]\r\n  },\r\n  {\r\n    \"title\": \"Empty Game\",\r\n    \"id\": 2,\r\n    \"players\": [\r\n      {\r\n        \"color\": \"orange\",\r\n        \"name\": \"Sam\",\r\n        \"id\": 0\r\n      },\r\n      {\r\n        \"color\": \"blue\",\r\n        \"name\": \"Brooke\",\r\n        \"id\": 1\r\n      },\r\n      {\r\n        \"color\": \"red\",\r\n        \"name\": \"Pete\",\r\n        \"id\": 10\r\n      },\r\n      {\r\n        \"color\": \"green\",\r\n        \"name\": \"Mark\",\r\n        \"id\": 11\r\n      }\r\n    ]\r\n  }\r\n]");
		responses.put("new_game", "{\r\n  \"title\": \"myNewGame\",\r\n  \"id\": 3,\r\n  \"players\": [\r\n    {},\r\n    {},\r\n    {},\r\n    {}\r\n  ]\r\n}");
		
	}

}
