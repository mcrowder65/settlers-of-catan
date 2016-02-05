package shared.communication.request;

import java.util.List;

public class ExecuteCommandsRequest extends Request {

	List<String> commands;
	public ExecuteCommandsRequest(List<String> commands) throws IllegalArgumentException {
		if (commands == null)
			throw new IllegalArgumentException("commands cannot be null");
		this.commands = commands;
	}
}
