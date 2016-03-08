package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.*;

public interface IOuterGameFacade {

	ListGamesResponse listGames(HttpExchange exchange);
	CreateGameResponse createGame(HttpExchange exchange);
	Response joinGame(HttpExchange exchange);
}
