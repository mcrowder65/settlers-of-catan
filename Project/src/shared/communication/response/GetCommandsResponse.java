package shared.communication.response;

import java.util.List;

public class GetCommandsResponse extends Response {

	List<String> commands;
	public GetCommandsResponse(int responseCode, String json) throws IllegalArgumentException {
		super(responseCode, json);
		if (success) {
		
		}
	}

}
