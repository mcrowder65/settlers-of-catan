package server.facade;

import com.sun.net.httpserver.HttpExchange;

import server.Game;
import server.util.RegisteredPersonInfo;
import shared.communication.request.LoginRequest;
import shared.communication.request.RegisterRequest;
import shared.communication.response.Response;
/**
 * used for logging in and registering
 * @author Brennen
 *
 */
public class ServerUserFacade implements IUserFacade {
	/**
	 * logs users in and gets response
	 * @param exchange HttpExchange 
	 */
	@Override
	public Response login(HttpExchange exchange) {
		LoginRequest request = new LoginRequest(exchange);
		return request.login();
	}

	/**
	 * registers user and gets response
	 * @param exchange HttpExchange 
	 */
	@Override
	public Response register(HttpExchange exchange) {
		RegisterRequest request = new RegisterRequest(exchange);
		Response response = request.register();
		if (response.isSuccess()) {
			RegisteredPersonInfo info = new RegisteredPersonInfo();
			info.setUsername(request.getUsername());
			info.setPassword(request.getPassword());
			Game.instance().getPersistenceProvider().addUser(info);
		}
		return response;
	}

}
