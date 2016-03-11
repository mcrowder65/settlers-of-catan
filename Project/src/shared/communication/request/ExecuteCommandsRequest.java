package shared.communication.request;

import java.util.List;

import com.sun.net.httpserver.HttpExchange;

public class ExecuteCommandsRequest extends Request {

	List<String> commands;
	public ExecuteCommandsRequest(List<String> commands) throws IllegalArgumentException {
		if (commands == null)
			throw new IllegalArgumentException("commands cannot be null");
		this.commands = commands;
	}
	public ExecuteCommandsRequest(HttpExchange exchange){
		super(exchange);
	}
}
