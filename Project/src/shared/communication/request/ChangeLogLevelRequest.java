package shared.communication.request;

import shared.definitions.LogLevel;

public class ChangeLogLevelRequest extends Request {

	LogLevel logLevel;
	public ChangeLogLevelRequest(LogLevel logLevel) throws IllegalArgumentException {
		if (logLevel == null)
			throw new IllegalArgumentException("logLevel cannot be null");
		this.logLevel = logLevel;
	}
}
