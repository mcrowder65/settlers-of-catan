package shared.communication.request;

import shared.definitions.CatanColor;

public class JoinGameRequest extends Request {
	int id;
	CatanColor color;
	public  JoinGameRequest(int id, CatanColor color) throws IllegalArgumentException {
		if (color == null) throw new IllegalArgumentException("Color cannot be null.");
		this.id = id;
		this.color = color;
		
	}
}
