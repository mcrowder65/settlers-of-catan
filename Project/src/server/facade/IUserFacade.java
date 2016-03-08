package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.response.Response;

public interface IUserFacade {
	Response login(HttpExchange exchange);
	Response register(HttpExchange exchange);
	
}
