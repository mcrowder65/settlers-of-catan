package server.facade;

import com.sun.net.httpserver.HttpExchange;

import shared.communication.request.LoginRequest;
import shared.communication.request.RegisterRequest;
import shared.communication.response.Response;

public class ServerUserFacade implements IUserFacade {

	@Override
	public Response login(HttpExchange exchange) {
		LoginRequest request = new LoginRequest(exchange);
		return request.login();
	}

	@Override
	public Response register(HttpExchange exchange) {
		RegisterRequest request = new RegisterRequest(exchange);
		return request.register();
	}

}
