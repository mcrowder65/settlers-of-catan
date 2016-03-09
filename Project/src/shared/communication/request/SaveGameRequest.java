package shared.communication.request;
/**
 * this is a request which saves a game, extends Request
 * @author mcrowder65
 */
public class SaveGameRequest extends Request {

	int id;
	String name;
	/**
	 * Constructor
	 * @param id of game.
	 * @param name of game.
	 */
	public SaveGameRequest(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
