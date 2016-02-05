package shared.communication.request;

public class CreateGameRequest extends Request {

	String name;
	boolean randomTiles;
	boolean randomNumbers;
	boolean randomPorts;
	
	public CreateGameRequest(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) {
		if (name == null) throw new IllegalArgumentException("name cannot be null.");
		this.name = name;
		this.randomNumbers = randomNumbers;
		this.randomTiles = randomTiles;
		this.randomPorts = randomPorts;
	}
}
